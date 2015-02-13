/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nosov.server.db.tables.logic;

import java.sql.SQLException;
import java.util.Collection;
import ru.nosov.client.messages.db.UsersTypes;

/**
 * Набор функций для взаимодействия с таблицей Users.
 * @author Носов А.В.
 */
public interface UsersTypesDAO {
    
    /**
     * Возвращает все записи из таблици.
     * @return все записи
     * @throws java.sql.SQLException
     */
    public Collection getAllUsersTypes() throws SQLException;

    /**
     * Возвращает запись по идентификатору.
     * @param uid идентификатор
     * @return запись
     * @throws java.sql.SQLException
     */
    public UsersTypes getUsersTypesById(int uid) throws SQLException;
    
    /**
     * Добавить нового пользователя.
     * @param usersTypes пользователь
     * @throws java.sql.SQLException
     */
    public void addUsersTypes(UsersTypes usersTypes) throws SQLException;
}
