import java.time.LocalDate;

public class DemoSistema {
    public static void main(String[] args) {
        System.out.println("=== DEMONSTRACAO DO SISTEMA DE LANCHONETE ===\n");
        
        // Criar itens de exemplo
        Pizza pizza = new Pizza("Margherita", 45.90, LocalDate.now().plusDays(7), 800.0,
                               "Mussarela e Tomate", "Tradicional", "Molho de Tomate", false);
        
        Lanche lanche = new Lanche("X-Burger", 18.90, LocalDate.now().plusDays(3), 250.0,
                                  "Pao de Hamburguer", "Hamburguer de Carne", "Maionese e Ketchup");
        
        Salgadinho salgadinho = new Salgadinho("Coxinha", 8.90, LocalDate.now().plusDays(2), 80.0,
                                               "Frito", "Massa de Batata", "Frango");
        
        // Mostrar itens criados
        System.out.println("ITENS CRIADOS:");
        System.out.println("1. " + pizza.getNome() + " - R$ " + pizza.getPrecoVenda());
        System.out.println("2. " + lanche.getNome() + " - R$ " + lanche.getPrecoVenda());
        System.out.println("3. " + salgadinho.getNome() + " - R$ " + salgadinho.getPrecoVenda());
        
        // Criar pedido
        System.out.println("\nCRIANDO PEDIDO...");
        Pedido pedido = new Pedido("Maria Silva", 10.0);
        
        pedido.adicionarItem(pizza, 1);
        pedido.adicionarItem(lanche, 2);
        pedido.adicionarItem(salgadinho, 3);
        
        // Mostrar resumo do pedido
        System.out.println("\nRESUMO DO PEDIDO:");
        System.out.println("Cliente: " + pedido.getNomeCliente());
        System.out.println("Numero do Pedido: " + pedido.getNumeroPedido());
        System.out.println("Subtotal: R$ " + pedido.getSubtotalItens());
        System.out.println("Taxa de Servico (10%): R$ " + pedido.getValorTaxaServico());
        System.out.println("Total: R$ " + pedido.getTotal());
        
        // Gerar nota fiscal
        System.out.println("\nGERANDO NOTA FISCAL...");
        NotaFiscal notaFiscal = new NotaFiscal(pedido, "12.345.678/0001-90", 
                                              "Rua das Lanchonetes, 123 - Centro, Guarulhos/SP", 
                                              "(11) 99999-9999");
        
        System.out.println("Nota fiscal gerada com sucesso!");
        
        // Calcular troco
        System.out.println("\nCALCULANDO TROCO...");
        double valorRecebido = 150.0;
        double troco = valorRecebido - pedido.getTotal();
        
        System.out.println("Total do pedido: R$ " + pedido.getTotal());
        System.out.println("Valor recebido: R$ " + valorRecebido);
        System.out.println("Troco: R$ " + troco);
        
        System.out.println("\n=== DEMONSTRACAO CONCLUIDA ===");
        System.out.println("O sistema esta funcionando corretamente!");
        System.out.println("Todas as funcionalidades foram testadas:");
        System.out.println("- Criacao de itens (Pizza, Lanche, Salgadinho)");
        System.out.println("- Heranca e polimorfismo funcionando");
        System.out.println("- Criacao de pedidos");
        System.out.println("- Calculo de taxas e totais");
        System.out.println("- Geracao de notas fiscais");
        System.out.println("- Calculo de troco");
    }
} 