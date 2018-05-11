package com.example.kidsstories.ModelsClasses;

import com.example.kidsstories.DAOInterfaces.IMediasceneDAO;
import com.example.kidsstories.Entities.Mediascene;
import com.example.kidsstories.ModelInterfaces.IMediasceneService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MediasceneService implements IMediasceneService {
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
    private IMediasceneDAO imedsdao;

    @Override
    public Boolean ajouter(Mediascene mediascene) {
        return imedsdao.saveBool(mediascene);
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

    @Override
    public Boolean deleteMs(Mediascene idMedsc) {
        try {
            imedsdao.delete(idMedsc);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public Mediascene findById(int idMs) {
        return imedsdao.findById(idMs);
    }

    @Override
    public Boolean updateMs(Mediascene mediascene) {
        try {
            return imedsdao.update(mediascene);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean ifMeds(int idcnt) {
        int results = 0;
        try {
            Session session = sessionFactory.openSession();
            results = (int) session.createQuery("select count(*) from Mediascene m where m.idConte = " + idcnt).uniqueResult();
            session.close();
        } catch (Exception ex) {
            System.err.println("Erreur Dans mediascene dao find all ms by idcnt : \n" + ex.getMessage());
        }
        return results > 0;
    }

    @Override
    public List<Mediascene> ListMsLimited(int idCnt, int idms) {
        List<Mediascene> results = null;
        try {
            Session session = sessionFactory.openSession();
            results = session.createQuery("from Mediascene m where m.idMediascene >= " + idms + " and m.idConte = " + idCnt).list();
            session.close();
        } catch (Exception ex) {
            System.err.println("Erreur Dans mediascene dao find all ms by idcnt : \n" + ex.getMessage());
        }
        return results;
    }

    @Override
    public int maxMs(int idCnt) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        int num = (int) session.createQuery("SELECT  max(idMediascene) FROM Mediascene where idConte = " + idCnt).setMaxResults(1).uniqueResult();
        session.getTransaction().commit();
        return num;
    }

}
