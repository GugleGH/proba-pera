/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nosov.client.panels;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlexTable.FlexCellFormatter;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import ru.nosov.client.messages.Message;
import ru.nosov.client.messages.MessageService;
import ru.nosov.client.messages.MessageServiceAsync;
import ru.nosov.client.messages.db.Users;
import ru.nosov.client.messages.system.MessageError;
import ru.nosov.client.messages.types.TypeMessage;
import ru.nosov.client.utils.Utils;

/**
 * Панель создания аккаунта.
 * @author Носов А.В.
 */
public class PanelRegistration extends SimplePanel implements AsyncCallback<Message> {
    
    // Variables declaration
    private MessageServiceAsync msgService = GWT.create(MessageService.class);
    private AsyncCallback<Message> parent;
    
    private FlexTable flexTable;
    
    /** Название панели. */
    private Label labelName;
    /** Логин. */
    private Label labelLogin;
    private TextBox textBoxLogin;
    private Label validLogin;
    /** Имя. */
    private Label labelFirstname;
    private TextBox textBoxFirstname;
    private Label validFirstName;
    /** Фамилия. */
    private Label labelLastname;
    private TextBox textBoxLastname;
    private Label validLastname;
    /** Отчество. */
    private Label labelMiddlename;
    private TextBox textBoxMiddlename;
    /** Электронный адрес. */
    private Label labelEmail1;
    private Label labelEmail2;
    private TextBox textBoxEmail1;
    private TextBox textBoxEmail2;
    private Label validEmail1;
    private Label validEmail2;
    /** День рождения. */
    private Label labelBirthday;
    private TextBox textBoxBirthday;
    private Label validBirthday;
    /** Пароль. */
    private Label labelPass1;
    private Label labelPass2;
    private PasswordTextBox textBoxPass1;
    private PasswordTextBox textBoxPass2;
    private Label validPass1;
    private Label validPass2;
    /** Войти. */
    private Button buttonInput;
    // End of variables declaration
    
    public PanelRegistration(AsyncCallback<Message> parent) {
        this.parent = parent;
        initComponents();
    }
    
    private void initComponents() {
        flexTable = new FlexTable();
        
        labelName = new Label("Создать аккаунт");
        labelLogin = new Label("Логин");
        textBoxLogin = new TextBox();
        validLogin = new Label();
        labelFirstname = new Label("Имя*");
        textBoxFirstname = new TextBox();
        validFirstName = new Label();
        labelLastname = new Label("Фамилия*");
        validLastname = new Label();
        textBoxLastname = new TextBox();
        labelMiddlename = new Label("Отчество");
        textBoxMiddlename = new TextBox();
        labelEmail1 = new Label("Электронный адрес*");
        labelEmail2 = new Label("Повторите электронный адрес*");
        textBoxEmail1 = new TextBox();
        textBoxEmail2 = new TextBox();
        validEmail1 = new Label();
        validEmail2 = new Label();
        labelBirthday = new Label("Дата рождения");
        textBoxBirthday = new TextBox();
        validBirthday = new Label();
        labelPass1 = new Label("Пароль*");
        labelPass2 = new Label("Повторите пароль*");
        textBoxPass1 = new PasswordTextBox();
        textBoxPass2 = new PasswordTextBox();
        validPass1 = new Label();
        validPass2 = new Label();
        
        buttonInput = new Button("Создать аккаунт");
        buttonInput.addClickHandler(new ClickHandler() {			
            @Override
            public void onClick(ClickEvent event) {
                buttonInputOnClick(event);
            }
        });
        
        flexTable.setWidget(0, 0, labelName);
        flexTable.setWidget(1, 0, labelLogin);
        flexTable.setWidget(1, 1, textBoxLogin);
        flexTable.setWidget(1, 2, validLogin);
        flexTable.setWidget(2, 0, labelFirstname);
        flexTable.setWidget(2, 1, textBoxFirstname);
        flexTable.setWidget(2, 2, validFirstName);
        flexTable.setWidget(3, 0, labelLastname);
        flexTable.setWidget(3, 1, textBoxLastname);
        flexTable.setWidget(3, 2, validLastname);
        flexTable.setWidget(4, 0, labelMiddlename);
        flexTable.setWidget(4, 1, textBoxMiddlename);
        flexTable.setWidget(5, 0, labelEmail1);
        flexTable.setWidget(5, 1, textBoxEmail1);
        flexTable.setWidget(5, 2, validEmail1);
        flexTable.setWidget(6, 0, labelEmail2);
        flexTable.setWidget(6, 1, textBoxEmail2);
        flexTable.setWidget(6, 2, validEmail2);
        flexTable.setWidget(7, 0, labelBirthday);
        flexTable.setWidget(7, 1, textBoxBirthday);
        flexTable.setWidget(7, 2, validBirthday);
        flexTable.setWidget(8, 0, labelPass1);
        flexTable.setWidget(8, 1, textBoxPass1);
        flexTable.setWidget(8, 2, validPass1);
        flexTable.setWidget(9, 0, labelPass2);
        flexTable.setWidget(9, 1, textBoxPass2);
        flexTable.setWidget(9, 2, validPass2);
        flexTable.setWidget(10, 0, buttonInput);
        FlexCellFormatter cellFlexTable = flexTable.getFlexCellFormatter();
//        cellFlexTable.setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);
//        cellFlexTable.setVerticalAlignment(0, 0, HasVerticalAlignment.ALIGN_TOP);
//        cellFlexTable.setStyleName(0, 0, "headerStatuses");
        cellFlexTable.setColSpan(0, 0, 3);
        cellFlexTable.setColSpan(10, 0, 3);
//        for (int row=1; row<5; row++) {
//            for (int col=0; col<3; col++) {
//                cellFlexTable.setHorizontalAlignment(row, col, HasHorizontalAlignment.ALIGN_LEFT);
//                cellFlexTable.setVerticalAlignment(row, col, HasVerticalAlignment.ALIGN_MIDDLE);
//            }
//        }
        
        ServiceDefTarget endpointMsg = (ServiceDefTarget) msgService;
        String urlMsg = "/WebProbaPera/ru_nosov_server_services/messageServiceImpl";
        endpointMsg.setServiceEntryPoint(urlMsg);
        
        this.add(flexTable);
    }
    
