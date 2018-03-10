package com.example.kidsstories.DAOClasses;

import com.example.kidsstories.DAOInterfaces.IMediasceneDAO;
import com.example.kidsstories.Entities.Conte;
import com.example.kidsstories.Entities.Mediascene;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

@Repository
public class MediasceneDAO extends GenericDAO<Mediascene, Integer> implements IMediasceneDAO {

    private static SessionFactory sessionFactory;
    static {
        try {
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Exception ex) {
            System.err.println("Erreur Dans GenericDAO.SessionFactory : \n" + ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Override
    public int maxIdConte() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Conte conte = (Conte) session.createQuery("FROM Conte order by idConte desc ").setMaxResults(1).uniqueResult();
        session.getTransaction().commit();
        System.out.println("mediascenedao "+conte.getIdConte());
        return conte.getIdConte();
    }
}
