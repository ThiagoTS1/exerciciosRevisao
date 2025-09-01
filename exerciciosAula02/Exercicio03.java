import java.util.List;
import java.util.ArrayList;

public class Exercicio03 {
    public static void main(String[] args) {
        // Criando lista de produtos
        List<Produto> produtos = criarListaProdutos();
        
        System.out.println("=== EXERCÍCIO 3 ===");
        System.out.println("Cálculo do valor total do estoque de Livros:\n");
        
        // Imprimindo produtos da categoria Livros para referência
        System.out.println("--- Produtos da categoria 'Livros' ---");
        produtos.stream()
                .filter(produto -> "Livros".equals(produto.getCategoria()))
                .forEach(produto -> 
                    System.out.println(produto.getNome() + " - R$ " + produto.getPreco())
                );
        
        System.out.println();
        
        // c. Calculando valor total do estoque de Livros
        System.out.println("--- Cálculo do valor total ---");
        double valorTotalLivros = produtos.stream()
                .filter(produto -> "Livros".equals(produto.getCategoria()))
                .mapToDouble(produto -> produto.getPreco())
                .sum();
        
        System.out.println("Valor total do estoque de Livros: R$ " + valorTotalLivros);
        
        // Mostrando detalhes do cálculo
        System.out.println("\n--- Detalhes do cálculo ---");
        produtos.stream()
                .filter(produto -> "Livros".equals(produto.getCategoria()))
                .forEach(produto -> 
                    System.out.println("+ " + produto.getNome() + ": R$ " + produto.getPreco())
                );
        System.out.println("Total: R$ " + valorTotalLivros);
    }
    
    private static List<Produto> criarListaProdutos() {
        List<Produto> produtos = new ArrayList<>();
        
        // Adicionando produtos de diferentes categorias
        produtos.add(new Produto("Smartphone Samsung", 1200.0, "Eletrônicos"));
        produtos.add(new Produto("Livro Java Programming", 89.90, "Livros"));
        produtos.add(new Produto("Notebook Dell", 2500.0, "Eletrônicos"));
        produtos.add(new Produto("Livro Python Basics", 75.50, "Livros"));
        produtos.add(new Produto("Fone de Ouvido Bluetooth", 150.0, "Eletrônicos"));
        produtos.add(new Produto("Livro Data Structures", 120.0, "Livros"));
        produtos.add(new Produto("Tablet Apple", 1800.0, "Eletrônicos"));
        produtos.add(new Produto("Livro Algorithms", 95.0, "Livros"));
        
        return produtos;
    }
}
