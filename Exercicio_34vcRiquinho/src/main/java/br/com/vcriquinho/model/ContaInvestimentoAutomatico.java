package br.com.vcriquinho.model;

import java.util.List;
import java.util.ArrayList;

public class ContaInvestimentoAutomatico extends Conta {
    private static final double TAXA_SERVICO_PF = 0.001; // 0.1% para PF
    private static final double TAXA_SERVICO_PJ = 0.0015; // 0.15% para PJ
    private List<ProdutoInvestimento> produtosInvestidos;
    
    public ContaInvestimentoAutomatico(String numero, Cliente titular) {
        super(numero, titular);
        this.produtosInvestidos = new ArrayList<>();
    }
    
    public void investirEmProduto(ProdutoInvestimento produto, double valor) {
        if (valor <= saldo) {
            produtosInvestidos.add(produto);
            saldo -= valor;
        } else {
            throw new IllegalArgumentException("Saldo insuficiente para investimento");
        }
    }
    
    @Override
    public double calcularRendimento(int dias) {
        double rendimentoTotal = 0.0;
        
        for (ProdutoInvestimento produto : produtosInvestidos) {
            // Verifica se o produto de renda fixa está dentro do período de carência
            if (produto instanceof ProdutoRendaFixa) {
                ProdutoRendaFixa rendaFixa = (ProdutoRendaFixa) produto;
                if (dias < rendaFixa.getPeriodoCarencia()) {
                    continue; // Pula produtos dentro do período de carência
                }
            }
            
            rendimentoTotal += produto.calcularRendimento(dias);
        }
        
        return rendimentoTotal;
    }
    
    @Override
    public double calcularTaxaServico(double rendimento) {
        double taxaServico;
        if (titular.getTipoCliente().equals("PF")) {
            taxaServico = TAXA_SERVICO_PF;
        } else {
            taxaServico = TAXA_SERVICO_PJ;
        }
        return rendimento * taxaServico;
    }
    
    @Override
    public String getTipoConta() {
        return "Investimento Automático";
    }
    
    public List<ProdutoInvestimento> getProdutosInvestidos() {
        return new ArrayList<>(produtosInvestidos);
    }
} 