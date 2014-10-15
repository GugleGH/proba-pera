/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nosov.server.db.tables.logic;

import java.sql.SQLException;
import java.util.Collection;
import ru.nosov.server.db.tables.Users;

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
    public Users getUsersByUid(int uid) throws SQLException;
}
