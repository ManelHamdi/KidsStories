package com.example.kidsstories.ModelsClasses;

import com.example.kidsstories.DAOInterfaces.IReponseDAO;
import com.example.kidsstories.Entities.Reponse;
import com.example.kidsstories.ModelInterfaces.IReponseService;
import org.hibernate.Session;
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

    @Override
    public Reponse repQs(int idQs) {
        Reponse results = null;
        try {
            Session session = sessionFactory.openSession();
            results = (Reponse) session.createQuery("from Reponse r where r.idQuestion = " + idQs).uniqueResult();
            session.close();
        } catch (Exception ex) {
            System.err.println("Erreur Dans Question dao find all ms by idcnt : \n" + ex.getMessage());
        }
        return results;
    }

    @Override
    public Reponse findRbyIdQ(int idQs) {
        Reponse results = null;
        try {
            Session session = sessionFactory.openSession();
            results = (Reponse) session.createQuery("from Reponse r where r.idQuestion = " + idQs).uniqueResult();
            session.close();
        } catch (Exception ex) {
            System.err.println("Erreur Dans mediascene dao find all rep by idqst : \n" + ex.getMessage());
        }
        return results;
    }

    @Override
    public Boolean updateQs(Reponse idRep) {
        try {
            return iReponseDAO.update(idRep);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
