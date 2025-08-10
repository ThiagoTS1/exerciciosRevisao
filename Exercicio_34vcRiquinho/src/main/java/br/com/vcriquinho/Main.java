package br.com.vcriquinho;

import br.com.vcriquinho.model.*;
import br.com.vcriquinho.service.*;
import java.util.*;
import java.text.DecimalFormat;

public class Main {
    private static ClienteService clienteService = new ClienteService();
    private static ProdutoService produtoService = new ProdutoService();
    private static SimulacaoService simulacaoService = new SimulacaoService();
    private static Scanner scanner = new Scanner(System.in);
    private static DecimalFormat df = new DecimalFormat("#,##0.00");
    
    public static void main(String[] args) {
        inicializarDadosExemplo();
        
        while (true) {
            exibirMenuPrincipal();
            int opcao = lerOpcao();
            
            try {
                switch (opcao) {
                    case 1:
                        menuGerenciarClientes();
                        break;
                    case 2:
                        menuGerenciarProdutos();
                        break;
                    case 3:
                        menuSimulacoes();
                        break;
                    case 4:
                        System.out.println("Saindo do sistema VcRiquinho...");
                        return;
                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
            
            System.out.println("\nPressione Enter para continuar...");
            scanner.nextLine();
        }
    }
    
    private static void exibirMenuPrincipal() {
        System.out.println("\n=== VcRiquinho - Sistema de Investimentos ===");
        System.out.println("1. Gerenciar Clientes");
        System.out.println("2. Gerenciar Produtos de Investimento");
        System.out.println("3. Simulações de Rendimento");
        System.out.println("4. Sair");
        System.out.print("Escolha uma opção: ");
    }
    
    private static void menuGerenciarClientes() {
        while (true) {
            System.out.println("\n=== Gerenciamento de Clientes ===");
            System.out.println("1. Cadastrar Cliente PF");
            System.out.println("2. Cadastrar Cliente PJ");
            System.out.println("3. Listar Clientes");
            System.out.println("4. Buscar Cliente");
            System.out.println("5. Atualizar Cliente");
            System.out.println("6. Remover Cliente");
            System.out.println("7. Adicionar Conta");
            System.out.println("8. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            
            int opcao = lerOpcao();
            
            switch (opcao) {
                case 1:
                    cadastrarClientePF();
                    break;
                case 2:
                    cadastrarClientePJ();
                    break;
                case 3:
                    listarClientes();
                    break;
                case 4:
                    buscarCliente();
                    break;
                case 5:
                    atualizarCliente();
                    break;
                case 6:
                    removerCliente();
                    break;
                case 7:
                    adicionarConta();
                    break;
                case 8:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
    
    private static void menuGerenciarProdutos() {
        while (true) {
            System.out.println("\n=== Gerenciamento de Produtos ===");
            System.out.println("1. Cadastrar Produto Renda Fixa");
            System.out.println("2. Cadastrar Produto Renda Variável");
            System.out.println("3. Listar Produtos");
            System.out.println("4. Buscar Produto");
            System.out.println("5. Atualizar Produto");
            System.out.println("6. Remover Produto");
            System.out.println("7. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            
            int opcao = lerOpcao();
            
            switch (opcao) {
                case 1:
                    cadastrarProdutoRendaFixa();
                    break;
                case 2:
                    cadastrarProdutoRendaVariavel();
                    break;
                case 3:
                    listarProdutos();
                    break;
                case 4:
                    buscarProduto();
                    break;
                case 5:
                    atualizarProduto();
                    break;
                case 6:
                    removerProduto();
                    break;
                case 7:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
    
    private static void menuSimulacoes() {
        while (true) {
            System.out.println("\n=== Simulações de Rendimento ===");
            System.out.println("1. Simular Rendimento por Período");
            System.out.println("2. Simular Todos os Períodos");
            System.out.println("3. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            
            int opcao = lerOpcao();
            
            switch (opcao) {
                case 1:
                    simularRendimentoPeriodo();
                    break;
                case 2:
                    simularTodosPeriodos();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
    
    private static void cadastrarClientePF() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        
        PessoaFisica cliente = new PessoaFisica(nome, email, cpf);
        clienteService.cadastrarCliente(cliente);
        
        // Criar conta padrão
        ContaCorrente conta = new ContaCorrente("CC" + cpf.substring(0, 8), cliente);
        cliente.adicionarConta(conta);
        
        System.out.println("Cliente PF cadastrado com sucesso!");
    }
    
    private static void cadastrarClientePJ() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("CNPJ: ");
        String cnpj = scanner.nextLine();
        
        PessoaJuridica cliente = new PessoaJuridica(nome, email, cnpj);
        clienteService.cadastrarCliente(cliente);
        
        // Criar conta padrão
        ContaCorrente conta = new ContaCorrente("CC" + cnpj.substring(0, 8), cliente);
        cliente.adicionarConta(conta);
        
        System.out.println("Cliente PJ cadastrado com sucesso!");
    }
    
    private static void listarClientes() {
        List<Cliente> clientes = clienteService.listarClientes();
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }
        
        System.out.println("\n=== Lista de Clientes ===");
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
    }
    
    private static void buscarCliente() {
        System.out.print("Digite o documento (CPF/CNPJ): ");
        String documento = scanner.nextLine();
        
        try {
            Cliente cliente = clienteService.buscarCliente(documento);
            System.out.println("\nCliente encontrado:");
            System.out.println(cliente);
            
            List<Conta> contas = cliente.getContas();
            if (!contas.isEmpty()) {
                System.out.println("\nContas:");
                for (Conta conta : contas) {
                    System.out.println("  " + conta);
                }
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    private static void atualizarCliente() {
        System.out.print("Digite o documento (CPF/CNPJ): ");
        String documento = scanner.nextLine();
        
        try {
            Cliente cliente = clienteService.buscarCliente(documento);
            System.out.println("Cliente atual: " + cliente.getNome());
            
            System.out.print("Novo nome: ");
            String novoNome = scanner.nextLine();
            System.out.print("Novo email: ");
            String novoEmail = scanner.nextLine();
            
            clienteService.atualizarCliente(documento, novoNome, novoEmail);
            System.out.println("Cliente atualizado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    private static void removerCliente() {
        System.out.print("Digite o documento (CPF/CNPJ): ");
        String documento = scanner.nextLine();
        
        try {
            clienteService.removerCliente(documento);
            System.out.println("Cliente removido com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    private static void adicionarConta() {
        System.out.print("Digite o documento (CPF/CNPJ): ");
        String documento = scanner.nextLine();
        
        try {
            Cliente cliente = clienteService.buscarCliente(documento);
            System.out.println("Tipos de conta disponíveis:");
            System.out.println("1. Conta Corrente");
            System.out.println("2. Conta CDI");
            System.out.println("3. Conta Investimento Automático");
            System.out.print("Escolha o tipo: ");
            
            int tipoConta = lerOpcao();
            System.out.print("Número da conta: ");
            String numeroConta = scanner.nextLine();
            
            Conta conta = null;
            switch (tipoConta) {
                case 1:
                    conta = new ContaCorrente(numeroConta, cliente);
                    break;
                case 2:
                    conta = new ContaCDI(numeroConta, cliente);
                    break;
                case 3:
                    conta = new ContaInvestimentoAutomatico(numeroConta, cliente);
                    break;
                default:
                    System.out.println("Tipo de conta inválido!");
                    return;
            }
            
            clienteService.adicionarConta(documento, conta);
            System.out.println("Conta adicionada com sucesso!");
            
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    private static void cadastrarProdutoRendaFixa() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("Rendimento mensal (%): ");
        double rendimentoMensal = Double.parseDouble(scanner.nextLine()) / 100.0;
        System.out.print("Período de carência (dias): ");
        int periodoCarencia = Integer.parseInt(scanner.nextLine());
        
        ProdutoRendaFixa produto = new ProdutoRendaFixa(nome, descricao, rendimentoMensal, periodoCarencia);
        produtoService.cadastrarProduto(produto);
        
        System.out.println("Produto de renda fixa cadastrado com sucesso!");
    }
    
    private static void cadastrarProdutoRendaVariavel() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("Rendimento mensal esperado (%): ");
        double rendimentoMensal = Double.parseDouble(scanner.nextLine()) / 100.0;
        
        ProdutoRendaVariavel produto = new ProdutoRendaVariavel(nome, descricao, rendimentoMensal);
        produtoService.cadastrarProduto(produto);
        
        System.out.println("Produto de renda variável cadastrado com sucesso!");
    }
    
    private static void listarProdutos() {
        List<ProdutoInvestimento> produtos = produtoService.listarProdutos();
        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }
        
        System.out.println("\n=== Lista de Produtos ===");
        for (ProdutoInvestimento produto : produtos) {
            System.out.println(produto);
        }
    }
    
    private static void buscarProduto() {
        System.out.print("Digite o nome do produto: ");
        String nome = scanner.nextLine();
        
        try {
            ProdutoInvestimento produto = produtoService.buscarProduto(nome);
            System.out.println("\nProduto encontrado:");
            System.out.println(produto);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    private static void atualizarProduto() {
        System.out.print("Digite o nome do produto: ");
        String nome = scanner.nextLine();
        
        try {
            ProdutoInvestimento produto = produtoService.buscarProduto(nome);
            System.out.println("Produto atual: " + produto.getNome());
            
            if (produto instanceof ProdutoRendaFixa) {
                cadastrarProdutoRendaFixa();
            } else if (produto instanceof ProdutoRendaVariavel) {
                cadastrarProdutoRendaVariavel();
            }
            
            System.out.println("Produto atualizado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    private static void removerProduto() {
        System.out.print("Digite o nome do produto: ");
        String nome = scanner.nextLine();
        
        try {
            produtoService.removerProduto(nome);
            System.out.println("Produto removido com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    private static void simularRendimentoPeriodo() {
        System.out.print("Digite o documento (CPF/CNPJ): ");
        String documento = scanner.nextLine();
        
        try {
            Cliente cliente = clienteService.buscarCliente(documento);
            System.out.println("Períodos disponíveis: 30, 60, 90, 180 dias");
            System.out.print("Digite o período: ");
            int periodo = Integer.parseInt(scanner.nextLine());
            
            SimulacaoService.ResultadoSimulacao resultado = simulacaoService.simularRendimento(cliente, periodo);
            exibirResultadoSimulacao(resultado);
            
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    private static void simularTodosPeriodos() {
        System.out.print("Digite o documento (CPF/CNPJ): ");
        String documento = scanner.nextLine();
        
        try {
            Cliente cliente = clienteService.buscarCliente(documento);
            List<SimulacaoService.ResultadoSimulacao> resultados = simulacaoService.simularTodosPeriodos(cliente);
            
            System.out.println("\n=== Simulação para Todos os Períodos ===");
            for (SimulacaoService.ResultadoSimulacao resultado : resultados) {
                exibirResultadoSimulacao(resultado);
                System.out.println("----------------------------------------");
            }
            
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    
    private static void exibirResultadoSimulacao(SimulacaoService.ResultadoSimulacao resultado) {
        System.out.println("\n=== Simulação para " + resultado.getPeriodo() + " dias ===");
        System.out.println("Rendimento Total: R$ " + df.format(resultado.getRendimentoTotal()));
        System.out.println("Taxa de Serviço Total: R$ " + df.format(resultado.getTaxaServicoTotal()));
        
        System.out.println("\nDetalhes por Conta:");
        for (SimulacaoService.DetalheConta detalhe : resultado.getDetalhesContas()) {
            System.out.println("  Conta " + detalhe.getNumeroConta() + " (" + detalhe.getTipoConta() + ")");
            System.out.println("    Saldo: R$ " + df.format(detalhe.getSaldo()));
            System.out.println("    Rendimento: R$ " + df.format(detalhe.getRendimento()));
            System.out.println("    Taxa de Serviço: R$ " + df.format(detalhe.getTaxaServico()));
            if (!detalhe.getObservacoes().isEmpty()) {
                System.out.println("    Observações: " + detalhe.getObservacoes());
            }
        }
    }
    
    private static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    private static void inicializarDadosExemplo() {
        try {
            // Criar produtos de exemplo
            ProdutoRendaFixa cdb = new ProdutoRendaFixa("CDB Banco XYZ", "CDB com rendimento mensal fixo", 0.008, 30);
            ProdutoRendaFixa lci = new ProdutoRendaFixa("LCI Banco ABC", "LCI com isenção de IR", 0.007, 60);
            ProdutoRendaVariavel acoes = new ProdutoRendaVariavel("Carteira de Ações", "Portfólio diversificado de ações", 0.012);
            
            produtoService.cadastrarProduto(cdb);
            produtoService.cadastrarProduto(lci);
            produtoService.cadastrarProduto(acoes);
            
            // Criar clientes de exemplo
            PessoaFisica joao = new PessoaFisica("João Silva", "joao@email.com", "12345678901");
            PessoaJuridica empresa = new PessoaJuridica("Empresa LTDA", "contato@empresa.com", "12345678000199");
            
            clienteService.cadastrarCliente(joao);
            clienteService.cadastrarCliente(empresa);
            
            // Adicionar contas aos clientes
            ContaCorrente ccJoao = new ContaCorrente("CC001", joao);
            ContaCDI cdiJoao = new ContaCDI("CDI001", joao);
            ContaInvestimentoAutomatico invJoao = new ContaInvestimentoAutomatico("INV001", joao);
            
            joao.adicionarConta(ccJoao);
            joao.adicionarConta(cdiJoao);
            joao.adicionarConta(invJoao);
            
            // Depositar valores
            ccJoao.depositar(1000.0);
            cdiJoao.depositar(5000.0);
            invJoao.depositar(10000.0);
            
            // Investir em produtos
            invJoao.investirEmProduto(cdb, 5000.0);
            invJoao.investirEmProduto(acoes, 5000.0);
            
            // Cliente PJ
            ContaCorrente ccEmpresa = new ContaCorrente("CC002", empresa);
            ContaInvestimentoAutomatico invEmpresa = new ContaInvestimentoAutomatico("INV002", empresa);
            
            empresa.adicionarConta(ccEmpresa);
            empresa.adicionarConta(invEmpresa);
            
            ccEmpresa.depositar(50000.0);
            invEmpresa.depositar(100000.0);
            invEmpresa.investirEmProduto(lci, 100000.0);
            
            System.out.println("Dados de exemplo carregados com sucesso!");
            
        } catch (Exception e) {
            System.err.println("Erro ao carregar dados de exemplo: " + e.getMessage());
        }
    }
} 