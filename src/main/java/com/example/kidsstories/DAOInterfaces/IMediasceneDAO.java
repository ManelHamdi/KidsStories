package com.example.kidsstories.DAOInterfaces;

import com.example.kidsstories.Entities.Mediascene;

import java.util.List;

public interface IMediasceneDAO extends IGenericDAO<Mediascene, Integer> {

    int NumOrd(int idMedsc);

    List<Mediascene> ListMs(int idCnt);
}
