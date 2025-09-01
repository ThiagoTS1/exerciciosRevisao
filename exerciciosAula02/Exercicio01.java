import java.util.List;
import java.util.ArrayList;

public class Exercicio01 {
    public static void main(String[] args) {
        // Criando lista de produtos
        List<Produto> produtos = criarListaProdutos();
        
        System.out.println("=== EXERCÍCIO 1 ===");
        System.out.println("Produtos da categoria 'Eletrônicos':\n");
        
        // a. Usando forEach e estrutura if tradicional
        System.out.println("--- Usando forEach e if tradicional ---");
        produtos.forEach(produto -> {
            if ("Eletrônicos".equals(produto.getCategoria())) {
                System.out.println("Nome: " + produto.getNome());
            }
        });
        
        System.out.println();
        
        // a. Usando stream() e filter()
        System.out.println("--- Usando stream() e filter() ---");
        produtos.stream()
                .filter(produto -> "Eletrônicos".equals(produto.getCategoria()))
                .forEach(produto -> System.out.println("Nome: " + produto.getNome()));
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
