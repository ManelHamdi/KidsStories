package com.example.kidsstories.DAOInterfaces;

import com.example.kidsstories.Entities.Categories;

public interface ICategoriesDAO extends IGenericDAO<Categories, Integer> {
    Categories catbyType(String type);
}
