package com.example.kidsstories.Controlleurs;

import com.example.kidsstories.Entities.Question;
import com.example.kidsstories.ModelInterfaces.IQuestionService;
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

    @RequestMapping(value = "/allQs/{idConte}", method = RequestMethod.GET, produces = "application/json")
    public List<Question> lstQs(@PathVariable("idConte") int idConte) {
        try {
            return iQuestionService.ListQs(idConte);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
