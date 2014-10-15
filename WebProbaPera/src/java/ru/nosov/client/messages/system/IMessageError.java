/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.nosov.client.messages.system;

import ru.nosov.client.messages.IMessage;

/**
 * Сообщение об ошибке.
 * @author Носов А.В.
 */
public interface IMessageError extends IMessage {
    
    /**
     * Возвращает описание ошибки.
     * @return описание ошибки
     */
    public String getDescription();

    /**
     * Устанавливает описание ошибки.
     * @param description описание ошибки
     */
    public void setDescription(String description);

    /**
     * Возвращает код ошибки.
     * @return код ошибки
     */
    public int getCode();

    /**
     * Устанавливает код ошибки.
     * @param code код ошибки
     */
    public void setCode(int code);
}
