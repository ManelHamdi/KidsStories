package com.example.kidsstories.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Question {
    private int idQuestion;
    private String titre;
    private byte[] image;
    private int idConte;
    private int idCategories;
    private int idMediascene;
    private Conte conteByIdConte;
    private Categories categoriesByIdCategories;
    private Mediascene mediasceneByIdMediascene;
    private Collection<Reponse> reponsesByIdQuestion;

    @Id
    @Column(name = "id_question", nullable = false)
    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
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
    @Column(name = "image", nullable = false)
    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
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

    @Basic
    @Column(name = "id_mediascene", nullable = false)
    public int getIdMediascene() {
        return idMediascene;
    }

    public void setIdMediascene(int idMediascene) {
        this.idMediascene = idMediascene;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return idQuestion == question.idQuestion &&
                idConte == question.idConte &&
                idCategories == question.idCategories &&
                idMediascene == question.idMediascene &&
                Objects.equals(titre, question.titre) &&
                Arrays.equals(image, question.image);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(idQuestion, titre, idConte, idCategories, idMediascene);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "id_conte", referencedColumnName = "id_conte", nullable = false, insertable = false, updatable = false)
    public Conte getConteByIdConte() {
        return conteByIdConte;
    }

    public void setConteByIdConte(Conte conteByIdConte) {
        this.conteByIdConte = conteByIdConte;
    }

    @ManyToOne
    @JoinColumn(name = "idCategories", referencedColumnName = "idCategories", nullable = false, insertable = false, updatable = false)
    public Categories getCategoriesByIdCategories() {
        return categoriesByIdCategories;
    }

    public void setCategoriesByIdCategories(Categories categoriesByIdCategories) {
        this.categoriesByIdCategories = categoriesByIdCategories;
    }

    @ManyToOne
    @JoinColumn(name = "id_mediascene", referencedColumnName = "id_mediascene", nullable = false, insertable = false, updatable = false)
    public Mediascene getMediasceneByIdMediascene() {
        return mediasceneByIdMediascene;
    }

    public void setMediasceneByIdMediascene(Mediascene mediasceneByIdMediascene) {
        this.mediasceneByIdMediascene = mediasceneByIdMediascene;
    }

    @JsonIgnore
    @OneToMany(mappedBy = "questionByIdQuestion")
    public Collection<Reponse> getReponsesByIdQuestion() {
        return reponsesByIdQuestion;
    }

    public void setReponsesByIdQuestion(Collection<Reponse> reponsesByIdQuestion) {
        this.reponsesByIdQuestion = reponsesByIdQuestion;
    }
}
