/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nosov.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import ru.nosov.client.messages.Message;
import ru.nosov.client.messages.MessageService;
import ru.nosov.client.messages.MessageServiceAsync;
import ru.nosov.client.messages.db.Users;
import ru.nosov.client.messages.system.MessageError;
import ru.nosov.client.messages.types.TypeMessage;
import ru.nosov.client.panels.PanelHeader;
import ru.nosov.client.panels.PanelLeftMenu;
import ru.nosov.client.panels.PanelLogin;
import ru.nosov.client.panels.PanelRegistration;

/**
 * Главная страница.
 * http://www.dokwork.ru/2014/02/daotalk.html#refactor
 * 
 * Структура.
 * http://ege59.ru/2013/02/03/gosudarstvo-i-pravo-struktura-organov-gosudarstvennoj-vlasti-rf/
 * @author Носов А.В.
 */
public class WelcomeEntryPoint implements EntryPoint, AsyncCallback<Message> {
    
    // Variables declaration
    private final String CLASS_NAME = "WelcomeEntryPoint";

    private FlexTable flexTable;
    /** Сервис сообщений. */
    private MessageServiceAsync msgService;
    // End of variables declaration
    
    /**
     * Creates a new instance of MainEntryPoint
     */
    public WelcomeEntryPoint() {
    }

    /**
     * The entry point method, called automatically by loading a module that
     * declares an implementing class as an entry-point
     */
    @Override
    public void onModuleLoad() {
        initMsgService();
        flexTable = new FlexTable();
        
        flexTable.setWidget(0, 0, new PanelHeader());
        flexTable.setWidget(0, 1, new Label("Регистрация"));
        flexTable.setWidget(1, 0, new PanelLeftMenu());
        flexTable.setWidget(1, 1, new Label("Добро пожаловать"));
        FlexTable.FlexCellFormatter cellFlex = flexTable.getFlexCellFormatter();
        cellFlex.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_LEFT);
        cellFlex.setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
        cellFlex.setWidth(0, 0, "130px");
        cellFlex.setHeight(0, 0, "40px");
        cellFlex.setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_RIGHT);
        cellFlex.setVerticalAlignment(0, 1, HasVerticalAlignment.ALIGN_TOP);
        cellFlex.setHeight(0, 1, "40px");
        cellFlex.setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);
        cellFlex.setVerticalAlignment(1, 0, HasVerticalAlignment.ALIGN_TOP);
        cellFlex.setWidth(1, 0, "130px");
        cellFlex.setHorizontalAlignment(1, 1, HasHorizontalAlignment.ALIGN_LEFT);
        cellFlex.setVerticalAlignment(1, 1, HasVerticalAlignment.ALIGN_TOP);
//        cellFlex.setStyleName(0, 0, "tableName");
//        cellFlex.setColSpan(0, 0, 2);
        
        Users msgUser = new Users();
        msgUser.setTypeMessage(TypeMessage.RqUser);
        msgService.getMessage(msgUser, this);
        
        RootPanel.get().add(flexTable);
        //RootPanel.get().add(new PanelCreateAcount());
        //RootPanel.get().add(new PanelLogin());
    }
    
    /**
     * Инициализация сервиса сообщений.
     */
    private void initMsgService() {
        msgService = GWT.create(MessageService.class);
        ServiceDefTarget endpointMsg = (ServiceDefTarget) msgService;
        String urlMsg = "/WebProbaPera/ru_nosov_server_services/messageServiceImpl";
        endpointMsg.setServiceEntryPoint(urlMsg);
    }
    
    @Override
    public void onFailure(Throwable caught) {
        Window.alert("WelcomeEntryPoint no response");
    }

    @Override
    public void onSuccess(Message result) {
        if (result == null) return;
        TypeMessage tm = result.getTypeMessage();
        switch (tm) {
            case Login:
                break;
            case Error:
                MessageError error = (MessageError) result;
                switch (error.getCode()) {
                    case 3:
                        flexTable.clearCell(1, 0);
                        flexTable.setWidget(1, 0, new PanelLogin(this));
                        flexTable.clearCell(1, 1);
                        flexTable.setWidget(1, 1, new PanelRegistration(this));
                        break;
                }
                break;
        }
    }
}
