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
 * Панель шапки.
 * @author Носов А.В.
 */
public class PanelHeader extends SimplePanel {
    
    // Variables declaration
    private Label label1;
    private Label label2;
    // End of variables declaration
    
    /**
     * Создает новую панель.
     */
    public PanelHeader() {
        VerticalPanel panelName = new VerticalPanel();
        panelName.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        panelName.setVerticalAlignment(HasVerticalAlignment.ALIGN_BOTTOM);
        panelName.setWidth("100%");
        
        label1 = new Label("Злобный");
        label2 = new Label("чебурашка");
        
        panelName.add(label1);
        panelName.add(label2);
        
        this.add(panelName);
    }
}
