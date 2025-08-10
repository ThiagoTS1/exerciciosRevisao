package br.com.vcriquinho.model;

public class ProdutoRendaFixa implements ProdutoInvestimento {
    private String nome;
    private String descricao;
    private double rendimentoMensal;
    private int periodoCarencia; // em dias
    
    public ProdutoRendaFixa(String nome, String descricao, double rendimentoMensal, int periodoCarencia) {
        this.nome = nome;
        this.descricao = descricao;
        this.rendimentoMensal = rendimentoMensal;
        this.periodoCarencia = periodoCarencia;
    }
    
    @Override
    public String getNome() {
        return nome;
    }
    
    @Override
    public String getDescricao() {
        return descricao;
    }
    
    @Override
    public double calcularRendimento(int dias) {
        if (dias < periodoCarencia) {
            return 0.0; // Não gera rendimento dentro do período de carência
        }
        
        // Converte rendimento mensal para diário e multiplica pelos dias
        double rendimentoDiario = rendimentoMensal / 30.0;
        return rendimentoDiario * dias;
    }
    
    @Override
    public String getTipoProduto() {
        return "Renda Fixa";
    }
    
    public double getRendimentoMensal() {
        return rendimentoMensal;
    }
    
    public void setRendimentoMensal(double rendimentoMensal) {
        this.rendimentoMensal = rendimentoMensal;
    }
    
    public int getPeriodoCarencia() {
        return periodoCarencia;
    }
    
    public void setPeriodoCarencia(int periodoCarencia) {
        this.periodoCarencia = periodoCarencia;
    }
    
    @Override
    public String toString() {
        return "ProdutoRendaFixa{" +
                "nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", rendimentoMensal=" + rendimentoMensal +
                ", periodoCarencia=" + periodoCarencia + " dias" +
                '}';
    }
} 