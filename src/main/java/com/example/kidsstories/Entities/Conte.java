package com.example.kidsstories.Entities;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Conte {
    private int idConte;
    private String titre;
    private byte[] imgconte;
    private int idAdmin;
    private Administrateur administrateurByIdAdmin;
    private Collection<Mediascene> mediascenesByIdConte;
    private Collection<Question> questionsByIdConte;

    @Id
    @Column(name = "id_conte", nullable = false)
    public int getIdConte() {
        return idConte;
    }

    public void setIdConte(int idConte) {
        this.idConte = idConte;
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
    @Column(name = "imgconte", nullable = false)
    public byte[] getImgconte() {
        return imgconte;
    }

    public void setImgconte(byte[] imgconte) {
        this.imgconte = imgconte;
    }

    @Basic
    @Column(name = "idAdmin", nullable = false)
    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conte conte = (Conte) o;
        return idConte == conte.idConte &&
                idAdmin == conte.idAdmin &&
                Objects.equals(titre, conte.titre) &&
                Arrays.equals(imgconte, conte.imgconte);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(idConte, titre, idAdmin);
        result = 31 * result + Arrays.hashCode(imgconte);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "idAdmin", referencedColumnName = "idAdmin", nullable = false,insertable = false,updatable = false)
    public Administrateur getAdministrateurByIdAdmin() {
        return administrateurByIdAdmin;
    }

    public void setAdministrateurByIdAdmin(Administrateur administrateurByIdAdmin) {
        this.administrateurByIdAdmin = administrateurByIdAdmin;
    }

    @OneToMany(mappedBy = "conteByIdConte")
    public Collection<Mediascene> getMediascenesByIdConte() {
        return mediascenesByIdConte;
    }

    public void setMediascenesByIdConte(Collection<Mediascene> mediascenesByIdConte) {
        this.mediascenesByIdConte = mediascenesByIdConte;
    }

    @OneToMany(mappedBy = "conteByIdConte")
    public Collection<Question> getQuestionsByIdConte() {
        return questionsByIdConte;
    }

    public void setQuestionsByIdConte(Collection<Question> questionsByIdConte) {
        this.questionsByIdConte = questionsByIdConte;
    }
}
