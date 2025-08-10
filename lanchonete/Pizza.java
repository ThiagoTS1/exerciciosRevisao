import java.time.LocalDate;

public class Pizza extends Item {
    private String recheio;
    private String borda;
    private String molho;
    private boolean bordaRecheada;

    public Pizza(String nome, double precoVenda, LocalDate dataValidade, double peso,
                 String recheio, String borda, String molho, boolean bordaRecheada) {
        super(nome, precoVenda, dataValidade, peso);
        this.recheio = recheio;
        this.borda = borda;
        this.molho = molho;
        this.bordaRecheada = bordaRecheada;
    }

    // Getters e Setters específicos da Pizza
    public String getRecheio() { return recheio; }
    public void setRecheio(String recheio) { this.recheio = recheio; }
    
    public String getBorda() { return borda; }
    public void setBorda(String borda) { this.borda = borda; }
    
    public String getMolho() { return molho; }
    public void setMolho(String molho) { this.molho = molho; }
    
    public boolean isBordaRecheada() { return bordaRecheada; }
    public void setBordaRecheada(boolean bordaRecheada) { this.bordaRecheada = bordaRecheada; }

    @Override
    public String getDescricaoCompleta() {
        String tipoBorda = bordaRecheada ? "Borda Recheada" : "Borda Simples";
        return String.format("Pizza %s - Recheio: %s, Borda: %s (%s), Molho: %s, Peso: %.2fg, Preço: R$ %.2f",
                           nome, recheio, borda, tipoBorda, molho, peso, precoVenda);
    }
} 