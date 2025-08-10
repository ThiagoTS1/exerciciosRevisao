package br.com.vcriquinho.model;

public interface ProdutoInvestimento {
    String getNome();
    String getDescricao();
    double calcularRendimento(int dias);
    String getTipoProduto();
} 