/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nosov.client.messages.tx;

import ru.nosov.client.messages.Message;
import ru.nosov.client.messages.types.TypeMessage;

/**
 * Сообщение авторизации.
 * @author Носов А.В.
 */
public class MessageLoginT extends Message {
    // Variables declaration
    /** Имя. */
    private String login;
    /** Пароль. */
    private String password;
    // End of variables declaration

    public MessageLoginT() {
        setTypeMessage(TypeMessage.Login);
    }

    /**
     * Возвращает имя.
     * @return имя
     */
    public String getLogin() {
        return login;
    }

    /**
     * Устанавливает имя.
     * @param login имя
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Возвращает пароль.
     * @return пароль
     */
    public String getPassword() {
        return password;
    }

    /**
     * Устанавливает пароль.
     * @param password пароль
     */
    public void setPassword(String password) {
        this.password = password;
    }
}