package com.example.kidsstories.ModelsClasses;

import com.example.kidsstories.DAOInterfaces.IQuestionDAO;
import com.example.kidsstories.Entities.Question;
import com.example.kidsstories.ModelInterfaces.IQuestionService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService implements IQuestionService {
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
    private IQuestionDAO iQuestionDAO;

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

    @Override
    public Boolean ajouter(Question question) {
        return iQuestionDAO.saveBool(question);
    }

    @Override
    public Question findQbyId(int idQs) {
        return iQuestionDAO.findById(idQs);
    }

    @Override
    public Boolean deleteQs(Question idQs) {
        try {
            iQuestionDAO.delete(idQs);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean updateQs(Question idQs) {
        try {
            return iQuestionDAO.update(idQs);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
