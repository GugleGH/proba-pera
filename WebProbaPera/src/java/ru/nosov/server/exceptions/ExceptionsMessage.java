/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.nosov.server.exceptions;

import ru.nosov.server.exceptions.errors.ErrorsMessage;

/**
 * Сообщение исключений.
 * @author Носов А.В.
 */
public class ExceptionsMessage extends ExceptionMessage {

    // Variables declaration
    /** Сообщение ошибки. */
    private final ErrorsMessage error;
    // End of variables declaration

    /**
     * Конструктор исключения.
     * @param error сообщение ошибки
     */
    public ExceptionsMessage(ErrorsMessage error) {
        super(error.getDescription());
        this.error = error;
    }

    /**
     * Возвращает ошибку.
     * @return ошибка
     */
    public ErrorsMessage getError() {
        return error;
    }
}
