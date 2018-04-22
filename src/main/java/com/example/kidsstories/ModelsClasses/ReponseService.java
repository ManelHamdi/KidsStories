package com.example.kidsstories.ModelsClasses;

import com.example.kidsstories.DAOInterfaces.IReponseDAO;
import com.example.kidsstories.Entities.Reponse;
import com.example.kidsstories.ModelInterfaces.IReponseService;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReponseService implements IReponseService {

    private static SessionFactory sessionFactory;

    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Exception ex) {
            System.err.println("Erreur Dans GenericDAO.SessionFactory : \n" + ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Autowired
    private IReponseDAO iReponseDAO;

    @Override
    public Boolean ajouter(Reponse reponse) {
        return iReponseDAO.saveBool(reponse);
    }
}
