package com.example.praktikum_thread_2072007.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Category {
    @Id
    @Column(name = "idCategory")
    private int idCategory;
    @Basic
    @Column(name = "namaCategory")
    private String namaCategory;

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getNamaCategory() {
        return namaCategory;
    }

    public void setNamaCategory(String namaCategory) {
        this.namaCategory = namaCategory;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return idCategory == category.idCategory && Objects.equals(namaCategory, category.namaCategory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCategory, namaCategory);
    }

    @Override
    public String toString() {
        return namaCategory;
    }
}
