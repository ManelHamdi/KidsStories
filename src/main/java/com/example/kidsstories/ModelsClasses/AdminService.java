package com.example.kidsstories.ModelsClasses;

import com.example.kidsstories.DAOInterfaces.IAdminDao;
import com.example.kidsstories.ModelInterfaces.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService implements IAdminService {

    @Autowired
    private IAdminDao iAdminDao;

    @Override
    public Boolean auth(String login, String pass) {
        if(iAdminDao.Teste(login,pass)){
            return true;
        }else {
            return false;
        }
    }
}
