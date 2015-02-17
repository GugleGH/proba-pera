/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nosov.client.messages;

import ru.nosov.client.messages.types.TypeMessage;

/**
 * Запросить/Отправить/Принять новости.
 * @author Носов А.В.
 */
public class MessageNews extends Message {
    
    // Variables declaration
    /** Новость. */
    private String news;
    // End of variables declaration
    
    /**
     * Создание сообщения новостей.
     * @param tm тип сообщения
     */
    public MessageNews() {
    }

    /**
     * Возвращает новость.
     * @return новость
     */
    public String getNews() {
        return news;
    }

    /**
     * Устанавливает новость.
     * @param news новость
     */
    public void setNews(String news) {
        this.news = news;
    }
}