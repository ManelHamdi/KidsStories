package com.example.kidsstories.DAOClasses;

import com.example.kidsstories.DAOInterfaces.IQuestionDAO;
import com.example.kidsstories.Entities.Question;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuestionDAO extends GenericDAO<Question, Integer> implements IQuestionDAO {

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
    public List<Question> ListQs(int idCnt) {
        List<Question> results = null;
        try {
            Session session = sessionFactory.openSession();
            results = session.createQuery("from Question q where q.idConte = " + idCnt).list();
            session.close();
        } catch (Exception ex) {
            System.err.println("Erreur Dans Question dao find all ms by idcnt : \n" + ex.getMessage());
        }
        return results;
    }
}
