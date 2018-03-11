package com.example.kidsstories.Controlleurs;

import com.example.kidsstories.ModelInterfaces.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ControlleurAuthAdmin {

    @Autowired
    private IAdminService iAdminService;

    @GetMapping("/")
    public String auth(){
        return "Admin/AuthAdmin";
    }

    @PostMapping("/")
    public String AuthAdmin(@RequestParam String nom,
                            @RequestParam String pass,
                            ModelMap modelMap){
        if (iAdminService.auth(nom,pass)){
            modelMap.put("nom",nom);
            modelMap.put("pass",pass);
            return "Conte/AjouterConte";
        }else {
            modelMap.put("notExist","Verifier votre nom et/ou votre mot de passe");
            return "Admin/AuthAdmin";
        }
    }
}
