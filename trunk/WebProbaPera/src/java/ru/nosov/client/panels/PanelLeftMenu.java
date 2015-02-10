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
    // End of variables declaration
    
    /**
     * Создает новую панель.
     */
    public PanelLeftMenu() {
        VerticalPanel panel = new VerticalPanel();
        panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        panel.setVerticalAlignment(HasVerticalAlignment.ALIGN_BOTTOM);
        panel.setWidth("100%");
        
        for (int i=0; i<5; i++)
            panel.add(new Label("Меню № " + i));
        
        this.add(panel);
    }
}
