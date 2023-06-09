package tech.danielalves.springdesk.controllers.tecnico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import tech.danielalves.springdesk.Enums.Perfil;
import tech.danielalves.springdesk.model.Tecnico;
import tech.danielalves.springdesk.repository.TecnicoRepository;
import tech.danielalves.springdesk.util.PasswordUtil;
import tech.danielalves.springdesk.util.UploadUtil;

@Controller
//@RequestMapping("/tecnico")
public class TecnicoController {
    
    @Autowired
    private TecnicoRepository tecnicoRepository;


    @GetMapping("/cadastro-tecnico")
    public ModelAndView cadastro(Tecnico tecnico){
        ModelAndView mv = new ModelAndView("tecnico/cadastro");
        mv.addObject("tecnico", tecnico);
        mv.addObject("perfils", Perfil.values());
        return mv;
    }

    @PostMapping("/salvar-tecnico")
    public ModelAndView cadastro(@ModelAttribute Tecnico tecnico, @RequestParam("file") MultipartFile imagem){
        ModelAndView mv = new ModelAndView("tecnico/cadastro");
        mv.addObject("tecnico", tecnico);
        try {
            String caminhoImagem = UploadUtil.fazerUploadImagem(imagem);
            tecnico.setImagem(caminhoImagem);
            tecnico.setSenha(PasswordUtil.encoder(tecnico.getSenha()));
            tecnicoRepository.save(tecnico);
            System.out.println("Técnico salvo com sucesso!");
            return tecnicosList();
        }catch (Exception e){
            mv.addObject("msgErro", e.getMessage());
            System.out.println("Erro ao salvar técnico. "+e.getMessage());
            return mv;
        }
    }


    @GetMapping("/list-tecnicos")
    public ModelAndView tecnicosList(){
        ModelAndView mv = new ModelAndView("tecnico/tecnico-list");
        mv.addObject("tecnicos", tecnicoRepository.findAll());
        return mv;
    }

    @GetMapping("/home-tecnico")
    public ModelAndView home(){
        ModelAndView mv =  new ModelAndView("home/index");
        return mv;
    }

    @GetMapping("/excluir-tecnico/{id}")
    public String excluirTecnico(@PathVariable("id") Integer id){
        tecnicoRepository.deleteById(id);
        return "tecnico/tecnico-list";
    }

    @GetMapping("/editar-tecnico/{id}")
    public ModelAndView editar(@PathVariable("id") Integer id){
        ModelAndView mv =  new ModelAndView("tecnico/editar");
        mv.addObject("perfils", Perfil.values());
        mv.addObject("tec", tecnicoRepository.findById(id));
        return mv;
    }

    @PostMapping("/editar-tecnico")
    public ModelAndView editar(Tecnico tecnico){
        ModelAndView mv =  new ModelAndView("tecnico/editar");
        tecnicoRepository.save(tecnico);
        return tecnicosList();
    }

}