    private void buttonInputOnClick(ClickEvent event) {
        validBirthday.setText("");
        validEmail1.setText("");
        validEmail2.setText("");
        validFirstName.setText("");
        validLastname.setText("");
        validLogin.setText("");
        validPass1.setText("");
        validPass2.setText("");
        
        boolean b = true;
        if (!Utils.validateBirthday(textBoxBirthday.getText())) {
            validFirstName.setText("Не верно заполнена дата.");
            b = false;
        }
        if (!Utils.validateName(textBoxFirstname.getText())) {
            validFirstName.setText("Поле, обязательное для заполнения.");
            b = false;
        }
        if (!Utils.validateName(textBoxLastname.getText())) {
            validLastname.setText("Поле, обязательное для заполнения.");
            b = false;
        }
        if (!Utils.validateLogin(textBoxLogin.getText())) {
            validLogin.setText("Логин не может быть короче 3х символов.");
            b = false;
        }
        if (!Utils.validateEmail(textBoxEmail1.getText())) {
            validEmail1.setText("Недопустимый электронный адрес.");
            b = false;
        }
        if (!Utils.validateEmail(textBoxEmail2.getText())) {
            validEmail2.setText("Недопустимый электронный адрес.");
            b = false;
        }
        if (textBoxEmail1.getText().trim().equals(textBoxEmail2.getText().trim())) {
            validEmail2.setText("Электронные адреса не совпадают.");
            b = false;
        }
        if (!Utils.validatePassword(textBoxPass1.getText())) {
            validPass1.setText("Пароль не может быть короче 3х символов.");
            b = false;
        }
        if (!Utils.validatePassword(textBoxPass2.getText())) {
            validPass2.setText("Пароль не может быть короче 3х символов.");
            b = false;
        }
        if (textBoxPass1.getText().trim().equals(textBoxPass2.getText().trim())) {
            validPass2.setText("Пароли не совпадают.");
            b = false;
        }
        
        Users u = new Users();
        u.setTypeMessage(TypeMessage.Registration);
        u.setLogin(textBoxLogin.getText().trim());
        u.setFirstname(textBoxFirstname.getText().trim());
        u.setLastname(textBoxLastname.getText().trim());
        u.setMiddlename(textBoxMiddlename.getText().trim());
        u.setEmail(textBoxEmail1.getText().trim());
        u.setPassword(textBoxPass1.getText().trim());
        msgService.getMessage(u, this);
    }

    @Override
    public void onFailure(Throwable caught) {
        Window.alert("PanelCreateAcount no response");
    }

    @Override
    public void onSuccess(Message result) {
        if (result == null) {
            Window.alert("PanelCreateAcount: NULL");
            return;
        }
        TypeMessage tm = result.getTypeMessage();
        switch (tm) {
            case LoginInfo:
                Users login = (Users) result;
                Window.alert("PanelCreateAcount\n"
                        + "Email:" + login.getEmail()+ ";\n"
                        + "NickName:" + login.getLogin()+ ";");
                break;
            case Error:
                MessageError error = (MessageError) result;
                Window.alert("PanelCreateAcount\n"
                        + "CODE:" + error.getCode() + ";\n"
                        + "DESC:" + error.getDescription() + ";");
                break;
        }
    }
}
