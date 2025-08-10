public class ItemPedido {
    private Item item;
    private int quantidade;

    public ItemPedido(Item item, int quantidade) {
        this.item = item;
        this.quantidade = quantidade;
    }

    // Getters e Setters
    public Item getItem() { return item; }
    public void setItem(Item item) { this.item = item; }
    
    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    // Calcula o subtotal deste item
    public double getSubtotal() {
        return item.getPrecoVenda() * quantidade;
    }

    // Retorna a descrição do item com quantidade
    public String getDescricaoCompleta() {
        return String.format("%dx %s - Subtotal: R$ %.2f", 
                           quantidade, item.getDescricaoCompleta(), getSubtotal());
    }
} 