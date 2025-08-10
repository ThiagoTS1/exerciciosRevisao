import java.time.LocalDate;

public class Lanche extends Item {
    private String tipoPao;
    private String recheio;
    private String molho;

    public Lanche(String nome, double precoVenda, LocalDate dataValidade, double peso,
                  String tipoPao, String recheio, String molho) {
        super(nome, precoVenda, dataValidade, peso);
        this.tipoPao = tipoPao;
        this.recheio = recheio;
        this.molho = molho;
    }

    // Getters e Setters específicos do Lanche
    public String getTipoPao() { return tipoPao; }
    public void setTipoPao(String tipoPao) { this.tipoPao = tipoPao; }
    
    public String getRecheio() { return recheio; }
    public void setRecheio(String recheio) { this.recheio = recheio; }
    
    public String getMolho() { return molho; }
    public void setMolho(String molho) { this.molho = molho; }

    @Override
    public String getDescricaoCompleta() {
        return String.format("Lanche %s - Pão: %s, Recheio: %s, Molho: %s, Peso: %.2fg, Preço: R$ %.2f",
                           nome, tipoPao, recheio, molho, peso, precoVenda);
    }
} 