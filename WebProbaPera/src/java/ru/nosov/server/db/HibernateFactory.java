/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.nosov.server.db;

import ru.nosov.server.db.tables.logic.UsersDAO;
import ru.nosov.server.db.tables.logic.UsersDAOImpl;
import ru.nosov.server.db.tables.logic.UsersTypesDAO;
import ru.nosov.server.db.tables.logic.UsersTypesDAOImpl;

/**
 *
 * @author nosov
 */
public class HibernateFactory {

    // Variables declaration
    private static HibernateFactory instance = null;
    private static UsersDAO usersDAO = null;
    private static UsersTypesDAO usersTypesDAO = null;
    // End of variables declaration

    public static synchronized HibernateFactory getInstance() {
        if (instance == null)  instance = new HibernateFactory();
        return instance;
    }

    /**
     * 
     * @return 
     */
    public UsersDAO getUsersDAO() {
        if (usersDAO == null) usersDAO = new UsersDAOImpl();
        return usersDAO;
    }

    /**
     * 
     * @return 
     */
    public UsersTypesDAO getUsersTypesDAO() {
        if (usersTypesDAO == null) usersTypesDAO = new UsersTypesDAOImpl();
        return usersTypesDAO;
    }
}
