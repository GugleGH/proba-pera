/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nosov.client;

import ru.nosov.client.messages.MessageService;
import ru.nosov.client.messages.MessageServiceAsync;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.AutoBeanUtils;
import ru.nosov.client.messages.IMessage;
import ru.nosov.client.messages.IMessageFactory;
import ru.nosov.client.messages.types.TypeMessage;
import ru.nosov.client.panels.PanelLogin;

/**
 * Главная страница.
 * http://www.dokwork.ru/2014/02/daotalk.html#refactor
 * @author Носов А.В.
 */
public class WelcomEntryPoint implements EntryPoint {
    
    // Variables declaration
    private MessageServiceAsync messageService = GWT.create(MessageService.class);
    // End of variables declaration
    
    /**
     * Creates a new instance of MainEntryPoint
     */
    public WelcomEntryPoint() {
    }

    /**
     * The entry point method, called automatically by loading a module that
     * declares an implementing class as an entry-point
     */
    @Override
    public void onModuleLoad() {
//        //CustomServiceAsync custService = (CustomServiceAsync) GWT.create(CustomService.class);
//        
//        ServiceDefTarget endpoint = (ServiceDefTarget) messageService;
//        //String moduleRelativeURL = GWT.getModuleBaseURL() + "messageServiceImpl";
//        String moduleRelativeURL = "/WebProbaPera/ru_nosov_server/messageServiceImpl";
//        //moduleRelativeURL = "http://localhost:8080/WebProbaPera/ru_nosov_server/messageServiceImpl";
//        endpoint.setServiceEntryPoint(moduleRelativeURL);
//        
//        /*create UI */
//        final TextBox txtName = new TextBox(); 
//        txtName.setWidth("200");
//        txtName.addKeyUpHandler(new KeyUpHandler() {
//            @Override
//            public void onKeyUp(KeyUpEvent event) {
//                if(event.getNativeKeyCode() == KeyCodes.KEY_ENTER){
//                    /* make remote call to server to get the message */
//                    final IMessageFactory beanFactory = GWT.create(IMessageFactory.class);
//                    AutoBean<IMessage> message = beanFactory.messageRq();
//                    IMessage im = message.as();
//                    im.setTypeMessage(TypeMessage.Error);
//                    AutoBean<IMessage> bean = AutoBeanUtils.getAutoBean(im);
//                    String beanListJson = AutoBeanCodex.encode(bean).getPayload();
//                    
//                    messageService.getMessage(beanListJson, new MessageCallBack());
//                }				
//            }
//        });
//        Label lblName = new Label("Enter your name: ");
//
//        Button buttonMessage = new Button("Click Me!");
//
//        buttonMessage.addClickHandler(new ClickHandler() {			
//        @Override
//        public void onClick(ClickEvent event) {
//            /* make remote call to server to get the message */
//            final IMessageFactory beanFactory = GWT.create(IMessageFactory.class);
//            AutoBean<IMessage> message = beanFactory.messageRq();
//            IMessage im = message.as();
//            im.setTypeMessage(TypeMessage.Error);
//            AutoBean<IMessage> bean = AutoBeanUtils.getAutoBean(im);
//            String beanListJson = AutoBeanCodex.encode(bean).getPayload();
//
//            messageService.getMessage(beanListJson, new MessageCallBack());
//        }});
//
//        HorizontalPanel hPanel = new HorizontalPanel();	
//        hPanel.add(lblName);
//        hPanel.add(txtName);
//        hPanel.setCellWidth(lblName, "130");
//
//        VerticalPanel vPanel = new VerticalPanel();
//        vPanel.setSpacing(10);
//        vPanel.add(hPanel);
//        vPanel.add(buttonMessage);
//        vPanel.setCellHorizontalAlignment(buttonMessage, HasHorizontalAlignment.ALIGN_RIGHT);
//
//        RootPanel.get().add(vPanel);
        
        RootPanel.get().add(new PanelLogin());
    }
}
