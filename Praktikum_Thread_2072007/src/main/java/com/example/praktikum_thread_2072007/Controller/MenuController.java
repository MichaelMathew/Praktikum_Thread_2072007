package com.example.praktikum_thread_2072007.Controller;

import com.example.praktikum_thread_2072007.MenuApplication;
import com.example.praktikum_thread_2072007.dao.MenuDao;
import com.example.praktikum_thread_2072007.dao.CategoryDao;
import com.example.praktikum_thread_2072007.thread.CallBack;
import com.example.praktikum_thread_2072007.thread.ThreadGroupReport;
import com.example.praktikum_thread_2072007.thread.ThreadSimpleReport;
import com.example.praktikum_thread_2072007.util.MyConnection;
import com.example.praktikum_thread_2072007.model.Category;
import com.example.praktikum_thread_2072007.model.Menu;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MenuController {

    public TextField txtID;
    public TextField txtName;
    public TextField txtPrice;
    public TextField txtDescription;
    public ComboBox<Category> CombCategory;
    public Button BtnSave;
    public Button BtnReset;
    public Button BtnUpdate;
    public Button BtnDelete;
    public TableView<Menu> TableMenu;
    public TableColumn<String,Menu> ColID;
    public TableColumn<String,Menu> ColNama;
    public TableColumn<Float,Menu> ColPrice;
    public TableColumn<Category, Menu> ColCategory;
    public MenuItem Category;
    public MenuItem Close;
    public MenuItem SReport;
    public MenuItem GReport;
    private Stage stage;
    ObservableList<Menu> mList_tampilan;
    ObservableList<Category> cList_tampilan;

    public void initialize() throws IOException {
        stage = new Stage();
        SelectedCategory();
        Category.setAccelerator(KeyCombination.keyCombination("Alt+F2"));
        Close.setAccelerator(KeyCombination.keyCombination("Alt+X"));
        SReport.setAccelerator(KeyCombination.keyCombination("Alt+S"));
        GReport.setAccelerator(KeyCombination.keyCombination("Alt+G"));
        mList_tampilan = FXCollections.observableArrayList();
        ShowData();
    }

    public void GoToCategory(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader;
        fxmlLoader = new FXMLLoader(MenuApplication.class.getResource("Category-View.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 558, 350);

        stage.setTitle("Category Management");
        stage.setScene(scene);
        stage.showAndWait();
    }

    public void Closed(ActionEvent actionEvent) {
        txtID.getScene().getWindow().hide();
    }

    public void ShowData() {
        MenuDao dao = new MenuDao();
        mList_tampilan.clear();
        mList_tampilan = FXCollections.observableArrayList(dao.getData());
        TableMenu.setItems(mList_tampilan);
        ColID.setCellValueFactory(new PropertyValueFactory<>("idMenu"));
        ColNama.setCellValueFactory(new PropertyValueFactory<>("namaMenu"));
        ColPrice.setCellValueFactory(new PropertyValueFactory<>("hargaMenu"));
        ColCategory.setCellValueFactory(new PropertyValueFactory<>("categoryByCategoryIdCategory"));
        BtnUpdate.setDisable(true);
        BtnDelete.setDisable(true);
    }

    public void SaveData(ActionEvent actionEvent) {
        MenuDao dao = new MenuDao();
        if(txtID.getText() == null || txtName.getText() == null || txtPrice.getText() == null || txtDescription.getText() == null || CombCategory.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Please Fill all the field",ButtonType.OK);
            alert.showAndWait();
        } else {
            Menu m = new Menu();
            m.setIdMenu(Integer.parseInt(txtID.getText()));
            m.setNamaMenu(txtName.getText());
            m.setHargaMenu(Double.parseDouble(txtPrice.getText()));
            m.setDescription(txtDescription.getText());
            m.setCategoryByCategoryIdCategory(CombCategory.getValue());
            dao.addData(m);
            ShowData();
            ResetData();
        }
    }

    public void ResetData() {
        txtID.clear();
        txtName.clear();
        txtPrice.clear();
        txtDescription.clear();
        CombCategory.getSelectionModel().select(null);
        BtnUpdate.setDisable(true);
        BtnDelete.setDisable(true);
        txtID.setDisable(false);
    }

    public void SelectedCategory() {
        CategoryDao dao = new CategoryDao();
        cList_tampilan = FXCollections.observableArrayList(dao.getData());
        CombCategory.setItems(cList_tampilan);
    }

    public void SelectedTable(MouseEvent mouseEvent) {
        if (!TableMenu.getSelectionModel().getSelectedCells().isEmpty()){
            TableMenu.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
            BtnUpdate.setDisable(false);
            BtnDelete.setDisable(false);
            txtID.setText(String.valueOf(TableMenu.getSelectionModel().getSelectedItem().getIdMenu()));
            txtName.setText(TableMenu.getSelectionModel().getSelectedItem().getNamaMenu());
            txtPrice.setText(String.valueOf(TableMenu.getSelectionModel().getSelectedItem().getHargaMenu()));
            txtDescription.setText(TableMenu.getSelectionModel().getSelectedItem().getDescription());
            CombCategory.setValue(TableMenu.getSelectionModel().getSelectedItem().getCategoryByCategoryIdCategory());
            txtID.setDisable(true);
        }
    }

    public void UpdateData(ActionEvent actionEvent) {
        MenuDao dao = new MenuDao();
        Menu m = new Menu();
        m.setIdMenu(Integer.parseInt(txtID.getText()));
        m.setNamaMenu(txtName.getText());
        m.setHargaMenu(Double.parseDouble(txtPrice.getText()));
        m.setDescription(txtDescription.getText());
        m.setCategoryByCategoryIdCategory(CombCategory.getValue());
        int hasil = dao.updateData(m);
        if (hasil > 0) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Berhasil Update ygy", ButtonType.OK);
            alert.showAndWait();
            alert.showAndWait();
            ShowData();
            ResetData();
        }
    }

    public void DeleteData(ActionEvent actionEvent) {
        ObservableList<Menu> SelectedMenu;
        SelectedMenu = TableMenu.getSelectionModel().getSelectedItems();
        Alert alert = new Alert(Alert.AlertType.WARNING, "Are you sure?", ButtonType.OK, ButtonType.CANCEL);
        alert.showAndWait();
        MenuDao dao = new MenuDao();
        if (alert.getResult() == ButtonType.OK) {
            for (Menu m : SelectedMenu) {
                dao.deleteData(m);
            }
            Alert alert2 = new Alert(Alert.AlertType.CONFIRMATION, "Success Delete ygy", ButtonType.OK);
            alert2.showAndWait();
        }
        ResetData();
        ShowData();
    }


    public void ShowSReport(ActionEvent actionEvent) {
        ThreadSimpleReport ts = new ThreadSimpleReport(new CallBack() {
            @Override
            public void OnComplete() {
                System.out.println("Thread Berhasil");
            }
        });
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(ts);
        executorService.shutdown();
    }

    public void ShowGReport(ActionEvent actionEvent) {
        ThreadGroupReport tg = new ThreadGroupReport(new CallBack() {
            @Override
            public void OnComplete() {
                System.out.println("Thread Berhasil");
            }
        });
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(tg);
        executorService.shutdown();
    }
}
