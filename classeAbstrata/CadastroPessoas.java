public class CadastroPessoas {
    private Pessoa[] pessoas;
    private int qtdAtual;
    private static final int CAPACIDADE_MAXIMA = 100;
    
    public CadastroPessoas() {
        this.pessoas = new Pessoa[CAPACIDADE_MAXIMA];
        this.qtdAtual = 0;
    }
    
    public void cadastraPessoa(Pessoa pess) {
        if (qtdAtual < CAPACIDADE_MAXIMA) {
            pessoas[qtdAtual] = pess;
            qtdAtual++;
            System.out.println("Pessoa cadastrada com sucesso!");
        } else {
            System.out.println("Erro: Capacidade máxima atingida!");
        }
    }
    
    public void imprimeCadastro() {
        if (qtdAtual == 0) {
            System.out.println("Nenhuma pessoa cadastrada no sistema.");
            return;
        }
        
        System.out.println("=== CADASTRO COMPLETO ===");
        System.out.println("Total de pessoas cadastradas: " + qtdAtual);
        System.out.println("========================\n");
        
        for (int i = 0; i < qtdAtual; i++) {
            pessoas[i].imprimeDados();
        }
    }
    
    public int getQtdAtual() {
        return qtdAtual;
    }
} 