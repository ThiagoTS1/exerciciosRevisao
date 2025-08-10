import java.time.LocalDate;

public class TesteSimples {
    public static void main(String[] args) {
        System.out.println("=== TESTE SIMPLES DO SISTEMA ===\n");
        
        // Teste básico de criação de itens
        Pizza pizza = new Pizza("Margherita", 45.90, LocalDate.now().plusDays(7), 800.0,
                               "Mussarela", "Tradicional", "Molho de Tomate", false);
        
        Lanche lanche = new Lanche("X-Burger", 18.90, LocalDate.now().plusDays(3), 250.0,
                                  "Pao de Hamburguer", "Hamburguer de Carne", "Maionese");
        
        Salgadinho salgadinho = new Salgadinho("Coxinha", 8.90, LocalDate.now().plusDays(2), 80.0,
                                               "Frito", "Massa de Batata", "Frango");
        
        System.out.println("Itens criados:");
        System.out.println("1. " + pizza.getNome() + " - R$ " + pizza.getPrecoVenda());
        System.out.println("2. " + lanche.getNome() + " - R$ " + lanche.getPrecoVenda());
        System.out.println("3. " + salgadinho.getNome() + " - R$ " + salgadinho.getPrecoVenda());
        
        // Teste de pedido
        Pedido pedido = new Pedido("Teste", 10.0);
        pedido.adicionarItem(pizza, 1);
        pedido.adicionarItem(lanche, 1);
        
        System.out.println("\nPedido criado:");
        System.out.println("Cliente: " + pedido.getNomeCliente());
        System.out.println("Total: R$ " + pedido.getTotal());
        
        System.out.println("\n=== TESTE CONCLUIDO ===");
    }
} 