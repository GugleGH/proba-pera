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
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanCodex;
import com.google.web.bindery.autobean.shared.AutoBeanUtils;
import ru.nosov.client.messages.Message;
import ru.nosov.client.messages.MsgService;
import ru.nosov.client.messages.MsgServiceAsync;
import ru.nosov.client.messages.tx.MessageLoginT;
import ru.nosov.client.messages.types.TypeMessage;

/**
 * Панель входа в систему.
 * Как защитить пароль:
 * 1. Использовать GoogleAcountAPI
 * 2. com.googlecode.gwt.crypto.Crypto (Заранее прописать ключи у клиента и сервера)
 * 3. Переложить все на Apache и закрыться полностью https.
 * @author Носов А.В.
 */
//public class PanelLogin extends SimplePanel implements AsyncCallback<String> {
public class PanelLogin extends SimplePanel implements AsyncCallback<Message> {
    
    // Variables declaration
    private MsgServiceAsync msgService = GWT.create(MsgService.class);
    
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
    private Button buttonInput;
    // End of variables declaration
    
    public PanelLogin() {
        initComponents();
    }
    
    private void initComponents() {
        verticalPanel = new VerticalPanel();
        labelName = new Label("Вход в систему");
        labelLogin = new Label("Имя или email");
        textBoxLogin = new TextBox();
        labelPass = new Label("Пароль");
        textBoxPass = new PasswordTextBox();
        buttonInput = new Button("Войти");
        
        textBoxPass.addKeyUpHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {
                if(event.getNativeKeyCode() == KeyCodes.KEY_ENTER){
                    loginig();
                }				
            }
        });
        
        buttonInput.addClickHandler(new ClickHandler() {			
            @Override
            public void onClick(ClickEvent event) {
                loginig();
            }
        });
        
        verticalPanel.add(labelName);
        verticalPanel.add(labelLogin);
        verticalPanel.add(textBoxLogin);
        verticalPanel.add(labelPass);
        verticalPanel.add(textBoxPass);
        verticalPanel.add(buttonInput);
        
        ServiceDefTarget endpointMsg = (ServiceDefTarget) msgService;
        String urlMsg = "/WebProbaPera/ru_nosov_server/msgServiceImpl";
        endpointMsg.setServiceEntryPoint(urlMsg);
        
        this.add(verticalPanel);
    }
    
    /**
     * Авторизация.
     */
    private void loginig() {
        if (!validateLogin()) return;
        if (!validatePassword()) return;
        
        MessageLoginT msgT = new MessageLoginT();
        msgT.setTypeMessage(TypeMessage.Login);
        msgT.setLogin(textBoxLogin.getText());
        msgT.setPassword(textBoxPass.getText());
        if (msgT instanceof Message) {
            msgService.getMsg((Message)msgT, this);
        } else {
            Window.alert("Не смог!");
        }
    }
    
    /**
     * Проверка корректности ввода логина.
     * @return <b>true</b> - верно, <br></br>
     * <b>false</b> - не верно.
     */
    public boolean validateLogin() {
        return textBoxLogin.getText().trim().length() >= 3;
    }
    
    /**
     * Проверка корректности ввода пароля.
     * @return <b>true</b> - верно, <br></br>
     * <b>false</b> - не верно.
     */
    public boolean validatePassword() {
        return textBoxPass.getText().trim().length() >= 3;
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
                MessageLoginT login = (MessageLoginT) result;
                Window.alert("PanelLogin LOGIN:" + login.getLogin()+
                        ";\nPASS:" + login.getPassword() + ";");
                break;
        }
    }
}
