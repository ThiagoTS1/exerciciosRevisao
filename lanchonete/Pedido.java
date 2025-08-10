import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private String nomeCliente;
    private List<ItemPedido> itens;
    private double taxaServico;
    private LocalDateTime dataHora;
    private int numeroPedido;
    private static int contadorPedidos = 1;

    public Pedido(String nomeCliente, double taxaServico) {
        this.nomeCliente = nomeCliente;
        this.taxaServico = taxaServico;
        this.itens = new ArrayList<>();
        this.dataHora = LocalDateTime.now();
        this.numeroPedido = contadorPedidos++;
    }

    // Getters e Setters
    public String getNomeCliente() { return nomeCliente; }
    public void setNomeCliente(String nomeCliente) { this.nomeCliente = nomeCliente; }
    
    public List<ItemPedido> getItens() { return itens; }
    
    public double getTaxaServico() { return taxaServico; }
    public void setTaxaServico(double taxaServico) { this.taxaServico = taxaServico; }
    
    public LocalDateTime getDataHora() { return dataHora; }
    
    public int getNumeroPedido() { return numeroPedido; }

    // Adiciona um item ao pedido
    public void adicionarItem(Item item, int quantidade) {
        itens.add(new ItemPedido(item, quantidade));
    }

    // Remove um item do pedido
    public void removerItem(int indice) {
        if (indice >= 0 && indice < itens.size()) {
            itens.remove(indice);
        }
    }

    // Calcula o subtotal dos itens
    public double getSubtotalItens() {
        return itens.stream()
                   .mapToDouble(ItemPedido::getSubtotal)
                   .sum();
    }

    // Calcula o valor da taxa de serviço
    public double getValorTaxaServico() {
        return getSubtotalItens() * (taxaServico / 100.0);
    }

    // Calcula o total do pedido
    public double getTotal() {
        return getSubtotalItens() + getValorTaxaServico();
    }

    // Retorna informações resumidas do pedido
    public String getResumoPedido() {
        StringBuilder resumo = new StringBuilder();
        resumo.append(String.format("Pedido #%d - Cliente: %s\n", numeroPedido, nomeCliente));
        resumo.append(String.format("Data/Hora: %s\n", dataHora.format(java.time.format.DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))));
        resumo.append("Itens:\n");
        
        for (ItemPedido itemPedido : itens) {
            resumo.append("  ").append(itemPedido.getDescricaoCompleta()).append("\n");
        }
        
        resumo.append(String.format("\nSubtotal: R$ %.2f\n", getSubtotalItens()));
        resumo.append(String.format("Taxa de Serviço (%d%%): R$ %.2f\n", (int)taxaServico, getValorTaxaServico()));
        resumo.append(String.format("Total: R$ %.2f", getTotal()));
        
        return resumo.toString();
    }
} 