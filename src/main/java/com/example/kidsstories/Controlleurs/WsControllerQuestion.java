package com.example.kidsstories.Controlleurs;

import com.example.kidsstories.Entities.Question;
import com.example.kidsstories.Entities.Reponse;
import com.example.kidsstories.ModelInterfaces.IQuestionService;
import com.example.kidsstories.ModelInterfaces.IReponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Question")
public class WsControllerQuestion {

    @Autowired
    private IQuestionService iQuestionService;

    @Autowired
    private IReponseService iReponseService;

    @RequestMapping(value = "/allQs/{idConte}", method = RequestMethod.GET, produces = "application/json")
    public List<Question> lstQs(@PathVariable("idConte") int idConte) {
        try {
            return iQuestionService.ListQs(idConte);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/Rs", method = RequestMethod.GET, produces = "application/json")
    public List<Reponse> lstRs() {
        try {
            return iReponseService.lstRep();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
