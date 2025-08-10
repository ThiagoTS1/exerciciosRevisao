package br.com.vcriquinho.service;

import br.com.vcriquinho.model.*;
import java.util.*;

public class ProdutoService {
    private Map<String, ProdutoInvestimento> produtos = new HashMap<>();
    
    public void cadastrarProduto(ProdutoInvestimento produto) {
        String nome = produto.getNome();
        if (produtos.containsKey(nome)) {
            throw new IllegalArgumentException("Produto já cadastrado com este nome");
        }
        produtos.put(nome, produto);
    }
    
    public ProdutoInvestimento buscarProduto(String nome) {
        ProdutoInvestimento produto = produtos.get(nome);
        if (produto == null) {
            throw new IllegalArgumentException("Produto não encontrado");
        }
        return produto;
    }
    
    public List<ProdutoInvestimento> listarProdutos() {
        return new ArrayList<>(produtos.values());
    }
    
    public List<ProdutoInvestimento> listarProdutosPorTipo(String tipo) {
        List<ProdutoInvestimento> produtosFiltrados = new ArrayList<>();
        for (ProdutoInvestimento produto : produtos.values()) {
            if (produto.getTipoProduto().equals(tipo)) {
                produtosFiltrados.add(produto);
            }
        }
        return produtosFiltrados;
    }
    
    public void atualizarProduto(String nome, ProdutoInvestimento novoProduto) {
        if (!produtos.containsKey(nome)) {
            throw new IllegalArgumentException("Produto não encontrado");
        }
        produtos.remove(nome);
        produtos.put(novoProduto.getNome(), novoProduto);
    }
    
    public void removerProduto(String nome) {
        if (!produtos.containsKey(nome)) {
            throw new IllegalArgumentException("Produto não encontrado");
        }
        produtos.remove(nome);
    }
} 