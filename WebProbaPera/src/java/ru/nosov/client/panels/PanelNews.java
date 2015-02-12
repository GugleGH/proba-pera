/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nosov.client.panels;

import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;

/**
 * Панель .
 * @author Носов А.В.
 */
public class PanelNews extends SimplePanel {
    
    // Variables declaration
    /** Новости. */
    public static final int TYPE_NEWS = 0;
    /** Ощибка авторизации. */
    public static final int TYPE_LOGIN_ERROR = 1;
    // End of variables declaration
    
    /**
     * Создает новую панель.
     * @param type тип данных в панели
     */
    public PanelNews(int type) {
        switch (type) {
            case TYPE_LOGIN_ERROR:
                getLoginError();
                break;
            default:
                getNews();
        }
    }
    
    private void getNews() {
        String htmlString = "Новость дня! <b>Это работает!</b>";
        this.add(new HTMLPanel(htmlString));
    }
    
    private void getLoginError() {
        String htmlString = "Не удается войти.\n"
            + "Пожалуйста, проверьте правильность написания логина и пароля.\n"
            + "\n<ul type=\"disc\">"
            + "<li>Возможно, нажата клавиша CAPS-lock?</li>"
            + "<li>Может быть, у Вас включена неправильная раскладка? (русская или английская)</li>"
            + "<li>Попробуйте набрать свой пароль в текстовом редакторе и скопировать в графу «Пароль»</li>"
            + "</ul>\n"
            + "\n"
            + "Если Вы всё внимательно проверили, но войти всё равно не удается, Вы можете нажать сюда. ";
        HTMLPanel p = new HTMLPanel(htmlString);
        
        this.add(new HTMLPanel(htmlString));
    }
}
