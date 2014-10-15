/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.nosov.client.messages;

import ru.nosov.client.messages.types.TypeMessage;

/**
 * Абстракция для работы с сообщениями.
 * @author Носов А.В.
 */
public abstract class AMessage implements IMessage {
    
    // Variables declaration
    /** Тип сообщения. */
    private TypeMessage typeMessage;
    // End of variables declaration
    
    @Override
    public TypeMessage getTypeMessage() {
        return typeMessage;
    }

    @Override
    public void setTypeMessage(TypeMessage typeMessage) {
        this.typeMessage = typeMessage;
    }
}
