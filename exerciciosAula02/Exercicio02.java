import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Exercicio02 {
    public static void main(String[] args) {
        // Criando lista de produtos
        List<Produto> produtos = criarListaProdutos();
        
        System.out.println("=== EXERCÍCIO 2 ===");
        System.out.println("Produtos com preço maior que R$ 500,00:\n");
        
        // Imprimindo produtos originais para referência
        System.out.println("--- Lista completa de produtos ---");
        produtos.forEach(produto -> 
            System.out.println(produto.getNome() + " - R$ " + produto.getPreco() + " - " + produto.getCategoria())
        );
        
        System.out.println();
        
        // b. Criando lista de preços > 500.0 usando filter() e map()
        System.out.println("--- Preços dos produtos com valor > R$ 500,00 ---");
        List<Double> precosMaiores500 = produtos.stream()
                .filter(produto -> produto.getPreco() > 500.0)
                .map(produto -> produto.getPreco())
                .collect(Collectors.toList());
        
        System.out.println("Lista de preços: " + precosMaiores500);
        
        // Imprimindo cada preço individualmente
        System.out.println("\nPreços individualmente:");
        precosMaiores500.forEach(preco -> System.out.println("R$ " + preco));
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
