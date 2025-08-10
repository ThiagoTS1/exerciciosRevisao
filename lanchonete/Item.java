import java.time.LocalDate;

public abstract class Item {
    protected double precoVenda;
    protected LocalDate dataValidade;
    protected double peso;
    protected String nome;

    public Item(String nome, double precoVenda, LocalDate dataValidade, double peso) {
        this.nome = nome;
        this.precoVenda = precoVenda;
        this.dataValidade = dataValidade;
        this.peso = peso;
    }

    // Getters e Setters
    public double getPrecoVenda() { return precoVenda; }
    public void setPrecoVenda(double precoVenda) { this.precoVenda = precoVenda; }
    
    public LocalDate getDataValidade() { return dataValidade; }
    public void setDataValidade(LocalDate dataValidade) { this.dataValidade = dataValidade; }
    
    public double getPeso() { return peso; }
    public void setPeso(double peso) { this.peso = peso; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    // Método abstrato que será implementado pelas subclasses
    public abstract String getDescricaoCompleta();
    
    // Método para verificar se o item está vencido
    public boolean estaVencido() {
        return LocalDate.now().isAfter(dataValidade);
    }
} 