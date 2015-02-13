/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nosov.server.db.tables.logic;

import java.sql.SQLException;
import java.util.Collection;
import ru.nosov.client.messages.db.Users;

/**
 * Набор функций для взаимодействия с таблицей Users.
 * @author Носов А.В.
 */
public interface UsersDAO {
    
    /**
     * Возвращает все записи из таблици.
     * @return все записи
     * @throws java.sql.SQLException
     */
    public Collection getAllUsers() throws SQLException;

    /**
     * Возвращает запись по идентификатору.
     * @param uid идентификатор
     * @return запись
     * @throws java.sql.SQLException
     */
    public Users getUsersById(int uid) throws SQLException;
    
    /**
     * Возвращает пользователя авторизовавшегося в системе или null, 
     * если таковой не найден.
     * @param login логин
     * @param pas пароль
     * @return пользователь
     * @throws SQLException 
     */
    public Users isAuthentication(String login, String pas) throws SQLException;
    
    /**
     * Добавить нового пользователя.
     * @param user пользователь
     * @return <b>true</b> - пользователь зарегистрирован,
     * <b>false</b> - ошибка регистрации пользователя
     * @throws java.sql.SQLException
     */
    public boolean addUser(Users user) throws SQLException;
    
    /**
     * Возвращает результат проверки прозвища.
     * @param login прозвище
     * @return <b>true</b> - прозвище естьв БД,
     * <b>false</b> - прозвище нет в БД.
     * @throws SQLException 
     */
    public boolean isLoginName(String login) throws SQLException;
//    public boolean isLoginOREmail(Users user) throws SQLException;
}
