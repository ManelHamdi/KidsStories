package com.example.kidsstories.ModelInterfaces;

import com.example.kidsstories.Entities.Conte;
import com.example.kidsstories.Entities.Mediascene;
import java.util.List;

public interface IMediasceneService {
    public List<Mediascene> getListconte();
    public Boolean ajouter(Mediascene mediascene);
    public int maxIdConte();
    public Conte getCntMeds(int idCnt);
    public List<Mediascene> ListMs(int idCnt);
}
