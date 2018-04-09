package com.example.kidsstories.ModelInterfaces;

import com.example.kidsstories.Entities.Conte;

import java.util.List;

public interface IConteService {

    List<Conte> ListCnt(int idAdmin);

    Conte findById(int idCnt);

    Boolean ajouter(Conte conte);

    int findIdAdmin(String nom, String pass);

    Boolean deleteConte(Conte idConte);

    Boolean updateConte(Conte idConte);
}
