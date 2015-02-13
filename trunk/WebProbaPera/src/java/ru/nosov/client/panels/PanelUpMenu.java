/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nosov.client.panels;

import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTMLTable;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;

/**
 * Панель с меню.
 * @author Носов А.В.
 */
public class PanelUpMenu extends SimplePanel {
    
    // Variables declaration
    /** Люди. */
    private Anchor labelPerson;
    /** Сообщества. */
    private Anchor labelCommunity;
    /** Выход. */
    private Anchor labelExit;
    // End of variables declaration
    
    /**
     * Создает новую панель.
     */
    public PanelUpMenu() {
//        FlexTable table = new FlexTable();
        HorizontalPanel panel = new HorizontalPanel();
        panel.setWidth("100%");
        panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
        panel.setVerticalAlignment(HasVerticalAlignment.ALIGN_TOP);
        
        labelPerson = new Anchor("Люди");
        labelCommunity = new Anchor("Сообщества");
        labelExit = new Anchor("Выход");
        
        panel.add(labelPerson);
        panel.add(labelCommunity);
        panel.add(labelExit);
//        FlexTable.FlexCellFormatter cellFlex = table.getFlexCellFormatter();
//        HTMLTable.RowFormatter rowFlex = table.getRowFormatter();
        
        this.add(panel);
    }
}
