package com.example.kidsstories.DAOClasses;

import com.example.kidsstories.DAOInterfaces.IReponseDAO;
import com.example.kidsstories.Entities.Reponse;
import org.springframework.stereotype.Repository;

@Repository
public class ReponseDAO extends GenericDAO<Reponse, Integer> implements IReponseDAO {
}
