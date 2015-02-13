/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nosov.server.services;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import org.hibernate.exception.GenericJDBCException;
import ru.nosov.client.messages.Message;
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
        
        String tx;
        //
        Message msgRx = (Message) getMsgError();
        switch (tm) {
            case NewSession:
                MessageError ns = new MessageError();
                ns.setCode(ErrorsMessage.WARNING_NEW_SESSION.getCode());
                ns.setDescription(ErrorsMessage.WARNING_NEW_SESSION.getDescription());
                msgRx = (Message) ns;
                break;
            case RqUser:
                Object idRq = session.getAttribute("id");
                Object loginRq = session.getAttribute("login");
                Object fnRq = session.getAttribute("firstname");
                Object lnRq = session.getAttribute("lastname");
                Object emailRq = session.getAttribute("email");
                if ( (idRq == null) || (loginRq != null) ||
                     (fnRq != null) || (lnRq != null) ||
                     (emailRq != null) ) break;
                try {
                    int idRqs = Integer.valueOf(idRq.toString());
                    Users userRqs = new Users(idRqs, String.valueOf(loginRq),
                            String.valueOf(fnRq), String.valueOf(lnRq), 
                            String.valueOf(emailRq), null);
                    userRqs.setTypeMessage(TypeMessage.LoginInfo);
                    msgRx = (Message) userRqs;
                } catch (NumberFormatException e) {
                    log.error("Не верный идентификатор пользователя.");
                }
                break;
            case Registration:
                if (!(msg instanceof Users)) break;
                msgRx = addNewUser((Users) msg);
                break;
            case Login:
                if (!(msg instanceof Users)) break;
                msgRx = (Message) isAuthentication((Users) msg);
                break;
            case Error:
                msgRx = msg;
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
            return (Message) user;
        } catch (SQLException ex) {
            log.error("SQLException", ex);
            return getMsgErrorRegistration();
        } catch (GenericJDBCException ex) {
            log.error("GenericJDBCException", ex);
            return getMsgErrorRegistration();
        }
    }
    
    private boolean isLogin(String login) {
        
        return true;
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