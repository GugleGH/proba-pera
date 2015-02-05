/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nosov.server.services;

import com.google.gson.Gson;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.util.Date;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import ru.nosov.client.messages.Message;
import ru.nosov.client.messages.MessageService;
import ru.nosov.client.messages.db.Users;
import ru.nosov.client.messages.system.MessageError;
import ru.nosov.client.messages.system.MessageLoginInfo;
import ru.nosov.client.messages.types.TypeMessage;
import ru.nosov.server.HttpSessionCollector;
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
        
        if (log.isDebugEnabled()) {
            log.debug("Тип входящего :"+ tm.name());
//            String str = new Gson().toJson(msg, Message.class);
//            log.debug("Сообщение " + str);
        }
        
        // 1. Если сессия новая, то неважно что там за запрос отправляем на страницу авторизации
        // 2. Проверяем есть ли авторизация
        // 2.1 Есть, открываем главную страницу
        // 2.2 Нет, проверяем что это страница регистрации
        // 2.2.1 Да, проводим регистрацию
        // 2.2.2 Нет, отправляем на страницу авторизации
        
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
                MessageLoginInfo loginInfo = new MessageLoginInfo();
                msgRx = (Message) loginInfo;
                break;
            case Registration:
                if (!(msg instanceof Users)) break;
                Users ur = (Users) msg;
                
                Users uRx = new Users();
                uRx.setEmail("TEST");
                uRx.setNicname("TEST name");
                msgRx = (Message) uRx;
                break;
            case Login:
//                if (!(msg instanceof MessageLogin)) break;
//                msgRx = isAuthentication((MessageLogin) msg);
                
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
    private boolean isAuthentication(String login, String pas) {
        if ( (pas == null) || (pas.length() < 3) ) return false;
        if ( (login == null) || (login.length() < 3) )  return false;
        
        if ( login.equals("test@test.ru") && (pas.equals("test")) ) 
            return true;
        
        //TODO реализация проверки логи/пароль
        
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
}