package com.example.kidsstories.DAOClasses;

import com.example.kidsstories.DAOInterfaces.IAdminDao;
import com.example.kidsstories.Entities.Administrateur;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDAO extends GenericDAO<Administrateur, Integer> implements IAdminDao {
}
