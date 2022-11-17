package br.com.notify.med;

import java.io.Serializable;
import java.time.LocalTime;

public class Medicacao implements Serializable {
    /*Declaração de Variaveis*/
    private String nome; // Ex: Amoxicillina
    private String tipo; // Ex: Antibiótico
    private String quantidade; // Ex: 1 comprimido
    private LocalTime horario; // Ex: A cada 12hs
    private String duracao; // Ex: 14 dias

    // Método Construtor Vazio
    public Medicacao () { }

    public Medicacao(String nome, String quantidade){
        this.nome = nome;
        this.quantidade = quantidade;
    }
    // Método Construtor
    public Medicacao(String nome, String tipo, String quantidade, LocalTime horario, String duracao) {
        this.nome = nome;
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.horario = horario;
        this.duracao = duracao;
    }

    /* Métodos Get e SET*/

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(String quantidade) {
        this.quantidade = quantidade;
    }

    public LocalTime getHorario() {
        return horario;
    }

    public void setHorario(LocalTime horario) {
        this.horario = horario;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }
}
