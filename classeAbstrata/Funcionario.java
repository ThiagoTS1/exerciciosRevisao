public class Funcionario extends Pessoa {
    protected float salario;
    
    public Funcionario(String nome, Data nascimento, float salario) {
        super(nome, nascimento);
        this.salario = salario;
    }
    
    public float getSalario() {
        return salario;
    }
    
    public void setSalario(float salario) {
        this.salario = salario;
    }
    
    public float calculaImposto() {
        // Imposto é de 3% do salário
        return salario * 0.03f;
    }
    
    @Override
    public void imprimeDados() {
        System.out.println("=== DADOS DO FUNCIONÁRIO ===");
        System.out.println("Nome: " + nome);
        System.out.println("Data de Nascimento: " + nascimento);
        System.out.println("Salário: R$ " + String.format("%.2f", salario));
        System.out.println("Imposto: R$ " + String.format("%.2f", calculaImposto()));
        System.out.println();
    }
} 