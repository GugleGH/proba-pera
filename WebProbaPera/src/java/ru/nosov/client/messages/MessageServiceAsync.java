/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nosov.client.messages;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface MessageServiceAsync {
    
    /**
     * Возаращает сообщение от сервера к клиенту.
     * @param msg сообщение от клиента на сервер
     * @param callback обратный вызов
     */
    void getMessage(String msg, AsyncCallback<String> callback);
}
