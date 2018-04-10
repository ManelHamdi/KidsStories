package com.example.kidsstories.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Categories {
    private int idCategories;
    private String typeCategories;
    private Collection<Question> questionsByIdCategories;

    @Id
    @Column(name = "idCategories", nullable = false)
    public int getIdCategories() {
        return idCategories;
    }

    public void setIdCategories(int idCategories) {
        this.idCategories = idCategories;
    }

    @Basic
    @Column(name = "typeCategories", nullable = true, length = -1)
    public String getTypeCategories() {
        return typeCategories;
    }

    public void setTypeCategories(String typeCategories) {
        this.typeCategories = typeCategories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categories that = (Categories) o;
        return idCategories == that.idCategories &&
                Objects.equals(typeCategories, that.typeCategories);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idCategories, typeCategories);
    }

    @JsonIgnore
    @OneToMany(mappedBy = "categoriesByIdCategories")
    public Collection<Question> getQuestionsByIdCategories() {
        return questionsByIdCategories;
    }

    public void setQuestionsByIdCategories(Collection<Question> questionsByIdCategories) {
        this.questionsByIdCategories = questionsByIdCategories;
    }
}
