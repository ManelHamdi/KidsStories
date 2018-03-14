package com.example.kidsstories.DAOInterfaces;

import com.example.kidsstories.Entities.Conte;
import com.example.kidsstories.Entities.Mediascene;

import java.util.List;

public interface IMediasceneDAO extends IGenericDAO<Mediascene,Integer> {
    public int maxIdConte();
    public Conte ConteMeds(int Idcnt);
    public List<Mediascene> ListMs(int idCnt);
}
