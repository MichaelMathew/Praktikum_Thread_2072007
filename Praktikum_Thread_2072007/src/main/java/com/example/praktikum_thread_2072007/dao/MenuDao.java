package com.example.praktikum_thread_2072007.dao;

import com.example.praktikum_thread_2072007.model.Menu;
import com.example.praktikum_thread_2072007.util.MyConnection;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class MenuDao implements daoInterface<Menu> {

    @Override
    public List<Menu> getData() {
        List<Menu> mList;
        Session s = MyConnection.getSession();
        CriteriaBuilder cb = s.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(Menu.class);
        cq.from(Menu.class);
        mList = s.createQuery(cq).getResultList();
        s.close();
        return mList;
    }

    public void addData(Menu data) {
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
    public int deleteData(Menu data) {
        int hasil = 0;
        Session s = MyConnection.getSession();
        Transaction t = s.beginTransaction();
        try{
            s.delete(data);
            t.commit();
            hasil = 1;
        }
        catch (Exception e){
            t.rollback();
        }
        s.close();
        return hasil;
    }

    @Override
    public int updateData(Menu data) {
        int hasil = 0;
        Session s = MyConnection.getSession();
        Transaction t = s.beginTransaction();
        try{
            s.update(data);
            t.commit();
            hasil = 1;
        }
        catch (Exception e){
            t.rollback();
        }
        s.close();
        return hasil;
    }
}
