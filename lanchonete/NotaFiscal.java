import java.time.format.DateTimeFormatter;

public class NotaFiscal {
    private Pedido pedido;
    private String cnpj;
    private String endereco;
    private String telefone;

    public NotaFiscal(Pedido pedido, String cnpj, String endereco, String telefone) {
        this.pedido = pedido;
        this.cnpj = cnpj;
        this.endereco = endereco;
        this.telefone = telefone;
    }

    // Getters e Setters
    public Pedido getPedido() { return pedido; }
    public void setPedido(Pedido pedido) { this.pedido = pedido; }
    
    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }
    
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }
    
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    // Gera a nota fiscal completa
    public String gerarNotaFiscal() {
        StringBuilder nota = new StringBuilder();
        
        // Cabeçalho
        nota.append("=".repeat(50)).append("\n");
        nota.append("           QUASE TRÊS LANCHES\n");
        nota.append("=".repeat(50)).append("\n");
        nota.append(String.format("CNPJ: %s\n", cnpj));
        nota.append(String.format("Endereço: %s\n", endereco));
        nota.append(String.format("Telefone: %s\n", telefone));
        nota.append("=".repeat(50)).append("\n");
        
        // Informações do pedido
        nota.append(String.format("NOTA FISCAL - Pedido #%d\n", pedido.getNumeroPedido()));
        nota.append(String.format("Cliente: %s\n", pedido.getNomeCliente()));
        nota.append(String.format("Data/Hora: %s\n", 
            pedido.getDataHora().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))));
        nota.append("-".repeat(50)).append("\n");
        
        // Itens do pedido
        nota.append("ITENS DO PEDIDO:\n");
        nota.append("-".repeat(50)).append("\n");
        
        for (ItemPedido itemPedido : pedido.getItens()) {
            nota.append(itemPedido.getDescricaoCompleta()).append("\n");
        }
        
        // Resumo financeiro
        nota.append("-".repeat(50)).append("\n");
        nota.append(String.format("Subtotal dos Itens: R$ %.2f\n", pedido.getSubtotalItens()));
        nota.append(String.format("Taxa de Serviço (%d%%): R$ %.2f\n", 
            (int)pedido.getTaxaServico(), pedido.getValorTaxaServico()));
        nota.append("=".repeat(50)).append("\n");
        nota.append(String.format("TOTAL A PAGAR: R$ %.2f\n", pedido.getTotal()));
        nota.append("=".repeat(50)).append("\n");
        nota.append("Obrigado pela preferência!\n");
        nota.append("Volte sempre!\n");
        nota.append("=".repeat(50));
        
        return nota.toString();
    }
} 