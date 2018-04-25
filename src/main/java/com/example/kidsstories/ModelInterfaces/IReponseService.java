package com.example.kidsstories.ModelInterfaces;

import com.example.kidsstories.Entities.Reponse;

public interface IReponseService {
    Boolean ajouter(Reponse question);

    Reponse repQs(int idQs);

    Reponse findRbyIdQ(int idQs);

    Boolean updateQs(Reponse idRep);

}
