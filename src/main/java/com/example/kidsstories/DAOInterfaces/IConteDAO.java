package com.example.kidsstories.DAOInterfaces;

import com.example.kidsstories.Entities.Conte;

import java.util.List;

public interface IConteDAO extends IGenericDAO<Conte, Integer> {
    int getIdAdmin(String nom, String pass);

    List<Conte> ListCnt(int idAdmin);
}
