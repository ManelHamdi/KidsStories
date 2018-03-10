package com.example.kidsstories.DAOClasses;

import com.example.kidsstories.DAOInterfaces.IConteDAO;
import com.example.kidsstories.Entities.Administrateur;
import com.example.kidsstories.Entities.Conte;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

@Repository
public class ConteDAO extends GenericDAO<Conte,Integer> implements IConteDAO {

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
    public int maxIdAdmin() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        System.out.println("contedao");
        Administrateur admin = (Administrateur) session.createQuery("FROM Administrateur order by idAdmin desc ").setMaxResults(1).uniqueResult();
        session.getTransaction().commit();
        System.out.println("contedao"+admin.getIdAdmin());
        return admin.getIdAdmin();
    }
}