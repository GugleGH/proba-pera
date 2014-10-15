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
import ru.nosov.server.db.HibernateSessionFactory;
import ru.nosov.server.db.tables.Users;

/**
 * Реализация методов для взаимодейсвия таблицы Users с БД.
 * @author Носов А.В.
 */
public class UsersDAOImpl implements UsersDAO {
    
    // Variables declaration
    private static final Logger log = Logger.getLogger(UsersDAOImpl.class);
    // End of variables declaration

    @Override
    public Collection getAllUsers() throws SQLException {
        Session session = null;
        List users = new ArrayList<Users>();
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            users = session.createCriteria(Users.class).list();
        } catch (Exception ex) {
            log.error("Error in getAllUsers", ex);
        } finally {
            if (session!=null && session.isOpen())
                session.close();
        }
        return (Collection) users;
    }

    @Override
    public Users getUsersByUid(int uid) throws SQLException {
        Session session = null;
        Users user = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            user = (Users) session.load(Users.class, uid);
//            System.out.println(telemetry);
        } catch (Exception ex) {
            log.error("Error in getUsersByUid", ex);
        } finally {
            if (session!=null && session.isOpen())
                session.close();
        }
        return user;
    }
}
