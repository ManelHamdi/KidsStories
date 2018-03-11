package com.example.kidsstories.ModelInterfaces;

import com.example.kidsstories.Entities.Conte;

import java.util.List;

public interface IConteService {
    public List<Conte> getListconte();
    public Boolean ajouter(Conte conte);
    public int maxIdAdmin(String nom, String pass);
}
