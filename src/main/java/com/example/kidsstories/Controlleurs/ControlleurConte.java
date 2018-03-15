package com.example.kidsstories.Controlleurs;

import com.example.kidsstories.Entities.Conte;
import com.example.kidsstories.ModelInterfaces.IConteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ControlleurConte {

    @Autowired
    private IConteService iConteService;

    @GetMapping("/Conte")
    public String ajouterC() {
        return "Conte/AjouterConte";
    }

    @PostMapping("/Conte")
    public String AjouterConte(@RequestParam String titre,
                               @RequestParam MultipartFile imgCnt,
                               ModelMap modelMap,
                               @RequestParam String nom,
                               @RequestParam String pass) {
        try {
            Conte conte = new Conte();
            conte.setTitre(titre);
            conte.setImgconte(imgCnt.getBytes());
            conte.setIdAdmin(iConteService.maxIdAdmin(nom, pass));
            System.out.println("admin id" + iConteService.maxIdAdmin(nom, pass));
            String type = imgCnt.getContentType().split("/")[0];
            String path = imgCnt.getOriginalFilename();
            if (type.equals("image")) {
                /* if(type.equals("audio")){*/
                System.out.println("It's an image");
            } else {
                modelMap.put("Erreur", "it's not an image");
                return "Conte/AjouterConte";
            }
            System.out.println("get request method " + imgCnt.getContentType());
            if (iConteService.ajouter(conte)) {
                System.out.println("Ajouter avec succe");
                return "Conte/AjouterMediascene";
            }
            modelMap.put("Erreur", "Verifier Tout les champs il ya un erreur");
            return "Conte/AjouterConte";
        } catch (Exception e) {
            System.out.println("il ya un erreur contelayoute:" + e);
            modelMap.put("Erreur", e);
            return "Erreur";
        }
    }
}