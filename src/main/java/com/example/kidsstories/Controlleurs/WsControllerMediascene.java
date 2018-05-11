package com.example.kidsstories.Controlleurs;

import com.example.kidsstories.Entities.Mediascene;
import com.example.kidsstories.ModelInterfaces.IMediasceneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/Mediascene")
public class WsControllerMediascene {

    @Autowired
    private IMediasceneService iMediasceneService;

    @RequestMapping(value = "/allMs/{idConte}", method = RequestMethod.GET, produces = "application/json")
    public List<Mediascene> lstConte(@PathVariable("idConte") int idConte) {
        try {
            return iMediasceneService.ListMs(idConte);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/allMsl/{idConte}/{idMs}", method = RequestMethod.GET, produces = "application/json")
    public List<Mediascene> lstMsL(@PathVariable("idConte") int idConte,
                                   @PathVariable("idMs") int idMs) {
        try {
            return iMediasceneService.ListMsLimited(idConte, idMs);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping(value = "/maxMs/{idConte}", method = RequestMethod.GET, produces = "application/json")
    public int maxMs(@PathVariable("idConte") int idConte) {
        try {
            return iMediasceneService.maxMs(idConte);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

}
