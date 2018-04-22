package com.example.kidsstories.ModelsClasses;


import com.example.kidsstories.DAOInterfaces.IConteDAO;
import com.example.kidsstories.Entities.Administrateur;
import com.example.kidsstories.Entities.Conte;
import com.example.kidsstories.ModelInterfaces.IConteService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConteService implements IConteService {
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
    private IConteDAO iConteDAO;

    public List<Conte> getListconte() {
        return iConteDAO.findAll();
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

    @Override
    public Conte findById(int idCnt) {
        return iConteDAO.findById(idCnt);
    }

    public Boolean ajouter(Conte conte) {
        return iConteDAO.saveBool(conte);
    }

    @Override
    public int findIdAdmin(String nom, String pass) {
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
    public Boolean deleteConte(Conte idConte) {
        try {
            iConteDAO.delete(idConte);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean updateConte(Conte conte) {
        try {
            return iConteDAO.update(conte);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Conte> listAllConte() {
        return iConteDAO.findAll();
    }

    @Override
    public List<Conte> listConteExceptLast(int idAdmin) {
        List<Conte> results = null;
        try {
            Session session = sessionFactory.openSession();
            results = session.createQuery("from Conte where idAdmin = :i and idConte != (select (max(idConte)) from Conte )")
                    .setParameter("i", idAdmin).list();
            session.close();
        } catch (Exception ex) {
            System.err.println("Erreur Dans mediascene dao find all ms by idcnt : \n" + ex.getMessage());
        }
        return results;
    }

    @Override
    public int lastConte(int idAdmin) {
        int results = 0;
        try {
            Session session = sessionFactory.openSession();
            results = (int) session.createQuery("select (max(c.idConte)) from Conte c where c.idAdmin = " + idAdmin).uniqueResult();
            session.close();
        } catch (Exception ex) {
            System.err.println("Erreur Dans mediascene dao find all ms by idcnt : \n" + ex.getMessage());
        }
        return results;
    }
}
