package tech.danielalves.springdesk.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tech.danielalves.springdesk.Enums.Perfil;
import tech.danielalves.springdesk.model.Cliente;
import tech.danielalves.springdesk.model.Tecnico;
import tech.danielalves.springdesk.repository.ClienteRepository;
import tech.danielalves.springdesk.repository.TecnicoRepository;
import tech.danielalves.springdesk.util.PasswordUtil;

@Service
public class InjectDB {


    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    public void injectDB(){
        Tecnico tecnico1 = new Tecnico(null, "Daniel", "daniel@gmail.com",
         PasswordUtil.encoder("123456"), null, Perfil.TECNICO);

        Cliente cliente1 = new Cliente(null, "Tereza", "tereza@mail.com", PasswordUtil.encoder("123456789"),null, 
        Perfil.CLIENTE);

        clienteRepository.saveAll(Arrays.asList(cliente1));
         tecnicoRepository.saveAll(Arrays.asList(tecnico1));
    }
    
}
