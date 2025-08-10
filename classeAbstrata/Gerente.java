public class Gerente extends Funcionario {
    private String area;
    
    public Gerente(String nome, Data nascimento, float salario, String area) {
        super(nome, nascimento, salario);
        this.area = area;
    }
    
    public String getArea() {
        return area;
    }
    
    public void setArea(String area) {
        this.area = area;
    }
    
    @Override
    public float calculaImposto() {
        // Imposto é de 5% do salário para gerentes
        return salario * 0.05f;
    }
    
    @Override
    public void imprimeDados() {
        System.out.println("=== DADOS DO GERENTE ===");
        System.out.println("Nome: " + nome);
        System.out.println("Data de Nascimento: " + nascimento);
        System.out.println("Salário: R$ " + String.format("%.2f", salario));
        System.out.println("Área: " + area);
        System.out.println("Imposto: R$ " + String.format("%.2f", calculaImposto()));
        System.out.println();
    }
} 