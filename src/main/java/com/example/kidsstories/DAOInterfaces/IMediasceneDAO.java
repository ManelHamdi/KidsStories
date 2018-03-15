package com.example.kidsstories.DAOInterfaces;

import com.example.kidsstories.Entities.Conte;
import com.example.kidsstories.Entities.Mediascene;

import java.util.List;

public interface IMediasceneDAO extends IGenericDAO<Mediascene, Integer> {
    int maxIdConte();

    Conte ConteMeds(int Idcnt);

    List<Mediascene> ListMs(int idCnt);
}
