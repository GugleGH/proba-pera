/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nosov.client.panels;

import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Панель создания аккаунта.
 * @author Носов А.В.
 */
public class PanelCreateAcount extends SimplePanel {
    
    // Variables declaration
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
    
    public PanelCreateAcount() {
        initComponents();
    }
    
    private void initComponents() {
        verticalPanel = new VerticalPanel();
        labelName = new Label("Создать аккаунт");
        labelLogin = new Label("Имя или email");
        textBoxLogin = new TextBox();
        labelPass = new Label("Пароль");
        textBoxPass = new PasswordTextBox();
        buttonInput = new Button("Войти");
        
        verticalPanel.add(labelName);
        verticalPanel.add(labelLogin);
        verticalPanel.add(textBoxLogin);
        verticalPanel.add(labelPass);
        verticalPanel.add(textBoxPass);
        verticalPanel.add(buttonInput);
        
        this.add(verticalPanel);
    }
}
