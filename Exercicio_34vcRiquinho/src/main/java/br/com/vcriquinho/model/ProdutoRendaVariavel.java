package br.com.vcriquinho.model;

public class ProdutoRendaVariavel implements ProdutoInvestimento {
    private String nome;
    private String descricao;
    private double rendimentoMensalEsperado;
    
    public ProdutoRendaVariavel(String nome, String descricao, double rendimentoMensalEsperado) {
        this.nome = nome;
        this.descricao = descricao;
        this.rendimentoMensalEsperado = rendimentoMensalEsperado;
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
        // Converte rendimento mensal esperado para diário e multiplica pelos dias
        double rendimentoDiario = rendimentoMensalEsperado / 30.0;
        return rendimentoDiario * dias;
    }
    
    @Override
    public String getTipoProduto() {
        return "Renda Variável";
    }
    
    public double getRendimentoMensalEsperado() {
        return rendimentoMensalEsperado;
    }
    
    public void setRendimentoMensalEsperado(double rendimentoMensalEsperado) {
        this.rendimentoMensalEsperado = rendimentoMensalEsperado;
    }
    
    @Override
    public String toString() {
        return "ProdutoRendaVariavel{" +
                "nome='" + nome + '\'' +
                ", descricao='" + nome + '\'' +
                ", rendimentoMensalEsperado=" + rendimentoMensalEsperado +
                '}';
    }
} 