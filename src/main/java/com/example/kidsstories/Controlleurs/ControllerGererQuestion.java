package com.example.kidsstories.Controlleurs;

import com.example.kidsstories.Entities.Categories;
import com.example.kidsstories.Entities.Conte;
import com.example.kidsstories.Entities.Mediascene;
import com.example.kidsstories.Entities.Question;
import com.example.kidsstories.ModelInterfaces.ICategorieService;
import com.example.kidsstories.ModelInterfaces.IConteService;
import com.example.kidsstories.ModelInterfaces.IMediasceneService;
import com.example.kidsstories.ModelInterfaces.IQuestionService;
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
public class ControllerGererQuestion {

    @Autowired
    private IQuestionService iQuestionService;

    @Autowired
    private IConteService iConteService;

    @Autowired
    private IMediasceneService iMediasceneService;

    @Autowired
    private ICategorieService iCategorieService;

    @RequestMapping(value = "/GererQuestion", params = "ajouterQs", method = RequestMethod.POST)
    public String GererQs(@RequestParam int idAdmin,
                          @RequestParam int idConte,
                          @RequestParam String titreQs,
                          @RequestParam int categorie,
                          @RequestParam String reponseQs,
                          @RequestParam int meds,
                          @RequestParam MultipartFile imgPage,
                          ModelMap modelMap) {
        try {

            Question question = new Question();
            question.setTitre(titreQs);
            question.setIdConte(idConte);
            question.setIdCategories(categorie);
            question.setReponse(reponseQs);
            question.setIdMediascene(meds);
            String type = imgPage.getContentType().split("/")[0];
            if (type.equals("image")) {
                System.out.println("It's an image");
            } else {
                modelMap.put("Erreur", "it's not an image try again");
                Conte cnt = iConteService.findById(idConte);
                List<Conte> lstcnt = iConteService.ListCnt(idAdmin);
                List<Question> lstQs = iQuestionService.ListQs(idConte);
                List<Mediascene> lstMs = iMediasceneService.ListMs(idConte);
                List<Categories> lstCat = iCategorieService.listCat();
                modelMap.put("idConte", idConte);
                modelMap.put("idAdmin", idAdmin);
                modelMap.put("titre", cnt.getTitre());
                modelMap.put("img", cnt.getImgconte());
                modelMap.put("lstQs", lstQs);
                modelMap.put("listMs", lstMs);
                modelMap.put("ListCnt", lstcnt);
                modelMap.put("LstCat", lstCat);
                return "Conte/GererQuestion";
            }


            InputStream in = new ByteArrayInputStream(imgPage.getBytes());
            BufferedImage bimg = ImageIO.read(in);


            // Image img= (Image) imgCnt;
            Image newImage = bimg.getScaledInstance(200, 200, Image.SCALE_DEFAULT);

            //***************convert to bytes***********
            InputStream inn = new ByteArrayInputStream(imgPage.getBytes());
            BufferedImage bbbImage = new BufferedImage(
                    newImage.getWidth(null), newImage.getHeight(null),
                    BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bbbImage.createGraphics();
            g.drawImage(newImage, 0, 0, null);
            g.dispose();


            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bbbImage, "jpg", baos);
            byte[] imageInByte = baos.toByteArray();
            question.setImage(imageInByte);

            if (iQuestionService.ajouter(question)) {
                System.out.println("Ajouter avec succe");
                Conte cnt = iConteService.findById(idConte);
                List<Conte> lstcnt = iConteService.ListCnt(idAdmin);
                List<Question> lstQs = iQuestionService.ListQs(idConte);
                List<Mediascene> lstMs = iMediasceneService.ListMs(idConte);
                List<Categories> lstCat = iCategorieService.listCat();
                modelMap.put("idConte", idConte);
                modelMap.put("idAdmin", idAdmin);
                modelMap.put("titre", cnt.getTitre());
                modelMap.put("img", cnt.getImgconte());
                modelMap.put("lstQs", lstQs);
                modelMap.put("listMs", lstMs);
                modelMap.put("ListCnt", lstcnt);
                modelMap.put("LstCat", lstCat);
                return "Conte/GererQuestion";
            }
            modelMap.put("Erreur", "Verifier Tout les champs il ya un erreur");
            return "Conte/GererConte";
        } catch (Exception e) {
            System.out.println("il ya un erreur contelayoute:" + e);
            modelMap.put("Erreur", e);
            return "Erreur";
        }
    }

    @RequestMapping(value = "/GererQuestion", params = "supprimer", method = RequestMethod.POST)
    public String SupprimerQs(@RequestParam int idConte,
                              @RequestParam int idAdmin,
                              @RequestParam int idQs,
                              ModelMap modelMap) {
        Question qs = iQuestionService.findQbyId(idQs);
        if (iQuestionService.deleteQs(qs)) {
            Conte cnt = iConteService.findById(idConte);
            modelMap.put("idConte", idConte);
            List<Conte> lstcnt = iConteService.ListCnt(idAdmin);
            List<Question> lstQs = iQuestionService.ListQs(idConte);
            List<Mediascene> lstMs = iMediasceneService.ListMs(idConte);
            List<Categories> lstCat = iCategorieService.listCat();
            modelMap.put("idAdmin", idAdmin);
            modelMap.put("titre", cnt.getTitre());
            modelMap.put("img", cnt.getImgconte());
            modelMap.put("lstQs", lstQs);
            modelMap.put("listMs", lstMs);
            modelMap.put("ListCnt", lstcnt);
            modelMap.put("LstCat", lstCat);
            return "Conte/GererQuestion";
        } else {
            return "Erreur";
        }

    }

