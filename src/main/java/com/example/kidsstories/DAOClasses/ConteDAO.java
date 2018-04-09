package com.example.kidsstories.DAOClasses;

import com.example.kidsstories.DAOInterfaces.IConteDAO;
import com.example.kidsstories.Entities.Administrateur;
import com.example.kidsstories.Entities.Conte;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ConteDAO extends GenericDAO<Conte, Integer> implements IConteDAO {

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
    public int maxIdAdmin(String nom, String pass) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        System.out.println("contedao");
        Administrateur admin = (Administrateur) session.createQuery("FROM Administrateur ad where ad.nom= :n and ad.password= :p ")
                .setParameter("n", nom).setParameter("p", pass).uniqueResult();
        System.out.println("admin cntdao" + admin.getIdAdmin());
        session.getTransaction().commit();
        System.out.println("contedao" + admin.getIdAdmin());
        return admin.getIdAdmin();
    }

    @Override
    public List<Conte> ListCnt(int idAdmin) {
        List<Conte> results = null;
        try {
            Session session = sessionFactory.openSession();
            results = session.createQuery("from Conte c where c.idAdmin = " + idAdmin + " order by idConte DESC ").list();
            session.close();
        } catch (Exception ex) {
            System.err.println("Erreur Dans mediascene dao find all ms by idcnt : \n" + ex.getMessage());
        }
        return results;
    }
}