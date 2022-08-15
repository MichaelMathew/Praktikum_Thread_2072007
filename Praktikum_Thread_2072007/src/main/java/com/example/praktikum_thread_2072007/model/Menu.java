package com.example.praktikum_thread_2072007.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Menu {
    @Id
    @Column(name = "idMenu")
    private int idMenu;
    @Basic
    @Column(name = "namaMenu")
    private String namaMenu;
    @Basic
    @Column(name = "hargaMenu")
    private Double hargaMenu;
    @Basic
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "Category_idCategory", referencedColumnName = "idCategory", nullable = false)
    private Category categoryByCategoryIdCategory;

    public int getIdMenu() {
        return idMenu;
    }

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    public String getNamaMenu() {
        return namaMenu;
    }

    public void setNamaMenu(String namaMenu) {
        this.namaMenu = namaMenu;
    }

    public Double getHargaMenu() {
        return hargaMenu;
    }

    public void setHargaMenu(Double hargaMenu) {
        this.hargaMenu = hargaMenu;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Menu menu = (Menu) o;
        return idMenu == menu.idMenu && Objects.equals(namaMenu, menu.namaMenu) && Objects.equals(hargaMenu, menu.hargaMenu) && Objects.equals(description, menu.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMenu, namaMenu, hargaMenu, description);
    }

    public Category getCategoryByCategoryIdCategory() {
        return categoryByCategoryIdCategory;
    }

    public void setCategoryByCategoryIdCategory(Category categoryByCategoryIdCategory) {
        this.categoryByCategoryIdCategory = categoryByCategoryIdCategory;
    }
}
