/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nosov.server.services;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.sql.SQLException;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.hibernate.exception.GenericJDBCException;
import ru.nosov.client.messages.Message;
import ru.nosov.client.messages.MessageNews;
import ru.nosov.client.messages.MessageNewsLine;
import ru.nosov.client.messages.MessageService;
import ru.nosov.client.messages.db.Users;
import ru.nosov.client.messages.db.UsersTypes;
import ru.nosov.client.messages.system.MessageError;
import ru.nosov.client.messages.types.TypeMessage;
import ru.nosov.client.utils.Utils;
import ru.nosov.server.db.HibernateFactory;
import ru.nosov.server.exceptions.errors.ErrorsMessage;

public class MessageServiceImpl extends RemoteServiceServlet implements MessageService {

    // Variables declaration
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(MessageServiceImpl.class);
    // End of variables declaration
    
    @Override
    public Message getMessage(Message msg) {
        TypeMessage tm = TypeMessage.Error;
        if ( (msg == null) || (msg.getTypeMessage() == null) ) tm = TypeMessage.Error;
        else tm = msg.getTypeMessage();
        
        if (log.isDebugEnabled())
            log.debug("Тип входящего :"+ tm.name());
        
        HttpSession session = this.getThreadLocalRequest().getSession();
        if (session.isNew()) {
            int interval = 60; //1min
            session.setMaxInactiveInterval(interval);
            if (tm != TypeMessage.Login)
                tm = TypeMessage.NewSession;
        }
        
//        if (HttpSessionCollector.isInRange(session.getId())) {
//            log.debug("get");
//            long create = session.getCreationTime();
//            long last = session.getLastAccessedTime();
//            log.debug("Create:" + new Date(create) + "; "
//                    + "Last:" + new Date(last) +"; "
//                    + "sessionCount:" + HttpSessionCollector.getActiveSessionsCount() + ";\n");
//        } else {
//            log.debug("Add");
//            HttpSessionCollector.addSession(session);
//        }
//        Message msgRx = (Message) getMessageError();
        
        Message msgRx = (Message) getMsgError();
        switch (tm) {
            case NewSession:
                MessageError ns = new MessageError();
                ns.setCode(ErrorsMessage.WARNING_NEW_SESSION.getCode());
                ns.setDescription(ErrorsMessage.WARNING_NEW_SESSION.getDescription());
                msgRx = (Message) ns;
                break;
            case RqUser:
                log.debug("RqUser");
                Users userRqs = new Users();
                userRqs.setStatus(false);
                Object idRq = session.getAttribute("id");
                Object loginRq = session.getAttribute("login");
                Object fnRq = session.getAttribute("firstname");
                Object lnRq = session.getAttribute("lastname");
                Object emailRq = session.getAttribute("email");
                if ( (idRq != null) || /*(loginRq != null) ||*/
                     (fnRq != null) || (lnRq != null) ||
                     (emailRq != null) ) {
                    try {
                        int idRqs = Integer.valueOf(String.valueOf(idRq));
                        userRqs.setId(idRqs);
                        userRqs.setLogin( (loginRq == null) ? null : String.valueOf(loginRq));
                        userRqs.setFirstname(String.valueOf(fnRq));
                        userRqs.setLastname(String.valueOf(lnRq));
                        userRqs.setEmail(String.valueOf(emailRq));
                        userRqs.setTypeMessage(TypeMessage.LoginInfo);
                    } catch (NumberFormatException e) {
                        log.error("RqUser. Не верный идентификатор пользователя.");
                    }
                }
                
                log.debug("RqUser " + userRqs.getId());
                
                msgRx = (Message) userRqs;
                break;
            case Registration:
                if (!(msg instanceof Users)) break;
                msgRx = addNewUser((Users) msg);
                break;
            case Login:
                if (!(msg instanceof Users)) break;
                
                Users uLogin = isAuthentication((Users) msg);
                session.setAttribute("id", uLogin.getId());
                session.setAttribute("login", uLogin.getLogin());
                session.setAttribute("firstname", uLogin.getFirstname());
                session.setAttribute("lastname", uLogin.getLastname());
                session.setAttribute("email", uLogin.getEmail());
                
                msgRx = (Message) uLogin;
                break;
            case Error:
                msgRx = msg;
                break;
            case isLoginName:
                if (!(msg instanceof Users)) break;
                msgRx = isLoginName((Users) msg);
                break;
            case RqNewsLine:
                if (!(msg instanceof Message)) break;
                msgRx = getNewsLine(session);
                break;
            default:
//                msgRx = (Message) getMsgError();
        }
        
        return msgRx;
    }
    
