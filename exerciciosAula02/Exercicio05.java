import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Exercicio05 {
    public static void main(String[] args) {
        // Criando lista de produtos
        List<Produto> produtos = criarListaProdutos();
        
        System.out.println("=== EXERCÍCIO 5 ===");
        System.out.println("Obtendo lista de nomes dos produtos:\n");
        
        // Imprimindo produtos originais para referência
        System.out.println("--- Lista completa de produtos ---");
        produtos.forEach(produto -> 
            System.out.println(produto.getNome() + " - R$ " + produto.getPreco() + " - " + produto.getCategoria())
        );
        
        System.out.println();
        
        // f. Usando expressão lambda (p -> p.getNome())
        System.out.println("--- Usando expressão lambda (p -> p.getNome()) ---");
        List<String> nomesComLambda = produtos.stream()
                .map(p -> p.getNome())
                .collect(Collectors.toList());
        
        System.out.println("Lista de nomes (lambda): " + nomesComLambda);
        
        // f. Usando referência de método (Produto::getNome)
        System.out.println("\n--- Usando referência de método (Produto::getNome) ---");
        List<String> nomesComReferencia = produtos.stream()
                .map(Produto::getNome)
                .collect(Collectors.toList());
        
        System.out.println("Lista de nomes (referência): " + nomesComReferencia);
        
        // Verificando se as listas são iguais
        System.out.println("\n--- Verificação ---");
        System.out.println("As listas são iguais? " + nomesComLambda.equals(nomesComReferencia));
        
        // Imprimindo nomes individualmente
        System.out.println("\n--- Nomes dos produtos ---");
        nomesComReferencia.forEach((index, nome) -> 
            System.out.println((index + 1) + ". " + nome)
        );
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
