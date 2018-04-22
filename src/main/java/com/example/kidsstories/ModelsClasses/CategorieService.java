package com.example.kidsstories.ModelsClasses;

import com.example.kidsstories.DAOInterfaces.ICategoriesDAO;
import com.example.kidsstories.Entities.Categories;
import com.example.kidsstories.ModelInterfaces.ICategorieService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieService implements ICategorieService {
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
    private ICategoriesDAO iCategoriesDAO;

    @Override
    public List<Categories> listCat() {
        return iCategoriesDAO.findAll();
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

    @Override
    public Categories findCbyId(int idCat) {
        return iCategoriesDAO.findById(idCat);
    }
}
