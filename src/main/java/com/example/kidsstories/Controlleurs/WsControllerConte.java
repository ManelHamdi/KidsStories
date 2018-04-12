package com.example.kidsstories.Controlleurs;

import com.example.kidsstories.Entities.Conte;
import com.example.kidsstories.ModelInterfaces.IConteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@Controller
@RestController
@RequestMapping("/Conte")
public class WsControllerConte {

    @Autowired
    private IConteService iConteService;

    @RequestMapping(value = "/allConte/", method = RequestMethod.GET, produces = "application/json")
    public List<Conte> lstConte() {
        try {
            return iConteService.listAllConte();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
