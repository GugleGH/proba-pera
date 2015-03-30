/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nosov.client.messages;

import java.util.ArrayList;
import java.util.List;
import ru.nosov.client.messages.types.TypeMessage;

/**
 * Запросить/Отправить/Принять новости.
 * @author Носов А.В.
 */
public class MessageNewsLine extends Message {
    
    // Variables declaration
    /** Имя пользователя. */
    private String userName;
    /** Список новостей. */
    private List<MessageNews> newsLine;
    // End of variables declaration
    
    /**
     * Создание сообщения новостей.
     * @param tm тип сообщения
     */
    public MessageNewsLine() {
    }

    /**
     * Возвращает имя пользователя.
     * @return имя пользователя
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Устанавливает имя пользователя.
     * @param userName имя пользователя
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Возвращает список новостей.
     * @return список новостей
     */
    public List<MessageNews> getNewsLine() {
        return newsLine;
    }

    /**
     * Устанавливает список новостей.
     * @param newses список нвостей
     */
    public void setNewsLine(List<MessageNews> newses) {
        this.newsLine = newses;
    }
    
    /**
     * Добавляет новость в ленту.
     * @param news новость
     */
    public void addNews(MessageNews news) {
        if (this.newsLine == null)
            this.newsLine = new ArrayList<>();
        this.newsLine.add(news);
    }
}