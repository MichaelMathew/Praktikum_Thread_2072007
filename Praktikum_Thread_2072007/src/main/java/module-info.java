module com.example.praktikum_database_2072007 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jasperreports;
    requires java.naming;
    requires org.hibernate.orm.core;
    requires java.persistence;

    opens com.example.praktikum_thread_2072007 to javafx.fxml;
    exports com.example.praktikum_thread_2072007;
    exports com.example.praktikum_thread_2072007.model;
    exports com.example.praktikum_thread_2072007.dao;
    exports com.example.praktikum_thread_2072007.Controller;
    exports com.example.praktikum_thread_2072007.util;
    exports com.example.praktikum_thread_2072007.thread;
    opens com.example.praktikum_thread_2072007.model;
}