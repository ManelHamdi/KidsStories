package com.example.kidsstories.ModelsClasses;

import com.example.kidsstories.DAOInterfaces.IMediasceneDAO;
import com.example.kidsstories.Entities.Conte;
import com.example.kidsstories.Entities.Mediascene;
import com.example.kidsstories.ModelInterfaces.IMediasceneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MediasceneService implements IMediasceneService {

    @Autowired
    private IMediasceneDAO imedsdao;

    @Override
    public List<Mediascene> getListconte() {
        return imedsdao.findAll();
    }

    @Override
    public Boolean ajouter(Mediascene mediascene) {
        return imedsdao.saveBool(mediascene);
    }

    @Override
    public int maxIdConte() {
        return imedsdao.maxIdConte();
    }

    @Override
    public Conte getCntMeds(int idCnt) {
        return imedsdao.ConteMeds(idCnt);
    }

    @Override
    public List<Mediascene> ListMs(int idCnt) {
        return imedsdao.ListMs(idCnt);
    }

    @Override
    public Boolean deleteMs(Mediascene idMedsc) {
        try {
            imedsdao.delete(idMedsc);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
