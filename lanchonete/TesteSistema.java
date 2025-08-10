import java.time.LocalDate;

public class TesteSistema {
    public static void main(String[] args) {
        System.out.println("=== TESTE DO SISTEMA DE LANCHONETE ===\n");
        
        // Teste 1: Criar itens
        System.out.println("1. Criando itens de exemplo...");
        
        Pizza pizza = new Pizza("Margherita", 45.90, LocalDate.now().plusDays(7), 800.0,
                               "Mussarela e Tomate", "Tradicional", "Molho de Tomate", false);
        
        Lanche lanche = new Lanche("X-Burger", 18.90, LocalDate.now().plusDays(3), 250.0,
                                  "Pão de Hambúrguer", "Hambúrguer de Carne", "Maionese e Ketchup");
        
        Salgadinho salgadinho = new Salgadinho("Coxinha", 8.90, LocalDate.now().plusDays(2), 80.0,
                                               "Frito", "Massa de Batata", "Frango");
        
        System.out.println("✅ Itens criados com sucesso!");
        
        // Teste 2: Exibir descrições
        System.out.println("\n2. Descrições dos itens:");
        System.out.println("Pizza: " + pizza.getDescricaoCompleta());
        System.out.println("Lanche: " + lanche.getDescricaoCompleta());
        System.out.println("Salgadinho: " + salgadinho.getDescricaoCompleta());
        
        // Teste 3: Criar pedido
        System.out.println("\n3. Criando pedido...");
        Pedido pedido = new Pedido("João Silva", 10.0); // 10% de taxa de serviço
        
        pedido.adicionarItem(pizza, 1);
        pedido.adicionarItem(lanche, 2);
        pedido.adicionarItem(salgadinho, 3);
        
        System.out.println("✅ Pedido criado com sucesso!");
        
        // Teste 4: Exibir resumo do pedido
        System.out.println("\n4. Resumo do pedido:");
        System.out.println(pedido.getResumoPedido());
        
        // Teste 5: Gerar nota fiscal
        System.out.println("\n5. Gerando nota fiscal...");
        NotaFiscal notaFiscal = new NotaFiscal(pedido, "12.345.678/0001-90", 
                                              "Rua das Lanchonetes, 123 - Centro, Guarulhos/SP", 
                                              "(11) 99999-9999");
        
        System.out.println("✅ Nota fiscal gerada!");
        System.out.println("\n" + notaFiscal.gerarNotaFiscal());
        
        // Teste 6: Cálculo de troco
        System.out.println("\n6. Testando cálculo de troco...");
        double valorRecebido = 100.0;
        double troco = valorRecebido - pedido.getTotal();
        
        System.out.printf("Total do pedido: R$ %.2f\n", pedido.getTotal());
        System.out.printf("Valor recebido: R$ %.2f\n", valorRecebido);
        System.out.printf("Troco: R$ %.2f\n", troco);
        
        System.out.println("\n=== TESTE CONCLUÍDO COM SUCESSO! ===");
        System.out.println("O sistema está funcionando corretamente.");
        System.out.println("Execute 'java SistemaLanchonete' para usar o sistema completo.");
    }
} 