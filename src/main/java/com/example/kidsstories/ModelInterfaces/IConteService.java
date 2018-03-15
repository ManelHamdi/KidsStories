package com.example.kidsstories.ModelInterfaces;

import com.example.kidsstories.Entities.Conte;

import java.util.List;

public interface IConteService {
    List<Conte> getListconte();

    Boolean ajouter(Conte conte);

    int maxIdAdmin(String nom, String pass);
}
