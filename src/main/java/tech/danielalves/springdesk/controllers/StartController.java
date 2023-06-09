package tech.danielalves.springdesk.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import tech.danielalves.springdesk.model.Cliente;
import tech.danielalves.springdesk.repository.ChamadoRepository;
import tech.danielalves.springdesk.repository.ClienteRepository;


@Controller
public class StartController {


    @Autowired
    private ChamadoRepository chamadoRepository;

    @GetMapping()
    public ModelAndView start(){
        ModelAndView mv = new ModelAndView("home/index");
        mv.addObject("chamadosList", chamadoRepository.findAllChamados());
        return mv;
    }


}
