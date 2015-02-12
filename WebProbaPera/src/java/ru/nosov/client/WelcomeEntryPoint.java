/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nosov.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import ru.nosov.client.messages.Message;
import ru.nosov.client.messages.MessageService;
import ru.nosov.client.messages.MessageServiceAsync;
import ru.nosov.client.messages.db.Users;
import ru.nosov.client.messages.system.MessageError;
import ru.nosov.client.messages.types.TypeMessage;
import ru.nosov.client.panels.PanelHeader;
import ru.nosov.client.panels.PanelLeftMenu;
import ru.nosov.client.panels.PanelLogin;
import ru.nosov.client.panels.PanelNews;
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
    private VerticalPanel verticalPanel;
    
    /** Модальное окно. */
    private final PopupPanel popupPanel;
    
    /** Сервис сообщений. */
    private MessageServiceAsync msgService;
    /** Регистрация. */
    private Anchor anchorRegistration;
    // End of variables declaration
    
    /**
     * Creates a new instance of MainEntryPoint
     */
    public WelcomeEntryPoint() {
        popupPanel = new PopupPanel(false, true);
    }

    /**
     * The entry point method, called automatically by loading a module that
     * declares an implementing class as an entry-point
     */
    @Override
    public void onModuleLoad() {
        initMsgService();
        flexTable = new FlexTable();
        verticalPanel = new VerticalPanel();
//        verticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
        
        anchorRegistration = new Anchor("Регистрация");
        anchorRegistration.setStyleName("anchorRegistration");
        
        flexTable.setWidget(0, 0, new PanelHeader());
        flexTable.setWidget(0, 1, anchorRegistration);
        flexTable.setWidget(1, 0, new PanelLeftMenu());
        verticalPanel.add(new PanelNews(PanelNews.TYPE_NEWS));
        flexTable.setWidget(1, 1, verticalPanel);
        flexTable.setStyleName("tableWelcom");
        FlexTable.FlexCellFormatter cellFlex = flexTable.getFlexCellFormatter();
        cellFlex.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_LEFT);
        cellFlex.setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
        cellFlex.setWidth(0, 0, "130px");
        cellFlex.setHeight(0, 0, "40px");
        cellFlex.setStyleName(0, 0, "borderWelcom borderWelcom-00");
        cellFlex.setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_RIGHT);
        cellFlex.setVerticalAlignment(0, 1, HasVerticalAlignment.ALIGN_TOP);
        cellFlex.setHeight(0, 1, "40px");
        cellFlex.setStyleName(0, 1, "borderWelcom borderWelcom-01");
        cellFlex.setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_CENTER);
        cellFlex.setVerticalAlignment(1, 0, HasVerticalAlignment.ALIGN_TOP);
        cellFlex.setWidth(1, 0, "130px");
        cellFlex.setStyleName(1, 0, "borderWelcom borderWelcom-10");
        cellFlex.setHorizontalAlignment(1, 1, HasHorizontalAlignment.ALIGN_CENTER);
        cellFlex.setVerticalAlignment(1, 1, HasVerticalAlignment.ALIGN_TOP);
        cellFlex.setStyleName(1, 1, "borderWelcom borderWelcom-11");
//        cellFlex.setStyleName(0, 0, "tableName");
//        cellFlex.setColSpan(0, 0, 2);
        
        
        anchorRegistration.addClickHandler(new ClickHandler() {			
            @Override
            public void onClick(ClickEvent event) {
                registration();
            }
        });
        
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
    
    /**
     * Показать модальное окно ожидания.
     * @param str отображаемая строка
     */
    public void modalVisible(String str) {
//        popupPanel.add(new Label(str));
//        popupPanel.setGlassEnabled(true);
//        popupPanel.center();
//        popupPanel.setVisible(true);
    }
    
    /**
     * Скрыть модальное окно ожидания.
     */
    public void modalUnvisible() {
        popupPanel.setVisible(false);
    }
    
    /**
     * Регистрация.
     */
    public void registration() {
        clearOneRow();
        flexTable.setWidget(1, 0, new PanelLogin(this));
        flexTable.setWidget(1, 1, new PanelRegistration(this));
    }
    
    @Override
    public void onFailure(Throwable caught) {
        Window.alert("WelcomeEntryPoint no response");
    }

    @Override
    public void onSuccess(Message result) {
//        popupPanel.setVisible(false);
        if (result == null) return;
        TypeMessage tm = result.getTypeMessage();
        switch (tm) {
            case LoginInfo:
                if ( !(result instanceof Users)) break;
                Users user = (Users) result;
                clearOneRow();
                if (user.getId() < 0)
                    createLoginError(user);
                else
                    createLogin(user);
                break;
            case Error:
                MessageError error = (MessageError) result;
                switch (error.getCode()) {
                    case 3:
                        clearOneRow();
                        flexTable.setWidget(1, 0, new PanelLogin(this));
                        verticalPanel.add(new PanelNews(PanelNews.TYPE_NEWS));
                        break;
                }
                break;
        }
    }
    
    /**
     * Очищает строку = 1 в главной таблице.
     */
    private void clearOneRow() {
        flexTable.clearCell(1, 0);
        verticalPanel.clear();
    }
    
    /**
     * Создает панели при удачном логине.
     * @param user пользователь
     */
    private void createLogin(Users user) {
        flexTable.setWidget(1, 0, new PanelLeftMenu());
        String str = "Login info:"
                + "<ul type=\"disc\">"
                + "<li>Login:" + user.getLogin() + "</li>"
                + "<li>FirstName:" + user.getFirstname() + "</li>"
                + "<li>LastName:" + user.getLastname() + "</li>"
                + "<li>E-mail:" + user.getEmail() + "</li>"
                + "</ul>";
        verticalPanel.add(new HTML(str));
    }
    
    /**
     * Создает панели при ошибке логина.
     * @param user пользователь
     */
    private void createLoginError(Users user) {
        PanelLogin login = new PanelLogin(this);
        login.setLogin(user.getLogin());
        flexTable.setWidget(1, 0, login);
        verticalPanel.add(new PanelNews(PanelNews.TYPE_LOGIN_ERROR));
        verticalPanel.add(login);
    }
    
    
}
