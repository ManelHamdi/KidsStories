package com.example.kidsstories.Controlleurs;

import com.example.kidsstories.Entities.Mediascene;
import com.example.kidsstories.ModelInterfaces.IMediasceneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@Controller
public class ControlleurMediascene {

    @Autowired
    private IMediasceneService iMedsService;

    // *********** Button Suivant ****************
    @RequestMapping(value = "/NouvellePage" ,params = "suivant", method = RequestMethod.POST)
    public String medsSbtn(@RequestParam int nbrPage,
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
            mediascene.setIdConte(iMedsService.maxIdConte());

        // Teste if audio file
            if(typeaudio.equals("audio")){
                mediascene.setAudio(audioPage.getBytes());
            } else {
                modelMap.put("Erreur", "it's not an audio file");
                return "Conte/AjouterMediascene";
            }
        // Teste if image file
            if(typeimg.equals("image")){
                mediascene.setImg(imgPage.getBytes());
            } else {
                modelMap.put("Erreur", "it's not an image");
                return "Conte/AjouterMediascene";
            }
            if (iMedsService.ajouter(mediascene)){
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



    // *********** Button Terminer ****************
    @RequestMapping(value = "/NouvellePage" ,params = "terminer", method = RequestMethod.POST)
    public String medsTbtn(@RequestParam int nbrPage,
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
            mediascene.setIdConte(iMedsService.maxIdConte());

            // Teste if audio file
            if(typeaudio.equals("audio")){
                mediascene.setAudio(audioPage.getBytes());
            } else {
                modelMap.put("Erreur", "it's not an audio file");
                return "Conte/AjouterMediascene";
            }
            // Teste if image file
            if(typeimg.equals("image")){
                mediascene.setImg(imgPage.getBytes());
            } else {
                modelMap.put("Erreur", "it's not an image");
                return "Conte/AjouterMediascene";
            }
            if (iMedsService.ajouter(mediascene)){
                int idCnt=iMedsService.maxIdConte();
                List<Mediascene> lstMscene=iMedsService.ListMs(idCnt);
                modelMap.put("titre",iMedsService.getCntMeds(iMedsService.maxIdConte()).getTitre());
                modelMap.put("imgCnt",iMedsService.getCntMeds(iMedsService.maxIdConte()).getImgconte());
                modelMap.put("Listms",lstMscene);
                return "Conte/ListeConte";
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
