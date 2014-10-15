/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.nosov.server.exceptions.errors;

import java.util.ResourceBundle;
import ru.nosov.server.utils.I18N;

/**
 * Перечень и расшифровка возможных ошибок библиотеки.
 * @author Носов А.В.
 */
public enum ErrorsMessage {

//    // Variables declaration
    /** Ошибка парсера JSON. */
    ERROR_JSON(0, "ERROR_JSON"),
    /** Не обрабатываемый тип сообщения. */
    WARNING_UNPROCESSED_TYPE(1, "WARNING_UNPROCESSED_TYPE"),
    /** Неизвестный тип сообщения. */
    WARNING_UNKNOWN_TYPE(2, "WARNING_UNKNOWN_TYPE"),
    /** Слишком много данных для отображения векторной диаграммы. */
    WARNING_VECTOR_DIAGRAM_DATA(3, "WARNING_VECTOR_DIAGRAM_DATA"),
    /** Несовпадение идентификаторов. */
    ERROR_MODULE_ID(4, "ERROR_MODULE_ID"),
    /** Неверная команда запроса. */
    ERROR_DATA_REQUEST(5, "ERROR_DATA_REQUEST"),
    /** Неверная команда ответа. */
    ERROR_DATA_RESPONSE(6, "ERROR_DATA_RESPONSE"),
    /** Неверный тип запроса. */
    ERROR_TYPE_REQUEST(5, "ERROR_TYPE_REQUEST"),
    /** Неверный тип ответа. */
    ERROR_TYPE_RESPONSE(6, "ERROR_TYPE_RESPONSE");
    
    /** Описание ошибки. */
    private final String description;
    /** Код ошибки. */
    private final int code;
    /** Загрузчик языковых ресурсов. */
    private final ResourceBundle rb = ResourceBundle.getBundle(this.getClass().getCanonicalName());
    // End of variables declaration

    /**
     * Инициализация ошибки.
     * @param description описание ошибки
     */
    ErrorsMessage(int code, String description) {
        this.code = code;
        this.description = I18N.getString(rb, description);
    }

    /**
     * Возвращает описание ошибки.
     * @return описание ошибки
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Возвращает код ошибки.
     * @return код ошибки
     */
    public int getCode() {
        return code;
    }
}
