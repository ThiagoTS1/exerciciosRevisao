package br.com.vcriquinho.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Cliente {
    protected String nome;
    protected String email;
    protected List<Conta> contas;
    
    public Cliente(String nome, String email) {
        this.nome = nome;
        this.email = email;
        this.contas = new ArrayList<>();
    }
    
    public abstract String getDocumento();
    public abstract String getTipoCliente();
    
    public void adicionarConta(Conta conta) {
        if (contas.isEmpty()) {
            contas.add(conta);
        } else {
            contas.add(conta);
        }
    }
    
    public void removerConta(Conta conta) {
        if (contas.size() > 1) {
            contas.remove(conta);
        } else {
            throw new IllegalStateException("Cliente deve ter pelo menos uma conta");
        }
    }
    
    public List<Conta> getContas() {
        return new ArrayList<>(contas);
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public String toString() {
        return "Cliente{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", tipo='" + getTipoCliente() + '\'' +
                ", documento='" + getDocumento() + '\'' +
                ", contas=" + contas.size() +
                '}';
    }
} 