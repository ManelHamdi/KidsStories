package com.example.kidsstories.ModelsClasses;

import com.example.kidsstories.DAOInterfaces.ICategoriesDAO;
import com.example.kidsstories.Entities.Categories;
import com.example.kidsstories.ModelInterfaces.ICategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieService implements ICategorieService {

    @Autowired
    private ICategoriesDAO iCategoriesDAO;

    @Override
    public List<Categories> listCat() {
        return iCategoriesDAO.findAll();
    }

    @Override
    public Categories catbyType(String type) {
        return iCategoriesDAO.catbyType(type);
    }

    @Override
    public Categories findCbyId(int idCat) {
        return iCategoriesDAO.findById(idCat);
    }
}