    /**
     * Проверка подлинности имени и пароля в БД.
     * @param msg сообщение
     * @return 
     */
    private Users isAuthentication(Users user) {
        String login = user.getLogin();
        String pas = user.getPassword();
        
        if ( (pas == null) || (pas.length() < 3) ) return null;
        if ( (login == null) || (login.length() < 3) )  return null;
        
        log.debug("Login:"+login+"; Pas:"+pas);
        if ( (login.equals("test")) || (login.equals("123"))
                || (login.equals("test@email.ru")) ) {
            if ( (pas.equals("test")) || (pas.equals("1234")) ) {
                return getTestUser();
            }
        }
        
        user = null;
        try {
            String md5Hex = DigestUtils.md5Hex(pas);
            user = HibernateFactory.getInstance().getUsersDAO().isAuthentication(login, md5Hex);
        } catch (SQLException ex) {
            log.fatal(ex);
        }
        
        if (user == null) {
            user = new Users(-1);
            if (log.isInfoEnabled())
                log.info("Пользователь не авторизован. Login:" + login + ";");
        }
        user.setTypeMessage(TypeMessage.LoginInfo);
        user.setLogin(login);
        return user;
    }
    
    /**
     * Добавляет нового пользователя.
     * @param user пользователь
     * @return результат
     */
    private Message addNewUser(Users user) {
        MessageError er = getMsgErrorRegistration();
        
        String firstName = user.getFirstname();
        String lastName = user.getLastname();
        if ( (!Utils.validateName(firstName)) || ((!Utils.validateName(lastName))) ) {
            er.setDescription("Ошибка в имени или фамилии пользователя.");
            return er;
        }
        String email = user.getEmail();
        if ( !Utils.validateEmail(email) ) {
            er.setDescription("Ошибка в email пользователя.");
            return er;
        }
        String pass = user.getPassword();
        if ( !Utils.validatePassword(pass) ) {
            er.setDescription("Ошибка в пароле пользователя.");
            return er;
        }
        user.setFirstname(firstName.trim());
        user.setLastname(lastName.trim());
        user.setEmail(email.trim());
        user.setPassword(pass.trim());
        
        String login = user.getLogin();
        if (login != null)
            if (Utils.validateLogin(login))
                user.setLogin( (login.trim().length() == 0) ? null : login.trim() );
        
        String middleName = user.getMiddlename();
        if (middleName != null)
            user.setMiddlename( (!Utils.validateName(middleName)) ? null : middleName.trim() );
            
        
        try {
            String md5Hex = DigestUtils.md5Hex(pass);
            user.setPassword(md5Hex);
            UsersTypes ut = HibernateFactory.getInstance().getUsersTypesDAO().getUsersTypesById(1);
            user.setUsersTypesId(ut);
            
            boolean b = HibernateFactory.getInstance().getUsersDAO().addUser(user);
            if (!b) return er;
            user.setTypeMessage(TypeMessage.LoginInfo);
            user.setPassword(null);
            return (Message) user;
        } catch (SQLException ex) {
            log.error("SQLException", ex);
            return getMsgErrorRegistration();
        } catch (GenericJDBCException ex) {
            log.error("GenericJDBCException", ex);
            return getMsgErrorRegistration();
        }
    }
    
    /**
     * Проверяет наличие прозвища в БД.
     * @param u пользователь
     * @return результат
     */
    private Message isLoginName(Users u) {
        String ul = u.getLogin().trim();
        try {
        if ( (ul != null) && (ul.length() > 3) )
            u.setStatus(HibernateFactory.getInstance().getUsersDAO().isLoginName(ul));
        } catch (SQLException ex) {
            log.error("SQLException", ex);
            u.setStatus(false);
        }
        return u;
    }
    
    /**
     * Возвращает сообщение об ошибке.
     * @return сообщение ошибки
     */
    private Message getMsgError() {
        MessageError msgError = new MessageError();
        msgError.setCode(ErrorsMessage.WARNING_UNKNOWN_TYPE.getCode());
        msgError.setDescription(ErrorsMessage.WARNING_UNKNOWN_TYPE.getDescription());
        return msgError;
    }
    
    /**
     * Возвращает новости.
     * @return список новостей
     */
    private Message getNewsLine(HttpSession s) {
        MessageNewsLine mn = new MessageNewsLine();
        mn.setTypeMessage(TypeMessage.RxNews);
        Object email = s.getAttribute("email");
        if (email == null) {
            MessageNews news = new MessageNews();
            news.setTypeMessage(TypeMessage.RxNews);
            news.setNews("Новость дня! <b>Это работает!</b>");
            mn.addNews(news);
            return mn;
        }
        
        mn.setUserName(s.getAttribute("email").toString());
        for (int i=0; i<5; i++) {
            MessageNews news = new MessageNews();
            news.setTypeMessage(TypeMessage.RxNews);
            news.setNews("<b>Новость с сервера №"+i+"</b><br>"
                    + "&nbsp;&nbsp;&nbsp;Осталось " + (4-i) + " новостей! :)");
            mn.addNews(news);
        }
        return mn;
    }
    
    private MessageError getMsgErrorRegistration() {
        MessageError er = new MessageError();
        er.setCode(ErrorsMessage.ERROR_REGISTRATION.getCode());
        er.setDescription(ErrorsMessage.ERROR_REGISTRATION.getDescription());
        return er;
    }
    
    private Users getTestUser() {
        Users user = new Users(0, "Test", "Иванов", "Иван", "test@email.ru", "1234");
        
        return user;
    }
}