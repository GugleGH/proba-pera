/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nosov.client.panels;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import ru.nosov.client.WelcomeEntryPoint;
import ru.nosov.client.messages.MessageNews;
import ru.nosov.client.messages.types.TypeMenu;
import ru.nosov.client.messages.types.TypeMessage;

/**
 * Панель с меню.
 * @author Носов А.В.
 */
public class PanelLeftMenu extends SimplePanel {
    
    // Variables declaration
    private WelcomeEntryPoint parent;
    
//    /** Моя страница. */
//    private Anchor anchorAboute;
//    /** Сообщения. */
//    private Anchor anchorMessages;
//    /** Группы. */
//    private Anchor anchorGoups;
//    /** Новости. */
//    private Anchor anchorNews;
//    /** Настройки. */
//    private Anchor anchorSettings;
    // End of variables declaration
    
    /**
     * Создает новую панель.
     * @param parent родитель
     */
    public PanelLeftMenu(WelcomeEntryPoint parent) {
        this.parent = parent;
        initComponents();
    }
    
    private void initComponents() {
        VerticalPanel panel = new VerticalPanel();
        panel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
        panel.setVerticalAlignment(HasVerticalAlignment.ALIGN_BOTTOM);
        panel.setWidth("100%");
        
        TypeMenu[] menus = TypeMenu.values();
        for (final TypeMenu menu : menus) {
            if ( (menu.getCode() < 100) && (menu.getCode() > 10) ) {
                Anchor anchor = new Anchor(menu.getDescription());
                anchor.addStyleName("gwt-Anchor-Left");
                anchor.addClickHandler(new ClickHandler() {			
                    @Override
                    public void onClick(ClickEvent event) {
                        onClickAnchor(menu);
                    }
                });
                panel.add(anchor);
            }
        }
        
//        anchorAboute = new Anchor("Моя страница");
//        anchorMessages = new Anchor("Мои сообщения");
//        anchorGoups = new Anchor("Мои группы");
//        anchorNews = new Anchor("Новости");
//        anchorSettings = new Anchor("Настройки");
//        
//        anchorAboute.addStyleName("gwt-Anchor-Left");
//        anchorAboute.addClickHandler(new ClickHandler() {			
//            @Override
//            public void onClick(ClickEvent event) {
//                onClick();
//            }
//        });
//        
//        anchorMessages.addStyleName("gwt-Anchor-Left");
//        anchorMessages.addClickHandler(new ClickHandler() {			
//            @Override
//            public void onClick(ClickEvent event) {
//                onClick();
//            }
//        });
//        
//        anchorGoups.addStyleName("gwt-Anchor-Left");
//        anchorGoups.addClickHandler(new ClickHandler() {			
//            @Override
//            public void onClick(ClickEvent event) {
//                onClick();
//            }
//        });
//        
//        anchorNews.addStyleName("gwt-Anchor-Left");
//        anchorNews.addClickHandler(new ClickHandler() {			
//            @Override
//            public void onClick(ClickEvent event) {
//                onClick();
//            }
//        });
//        
//        anchorSettings.addStyleName("gwt-Anchor-Left");
//        anchorSettings.addClickHandler(new ClickHandler() {			
//            @Override
//            public void onClick(ClickEvent event) {
//                onClick();
//            }
//        });
//        
//        panel.add(anchorAboute);
//        panel.add(anchorMessages);
//        panel.add(anchorGoups);
//        panel.add(anchorNews);
//        panel.add(anchorSettings);
        
        this.add(panel);
    }

    private void onClickAnchor(TypeMenu tm) {
        if (parent == null) return;
        switch (tm) {
            case Aboute:
                break;
            case Messages:
                break;
            case Goups:
                break;
            case News:
                MessageNews msgNews = new MessageNews();
                msgNews.setTypeMessage(TypeMessage.RqNewsLine);
                parent.getMsgService().getMessage(msgNews, parent);
                break;
            case Settings:
                break;
        }
    }
}
