/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nosov.server.messages;

import ru.nosov.client.messages.Message;
import ru.nosov.client.messages.types.TypeMessage;

/**
 * Сообщение об ошибке в канале.
 * @author Носов А.В.
 */
public class MessageError extends Message {

    // Variables declaration
    /** Код ошибки. */
    private int code;
    /** Описание ошибки в канале. */
    private String description;
    // End of variables declaration

    public MessageError() {
        setTypeMessage(TypeMessage.Error);
    }

    /**
     * Возвращает описание ошибки.
     * @return описание ошибки
     */
    public String getDescription() {
        return description;
    }

    /**
     * Устанавливает описание ошибки.
     * @param description описание ошибки
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Возвращает код ошибки.
     * @return код ошибки
     */
    public int getCode() {
        return code;
    }

    /**
     * Устанавливает код ошибки.
     * @param code код ошибки
     */
    public void setCode(int code) {
        this.code = code;
    }
}