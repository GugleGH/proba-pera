/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.nosov.client.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * Класс полезных утилиток.
 * @author Носов А.В.
 */
public class Utils {

    // Variables declaration
    
    // End of variables declaration

    
    /**
     * Проверка корректности ввода даты рождения.
     * @param birthday дата рождения
     * @return <b>true</b> - верно, <br>
     * <b>false</b> - не верно.
     */
    public static boolean validateBirthday(String birthday) {
        return true;
    }
    
    /**
     * Проверка корректности ввода логина.
     * @param name логин
     * @return <b>true</b> - верно, <br>
     * <b>false</b> - не верно.
     */
    public static boolean validateName(String name) {
        return (name != null);
    }
    
    /**
     * Проверка корректности ввода логина.
     * @param login логин
     * @return <b>true</b> - верно, <br>
     * <b>false</b> - не верно.
     */
    public static boolean validateLogin(String login) {
        if (login == null) return false;
        return login.trim().length() >= 3;
    }
    
    /**
     * Проверка корректности ввода пароля.
     * @param pass пароль
     * @return <b>true</b> - верно, <br>
     * <b>false</b> - не верно.
     */
    public static boolean validatePassword(String pass) {
        if (pass == null) return false;
        return pass.trim().length() >= 3;
    }
    
    /**
     * Проверка корректности ввода электронной почты.
     * @param email электронная почта
     * @return <b>true</b> - верно, <br>
     * <b>false</b> - не верно.
     */
    public static boolean validateEmail(String email) {
        return (email != null);
    }
}
