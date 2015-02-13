/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nosov.client.panels;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import ru.nosov.client.WelcomeEntryPoint;
import ru.nosov.client.messages.Message;
import ru.nosov.client.messages.MessageService;
import ru.nosov.client.messages.MessageServiceAsync;
import ru.nosov.client.messages.db.Users;
import ru.nosov.client.messages.system.MessageError;
import ru.nosov.client.messages.types.TypeMessage;
import ru.nosov.client.utils.Utils;

/**
 * Панель входа в систему.
 * http://ru.enetri.com/2010/05/18/1.html
 * Как защитить пароль:
 * 1. Использовать GoogleAcountAPI
 * 2. com.googlecode.gwt.crypto.Crypto (Заранее прописать ключи у клиента и сервера)
 * 3. Переложить все на Apache и закрыться полностью https.
 * @author Носов А.В.
 */
public class PanelLogin extends SimplePanel implements AsyncCallback<Message> {
    
    // Variables declaration
    private MessageServiceAsync msgService = GWT.create(MessageService.class);
    private WelcomeEntryPoint parent;
    
    private VerticalPanel verticalPanel;
    /** Название панели. */
    private Label labelName;
    /** Имя. */
    private Label labelLogin;
    /** Имя. */
    private TextBox textBoxLogin;
    /** Пароль. */
    private Label labelPass;
    /** Пароль. */
    private PasswordTextBox textBoxPass;
    /** Войти. */
    private Button buttonLogin;
    private String strLogin = "Войти";
    /** Регистрация. */
    private Button buttonRegistration;
    /** Забыли пароль. */
    private Anchor anchorForgot;
    // End of variables declaration
    
    public PanelLogin(WelcomeEntryPoint parent) {
        this.parent = parent;
        initComponents();
    }
    
    private void initComponents() {
        verticalPanel = new VerticalPanel();
        verticalPanel.setStyleName("login");
        labelName = new Label("Вход в систему");
        labelLogin = new Label("Имя или email");
        textBoxLogin = new TextBox();
        labelPass = new Label("Пароль");
        textBoxPass = new PasswordTextBox();
        buttonLogin = new Button(strLogin);
        buttonRegistration = new Button("Регистрация");
        anchorForgot = new Anchor("Забыли пароль?");
        
        textBoxPass.addKeyUpHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {
                if(event.getNativeKeyCode() == KeyCodes.KEY_ENTER){
                    loginig();
                }				
            }
        });
        
        buttonLogin.addClickHandler(new ClickHandler() {			
            @Override
            public void onClick(ClickEvent event) {
                loginig();
            }
        });
        
        buttonRegistration.addClickHandler(new ClickHandler() {			
            @Override
            public void onClick(ClickEvent event) {
                registration();
            }
        });
        
        verticalPanel.add(labelName);
        verticalPanel.add(labelLogin);
        verticalPanel.add(textBoxLogin);
        verticalPanel.add(labelPass);
        verticalPanel.add(textBoxPass);
        verticalPanel.add(buttonLogin);
        verticalPanel.add(buttonRegistration);
        verticalPanel.add(anchorForgot);
        
        ServiceDefTarget endpointMsg = (ServiceDefTarget) msgService;
        String urlMsg = "/WebProbaPera/ru_nosov_server_services/messageServiceImpl";
        endpointMsg.setServiceEntryPoint(urlMsg);
        
        this.add(verticalPanel);
    }
    
    /**
     * Авторизация.
     */
    private void loginig() {
        if (!Utils.validateLogin(textBoxLogin.getText())) return;
        if (!Utils.validatePassword(textBoxPass.getText())) return;
        
        Users msgLogin = new Users();
        msgLogin.setTypeMessage(TypeMessage.Login);
        msgLogin.setLogin(textBoxLogin.getText());
        msgLogin.setPassword(textBoxPass.getText());
        msgService.getMessage((Message)msgLogin, parent);
        buttonLogin.setHTML(parent.getAwesomeRefresh().toSafeHtml());
    }
    
    /**
     * Регистрация.
     */
    private void registration() {
        parent.registration();
    }
    
    /**
     * Устанавливает логин.
     * @param login логин
     */
    public void setLogin(String login) {
        if (login == null) return;
        textBoxLogin.setText(login);
        updateButtons();
    }
    
    private void updateButtons() {
        buttonLogin.setText(strLogin);
    }
    
    @Override
    public void onFailure(Throwable caught) {	
        Window.alert("PanelLogin no response");	
    }
    @Override
    public void onSuccess(Message result) {
        if (result == null) {
            Window.alert("PanelLogin: NULL");
            return;
        }
        TypeMessage tm = result.getTypeMessage();
        switch (tm) {
            case Login:
                break;
            case Error:
                MessageError error = (MessageError) result;
                Window.alert("PanelLogin\n"
                        + "CODE:" + error.getCode() + ";\n"
                        + "DESC:" + error.getDescription() + ";");
                break;
        }
    }
}
