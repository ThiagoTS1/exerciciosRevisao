package br.com.vcriquinho.service;

import br.com.vcriquinho.model.*;
import java.util.*;
import java.util.stream.Collectors;

public class ClienteService {
    private Map<String, Cliente> clientes = new HashMap<>();
    
    public void cadastrarCliente(Cliente cliente) {
        String documento = cliente.getDocumento();
        if (clientes.containsKey(documento)) {
            throw new IllegalArgumentException("Cliente já cadastrado com este documento");
        }
        clientes.put(documento, cliente);
    }
    
    public Cliente buscarCliente(String documento) {
        Cliente cliente = clientes.get(documento);
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não encontrado");
        }
        return cliente;
    }
    
    public List<Cliente> listarClientes() {
        return new ArrayList<>(clientes.values());
    }
    
    public List<Cliente> buscarClientesPorTipo(String tipo) {
        return clientes.values().stream()
                .filter(cliente -> cliente.getTipoCliente().equals(tipo))
                .collect(Collectors.toList());
    }
    
    public void atualizarCliente(String documento, String novoNome, String novoEmail) {
        Cliente cliente = buscarCliente(documento);
        cliente.setNome(novoNome);
        cliente.setEmail(novoEmail);
    }
    
    public void removerCliente(String documento) {
        Cliente cliente = buscarCliente(documento);
        if (cliente.getContas().isEmpty()) {
            throw new IllegalStateException("Cliente deve ter pelo menos uma conta");
        }
        clientes.remove(documento);
    }
    
    public void adicionarConta(String documento, Conta conta) {
        Cliente cliente = buscarCliente(documento);
        cliente.adicionarConta(conta);
    }
    
    public void removerConta(String documento, Conta conta) {
        Cliente cliente = buscarCliente(documento);
        cliente.removerConta(conta);
    }
    
    public List<Conta> listarContasCliente(String documento) {
        Cliente cliente = buscarCliente(documento);
        return cliente.getContas();
    }
} 