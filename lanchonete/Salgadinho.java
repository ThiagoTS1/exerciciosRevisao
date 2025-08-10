import java.time.LocalDate;

public class Salgadinho extends Item {
    private String tipo; // "Frito" ou "Assado"
    private String massa;
    private String recheio;

    public Salgadinho(String nome, double precoVenda, LocalDate dataValidade, double peso,
                      String tipo, String massa, String recheio) {
        super(nome, precoVenda, dataValidade, peso);
        this.tipo = tipo;
        this.massa = massa;
        this.recheio = recheio;
    }

    // Getters e Setters específicos do Salgadinho
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    
    public String getMassa() { return massa; }
    public void setMassa(String massa) { this.massa = massa; }
    
    public String getRecheio() { return recheio; }
    public void setRecheio(String recheio) { this.recheio = recheio; }

    @Override
    public String getDescricaoCompleta() {
        return String.format("Salgadinho %s - Tipo: %s, Massa: %s, Recheio: %s, Peso: %.2fg, Preço: R$ %.2f",
                           nome, tipo, massa, recheio, peso, precoVenda);
    }
} 