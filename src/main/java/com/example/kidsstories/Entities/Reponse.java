package com.example.kidsstories.Entities;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Reponse {
    private int idReponse;
    private String texteReponse1;
    private String texteReponse2;
    private String texteReponse3;
    private String correcte;
    private int idQuestion;
    private Question questionByIdQuestion;

    @Id
    @Column(name = "idReponse", nullable = false)
    public int getIdReponse() {
        return idReponse;
    }

    public void setIdReponse(int idReponse) {
        this.idReponse = idReponse;
    }

    @Basic
    @Column(name = "texteReponse1", nullable = false, length = 50)
    public String getTexteReponse1() {
        return texteReponse1;
    }

    public void setTexteReponse1(String texteReponse1) {
        this.texteReponse1 = texteReponse1;
    }

    @Basic
    @Column(name = "texteReponse2", nullable = false, length = 50)
    public String getTexteReponse2() {
        return texteReponse2;
    }

    public void setTexteReponse2(String texteReponse2) {
        this.texteReponse2 = texteReponse2;
    }

    @Basic
    @Column(name = "texteReponse3", nullable = false, length = 50)
    public String getTexteReponse3() {
        return texteReponse3;
    }

    public void setTexteReponse3(String texteReponse3) {
        this.texteReponse3 = texteReponse3;
    }

    @Basic
    @Column(name = "correcte", nullable = false)
    public String getCorrecte() {
        return correcte;
    }

    public void setCorrecte(String correcte) {
        this.correcte = correcte;
    }

    @Basic
    @Column(name = "idQuestion", nullable = false)
    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reponse reponse = (Reponse) o;
        return idReponse == reponse.idReponse &&
                correcte == reponse.correcte &&
                idQuestion == reponse.idQuestion &&
                Objects.equals(texteReponse1, reponse.texteReponse1) &&
                Objects.equals(texteReponse2, reponse.texteReponse2) &&
                Objects.equals(texteReponse3, reponse.texteReponse3);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idReponse, texteReponse1, texteReponse2, texteReponse3, correcte, idQuestion);
    }

    @ManyToOne
    @JoinColumn(name = "idQuestion", referencedColumnName = "id_question", nullable = false, insertable = false, updatable = false)
    public Question getQuestionByIdQuestion() {
        return questionByIdQuestion;
    }

    public void setQuestionByIdQuestion(Question questionByIdQuestion) {
        this.questionByIdQuestion = questionByIdQuestion;
    }
}
