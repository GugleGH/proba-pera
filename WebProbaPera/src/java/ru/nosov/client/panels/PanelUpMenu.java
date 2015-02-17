/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nosov.client.panels;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import ru.nosov.client.messages.types.TypeMenu;

/**
 * Панель с меню.
 * @author Носов А.В.
 */
public class PanelUpMenu extends SimplePanel {
    
    // Variables declaration
//    /** Люди. */
//    private Anchor anchorPerson;
//    /** Сообщества. */
//    private Anchor anchorCommunity;
//    /** Выход. */
//    private Anchor anchorExit;
    // End of variables declaration
    
    /**
     * Создает новую панель.
     */
    public PanelUpMenu() {
        HorizontalPanel panel = new HorizontalPanel();
        
        TypeMenu[] menus = TypeMenu.values();
        for (final TypeMenu menu : menus) {
            if ( (menu.getCode() < 150) && (menu.getCode() > 100) ) {
                Anchor anchor = new Anchor(menu.getDescription());
                if (menu == TypeMenu.Exit)
                    anchor.addStyleName("gwt-Anchor-Exit");
                else anchor.addStyleName("gwt-Anchor-Up");
                anchor.addClickHandler(new ClickHandler() {			
                    @Override
                    public void onClick(ClickEvent event) {
                        onClickAnchor(menu);
                    }
                });
                panel.add(anchor);
            }
        }
        
//        anchorPerson = new Anchor("Люди");
//        anchorPerson.addStyleName("gwt-Anchor-Up");
//        anchorCommunity = new Anchor("Сообщества");
//        anchorCommunity.addStyleName("gwt-Anchor-Up");
//        anchorExit = new Anchor("Выход");
//        anchorExit.addStyleName("gwt-Anchor-Rigth");
//        
//        panel.add(anchorPerson);
//        panel.add(anchorCommunity);
//        panel.add(anchorExit);
        
        this.add(panel);
    }
    
    private void onClickAnchor(TypeMenu tm) {
        switch (tm) {
            case Person:
                break;
            case Community:
                break;
            case Exit:
                break;
        }
    }
}
