public class TestaCadastro {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE CADASTRO DE PESSOAS ===\n");
        
        // Criando instância do sistema de cadastro
        CadastroPessoas cadastro = new CadastroPessoas();
        
        // Criando datas de nascimento
        Data data1 = new Data(15, 3, 1985);
        Data data2 = new Data(22, 7, 1990);
        Data data3 = new Data(10, 11, 1978);
        
        // Cadastrando um cliente
        Cliente cliente = new Cliente("João Silva", data1, 1001);
        cadastro.cadastraPessoa(cliente);
        
        // Cadastrando um funcionário
        Funcionario funcionario = new Funcionario("Maria Santos", data2, 2500.00f);
        cadastro.cadastraPessoa(funcionario);
        
        // Cadastrando um gerente
        Gerente gerente = new Gerente("Carlos Oliveira", data3, 5000.00f, "Vendas");
        cadastro.cadastraPessoa(gerente);
        
        System.out.println("\n" + "=".repeat(50));
        
        // Imprimindo todo o cadastro
        cadastro.imprimeCadastro();
        
        System.out.println("=== TESTE CONCLUÍDO ===");
    }
} 