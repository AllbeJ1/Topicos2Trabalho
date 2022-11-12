package br.com.notify.med;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;


public class Consulta implements Serializable {
    /*Declaração de Variaveis*/
    private String nomeMedico; //Ex: Dra. Fernanda.
    private String motivoConsulta; // Ex: Dor de cabeça, professor não gostou do meu trabalho...
    private String lembrete; //Ex: Levar os exames, pedir a receita de um remédio..
    private String localizacao; //Ex: Rua XYZ, número 1234
    private LocalDate data; //Ex: Data consulta 20/10/2022
    private LocalTime horario; //Ex: 9:30;

    /*
    Obs: Poderiamos ter também representado o horário e data com um unico LocalDateTime,
    porém achamos mais intuitivo trabalhar com variaveis separadas.
    */

    // Método Construtor Vazio
    public Consulta() { }

    // Método Construtor
    public Consulta(String nomeMedico, String motivoConsulta, String lembrete, String localizacao, LocalDate data, LocalTime horario) {
        this.nomeMedico = nomeMedico;
        this.motivoConsulta = motivoConsulta;
        this.lembrete = lembrete;
        this.localizacao = localizacao;
        this.data = data;
        this.horario = horario;
    }

    /* Métodos Get e SET*/

    public String getNomeMedico() {
        return nomeMedico;
    }

    public void setNomeMedico(String nomeMedico) {
        this.nomeMedico = nomeMedico;
    }

    public String getMotivoConsulta() {
        return motivoConsulta;
    }

    public void setMotivoConsulta(String motivoConsulta) {
        this.motivoConsulta = motivoConsulta;
    }

    public String getLembrete() {
        return lembrete;
    }

    public void setLembrete(String lembrete) {
        this.lembrete = lembrete;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }
}
