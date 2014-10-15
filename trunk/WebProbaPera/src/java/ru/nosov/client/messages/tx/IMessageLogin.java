/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nosov.client.messages.tx;

import ru.nosov.client.messages.IMessage;

/**
 * Сообщение авторизации.
 * @author Носов А.В.
 */
public interface IMessageLogin extends IMessage {
    
    /**
     * Возвращает имя.
     * @return имя
     */
    public String getLogin();

    /**
     * Устанавливает имя.
     * @param login имя
     */
    public void setLogin(String login);

    /**
     * Возвращает пароль.
     * @return пароль
     */
    public String getPassword();

    /**
     * Устанавливает пароль.
     * @param password пароль
     */
    public void setPassword(String password);
}