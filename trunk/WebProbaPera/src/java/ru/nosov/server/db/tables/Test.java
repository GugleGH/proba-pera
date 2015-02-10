/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nosov.server.db.tables;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import ru.nosov.client.messages.db.Users;
import ru.nosov.server.db.HibernateFactory;

/**
 *
 * @author nosov
 */
public class Test {
    
    public static void main(String[] args) {
        System.out.println("Погнали.");
        try {
            Collection users = HibernateFactory.getInstance().getUsersDAO().getAllUsers();
            System.out.println("Кол-во:" + users.size());
            Iterator it = users.iterator();
            while (it.hasNext()) {
                Users u = (Users) it.next();
                System.out.println("ID=" + u.getId()+"; "
                        + "Login=" + u.getLogin() + "; "
                        + "FirstName=" + u.getFirstname());
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        System.exit(0);
    }
}
