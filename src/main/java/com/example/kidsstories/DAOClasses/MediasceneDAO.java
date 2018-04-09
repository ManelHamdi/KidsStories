package com.example.kidsstories.DAOClasses;

import com.example.kidsstories.DAOInterfaces.IMediasceneDAO;
import com.example.kidsstories.Entities.Conte;
import com.example.kidsstories.Entities.Mediascene;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.List;

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
        System.out.println("mediascenedao " + conte.getIdConte());
        return conte.getIdConte();
    }

    @Override
    public Conte ConteMeds(int Idcnt) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Conte conte = (Conte) session.createQuery("FROM Conte order by idConte desc ").setMaxResults(1).uniqueResult();
        session.getTransaction().commit();
        System.out.println("mediascenedao " + conte.getIdConte());
        return conte;
    }

    @Override
    public int NumOrd(int idcnt) {
        int num = 0;
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Mediascene medsc = (Mediascene) session.createQuery("FROM Mediascene where idConte = " + idcnt + "order by idMediascene desc ").setMaxResults(1).uniqueResult();
        session.getTransaction().commit();
        try {
            num = medsc.getNumOrdre() + 1;
        } catch (Exception e) {
            num = 1;
        }
        return num;

    }

    @Override
    public List<Mediascene> ListMs(int idCnt) {
        List<Mediascene> results = null;
        try {
            Session session = sessionFactory.openSession();
            results = session.createQuery("from Mediascene m where m.idConte = " + idCnt).list();
            session.close();
        } catch (Exception ex) {
            System.err.println("Erreur Dans mediascene dao find all ms by idcnt : \n" + ex.getMessage());
        }
        return results;
    }
}
