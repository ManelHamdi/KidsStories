package com.example.kidsstories.Controlleurs;

import com.example.kidsstories.Entities.Mediascene;
import com.example.kidsstories.ModelInterfaces.IMediasceneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Controller
public class ControlleurMediascene {

    @Autowired
    private IMediasceneService iMedsService;

    @PostMapping("/NouvellePage")
    public String meds(@RequestParam int nbrPage,
                       @RequestParam MultipartFile imgPage,
                       @RequestParam String txtPage,
                       @RequestParam MultipartFile audioPage,
                       ModelMap modelMap){
        String typeimg = imgPage.getContentType().split("/")[0];
        String typeaudio = audioPage.getContentType().split("/")[0];
        try {
            Mediascene mediascene = new Mediascene();
            mediascene.setNumOrdre(nbrPage);
            mediascene.setTexte(txtPage);
            System.out.println("controlleurmediascene nbrp : "+nbrPage);
            System.out.println("controlleurmediascene img : "+ imgPage.getContentType());
            System.out.println("controlleurmediascene text : "+txtPage);
            System.out.println("controlleurmediascene audio : "+audioPage.getContentType());
            System.out.println("controlleurmediascene idc : "+iMedsService.maxIdConte());
            mediascene.setIdConte(iMedsService.maxIdConte());
            mediascene.setType("t");

        // Teste if audio file
            if(typeaudio.equals("audio")){
                System.out.println("It's an audio file");
                mediascene.setAudio(audioPage.getBytes());
            } else {
                modelMap.put("Erreur", "it's not an audio file");
                return "Conte/AjouterMediascene";
            }
        // Teste if image file
            if(typeimg.equals("image")){
                System.out.println("It's an image");
                mediascene.setImg(imgPage.getBytes());
            } else {
                modelMap.put("Erreur", "it's not an image");
                return "Conte/AjouterMediascene";
            }
            if (iMedsService.ajouter(mediascene)){
                System.out.println("Ajouter avec succe");
                return "Conte/AjouterMediascene";
            }
            modelMap.put("Erreur", "Verifier Tout les champs il ya un erreur");
            return "Conte/AjouterMediascene";
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("il ya un erreur contelayoute:"+e);
            modelMap.put("Erreur", e);
            return "Erreur";
        }
    }
}
