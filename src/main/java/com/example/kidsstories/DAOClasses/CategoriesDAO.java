package com.example.kidsstories.DAOClasses;

import com.example.kidsstories.DAOInterfaces.ICategoriesDAO;
import com.example.kidsstories.Entities.Categories;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

@Repository
public class CategoriesDAO extends GenericDAO<Categories, Integer> implements ICategoriesDAO {

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
    public Categories catbyType(String type) {
        Categories results = null;
        try {
            Session session = sessionFactory.openSession();
            results = (Categories) session.createQuery("from Categories c where c.typeCategories = :t")
                    .setParameter("t", type).uniqueResult();
            session.close();
        } catch (Exception ex) {
            System.err.println("Erreur Dans mediascene dao find all ms by idcnt : \n" + ex.getMessage());
        }
        return results;
    }
}
