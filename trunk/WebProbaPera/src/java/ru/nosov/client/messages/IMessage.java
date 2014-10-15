package ru.nosov.client.messages;

import ru.nosov.client.messages.types.TypeMessage;

/**
 * Интерфейс для работы с сообщениями.
 */
public interface IMessage {
    
    /**
     * Получить тип сообщения.
     * @return тип сообщения
     * @see TypeMessage Типы сообщений
     */
    TypeMessage getTypeMessage();
    
    /**
     * Устанавливает тип сообщения.
     * @param typeMessage тип сообщения
     */
    void setTypeMessage(TypeMessage typeMessage);

}
