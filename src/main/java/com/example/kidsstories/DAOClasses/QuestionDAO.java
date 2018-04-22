package com.example.kidsstories.DAOClasses;

import com.example.kidsstories.DAOInterfaces.IQuestionDAO;
import com.example.kidsstories.Entities.Question;
import org.springframework.stereotype.Repository;

@Repository
public class QuestionDAO extends GenericDAO<Question, Integer> implements IQuestionDAO {
}
