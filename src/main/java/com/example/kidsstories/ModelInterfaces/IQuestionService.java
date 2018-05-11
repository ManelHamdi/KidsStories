package com.example.kidsstories.ModelInterfaces;

import com.example.kidsstories.Entities.Question;

import java.util.List;

public interface IQuestionService {
    List<Question> ListQs(int idQs);

    Boolean ajouter(Question question);

    Question findQbyId(int idQs);

    Boolean deleteQs(Question idQs);

    Boolean updateQs(Question idQs);

    int mexIdQuestion(int idCnt);

    Question qsbyCntMs(int idConte, int idMs);
}
