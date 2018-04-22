package com.example.kidsstories.DAOClasses;

import com.example.kidsstories.DAOInterfaces.IConteDAO;
import com.example.kidsstories.Entities.Conte;
import org.springframework.stereotype.Repository;

@Repository
public class ConteDAO extends GenericDAO<Conte, Integer> implements IConteDAO {
}