package tech.danielalves.springdesk.controllers.cliente;

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
import tech.danielalves.springdesk.model.Cliente;
import tech.danielalves.springdesk.repository.ClienteRepository;
import tech.danielalves.springdesk.util.PasswordUtil;
import tech.danielalves.springdesk.util.UploadUtil;

@Controller
@RequestMapping("cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/cadastro")
    public ModelAndView cadastro(Cliente cliente){
        ModelAndView mv = new ModelAndView("cliente/cadastro");
        mv.addObject("usuario", new Cliente());
        mv.addObject("perfils", Perfil.values());
        return mv;
    }

    @PostMapping("/cadastro-cliente")
    public ModelAndView cadastro(@ModelAttribute Cliente cliente, @RequestParam("file") MultipartFile imagem){
        ModelAndView mv = new ModelAndView("cliente/cadastro");
        mv.addObject("usuario", cliente);
        try {
            String caminhoImagem = UploadUtil.fazerUploadImagem(imagem);
            cliente.setImagem(caminhoImagem);
            cliente.setSenha(PasswordUtil.encoder(cliente.getSenha()));
            clienteRepository.save(cliente);
            System.out.println("Cliente salvo com sucesso!");
            return clientesList();
        }catch (Exception e){
            mv.addObject("msgErro", e.getMessage());
            System.out.println("Erro ao salvar cliente. "+e.getMessage());
            return mv;
        }
    }

    @GetMapping("/list-clientes")
    public ModelAndView clientesList(){
        ModelAndView mv = new ModelAndView("cliente/list-cliente");
        mv.addObject("clientes", clienteRepository.findAll());
        return mv;
    }

    @GetMapping("/excluir/{id}")
    public String excluirCliente(@PathVariable("id") Integer id){
        clienteRepository.deleteById(id);
        return "home/index";
    }

    @GetMapping("/inicio")
    public ModelAndView home(){
        ModelAndView mv =  new ModelAndView("home/index");
        return mv;
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Integer id){
        ModelAndView mv =  new ModelAndView("cliente/editar");
        mv.addObject("perfils", Perfil.values());
        mv.addObject("usuario", clienteRepository.findById(id));
        return mv;
    }

    @PostMapping("/editar-cliente")
    public ModelAndView editar(Cliente cliente){
        ModelAndView mv =  new ModelAndView("cliente/editar");
        clienteRepository.save(cliente);
        return clientesList();
    }
    
}
