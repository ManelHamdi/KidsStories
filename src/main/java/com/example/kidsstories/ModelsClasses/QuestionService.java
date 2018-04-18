package com.example.kidsstories.ModelsClasses;

import com.example.kidsstories.DAOInterfaces.IQuestionDAO;
import com.example.kidsstories.Entities.Question;
import com.example.kidsstories.ModelInterfaces.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService implements IQuestionService {

    @Autowired
    private IQuestionDAO iQuestionDAO;

    @Override
    public List<Question> ListQs(int idQs) {
        return iQuestionDAO.ListQs(idQs);
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
