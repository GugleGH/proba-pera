/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nosov.server.db.tables.logic;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import ru.nosov.client.messages.db.Users;
import ru.nosov.server.db.HibernateSessionFactory;

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
            if (session != null && session.isOpen())
                session.close();
        }
        return (Collection) users;
    }

    @Override
    public Users getUsersById(int uid) throws SQLException {
        Session session = null;
        Users user = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            user = (Users) session.load(Users.class, uid);
//            System.out.println(telemetry);
        } catch (Exception ex) {
            log.error("Error in getUsersById", ex);
        } finally {
            if (session != null && session.isOpen())
                session.close();
        }
        return user;
    }
    
    @Override
    public Users isAuthentication(String login, String pas) throws SQLException {
        Session session = null;
        Users user = null;
        List users;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            if (session != null)
                log.debug("Session is open");
            users = session.createCriteria(Users.class)
                    .add(Restrictions.like("login", login))
                    .add(Restrictions.like("password", pas)).list();
            
            if ( (users != null) && (users.size() == 1) )
                user = (Users) users.get(0);
        } catch (Exception ex) {
            log.error("Error in isAuthentication", ex);
        } finally {
            if (session != null && session.isOpen())
                session.close();
        }
        return user;
    }
    
    @Override
    public boolean isLoginName(String login) throws SQLException {
        Session session = null;
        List users = new ArrayList<Users>();
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            
            users = session.createCriteria(Users.class)
                .add( Restrictions.like("login", login) ).list();
            
        } catch (Exception ex) {
            log.error("Error in isLoginName", ex);
        } finally {
            if (session != null && session.isOpen())
                session.close();
        }
        
        if (users == null) return false;
        return users.isEmpty();
    }
    
//    @Override
    private boolean isLoginOREmail(Users user) throws SQLException {
        Session session = null;
        //Users user = null;
        List users = new ArrayList<Users>();
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            if (user.getLogin() == null) {
                users = session.createCriteria(Users.class)
                    .add( Restrictions.like("email", user.getEmail()) ).list();
            } else {
                users = session.createCriteria(Users.class)
                        .add(Restrictions.or(
                                Restrictions.like("login", user.getLogin()),
                                Restrictions.like("email", user.getEmail()))
                        ).list();
            }
        } catch (Exception ex) {
            log.error("Error in isLoginOREmail", ex);
        } finally {
            if (session != null && session.isOpen())
                session.close();
        }
        
        if (users == null) return true;
        return users.isEmpty();
    }
    
    @Override
    public boolean addUser(Users user) throws SQLException {
        Session session = null;
        try {
            Calendar cal = Calendar.getInstance();
            user.setCreateTime(cal.getTime());
            session = HibernateSessionFactory.getSessionFactory().openSession();
            session.beginTransaction();
            if (!isLoginOREmail(user)) return false;
            session.save(user);
            session.getTransaction().commit();
        } catch (HibernateException | SQLException ex) {
            log.error("Error in addUser", ex);
        } finally {
            log.error("Session is close.");
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return true;
    }
}
