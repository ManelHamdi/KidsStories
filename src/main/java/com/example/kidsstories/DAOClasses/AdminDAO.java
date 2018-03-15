package com.example.kidsstories.DAOClasses;

import com.example.kidsstories.DAOInterfaces.IAdminDao;
import com.example.kidsstories.Entities.Administrateur;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.List;

@Repository
public class AdminDAO extends GenericDAO<Administrateur, Integer> implements IAdminDao {
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
    public Boolean Teste(String login, String password) {
        Session session = sessionFactory.openSession();
        Boolean bool = false;
        List list;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM Administrateur where nom= :log and password= :pass");
            query.setParameter("log", login);
            query.setParameter("pass", password);
            list = query.getResultList();
            session.getTransaction().commit();
            return !list.isEmpty();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
