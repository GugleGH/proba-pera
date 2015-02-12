/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nosov.server.services;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import javax.servlet.http.HttpSession;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Logger;
import ru.nosov.client.messages.Message;
import ru.nosov.client.messages.MessageService;
import ru.nosov.client.messages.db.Users;
import ru.nosov.client.messages.system.MessageError;
import ru.nosov.client.messages.types.TypeMessage;
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
//                if (!(msg instanceof Users)) break;
//                Users ur = (Users) msg;
//                
//                Users uRx = new Users();
//                uRx.setEmail("TEST");
//                uRx.setLogin("TEST name");
//                msgRx = (Message) uRx;
//                break;
            case Login:
                if (!(msg instanceof Users)) break;
                Users user = (Users) msg;
                String login = user.getLogin();
                String pas = user.getPassword();
                user = isAuthentication(login, pas);
                if (user == null) {
                    user = new Users(-1);
                    if (log.isInfoEnabled())
                        log.info("Пользователь не авторизован. Login:" + login + ";");
                }
                user.setTypeMessage(TypeMessage.LoginInfo);
                user.setLogin(login);
                msgRx = (Message) user;
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
    private Users isAuthentication(String login, String pas) {
        if ( (pas == null) || (pas.length() < 3) ) return null;
        if ( (login == null) || (login.length() < 3) )  return null;
        
        log.debug("Login:"+login+"; Pas:"+pas);
        if ( (login.equals("test")) || (login.equals("123"))
                || (login.equals("test@email.ru")) ) {
            if ( (pas.equals("test")) || (pas.equals("1234")) ) {
                return getTestUser();
            }
        }
        
        Users user = null;
        try {
            String md5Hex = DigestUtils.md5Hex(pas);
            user = HibernateFactory.getInstance().getUsersDAO().isAuthentication(login, md5Hex);
        } catch (SQLException ex) {
            log.fatal(ex);
        }
        return user;
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
    
    private Users getTestUser() {
        Users user = new Users(0, "Test", "Иванов", "Иван", "test@email.ru", "1234");
        
        return user;
    }
}