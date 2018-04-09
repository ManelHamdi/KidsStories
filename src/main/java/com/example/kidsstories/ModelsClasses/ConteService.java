package com.example.kidsstories.ModelsClasses;


import com.example.kidsstories.DAOInterfaces.IConteDAO;
import com.example.kidsstories.Entities.Conte;
import com.example.kidsstories.ModelInterfaces.IConteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConteService implements IConteService {

    @Autowired
    private IConteDAO iConteDAO;

    public List<Conte> getListconte() {
        return iConteDAO.findAll();
    }

    @Override
    public List<Conte> ListCnt(int idAdmin) {
        return iConteDAO.ListCnt(idAdmin);
    }

    @Override
    public Conte findById(int idCnt) {
        return iConteDAO.findById(idCnt);
    }

    public Boolean ajouter( /* String titre,imgconte */ Conte conte) {
        return iConteDAO.saveBool(conte);
    }

    @Override
    public int findIdAdmin(String nom, String pass) {
        return iConteDAO.maxIdAdmin(nom, pass);
    }

    @Override
    public Boolean deleteConte(Conte idConte) {
        try {
            iConteDAO.delete(idConte);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean updateConte(Conte conte) {
        try {
            return iConteDAO.update(conte);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
