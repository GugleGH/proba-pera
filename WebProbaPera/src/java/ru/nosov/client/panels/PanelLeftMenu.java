/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nosov.client.panels;

import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Панель с меню.
 * @author Носов А.В.
 */
public class PanelLeftMenu extends SimplePanel {
    
    // Variables declaration
    /** Моя страница. */
    private Label labelAboute;
    /** Сообщения. */
    private Label labelMessages;
    /** Группы. */
    private Label labelGoups;
    /** Новости. */
    private Label labelNews;
    /** Настройки. */
    private Label labelSettings;
    // End of variables declaration
    
    /**
     * Создает новую панель.
     */
    public PanelLeftMenu() {
        VerticalPanel panel = new VerticalPanel();
        panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        panel.setVerticalAlignment(HasVerticalAlignment.ALIGN_BOTTOM);
        panel.setWidth("100%");
        
        labelAboute = new Label("Моя страница");
        labelMessages = new Label("Мои сообщения");
        labelGoups = new Label("Мои группы");
        labelNews = new Label("Новости");
        labelSettings = new Label("Настройки");
        
        panel.add(labelAboute);
        panel.add(labelMessages);
        panel.add(labelGoups);
        panel.add(labelNews);
        panel.add(labelSettings);
        
        this.add(panel);
    }
}
