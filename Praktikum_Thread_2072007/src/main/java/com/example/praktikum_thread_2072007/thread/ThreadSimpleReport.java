package com.example.praktikum_thread_2072007.thread;

import com.example.praktikum_thread_2072007.util.MyConnection;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;



public class ThreadSimpleReport extends Thread{

    private CallBack onCompleteAction;

    public ThreadSimpleReport(CallBack onCompleteAction){
        this.onCompleteAction = onCompleteAction;
    }
    @Override
    public void run() {
        JasperPrint jp;
        Connection conn = MyConnection.getConnection();
        Map param = new HashMap();
        try {
            jp = JasperFillManager.fillReport("report/Menu.jasper",param,conn);
            JasperViewer viewer = new JasperViewer(jp,false);
            viewer.setTitle("Laporan Menu");
            viewer.setVisible(true);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
        if (onCompleteAction != null){
            onCompleteAction.OnComplete();
        }
    }
}
