package com.example.kidsstories.DAOInterfaces;

import com.example.kidsstories.Entities.Conte;

public interface IConteDAO extends IGenericDAO<Conte,Integer> {
    public int maxIdAdmin(String nom, String pass);
}
