/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.nosov.client.utils;

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
        return !((name == null) || (name.trim().length() < 1));
    }
    
    /**
     * Проверка корректности ввода логина.
     * @param login логин
     * @return <b>true</b> - верно, <br>
     * <b>false</b> - не верно.
     */
    public static boolean validateLogin(String login) {
        return ((login == null) || (login.trim().length() == 0) || (login.trim().length() >= 3));
    }
    
    /**
     * Проверка корректности ввода пароля.
     * @param pass пароль
     * @return <b>true</b> - верно, <br>
     * <b>false</b> - не верно.
     */
    public static boolean validatePassword(String pass) {
        return !((pass == null) || (pass.trim().length() < 3));
    }
    
    /**
     * Проверка корректности ввода электронной почты.
     * @param email электронная почта
     * @return <b>true</b> - верно, <br>
     * <b>false</b> - не верно.
     */
    public static boolean validateEmail(String email) {
        return !((email == null) || (email.indexOf("@") < 1));
    }
}
