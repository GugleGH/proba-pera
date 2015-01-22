/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nosov.server.services;

import com.google.gson.Gson;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.apache.log4j.Logger;
import ru.nosov.client.messages.Message;
import ru.nosov.client.messages.MessageService;
import ru.nosov.client.messages.system.MessageError;
import ru.nosov.client.messages.system.MessageLogin;
import ru.nosov.server.exceptions.errors.ErrorsMessage;

public class MessageServiceImpl extends RemoteServiceServlet 
    implements MessageService {

    // Variables declaration
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(MessageServiceImpl.class);
    // End of variables declaration
    
    @Override
    public Message getMessage(Message msg) {
        if (log.isDebugEnabled())
            log.debug(msg);
        if (msg == null) return null;
        
        log.debug(msg.getTypeMessage().name());
        String str = new Gson().toJson(msg, MessageLogin.class);
        log.debug(str);
        MessageError msgRx = new MessageError();
        msgRx.setCode(ErrorsMessage.WARNING_UNKNOWN_TYPE.getCode());
        msgRx.setDescription(ErrorsMessage.WARNING_UNKNOWN_TYPE.getDescription());

        return msgRx;
    }
}