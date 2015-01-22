/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nosov.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import ru.nosov.client.panels.PanelLogin;

/**
 * Главная страница.
 * http://www.dokwork.ru/2014/02/daotalk.html#refactor
 * @author Носов А.В.
 */
public class WelcomeEntryPoint implements EntryPoint {
    
    // Variables declaration
    
    // End of variables declaration
    
    /**
     * Creates a new instance of MainEntryPoint
     */
    public WelcomeEntryPoint() {
    }

    /**
     * The entry point method, called automatically by loading a module that
     * declares an implementing class as an entry-point
     */
    @Override
    public void onModuleLoad() {
        RootPanel.get().add(new PanelLogin());
    }
}
