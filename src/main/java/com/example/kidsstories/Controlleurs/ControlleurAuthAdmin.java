package com.example.kidsstories.Controlleurs;

import com.example.kidsstories.Entities.Conte;
import com.example.kidsstories.ModelInterfaces.IAdminService;
import com.example.kidsstories.ModelInterfaces.IConteService;
import com.example.kidsstories.ModelInterfaces.IMediasceneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ControlleurAuthAdmin {

    @Autowired
    private IConteService iConteService;

    @Autowired
    private IMediasceneService iMsService;

    @Autowired
    private IAdminService iAdminService;

    @GetMapping("/")
    public String auth() {
        return "Admin/AuthAdmin";
    }

    @PostMapping("/")
    public String AuthAdmin(@RequestParam String nom,
                            @RequestParam String pass,
                            ModelMap modelMap) {

        if (iAdminService.auth(nom, pass)) {
            int idAdmin = iConteService.findIdAdmin(nom, pass);
            modelMap.put("idAdmin", idAdmin);
            List<Conte> lstCnt = iConteService.ListCnt(idAdmin);
            modelMap.put("ListCnt", lstCnt);
            List<Conte> lstCntEx = iConteService.listConteExceptLast(idAdmin);
            modelMap.put("ListCntEx", lstCntEx);
            Conte cnt = iConteService.findById(iConteService.lastConte(idAdmin));
            modelMap.put("LastCnt", cnt);
            return "Conte/GererConte";
        } else {
            modelMap.put("notExist", "Verifier votre nom et/ou votre mot de passe");
            return "Admin/AuthAdmin";
        }
    }
}
