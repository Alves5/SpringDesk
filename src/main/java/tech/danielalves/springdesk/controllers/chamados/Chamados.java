package tech.danielalves.springdesk.controllers.chamados;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import tech.danielalves.springdesk.Enums.Prioridade;
import tech.danielalves.springdesk.Enums.StatusTicket;
import tech.danielalves.springdesk.model.Chamado;
import tech.danielalves.springdesk.repository.ChamadoRepository;
import tech.danielalves.springdesk.repository.TecnicoRepository;

@Controller
//@RequestMapping("/ticket")
public class Chamados {

    @Autowired
    private TecnicoRepository tecnicoRepository;
    
    @Autowired
    private ChamadoRepository chamadoRepository;

    @GetMapping("/")
    public ModelAndView chamadoHome(){
        ModelAndView mv = new ModelAndView("home/index");
        return mv;
    }


    @GetMapping("/cadastro-chamado")
    public ModelAndView ticket(Chamado chamado){
        ModelAndView mv = new ModelAndView("chamados/ticket");
        mv.addObject("statusChamados", StatusTicket.values());
        mv.addObject("Prioridade", Prioridade.values());
        mv.addObject("tecnicos", tecnicoRepository.findAll());
        mv.addObject("tickets", chamado);
        return mv;
    }

    @PostMapping("/new-ticket")
    public ModelAndView newticket(Chamado chamado){
        chamadoRepository.save(chamado);
        return chamadoHome();
    }
}
