package com.example.kidsstories.ModelsClasses;

import com.example.kidsstories.ModelInterfaces.IAdminService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import javax.persistence.Query;
import java.util.List;

@Service
public class AdminService implements IAdminService {
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
    public Boolean auth(String login, String pass) {
        Session session = sessionFactory.openSession();
        Boolean bool = false;
        List list;
        try {
            session.beginTransaction();
            Query query = session.createQuery("FROM Administrateur where nom= :log and password= :pass");
            query.setParameter("log", login);
            query.setParameter("pass", pass);
            list = query.getResultList();
            session.getTransaction().commit();
            return !list.isEmpty();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
