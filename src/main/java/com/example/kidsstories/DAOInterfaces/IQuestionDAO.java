package com.example.kidsstories.DAOInterfaces;

import com.example.kidsstories.Entities.Question;

import java.util.List;

public interface IQuestionDAO extends IGenericDAO<Question, Integer> {
    List<Question> ListQs(int idCnt);
}
