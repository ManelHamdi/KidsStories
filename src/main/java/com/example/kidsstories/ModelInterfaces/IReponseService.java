package com.example.kidsstories.ModelInterfaces;

import com.example.kidsstories.Entities.Reponse;

import java.util.List;

public interface IReponseService {
    Boolean ajouter(Reponse question);

    Reponse repQs(int idQs);

    Reponse findRbyIdQ(int idQs);

    Boolean updateQs(Reponse idRep);

    List<Reponse> lstRep();

}
