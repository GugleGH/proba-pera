/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nosov.server;

import com.google.gson.Gson;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.apache.log4j.Logger;
import ru.nosov.client.messages.MessageService;
import ru.nosov.client.messages.IMessage;
import ru.nosov.server.exceptions.ExceptionsMessage;
import ru.nosov.server.messages.MessageError;
import ru.nosov.server.messages.parser.MessageParser;

public class MessageServiceImpl extends RemoteServiceServlet 
    implements MessageService {

    // Variables declaration
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(MessageServiceImpl.class);
    // End of variables declaration
    
    @Override
    public String getMessage(String msg) {
        if (log.isDebugEnabled())
            log.debug(msg);
        if (msg == null) return null;
        
        String msgRx;
        
        try {
            IMessage m = MessageParser.parseJSON(msg);
            log.debug(m.getTypeMessage().name());

            MessageError me = new MessageError();
            me.setCode(0);
            me.setDescription("Test msg");
            msgRx = new Gson().toJson(me, MessageError.class);
//            msgRx = me;
            
        } catch (ExceptionsMessage ex) {
            log.error(ex.getMessage());
            MessageError me = new MessageError();
            me.setCode(ex.getError().getCode());
            me.setDescription(ex.getMessage());
            msgRx = new Gson().toJson(me, MessageError.class);
//            msgRx = me;
        }
        return msgRx;
    }   
}