package com.example.kidsstories.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Administrateur {
    private int idAdmin;
    private String nom;
    private String prenom;
    private String password;
    private String email;
    private Collection<Conte> contesByIdAdmin;

    @Id
    @Column(name = "idAdmin", nullable = false)
    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    @Basic
    @Column(name = "nom", nullable = true, length = 254)
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Basic
    @Column(name = "prenom", nullable = true, length = 254)
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Basic
    @Column(name = "password", nullable = true, length = 254)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "email", nullable = true, length = 254)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Administrateur that = (Administrateur) o;
        return idAdmin == that.idAdmin &&
                Objects.equals(nom, that.nom) &&
                Objects.equals(prenom, that.prenom) &&
                Objects.equals(password, that.password) &&
                Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idAdmin, nom, prenom, password, email);
    }

    @JsonIgnore
    @OneToMany(mappedBy = "administrateurByIdAdmin")
    public Collection<Conte> getContesByIdAdmin() {
        return contesByIdAdmin;
    }

    public void setContesByIdAdmin(Collection<Conte> contesByIdAdmin) {
        this.contesByIdAdmin = contesByIdAdmin;
    }
}
