package com.example.kidsstories.Controlleurs;

import com.example.kidsstories.Entities.Conte;
import com.example.kidsstories.Entities.Mediascene;
import com.example.kidsstories.ModelInterfaces.IConteService;
import com.example.kidsstories.ModelInterfaces.IMediasceneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
public class ControllerGererMediascene {

    @Autowired
    private IConteService iConteService;

    @Autowired
    private IMediasceneService iMediasceneService;

    @RequestMapping(value = "/GererMs", params = "ajouterMs", method = RequestMethod.POST)
    public String CreerMs(@RequestParam MultipartFile imgPage,
                          @RequestParam MultipartFile audioPage,
                          @RequestParam String txtPage,
                          @RequestParam int idConte,
                          ModelMap modelMap,
                          @RequestParam int idAdmin) {
        try {
            Mediascene mediascene = new Mediascene();
            mediascene.setTexte(txtPage);
            mediascene.setIdConte(idConte);
            mediascene.setAudio(audioPage.getBytes());
            mediascene.setNumOrdre(iMediasceneService.NumOrd(idConte));
            String type = imgPage.getContentType().split("/")[0];
            String typeaudio = audioPage.getContentType().split("/")[0];
            if (typeaudio.equals("audio")) {
                mediascene.setAudio(audioPage.getBytes());
            } else {
                modelMap.put("Erreur", "it's not an audio file");
                return "Erreur";
            }
            if (type.equals("image")) {
                System.out.println("It's an image");
            } else {
                modelMap.put("Erreur", "it's not an image");
                return "Erreur";
            }
            InputStream in = new ByteArrayInputStream(imgPage.getBytes());
            BufferedImage bimg = ImageIO.read(in);
            Image newImage = bimg.getScaledInstance(400, 400, Image.SCALE_DEFAULT);
            //***************convert to bytes***********
            InputStream inn = new ByteArrayInputStream(imgPage.getBytes());
            BufferedImage bbbImage = new BufferedImage(
                    newImage.getWidth(null), newImage.getHeight(null),
                    BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = bbbImage.createGraphics();
            g.drawImage(newImage, 0, 0, null);
            g.dispose();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bbbImage, "jpg", baos);
            byte[] imageInByte = baos.toByteArray();
            mediascene.setImg(imageInByte);
            if (iMediasceneService.ajouter(mediascene)) {
                System.out.println("Ajouter avec succe");
                List<Conte> lstCnt = iConteService.ListCnt(idAdmin);
                Conte cnt = iConteService.findById(idConte);
                //**************** Liste Mediascene *************
                List<Mediascene> lstMs = iMediasceneService.ListMs(idConte);
                //************************  ***********************
                modelMap.put("ListCnt", lstCnt);
                modelMap.put("idConte", idConte);
                modelMap.put("listMs", lstMs);
                modelMap.put("idAdmin", idAdmin);
                modelMap.put("titre", cnt.getTitre());
                modelMap.put("img", cnt.getImgconte());
                return "Conte/GererMediascene";
            }
            modelMap.put("Erreur", "Verifier Tout les champs il ya un erreur");
            List<Conte> lstCnt = iConteService.ListCnt(idAdmin);
            Conte cnt = iConteService.findById(idConte);
            //**************** Liste Mediascene *************
            List<Mediascene> lstMs = iMediasceneService.ListMs(idConte);
            //************************  ***********************
            modelMap.put("ListCnt", lstCnt);
            modelMap.put("idConte", idConte);
            modelMap.put("listMs", lstMs);
            modelMap.put("idAdmin", idAdmin);
            modelMap.put("titre", cnt.getTitre());
            modelMap.put("img", cnt.getImgconte());
            return "Conte/GererMediascene";
        } catch (Exception e) {
            System.out.println("il ya un erreur contelayoute:" + e);
            modelMap.put("Erreur", e);
            return "Erreur";
        }

    }

    @RequestMapping(value = "/GererMs", params = "annulerModif", method = RequestMethod.POST)
    public String AnullerModifMs(@RequestParam int idConte,
                                 @RequestParam int idAdmin,
                                 ModelMap modelMap) {
        Conte cnt = iConteService.findById(idConte);
        modelMap.put("titre", cnt.getTitre());
        modelMap.put("img", cnt.getImgconte());
        //**************** Liste Conte ******************
        List<Conte> lstCnt = iConteService.ListCnt(idAdmin);
        //**************** Liste Mediascene *************
        List<Mediascene> lstMs = iMediasceneService.ListMs(idConte);
        //************************  ***********************
        modelMap.put("ListCnt", lstCnt);
        modelMap.put("idConte", idConte);
        modelMap.put("listMs", lstMs);
        modelMap.put("idAdmin", idAdmin);
        return "Conte/GererMediascene";
    }

