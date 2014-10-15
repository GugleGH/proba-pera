/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.nosov.server.exceptions;

import ru.nosov.server.exceptions.errors.ErrorsMessage;

/**
 * Исключения для соединения.
 * @author Носов А.В.
 */
public class ExceptionsMessage extends ExceptionMessage {

    // Variables declaration
    /** Ошибка при работе с соединением. */
    private final ErrorsMessage error;
    // End of variables declaration

    /**
     * Конструктор исключения.
     * @param error ошибка приработе с настройками
     */
    public ExceptionsMessage(ErrorsMessage error) {
        super(error.getDescription());
        this.error = error;
    }

    /**
     * Возвращает ошибку при работе с настройками.
     * @return ошибка при работе с настройками
     */
    public ErrorsMessage getError() {
        return error;
    }
}
