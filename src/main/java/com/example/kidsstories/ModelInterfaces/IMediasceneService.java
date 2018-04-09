package com.example.kidsstories.ModelInterfaces;

import com.example.kidsstories.Entities.Conte;
import com.example.kidsstories.Entities.Mediascene;

import java.util.List;

public interface IMediasceneService {

    List<Mediascene> getListconte();

    Boolean ajouter(Mediascene mediascene);

    int maxIdConte();

    Conte getCntMeds(int idCnt);

    int NumOrd(int idMedsc);

    List<Mediascene> ListMs(int idCnt);

    Boolean deleteMs(Mediascene idMedsc);

    Mediascene findById(int idMs);

    Boolean updateMs(Mediascene mediascene);
}
