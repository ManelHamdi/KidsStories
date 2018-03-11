package com.example.kidsstories.DAOInterfaces;

import com.example.kidsstories.Entities.Administrateur;

public interface IAdminDao extends IGenericDAO<Administrateur, Integer> {
    public Boolean Teste(String login, String password);
}
