package com.example.praktikum_thread_2072007.dao;

import com.example.praktikum_thread_2072007.model.Category;
import com.example.praktikum_thread_2072007.util.MyConnection;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class CategoryDao implements daoInterface<Category> {

    @Override
    public List<Category> getData() {
        List<Category> cList;
        Session s = MyConnection.getSession();
        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(Category.class);
        cq.from(Category.class);
        cList = s.createQuery(cq).getResultList();
        s.close();
        return cList;
    }

    @Override
    public void addData(Category data) {
        Session s = MyConnection.getSession();
        Transaction t = s.beginTransaction();
        try{
            s.save(data);
            t.commit();
        }
        catch (Exception e){
            t.rollback();
        }
        s.close();
    }

    @Override
    public int deleteData(Category data) {
        return 0;
    }

    @Override
    public int updateData(Category data) {
        return 0;
    }
}
