import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

public class Exercicio04 {
    public static void main(String[] args) {
        // Criando lista de produtos
        List<Produto> produtos = criarListaProdutos();
        
        System.out.println("=== EXERCÍCIO 4 ===");
        System.out.println("Busca de produtos por nome:\n");
        
        // Imprimindo lista de produtos para referência
        System.out.println("--- Lista completa de produtos ---");
        produtos.forEach(produto -> 
            System.out.println(produto.getNome() + " - R$ " + produto.getPreco() + " - " + produto.getCategoria())
        );
        
        System.out.println();
        
        // e. Buscando produto que existe
        System.out.println("--- Buscando produto que EXISTE ---");
        String nomeProdutoExistente = "Smartphone Samsung";
        Optional<Produto> produtoEncontrado = buscarProdutoPorNome(produtos, nomeProdutoExistente);
        
        produtoEncontrado.ifPresent(produto -> {
            System.out.println("Produto encontrado!");
            System.out.println("Nome: " + produto.getNome());
            System.out.println("Preço: R$ " + produto.getPreco());
            System.out.println("Categoria: " + produto.getCategoria());
        });
        
        System.out.println();
        
        // e. Buscando produto que NÃO existe
        System.out.println("--- Buscando produto que NÃO existe ---");
        String nomeProdutoInexistente = "Produto Inexistente";
        
        try {
            Produto produtoInexistente = buscarProdutoPorNome(produtos, nomeProdutoInexistente)
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));
        } catch (RuntimeException e) {
            System.out.println("Erro capturado: " + e.getMessage());
        }
    }
    
    // d. Método que retorna Optional<Produto> usando Stream API
    public static Optional<Produto> buscarProdutoPorNome(List<Produto> produtos, String nome) {
        return produtos.stream()
                .filter(produto -> produto.getNome().equals(nome))
                .findFirst();
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
