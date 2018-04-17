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
import org.springframework.web.bind.annotation.PostMapping;
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
public class ControllerGererConte {

    @Autowired
    private IConteService iConteService;

    @Autowired
    private IMediasceneService iMediasceneService;

    @Autowired
    private IQuestionService iQuestionService;

    @Autowired
    private ICategorieService iCategorieService;

    @RequestMapping(value = "/GererConte", params = "index", method = RequestMethod.POST)
    public String GererCnt(@RequestParam int idAdmin,
                           ModelMap modelMap) {
        List<Conte> lstCnt = iConteService.ListCnt(idAdmin);
        modelMap.put("ListCnt", lstCnt);
        modelMap.put("idAdmin", idAdmin);
        return "Conte/GererConte";
    }

    @RequestMapping(value = "/GererConte", params = "logout", method = RequestMethod.POST)
    public String LogOut() {
        return "Admin/AuthAdmin";
    }


    @PostMapping("/CreerConte")
    public String CreerCnt(@RequestParam String titre,
                           @RequestParam MultipartFile imgCnt,
                           ModelMap modelMap,
                           @RequestParam int idAdmin) {


        try {
            Conte conte = new Conte();
            conte.setTitre(titre);
            conte.setImgconte(imgCnt.getBytes());
            conte.setIdAdmin(idAdmin);
            String type = imgCnt.getContentType().split("/")[0];
            String path = imgCnt.getOriginalFilename();
            if (type.equals("image")) {
                /* if(type.equals("audio")){*/
                System.out.println("It's an image");
            } else {
                modelMap.put("Erreur", "it's not an image");
                return "Conte/GererConte";
            }
            System.out.println("get request method " + imgCnt.getContentType());

            InputStream in = new ByteArrayInputStream(imgCnt.getBytes());
            BufferedImage bimg = ImageIO.read(in);


            // Image img= (Image) imgCnt;
            Image newImage = bimg.getScaledInstance(180, 180, Image.SCALE_DEFAULT);

            //***************convert to bytes***********
            InputStream inn = new ByteArrayInputStream(imgCnt.getBytes());
            BufferedImage bbbImage = new BufferedImage(
                    newImage.getWidth(null), newImage.getHeight(null),
                    BufferedImage.TYPE_INT_RGB);
            Graphics2D g = bbbImage.createGraphics();
            g.drawImage(newImage, 0, 0, null);
            g.dispose();


            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bbbImage, "jpg", baos);
            byte[] imageInByte = baos.toByteArray();
            conte.setImgconte(imageInByte);
            if (iConteService.ajouter(conte)) {
                System.out.println("Ajouter avec succe");
                List<Conte> lstCnt = iConteService.ListCnt(idAdmin);
                modelMap.put("ListCnt", lstCnt);
                modelMap.put("idAdmin", idAdmin);
                return "Conte/GererConte";
            }
            modelMap.put("Erreur", "Verifier Tout les champs il ya un erreur");
            return "Conte/GererConte";
        } catch (Exception e) {
            System.out.println("il ya un erreur contelayoute:" + e);
            modelMap.put("Erreur", e);
            return "Erreur";
        }

    }

    @RequestMapping(value = "/GererConte", params = "gererMs", method = RequestMethod.POST)
    public String AjouterMsCnt(@RequestParam int idConte,
                               @RequestParam int idAdmin,
                               ModelMap modelMap) {
        Conte cnt = iConteService.findById(idConte);
        //**************** Liste Conte ******************
        List<Conte> lstCnt = iConteService.ListCnt(idAdmin);
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

    @RequestMapping(value = "/GererConte", params = "mod", method = RequestMethod.POST)
    public String modCnt(@RequestParam int idConte,
                         @RequestParam int idAdmin,
                         ModelMap modelMap) {
        Conte cnt = iConteService.findById(idConte);
        List<Conte> lstCnt = iConteService.ListCnt(idAdmin);
        modelMap.put("ListCnt", lstCnt);
        modelMap.put("idAdmin", idAdmin);
        modelMap.put("idConte", idConte);
        modelMap.put("titre", cnt.getTitre());
        modelMap.put("img", cnt.getImgconte());
        return "Conte/modifierConte";
    }

    @RequestMapping(value = "/GererConte", params = "anuulerModif", method = RequestMethod.POST)
    public String annulermodCnt(@RequestParam int idAdmin,
                                ModelMap modelMap) {
        List<Conte> lstCnt = iConteService.ListCnt(idAdmin);
        modelMap.put("ListCnt", lstCnt);
        modelMap.put("idAdmin", idAdmin);
        return "Conte/GererConte";
    }

    @RequestMapping(value = "/GererConte", params = "modifier", method = RequestMethod.POST)
    public String ModifierCnt(@RequestParam int idConte,
                              @RequestParam int idAdmin,
                              @RequestParam String titre,
                              @RequestParam MultipartFile newimg,
                              ModelMap modelMap) throws IOException {
        Conte cnt = iConteService.findById(idConte);
        cnt.setTitre(titre);
        //*************** settings **************
        String type = newimg.getContentType().split("/")[0];
        if (type.equals("image")) {
            //****************** transform image *********
            InputStream in = new ByteArrayInputStream(newimg.getBytes());
            BufferedImage bimg = ImageIO.read(in);
            Image newImage = bimg.getScaledInstance(180, 180, Image.SCALE_DEFAULT);
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
            cnt.setImgconte(imageInByte);
        }
        //******************** update ************************
        if (iConteService.updateConte(cnt)) {
            List<Conte> lstCnt = iConteService.ListCnt(idAdmin);
            modelMap.put("ListCnt", lstCnt);
            modelMap.put("idAdmin", idAdmin);
            return "Conte/GererConte";
        } else {
            return "Erreur";
        }

    }

    @RequestMapping(value = "/GererConte", params = "supprimer", method = RequestMethod.POST)
    public String SupprimerCnt(@RequestParam String idConte,
                               @RequestParam int idAdmin,
                               ModelMap modelMap) {
        Conte cnt = iConteService.findById(Integer.parseInt(idConte));
        if (iConteService.deleteConte(cnt)) {
            List<Conte> lstCnt = iConteService.ListCnt(idAdmin);
            modelMap.put("ListCnt", lstCnt);
            modelMap.put("idAdmin", idAdmin);
            return "Conte/GererConte";
        } else {
            return "Erreur";
        }

    }

    @RequestMapping(value = "/GererConte", params = "play", method = RequestMethod.POST)
    public String PlayCnt(ModelMap modelMap,
                          @RequestParam int idConte,
                          @RequestParam int idAdmin) {
        Conte cnt = iConteService.findById(idConte);
        List<Mediascene> lstMs = iMediasceneService.ListMs(idConte);
        modelMap.put("idConte", idConte);
        modelMap.put("idAdmin", idAdmin);
        modelMap.put("listMs", lstMs);
        modelMap.put("titre", cnt.getTitre());
        modelMap.put("img", cnt.getImgconte());
        return "Conte/PlayConte";
    }

    @RequestMapping(value = "/GererConte", params = "gererQuestion", method = RequestMethod.POST)
    public String QuestionCnt(@RequestParam int idConte,
                              @RequestParam int idAdmin,
                              ModelMap modelMap) {
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
}
