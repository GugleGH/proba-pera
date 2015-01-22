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
import ru.nosov.client.messages.MsgService;
import ru.nosov.client.messages.tx.MessageLoginT;
import ru.nosov.client.messages.types.TypeMessage;

public class MsgServiceImpl extends RemoteServiceServlet 
    implements MsgService {

    // Variables declaration
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(MsgServiceImpl.class);
    // End of variables declaration
    
    @Override
    public Message getMsg(Message msg) {
        if (log.isDebugEnabled())
            log.debug(msg);
        if (msg == null) return null;
        
        Message msgRx = new Message();
        
//        try {
//            IMessage m = MessageParser.parseJSON(msg);
            log.debug(msg.getTypeMessage().name());
            String str = new Gson().toJson(msg, MessageLoginT.class);
            log.debug(str);
//            
//            Message me = new Message();
//            me.setCode(0);
//            me.setDescription("Test msg");
//            msgRx = new Gson().toJson(me, MessageError.class);
            msgRx.setTypeMessage(TypeMessage.Error);
            
//        } catch (ExceptionsMessage ex) {
//            log.error(ex.getMessage());
//            MessageError me = new MessageError();
//            me.setCode(ex.getError().getCode());
//            me.setDescription(ex.getMessage());
//////            msgRx = new Gson().toJson(me, MessageError.class);
//            msgRx.setTypeMessage(TypeMessage.Error);
////            msgRx = me;
//        }
        return msgRx;
    }
}