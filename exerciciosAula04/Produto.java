public class Produto {
	private final String nome;
	private final double preco;
	private final String categoria;

	public Produto(String nome, double preco, String categoria) {
		this.nome = nome;
		this.preco = preco;
		this.categoria = categoria;
	}

	public String getNome() {
		return nome;
	}

	public double getPreco() {
		return preco;
	}

	public String getCategoria() {
		return categoria;
	}

	@Override
	public String toString() {
		return "Produto{" +
			"nome='" + nome + '\'' +
			", preco=" + preco +
			", categoria='" + categoria + '\'' +
			'}';
	}
}