    @RequestMapping(value = "/GererMs", params = "modifier", method = RequestMethod.POST)
    public String ModifierMs(@RequestParam int idConte,
                             @RequestParam int idAdmin,
                             @RequestParam int idMs,
                             @RequestParam String txtPage,
                             @RequestParam MultipartFile newImg,
                             @RequestParam MultipartFile newAudio,
                             ModelMap modelMap) throws IOException {

        Mediascene ms = iMediasceneService.findById(idMs);
        ms.setTexte(txtPage);
        //*************** settings **************
        //*************** settings image **************
        String type = newImg.getContentType().split("/")[0];
        if (type.equals("image")) {
            //****************** transform image *********
            InputStream in = new ByteArrayInputStream(newImg.getBytes());
            BufferedImage bimg = ImageIO.read(in);
            Image newImage = bimg.getScaledInstance(400, 400, Image.SCALE_DEFAULT);
            //***************convert to bytes***********
            BufferedImage bufferedImage = new BufferedImage(
                    newImage.getWidth(null), newImage.getHeight(null),
                    BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = bufferedImage.createGraphics();
            g.drawImage(newImage, 0, 0, null);
            g.dispose();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", baos);
            byte[] imageInByte = baos.toByteArray();
            //*************** set image *************
            ms.setImg(imageInByte);
        }

//*************** cheking audio audio **************
        String typea = newAudio.getContentType().split("/")[0];
        if (typea.equals("audio")) {
            //*************** set image *************
            ms.setAudio(newAudio.getBytes());
        }


        //******************** update ************************
        if (iMediasceneService.updateMs(ms)) {
            //************************ general return ***********************
            Conte cnt = iConteService.findById(idConte);
            List<Conte> lstCnt = iConteService.ListCnt(idAdmin);
            List<Mediascene> lstMs = iMediasceneService.ListMs(idConte);
            //************************  ***********************
            modelMap.put("ListCnt", lstCnt);
            modelMap.put("idConte", idConte);
            modelMap.put("listMs", lstMs);
            modelMap.put("idAdmin", idAdmin);
            modelMap.put("titre", cnt.getTitre());
            modelMap.put("img", cnt.getImgconte());
            return "Conte/GererMediascene";
        } else {
            return "Erreur";
        }


    }


    @RequestMapping(value = "/GererMs", params = "supprimer", method = RequestMethod.POST)
    public String SupprimerMs(@RequestParam int idConte,
                              ModelMap modelMap,
                              @RequestParam int idMs,
                              @RequestParam int idAdmin) {
        Mediascene mds = new Mediascene();
        mds.setIdMediascene(idMs);
        if (iMediasceneService.deleteMs(mds)) {
            List<Conte> lstCnt = iConteService.ListCnt(idAdmin);
            Conte cnt = iConteService.findById(idConte);
            //**************** Liste Mediascene *************
            List<Mediascene> lstMs = iMediasceneService.ListMs(idConte);
            //************************  ***********************
            modelMap.put("ListCnt", lstCnt);
            modelMap.put("idConte", idConte);
            modelMap.put("listMs", lstMs);
            modelMap.put("idAdmin", idAdmin);
            modelMap.put("titre", cnt.getTitre());
            modelMap.put("img", cnt.getImgconte());
            return "Conte/GererMediascene";
        } else {
            return "Erreur";
        }


    }

    @RequestMapping(value = "/GererMs", params = "modi", method = RequestMethod.POST)
    public String ModifierMs(@RequestParam int idConte,
                             ModelMap modelMap,
                             @RequestParam int idMs,
                             @RequestParam int idAdmin) {
        List<Conte> lstCnt = iConteService.ListCnt(idAdmin);
        Conte cnt = iConteService.findById(idConte);
        //**************** Liste Mediascene *************
        List<Mediascene> lstMs = iMediasceneService.ListMs(idConte);
        //************************  ***********************
        Mediascene ms = iMediasceneService.findById(idMs);
        //*********************** **********************
        modelMap.put("imgMs", ms.getImg());
        modelMap.put("audio", ms.getAudio());
        modelMap.put("texte", ms.getTexte());

        modelMap.put("ListCnt", lstCnt);
        modelMap.put("idMs", idMs);
        modelMap.put("idConte", idConte);
        modelMap.put("listMs", lstMs);
        modelMap.put("idAdmin", idAdmin);
        modelMap.put("titre", cnt.getTitre());
        modelMap.put("img", cnt.getImgconte());
        return "Conte/modifierMediascene";


    }




   /* @GetMapping
    public String GererMediascene(ModelMap modelMap,
                                  @RequestParam int idConte,
                                  @RequestParam int idAdmin){
        //************************ general return ***********************
        Conte cnt = iConteService.findById(idConte);
        List<Conte> lstCnt = iConteService.ListCnt(idAdmin);
        List<Mediascene> lstMs = iMediasceneService.ListMs(idConte);
        //************************  ***********************
        modelMap.put("ListCnt", lstCnt);
        modelMap.put("idConte", idConte);
        modelMap.put("listMs", lstMs);
        modelMap.put("idAdmin", idAdmin);
        modelMap.put("titre", cnt.getTitre());
        modelMap.put("img", cnt.getImgconte());
        return "Conte/GererMediascene";
    }*/


}
