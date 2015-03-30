/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nosov.server.db.tables.logic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import ru.nosov.client.messages.db.UsersTypes;
import ru.nosov.server.db.HibernateSessionFactory;

/**
 * Реализация методов для взаимодейсвия таблицы Users с БД.
 * @author Носов А.В.
 */
public class UsersTypesDAOImpl implements UsersTypesDAO {
    
    // Variables declaration
    private static final Logger log = Logger.getLogger(UsersTypesDAOImpl.class);
    // End of variables declaration

    @Override
    public Collection getAllUsersTypes() throws SQLException {
        Session session = null;
        List usersTypes = new ArrayList<UsersTypes>();
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            usersTypes = session.createCriteria(UsersTypes.class).list();
        } catch (Exception ex) {
            log.error("Error in getAllUsersTypes", ex);
        } finally {
            if (session != null && session.isOpen())
                session.close();
        }
        return (Collection) usersTypes;
    }

    @Override
    public UsersTypes getUsersTypesById(int uid) throws SQLException {
        Session session = null;
        UsersTypes usersTypes = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            usersTypes = (UsersTypes) session.load(UsersTypes.class, (short)uid);
//            System.out.println(telemetry);
        } catch (Exception ex) {
            log.error("Error in getUsersTypesById", ex);
        } finally {
            if (session != null && session.isOpen())
                session.close();
        }
        return usersTypes;
    }
    
    @Override
    public void addUsersTypes(UsersTypes usersTypes) throws SQLException {
        Session session = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(usersTypes);
            session.getTransaction().commit();
        } catch (Exception ex) {
            log.error("Error in addUsersTypes", ex);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
