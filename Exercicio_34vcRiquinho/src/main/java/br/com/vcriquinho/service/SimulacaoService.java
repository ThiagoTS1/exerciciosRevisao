package br.com.vcriquinho.service;

import br.com.vcriquinho.model.*;
import java.util.*;

public class SimulacaoService {
    
    public static final int[] PERIODOS_DISPONIVEIS = {30, 60, 90, 180};
    
    public class ResultadoSimulacao {
        private int periodo;
        private double rendimentoTotal;
        private double taxaServicoTotal;
        private List<DetalheConta> detalhesContas;
        private List<String> avisos;
        
        public ResultadoSimulacao(int periodo) {
            this.periodo = periodo;
            this.detalhesContas = new ArrayList<>();
            this.avisos = new ArrayList<>();
        }
        
        // Getters e setters
        public int getPeriodo() { return periodo; }
        public double getRendimentoTotal() { return rendimentoTotal; }
        public void setRendimentoTotal(double rendimentoTotal) { this.rendimentoTotal = rendimentoTotal; }
        public double getTaxaServicoTotal() { return taxaServicoTotal; }
        public void setTaxaServicoTotal(double taxaServicoTotal) { this.taxaServicoTotal = taxaServicoTotal; }
        public List<DetalheConta> getDetalhesContas() { return detalhesContas; }
        public List<String> getAvisos() { return avisos; }
        
        public void adicionarDetalheConta(DetalheConta detalhe) {
            detalhesContas.add(detalhe);
        }
        
        public void adicionarAviso(String aviso) {
            avisos.add(aviso);
        }
    }
    
    public class DetalheConta {
        private String numeroConta;
        private String tipoConta;
        private double saldo;
        private double rendimento;
        private double taxaServico;
        private String observacoes;
        
        public DetalheConta(String numeroConta, String tipoConta, double saldo, 
                           double rendimento, double taxaServico, String observacoes) {
            this.numeroConta = numeroConta;
            this.tipoConta = tipoConta;
            this.saldo = saldo;
            this.rendimento = rendimento;
            this.taxaServico = taxaServico;
            this.observacoes = observacoes;
        }
        
        // Getters
        public String getNumeroConta() { return numeroConta; }
        public String getTipoConta() { return tipoConta; }
        public double getSaldo() { return saldo; }
        public double getRendimento() { return rendimento; }
        public double getTaxaServico() { return taxaServico; }
        public String getObservacoes() { return observacoes; }
    }
    
    public ResultadoSimulacao simularRendimento(Cliente cliente, int periodo) {
        if (!Arrays.stream(PERIODOS_DISPONIVEIS).anyMatch(p -> p == periodo)) {
            throw new IllegalArgumentException("Período deve ser 30, 60, 90 ou 180 dias");
        }
        
        ResultadoSimulacao resultado = new ResultadoSimulacao(periodo);
        double rendimentoTotal = 0.0;
        double taxaServicoTotal = 0.0;
        
        for (Conta conta : cliente.getContas()) {
            double rendimento = conta.calcularRendimento(periodo);
            double taxaServico = conta.calcularTaxaServico(rendimento);
            
            String observacoes = "";
            if (conta instanceof ContaInvestimentoAutomatico) {
                ContaInvestimentoAutomatico contaInv = (ContaInvestimentoAutomatico) conta;
                for (ProdutoInvestimento produto : contaInv.getProdutosInvestidos()) {
                    if (produto instanceof ProdutoRendaFixa) {
                        ProdutoRendaFixa rendaFixa = (ProdutoRendaFixa) produto;
                        if (periodo < rendaFixa.getPeriodoCarencia()) {
                            observacoes += "Produto " + produto.getNome() + " dentro do período de carência (" + 
                                         rendaFixa.getPeriodoCarencia() + " dias). ";
                        }
                    }
                }
            }
            
            DetalheConta detalhe = new DetalheConta(
                conta.getNumero(),
                conta.getTipoConta(),
                conta.getSaldo(),
                rendimento,
                taxaServico,
                observacoes
            );
            
            resultado.adicionarDetalheConta(detalhe);
            rendimentoTotal += rendimento;
            taxaServicoTotal += taxaServico;
        }
        
        resultado.setRendimentoTotal(rendimentoTotal);
        resultado.setTaxaServicoTotal(taxaServicoTotal);
        
        return resultado;
    }
    
    public List<ResultadoSimulacao> simularTodosPeriodos(Cliente cliente) {
        List<ResultadoSimulacao> resultados = new ArrayList<>();
        
        for (int periodo : PERIODOS_DISPONIVEIS) {
            try {
                ResultadoSimulacao resultado = simularRendimento(cliente, periodo);
                resultados.add(resultado);
            } catch (Exception e) {
                // Log do erro e continua com os outros períodos
                System.err.println("Erro ao simular período " + periodo + ": " + e.getMessage());
            }
        }
        
        return resultados;
    }
} 