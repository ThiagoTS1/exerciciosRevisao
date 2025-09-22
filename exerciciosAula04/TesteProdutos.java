import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TesteProdutos {
	public static final String CATEGORIA_ELETRONICOS = "Eletrônicos";
	public static final String CATEGORIA_LIVROS = "Livros";
	public static final String CATEGORIA_VESTUARIO = "Vestuário";
	public static final String CATEGORIA_COZINHA = "Cozinha";

	public static void main(String[] args) {
		List<Produto> produtos = criarProdutos();

		// a) forEach + if tradicional para imprimir nomes dos Eletrônicos
		System.out.println("a) forEach + if (Eletrônicos):");
		produtos.forEach(p -> {
			if (CATEGORIA_ELETRONICOS.equals(p.getCategoria())) {
				System.out.println(p.getNome());
			}
		});

		// a) stream + filter
		System.out.println("\na) stream + filter (Eletrônicos):");
		produtos.stream()
			.filter(p -> CATEGORIA_ELETRONICOS.equals(p.getCategoria()))
			.map(Produto::getNome)
			.forEach(System.out::println);

		// b) preços > 500.0 usando filter + map
		System.out.println("\nb) Preços > 500.0:");
		List<Double> precosMaioresQue500 = produtos.stream()
			.filter(p -> p.getPreco() > 500.0)
			.map(Produto::getPreco)
			.collect(Collectors.toList());
		System.out.println(precosMaioresQue500);

		// c) total do estoque para Livros usando filter + mapToDouble + sum
		double totalLivros = produtos.stream()
			.filter(p -> CATEGORIA_LIVROS.equals(p.getCategoria()))
			.mapToDouble(Produto::getPreco)
			.sum();
		System.out.println("\nc) Total em Livros: " + totalLivros);

		// d) buscarProdutoPorNome
		// e) chamadas no main com ifPresent e orElseThrow
		Optional<Produto> encontrado = buscarProdutoPorNome(produtos, "Notebook Gamer");
		encontrado.ifPresent(p -> System.out.println("\nd) e) Encontrado: " + p));

		try {
			buscarProdutoPorNome(produtos, "Nome Inexistente").orElseThrow(() -> new RuntimeException("Produto não encontrado!"));
		} catch (RuntimeException ex) {
			System.out.println("Exceção esperada: " + ex.getMessage());
		}

		// f) map para List<String> de nomes – lambda
		List<String> nomesLambda = produtos.stream()
			.map(p -> p.getNome())
			.collect(Collectors.toList());
		System.out.println("\nf) Nomes (lambda): " + nomesLambda);

		// f) refatorado para referência de método
		List<String> nomesRefMetodo = produtos.stream()
			.map(Produto::getNome)
			.collect(Collectors.toList());
		System.out.println("f) Nomes (ref método): " + nomesRefMetodo);
	}

	private static List<Produto> criarProdutos() {
		List<Produto> lista = new ArrayList<>();
		lista.add(new Produto("Notebook Gamer", 6500.0, CATEGORIA_ELETRONICOS));
		lista.add(new Produto("Smartphone", 2500.0, CATEGORIA_ELETRONICOS));
		lista.add(new Produto("Fone Bluetooth", 350.0, CATEGORIA_ELETRONICOS));
		lista.add(new Produto("Livro Java", 120.0, CATEGORIA_LIVROS));
		lista.add(new Produto("Livro Streams", 90.0, CATEGORIA_LIVROS));
		lista.add(new Produto("Jaqueta", 320.0, CATEGORIA_VESTUARIO));
		lista.add(new Produto("Air Fryer", 700.0, CATEGORIA_COZINHA));
		lista.add(new Produto("Geladeira", 3800.0, CATEGORIA_COZINHA));
		lista.add(new Produto("Camiseta", 60.0, CATEGORIA_VESTUARIO));
		return lista;
	}

	public static Optional<Produto> buscarProdutoPorNome(List<Produto> produtos, String nome) {
		return produtos.stream()
			.filter(p -> p.getNome().equalsIgnoreCase(nome))
			.findFirst();
	}
}
