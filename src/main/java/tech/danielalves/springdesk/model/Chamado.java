package tech.danielalves.springdesk.model;

import java.time.LocalDate;

import javax.persistence.*;

import tech.danielalves.springdesk.Enums.Prioridade;
import tech.danielalves.springdesk.Enums.StatusTicket;

@Entity
public class Chamado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titulo;
    private LocalDate dataAbertura = LocalDate.now();
    private LocalDate dataFechamento;
    private String observacao;
    @Enumerated(EnumType.STRING)
    private StatusTicket status;
    @Enumerated(EnumType.STRING)
    private Prioridade prioridade;

    @ManyToOne
    @JoinColumn(name = "cliente_id_fk")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "tecnico_id_fk")
    private Tecnico tecnico;

    public Chamado(){}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDate dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    public LocalDate getDataFechamento() {
        return dataFechamento;
    }

    public void setDataFechamento(LocalDate dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public StatusTicket getStatus() {
        return status;
    }

    public void setStatus(StatusTicket status) {
        this.status = status;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Prioridade prioridade) {
        this.prioridade = prioridade;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

    @Override
    public String toString() {
        return "Chamado [id=" + id + ", titulo=" + titulo + ", dataAbertura=" + dataAbertura + ", dataFechamento="
                + dataFechamento + ", observacao=" + observacao + ", status=" + status + ", prioridade=" + prioridade
                + ", cliente=" + cliente + ", tecnico=" + tecnico + "]";
    }
    
   
   
    
}
