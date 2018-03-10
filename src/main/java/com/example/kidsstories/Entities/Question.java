package com.example.kidsstories.Entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Question {
    private String idQuestion;
    private String titre;
    private String réponse;
    private String image;
    private int idConte;
    private int idCategories;
    private Conte conteByIdConte;
    private Categories categoriesByIdCategories;

    @Id
    @Column(name = "id_question", nullable = false, length = 50)
    public String getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(String idQuestion) {
        this.idQuestion = idQuestion;
    }

    @Basic
    @Column(name = "titre", nullable = false, length = 50)
    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    @Basic
    @Column(name = "réponse", nullable = false, length = 50)
    public String getRéponse() {
        return réponse;
    }

    public void setRéponse(String réponse) {
        this.réponse = réponse;
    }

    @Basic
    @Column(name = "image", nullable = false, length = 200)
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Basic
    @Column(name = "id_conte", nullable = false)
    public int getIdConte() {
        return idConte;
    }

    public void setIdConte(int idConte) {
        this.idConte = idConte;
    }

    @Basic
    @Column(name = "idCategories", nullable = false)
    public int getIdCategories() {
        return idCategories;
    }

    public void setIdCategories(int idCategories) {
        this.idCategories = idCategories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return idConte == question.idConte &&
                idCategories == question.idCategories &&
                Objects.equals(idQuestion, question.idQuestion) &&
                Objects.equals(titre, question.titre) &&
                Objects.equals(réponse, question.réponse) &&
                Objects.equals(image, question.image);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idQuestion, titre, réponse, image, idConte, idCategories);
    }

    @ManyToOne
    @JoinColumn(name = "id_conte", referencedColumnName = "id_conte", nullable = false,insertable = false,updatable = false)
    public Conte getConteByIdConte() {
        return conteByIdConte;
    }

    public void setConteByIdConte(Conte conteByIdConte) {
        this.conteByIdConte = conteByIdConte;
    }

    @ManyToOne
    @JoinColumn(name = "idCategories", referencedColumnName = "idCategories", nullable = false,insertable = false,updatable = false)
    public Categories getCategoriesByIdCategories() {
        return categoriesByIdCategories;
    }

    public void setCategoriesByIdCategories(Categories categoriesByIdCategories) {
        this.categoriesByIdCategories = categoriesByIdCategories;
    }
}
