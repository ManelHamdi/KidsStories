package com.example.kidsstories.ModelInterfaces;

import com.example.kidsstories.Entities.Mediascene;

import java.util.List;

public interface IMediasceneService {

    Boolean ajouter(Mediascene mediascene);

    int NumOrd(int idMedsc);

    List<Mediascene> ListMs(int idCnt);

    Boolean deleteMs(Mediascene idMedsc);

    Mediascene findById(int idMs);

    Boolean updateMs(Mediascene mediascene);

    Boolean ifMeds(int idcnt);
}
