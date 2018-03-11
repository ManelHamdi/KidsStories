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

    public Boolean ajouter( /* String titre,imgconte */ Conte conte) {
      if(iConteDAO.saveBool(conte)){
          return  true;
      }
          return  false;
    }

    @Override
    public int maxIdAdmin(String nom, String pass) {
        return iConteDAO.maxIdAdmin(nom,pass);
    }
}
