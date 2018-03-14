package com.example.kidsstories.Controlleurs;

import com.example.kidsstories.Entities.Mediascene;
import com.example.kidsstories.ModelInterfaces.IMediasceneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class ControlleurListeMs {

    @Autowired
    private IMediasceneService iMedsService;

    public  String Lms(ModelMap modelMap){
        int idCnt=iMedsService.maxIdConte();
        List<Mediascene> lstMscene=iMedsService.ListMs(idCnt);
        modelMap.put("Listms",lstMscene);
        return "";
    }
}