    @RequestMapping(value = "/GererQuestion", params = "modi", method = RequestMethod.POST)
    public String redModQs(@RequestParam int idConte,
                           @RequestParam int idAdmin,
                           @RequestParam int idQs,
                           ModelMap modelMap) {
        Question question = iQuestionService.findQbyId(idQs);
        Categories categories = iCategorieService.findCbyId(question.getIdCategories());
        Mediascene mediascene = iMediasceneService.findById(question.getIdMediascene());
        modelMap.put("imgQs", question.getImage());
        modelMap.put("titreQs", question.getTitre());
        modelMap.put("reponse", question.getReponse());
        modelMap.put("idCat", categories.getIdCategories());
        modelMap.put("typeCat", categories.getTypeCategories());
        modelMap.put("idMs", mediascene.getIdMediascene());
        modelMap.put("numP", mediascene.getNumOrdre());
        //modelMap.put("categ", question.getIdCategories());
        Conte cnt = iConteService.findById(idConte);
        modelMap.put("idConte", idConte);
        List<Conte> lstcnt = iConteService.ListCnt(idAdmin);
        List<Question> lstQs = iQuestionService.ListQs(idConte);
        List<Mediascene> lstMs = iMediasceneService.ListMs(idConte);
        List<Categories> lstCat = iCategorieService.listCat();
        modelMap.put("idAdmin", idAdmin);
        modelMap.put("titre", cnt.getTitre());
        modelMap.put("img", cnt.getImgconte());
        modelMap.put("lstQs", lstQs);
        modelMap.put("listMs", lstMs);
        modelMap.put("ListCnt", lstcnt);
        modelMap.put("LstCat", lstCat);
        modelMap.put("idQs", idQs);
        return "Conte/modifierQuestion";
    }

    @RequestMapping(value = "/GererQuestion", params = "anuulerModif", method = RequestMethod.POST)
    public String annulermodQs(@RequestParam int idAdmin,
                               @RequestParam int idConte,
                               ModelMap modelMap) {
        Conte cnt = iConteService.findById(idConte);
        modelMap.put("idConte", idConte);
        List<Conte> lstcnt = iConteService.ListCnt(idAdmin);
        modelMap.put("ListCnt", lstcnt);
        List<Question> lstQs = iQuestionService.ListQs(idConte);
        List<Mediascene> lstMs = iMediasceneService.ListMs(idConte);
        List<Categories> lstCat = iCategorieService.listCat();
        modelMap.put("idAdmin", idAdmin);
        modelMap.put("titre", cnt.getTitre());
        modelMap.put("img", cnt.getImgconte());
        modelMap.put("lstQs", lstQs);
        modelMap.put("listMs", lstMs);
        modelMap.put("LstCat", lstCat);
        return "Conte/GererQuestion";
    }

    @RequestMapping(value = "/GererQuestion", params = "modifier", method = RequestMethod.POST)
    public String modQs(@RequestParam int idAdmin,
                        @RequestParam int idConte,
                        @RequestParam int idQs,
                        @RequestParam String titre,
                        @RequestParam int categorie,
                        @RequestParam String reponse,
                        @RequestParam int meds,
                        @RequestParam MultipartFile newimg,
                        ModelMap modelMap) throws IOException {

        Question question = iQuestionService.findQbyId(idQs);
        question.setTitre(titre);
        question.setIdConte(idConte);
        question.setIdCategories(categorie);
        question.setReponse(reponse);
        question.setIdMediascene(meds);
        String type = newimg.getContentType().split("/")[0];
        if (type.equals("image")) {
            //****************** transform image *********
            InputStream in = new ByteArrayInputStream(newimg.getBytes());
            BufferedImage bimg = ImageIO.read(in);
            Image newImage = bimg.getScaledInstance(200, 200, Image.SCALE_DEFAULT);
            //***************convert to bytes***********
            BufferedImage bufferedImage = new BufferedImage(
                    newImage.getWidth(null), newImage.getHeight(null),
                    BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bufferedImage.createGraphics();
            g.drawImage(newImage, 0, 0, null);
            g.dispose();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", baos);
            byte[] imageInByte = baos.toByteArray();
            //*************** set image *************
            question.setImage(imageInByte);
        }
        /*
        //******************** update ************************
        if (iConteService.updateConte(cnt)) {
            List<Conte> lstCnt = iConteService.ListCnt(idAdmin);
            modelMap.put("ListCnt", lstCnt);
            modelMap.put("idAdmin", idAdmin);
            return "Conte/GererConte";
        } else {
            return "Erreur";
        }
         */
        if (iQuestionService.updateQs(question)) {
            //general return
            List<Conte> lstcnt = iConteService.ListCnt(idAdmin);
            modelMap.put("ListCnt", lstcnt);
            Conte cnt = iConteService.findById(idConte);
            modelMap.put("idConte", idConte);
            List<Question> lstQs = iQuestionService.ListQs(idConte);
            List<Mediascene> lstMs = iMediasceneService.ListMs(idConte);
            List<Categories> lstCat = iCategorieService.listCat();
            modelMap.put("idAdmin", idAdmin);
            modelMap.put("titre", cnt.getTitre());
            modelMap.put("img", cnt.getImgconte());
            modelMap.put("lstQs", lstQs);
            modelMap.put("listMs", lstMs);
            modelMap.put("LstCat", lstCat);
            return "Conte/GererQuestion";
        } else {
            return "Erreur";
        }

    }

}
