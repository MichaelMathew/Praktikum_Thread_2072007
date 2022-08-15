package com.example.praktikum_thread_2072007.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {

    public static Session getSession() {
        Session s;
        SessionFactory sf = new Configuration().configure().buildSessionFactory();
        s = sf.openSession();
        return s;
    }
    public static Connection getConnection(){
        Connection conn;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/dbmenu",
                    "root",
                    ""
            );
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }
}
