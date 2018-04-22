package com.example.kidsstories.DAOClasses;

import com.example.kidsstories.DAOInterfaces.ICategoriesDAO;
import com.example.kidsstories.Entities.Categories;
import org.springframework.stereotype.Repository;

@Repository
public class CategoriesDAO extends GenericDAO<Categories, Integer> implements ICategoriesDAO {
}
