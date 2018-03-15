package com.example.kidsstories.Entities;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Objects;

@Entity
public class Mediascene {
    private int idMediascene;
    private String texte;
    private int numOrdre;
    private byte[] img;
    private byte[] audio;
    private int idConte;
    private Conte conteByIdConte;

    @Id
    @Column(name = "id_mediascene", nullable = false)
    public int getIdMediascene() {
        return idMediascene;
    }

    public void setIdMediascene(int idMediascene) {
        this.idMediascene = idMediascene;
    }

    @Basic
    @Column(name = "texte", nullable = false, length = 255)
    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    @Basic
    @Column(name = "num_ordre", nullable = false)
    public int getNumOrdre() {
        return numOrdre;
    }

    public void setNumOrdre(int numOrdre) {
        this.numOrdre = numOrdre;
    }

    @Basic
    @Column(name = "img", nullable = false)
    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    @Basic
    @Column(name = "audio", nullable = false)
    public byte[] getAudio() {
        return audio;
    }

    public void setAudio(byte[] audio) {
        this.audio = audio;
    }

    @Basic
    @Column(name = "id_conte", nullable = false)
    public int getIdConte() {
        return idConte;
    }

    public void setIdConte(int idConte) {
        this.idConte = idConte;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mediascene that = (Mediascene) o;
        return idMediascene == that.idMediascene &&
                numOrdre == that.numOrdre &&
                idConte == that.idConte &&
                Objects.equals(texte, that.texte) &&
                Arrays.equals(img, that.img) &&
                Arrays.equals(audio, that.audio);
    }

    @Override
    public int hashCode() {

        int result = Objects.hash(idMediascene, texte, numOrdre, idConte);
        result = 31 * result + Arrays.hashCode(img);
        result = 31 * result + Arrays.hashCode(audio);
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
}
