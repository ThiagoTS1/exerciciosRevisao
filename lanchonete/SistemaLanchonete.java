import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SistemaLanchonete {
    private List<Item> cardapio;
    private List<Pedido> pedidos;
    private Scanner scanner;
    
    // Dados da empresa
    private static final String CNPJ = "12.345.678/0001-90";
    private static final String ENDERECO = "Rua das Lanchonetes, 123 - Centro, Guarulhos/SP";
    private static final String TELEFONE = "(11) 99999-9999";
    private static final double TAXA_SERVICO_PADRAO = 10.0; // 10%

    public SistemaLanchonete() {
        this.cardapio = new ArrayList<>();
        this.pedidos = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        inicializarCardapio();
    }

    // Inicializa o cardápio com alguns itens de exemplo
    private void inicializarCardapio() {
        // Pizzas
        cardapio.add(new Pizza("Margherita", 45.90, LocalDate.now().plusDays(7), 800.0,
                              "Mussarela e Tomate", "Tradicional", "Molho de Tomate", false));
        cardapio.add(new Pizza("Quatro Queijos", 52.90, LocalDate.now().plusDays(7), 850.0,
                              "Mussarela, Provolone, Parmesão e Gorgonzola", "Tradicional", "Molho de Tomate", false));
        cardapio.add(new Pizza("Calabresa", 48.90, LocalDate.now().plusDays(7), 800.0,
                              "Calabresa e Cebola", "Recheada com Catupiry", "Molho de Tomate", true));

        // Lanches
        cardapio.add(new Lanche("X-Burger", 18.90, LocalDate.now().plusDays(3), 250.0,
                                "Pão de Hambúrguer", "Hambúrguer de Carne", "Maionese e Ketchup"));
        cardapio.add(new Lanche("X-Salada", 22.90, LocalDate.now().plusDays(3), 280.0,
                                "Pão de Hambúrguer", "Hambúrguer de Carne, Alface, Tomate", "Maionese"));
        cardapio.add(new Lanche("X-Frango", 20.90, LocalDate.now().plusDays(3), 260.0,
                                "Pão de Hambúrguer", "Filé de Frango", "Maionese e Mostarda"));

        // Salgadinhos
        cardapio.add(new Salgadinho("Coxinha", 8.90, LocalDate.now().plusDays(2), 80.0,
                                   "Frito", "Massa de Batata", "Frango"));
        cardapio.add(new Salgadinho("Pastel", 7.90, LocalDate.now().plusDays(2), 70.0,
                                   "Frito", "Massa de Pastel", "Carne"));
        cardapio.add(new Salgadinho("Empada", 6.90, LocalDate.now().plusDays(2), 60.0,
                                   "Assado", "Massa de Empada", "Frango"));
    }

    // Exibe o cardápio
    public void exibirCardapio() {
        System.out.println("\n" + "=".repeat(80));
        System.out.println("                           CARDÁPIO QUASE TRÊS LANCHES");
        System.out.println("=".repeat(80));
        
        System.out.println("\n🍕 PIZZAS:");
        System.out.println("-".repeat(40));
        for (int i = 0; i < cardapio.size(); i++) {
            Item item = cardapio.get(i);
            if (item instanceof Pizza) {
                System.out.printf("%d. %s\n", i + 1, item.getDescricaoCompleta());
            }
        }
        
        System.out.println("\n🍔 LANCHES:");
        System.out.println("-".repeat(40));
        for (int i = 0; i < cardapio.size(); i++) {
            Item item = cardapio.get(i);
            if (item instanceof Lanche) {
                System.out.printf("%d. %s\n", i + 1, item.getDescricaoCompleta());
            }
        }
        
        System.out.println("\n🥟 SALGADINHOS:");
        System.out.println("-".repeat(40));
        for (int i = 0; i < cardapio.size(); i++) {
            Item item = cardapio.get(i);
            if (item instanceof Salgadinho) {
                System.out.printf("%d. %s\n", i + 1, item.getDescricaoCompleta());
            }
        }
        System.out.println("=".repeat(80));
    }

    // Cria um novo pedido
    public void criarPedido() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("           NOVO PEDIDO");
        System.out.println("=".repeat(50));
        
        System.out.print("Nome do cliente: ");
        String nomeCliente = scanner.nextLine();
        
        Pedido pedido = new Pedido(nomeCliente, TAXA_SERVICO_PADRAO);
        
        boolean adicionandoItens = true;
        while (adicionandoItens) {
            exibirCardapio();
            
            System.out.print("\nDigite o número do item (0 para finalizar): ");
            int escolha = Integer.parseInt(scanner.nextLine());
            
            if (escolha == 0) {
                adicionandoItens = false;
            } else if (escolha > 0 && escolha <= cardapio.size()) {
                Item itemEscolhido = cardapio.get(escolha - 1);
                
                System.out.print("Quantidade: ");
                int quantidade = Integer.parseInt(scanner.nextLine());
                
                pedido.adicionarItem(itemEscolhido, quantidade);
                System.out.println("Item adicionado com sucesso!");
            } else {
                System.out.println("Opção inválida!");
            }
        }
        
        if (!pedido.getItens().isEmpty()) {
            pedidos.add(pedido);
            System.out.println("\nPedido criado com sucesso!");
            System.out.println(pedido.getResumoPedido());
        } else {
            System.out.println("Pedido cancelado - nenhum item foi adicionado.");
        }
    }

    // Gera nota fiscal para um pedido
    public void gerarNotaFiscal() {
        if (pedidos.isEmpty()) {
            System.out.println("Não há pedidos para gerar nota fiscal.");
            return;
        }
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("           GERAR NOTA FISCAL");
        System.out.println("=".repeat(50));
        
        for (int i = 0; i < pedidos.size(); i++) {
            Pedido pedido = pedidos.get(i);
            System.out.printf("%d. Pedido #%d - Cliente: %s - Total: R$ %.2f\n", 
                            i + 1, pedido.getNumeroPedido(), pedido.getNomeCliente(), pedido.getTotal());
        }
        
        System.out.print("\nEscolha o número do pedido: ");
        int escolha = Integer.parseInt(scanner.nextLine());
        
        if (escolha > 0 && escolha <= pedidos.size()) {
            Pedido pedidoEscolhido = pedidos.get(escolha - 1);
            NotaFiscal notaFiscal = new NotaFiscal(pedidoEscolhido, CNPJ, ENDERECO, TELEFONE);
            
            System.out.println("\n" + "=".repeat(50));
            System.out.println("           NOTA FISCAL GERADA");
            System.out.println("=".repeat(50));
            System.out.println(notaFiscal.gerarNotaFiscal());
        } else {
            System.out.println("Opção inválida!");
        }
    }

    // Calcula troco para um pedido
    public void calcularTroco() {
        if (pedidos.isEmpty()) {
            System.out.println("Não há pedidos para calcular troco.");
            return;
        }
        
        System.out.println("\n" + "=".repeat(50));
        System.out.println("           CALCULAR TROCO");
        System.out.println("=".repeat(50));
        
        for (int i = 0; i < pedidos.size(); i++) {
            Pedido pedido = pedidos.get(i);
            System.out.printf("%d. Pedido #%d - Cliente: %s - Total: R$ %.2f\n", 
                            i + 1, pedido.getNumeroPedido(), pedido.getNomeCliente(), pedido.getTotal());
        }
        
        System.out.print("\nEscolha o número do pedido: ");
        int escolha = Integer.parseInt(scanner.nextLine());
        
        if (escolha > 0 && escolha <= pedidos.size()) {
            Pedido pedidoEscolhido = pedidos.get(escolha - 1);
            
            System.out.printf("\nTotal do pedido: R$ %.2f\n", pedidoEscolhido.getTotal());
            System.out.print("Valor recebido: R$ ");
            double valorRecebido = Double.parseDouble(scanner.nextLine());
            
            if (valorRecebido >= pedidoEscolhido.getTotal()) {
                double troco = valorRecebido - pedidoEscolhido.getTotal();
                System.out.printf("Troco: R$ %.2f\n", troco);
            } else {
                System.out.println("Valor insuficiente para pagar o pedido!");
            }
        } else {
            System.out.println("Opção inválida!");
        }
    }

    // Exibe todos os pedidos
    public void exibirPedidos() {
        if (pedidos.isEmpty()) {
            System.out.println("Não há pedidos registrados.");
            return;
        }
        
        System.out.println("\n" + "=".repeat(80));
        System.out.println("                           PEDIDOS REGISTRADOS");
        System.out.println("=".repeat(80));
        
        for (Pedido pedido : pedidos) {
            System.out.println(pedido.getResumoPedido());
            System.out.println("-".repeat(80));
        }
    }

    // Menu principal do sistema
    public void executarSistema() {
        boolean executando = true;
        
        while (executando) {
            System.out.println("\n" + "=".repeat(50));
            System.out.println("           SISTEMA QUASE TRÊS LANCHES");
            System.out.println("=".repeat(50));
            System.out.println("1. Exibir Cardápio");
            System.out.println("2. Criar Novo Pedido");
            System.out.println("3. Exibir Pedidos");
            System.out.println("4. Gerar Nota Fiscal");
            System.out.println("5. Calcular Troco");
            System.out.println("0. Sair");
            System.out.println("=".repeat(50));
            
            System.out.print("Escolha uma opção: ");
            String opcao = scanner.nextLine();
            
            switch (opcao) {
                case "1":
                    exibirCardapio();
                    break;
                case "2":
                    criarPedido();
                    break;
                case "3":
                    exibirPedidos();
                    break;
                case "4":
                    gerarNotaFiscal();
                    break;
                case "5":
                    calcularTroco();
                    break;
                case "0":
                    executando = false;
                    System.out.println("Sistema encerrado. Obrigado!");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
        
        scanner.close();
    }

    // Método main para executar o sistema
    public static void main(String[] args) {
        SistemaLanchonete sistema = new SistemaLanchonete();
        sistema.executarSistema();
    }
} 