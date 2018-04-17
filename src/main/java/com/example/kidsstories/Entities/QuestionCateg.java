package com.example.kidsstories.Entities;

public class QuestionCateg {
    private int idQuestion;
    private String titre;
    private String reponse;
    private byte[] image;
    private int idConte;
    private int idCategories;
    private int idMediascene;
    private String typeCategories;

    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getIdConte() {
        return idConte;
    }

    public void setIdConte(int idConte) {
        this.idConte = idConte;
    }

    public int getIdCategories() {
        return idCategories;
    }

    public void setIdCategories(int idCategories) {
        this.idCategories = idCategories;
    }

    public int getIdMediascene() {
        return idMediascene;
    }

    public void setIdMediascene(int idMediascene) {
        this.idMediascene = idMediascene;
    }

    public String getTypeCategories() {
        return typeCategories;
    }

    public void setTypeCategories(String typeCategories) {
        this.typeCategories = typeCategories;
    }
}
