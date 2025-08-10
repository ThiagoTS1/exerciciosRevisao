package br.com.vcriquinho.model;

public class PessoaFisica extends Cliente {
    private String cpf;
    
    public PessoaFisica(String nome, String email, String cpf) {
        super(nome, email);
        this.cpf = cpf;
    }
    
    @Override
    public String getDocumento() {
        return cpf;
    }
    
    @Override
    public String getTipoCliente() {
        return "PF";
    }
    
    public String getCpf() {
        return cpf;
    }
    
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
    @Override
    public String toString() {
        return "PessoaFisica{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", cpf='" + cpf + '\'' +
                ", contas=" + contas.size() +
                '}';
    }
} 