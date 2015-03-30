/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nosov.client.messages.system;

import ru.nosov.client.messages.*;
import ru.nosov.client.messages.types.TypeMessage;

/**
 * Запросить/Отправить/Принять .
 * @author Носов А.В.
 */
public class MessageLoginInfo extends Message {
    
    // Variables declaration
    private Boolean isLogged;
    private String urlLogin;
    // End of variables declaration
    
    /**
     * Создание сообщения .
     * @param tm тип сообщения
     */
    public MessageLoginInfo() {
        setTypeMessage(TypeMessage.LoginInfo);
        this.isLogged = false;
        this.urlLogin = "login";
    }

    public Boolean getIsLogged() {
        return isLogged;
    }

    public void setIsLogged(Boolean isLogged) {
        this.isLogged = isLogged;
    }

    public String getUrlLogin() {
        return urlLogin;
    }

    public void setUrlLogin(String urlLogin) {
        this.urlLogin = urlLogin;
    }
}