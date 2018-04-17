package com.example.kidsstories.ModelInterfaces;

import com.example.kidsstories.Entities.Categories;

import java.util.List;

public interface ICategorieService {
    List<Categories> listCat();

    Categories catbyType(String type);

    Categories findCbyId(int idCat);
}
