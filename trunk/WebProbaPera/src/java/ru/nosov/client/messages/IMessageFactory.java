/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.nosov.client.messages;

import com.google.web.bindery.autobean.shared.AutoBean;
import com.google.web.bindery.autobean.shared.AutoBeanFactory;
import ru.nosov.client.messages.system.IMessageError;
import ru.nosov.client.messages.tx.IMessageLogin;

/**
 *
 * @author nosov
 */
public interface IMessageFactory extends AutoBeanFactory {
    
    /**
     * Возвращает сообщение запроса.
     * @return сообщение запроса
     */
    AutoBean<IMessage> messageRq();
    
    /**
     * Возвращает сообщение ошибки.
     * @return сообщение ошибки
     */
    AutoBean<IMessageError> messageError();
    
    
    /**
     * Возвращает сообщение авторизации.
     * @return сообщение авторизации
     */
    AutoBean<IMessageLogin> messageLogin();
}
