package br.com.vcriquinho.model;

public class ContaCDI extends Conta {
    private static final double TAXA_CDI_DIARIA = 0.01; // 1% ao dia (1/30 do CDI)
    private static final double TAXA_SERVICO = 0.0007; // 0.07%
    
    public ContaCDI(String numero, Cliente titular) {
        super(numero, titular);
    }
    
    @Override
    public double calcularRendimento(int dias) {
        double rendimentoDiario = saldo * TAXA_CDI_DIARIA;
        return rendimentoDiario * dias;
    }
    
    @Override
    public double calcularTaxaServico(double rendimento) {
        return rendimento * TAXA_SERVICO;
    }
    
    @Override
    public String getTipoConta() {
        return "CDI";
    }
} 