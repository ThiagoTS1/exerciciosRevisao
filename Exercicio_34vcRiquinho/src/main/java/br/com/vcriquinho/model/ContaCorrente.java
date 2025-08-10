package br.com.vcriquinho.model;

public class ContaCorrente extends Conta {
    
    public ContaCorrente(String numero, Cliente titular) {
        super(numero, titular);
    }
    
    @Override
    public double calcularRendimento(int dias) {
        // Conta corrente não gera rendimento
        return 0.0;
    }
    
    @Override
    public double calcularTaxaServico(double rendimento) {
        // Conta corrente não gera rendimento, logo não há taxa de serviço
        return 0.0;
    }
    
    @Override
    public String getTipoConta() {
        return "Corrente";
    }
} 