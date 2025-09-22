import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main {

    public static Optional<Produto> buscarProdutoPorNome(List<Produto> produtos, String nome) {
        return produtos.stream()
                .filter(p -> p.getNome().equalsIgnoreCase(nome))
                .findFirst();
    }

    public static void main(String[] args) {
        List<Produto> produtos = new ArrayList<>();
        produtos.add(new Produto("Notebook", 4500.0, Produto.CATEGORIA_ELETRONICOS));
        produtos.add(new Produto("Smartphone", 2500.0, Produto.CATEGORIA_ELETRONICOS));
        produtos.add(new Produto("Fone Bluetooth", 350.0, Produto.CATEGORIA_ELETRONICOS));
        produtos.add(new Produto("Livro Java", 120.0, Produto.CATEGORIA_LIVROS));
        produtos.add(new Produto("Livro Streams", 80.0, Produto.CATEGORIA_LIVROS));
        produtos.add(new Produto("TV 55\"", 2800.0, Produto.CATEGORIA_ELETRONICOS));
        produtos.add(new Produto("Cafeteira", 320.0, "Eletrodomésticos"));
        produtos.add(new Produto("Câmera", 1900.0, Produto.CATEGORIA_ELETRONICOS));
        produtos.add(new Produto("Kindle", 570.0, Produto.CATEGORIA_ELETRONICOS));

        // a) forEach + if tradicional
        System.out.println("Eletrônicos (forEach + if):");
        produtos.forEach(p -> {
            if (Produto.CATEGORIA_ELETRONICOS.equals(p.getCategoria())) {
                System.out.println(p.getNome());
            }
        });

        // a) stream + filter
        System.out.println("Eletrônicos (stream + filter):");
        produtos.stream()
                .filter(p -> Produto.CATEGORIA_ELETRONICOS.equals(p.getCategoria()))
                .forEach(p -> System.out.println(p.getNome()));

        // b) lista de preços > 500.0 usando filter + map
        List<Double> precosMaioresQue500 = produtos.stream()
                .filter(p -> p.getPreco() > 500.0)
                .map(Produto::getPreco)
                .collect(Collectors.toList());
        System.out.println("Preços > 500.0: " + precosMaioresQue500);

        // c) total do estoque de livros
        double totalLivros = produtos.stream()
                .filter(p -> Produto.CATEGORIA_LIVROS.equals(p.getCategoria()))
                .mapToDouble(Produto::getPreco)
                .sum();
        System.out.println("Total de Livros: " + totalLivros);

        // d) método buscarProdutoPorNome já implementado acima

        // e) chamar buscarProdutoPorNome - existente com ifPresent
        buscarProdutoPorNome(produtos, "Notebook").ifPresent(p ->
                System.out.println("Encontrado: " + p)
        );

        // e) chamar com nome que não existe - orElseThrow
        try {
            Produto inexistente = buscarProdutoPorNome(produtos, "Geladeira").orElseThrow(
                    () -> new RuntimeException("Produto não encontrado!")
            );
            System.out.println(inexistente); // não será executado
        } catch (RuntimeException ex) {
            System.out.println("Exceção: " + ex.getMessage());
        }

        // f) map para List<String> de nomes - expressão lambda
        List<String> nomesLambda = produtos.stream()
                .map(p -> p.getNome())
                .collect(Collectors.toList());
        System.out.println("Nomes (lambda): " + nomesLambda);

        // f) refatorar para referência de método
        List<String> nomesMethodRef = produtos.stream()
                .map(Produto::getNome)
                .collect(Collectors.toList());
        System.out.println("Nomes (method ref): " + nomesMethodRef);
    }
}


