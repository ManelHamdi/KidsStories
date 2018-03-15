package com.example.kidsstories.Controlleurs;

import com.example.kidsstories.Entities.Mediascene;
import com.example.kidsstories.ModelInterfaces.IMediasceneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ControlleurListeMs {

    @Autowired
    private IMediasceneService iMedsService;

    @RequestMapping(value = "/Liste", params = "supprimer", method = RequestMethod.POST)
    public String suppMds(@RequestParam int idMedsn,
                          ModelMap modelMap) {
        Mediascene mds = new Mediascene();
        mds.setIdMediascene(idMedsn);
        if (iMedsService.deleteMs(mds)) {
            int idCnt = iMedsService.maxIdConte();
            List<Mediascene> lstMscene = iMedsService.ListMs(idCnt);
            modelMap.put("titre", iMedsService.getCntMeds(iMedsService.maxIdConte()).getTitre());
            modelMap.put("imgCnt", iMedsService.getCntMeds(iMedsService.maxIdConte()).getImgconte());
            modelMap.put("Listms", lstMscene);
            return "Conte/ListeConte";
        } else {
            return "Erreur";
        }
    }
}
