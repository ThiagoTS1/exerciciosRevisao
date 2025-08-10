@startuml SistemaLanchonete

' Classe Abstrata Item
abstract class Item {
    - precoVenda: double
    - dataValidade: LocalDate
    - peso: double
    - nome: String
    
    + getPrecoVenda(): double
    + setPrecoVenda(double): void
    + getDataValidade(): LocalDate
    + setDataValidade(LocalDate): void
    + getPeso(): double
    + setPeso(double): void
    + getNome(): String
    + setNome(String): void
    + getDescricaoCompleta(): String {abstract}
    + estaVencido(): boolean
}

' Classes que herdam de Item
class Pizza {
    - recheio: String
    - borda: String
    - molho: String
    - bordaRecheada: boolean
    
    + getRecheio(): String
    + setRecheio(String): void
    + getBorda(): String
    + setBorda(String): void
    + getMolho(): String
    + setMolho(String): void
    + isBordaRecheada(): boolean
    + setBordaRecheada(boolean): void
    + getDescricaoCompleta(): String
}

class Lanche {
    - tipoPao: String
    - recheio: String
    - molho: String
    
    + getTipoPao(): String
    + setTipoPao(String): void
    + getRecheio(): String
    + setRecheio(String): void
    + getMolho(): String
    + setMolho(String): void
    + getDescricaoCompleta(): String
}

class Salgadinho {
    - tipo: String
    - massa: String
    - recheio: String
    
    + getTipo(): String
    + setTipo(String): void
    + getMassa(): String
    + setMassa(String): void
    + getRecheio(): String
    + setRecheio(String): void
    + getDescricaoCompleta(): String
}

' Classe ItemPedido
class ItemPedido {
    - item: Item
    - quantidade: int
    
    + getItem(): Item
    + setItem(Item): void
    + getQuantidade(): int
    + setQuantidade(int): void
    + getSubtotal(): double
    + getDescricaoCompleta(): String
}

' Classe Pedido
class Pedido {
    - nomeCliente: String
    - itens: List<ItemPedido>
    - taxaServico: double
    - dataHora: LocalDateTime
    - numeroPedido: int
    - {static} contadorPedidos: int
    
    + getNomeCliente(): String
    + setNomeCliente(String): void
    + getItens(): List<ItemPedido>
    + getTaxaServico(): double
    + setTaxaServico(double): void
    + getDataHora(): LocalDateTime
    + getNumeroPedido(): int
    + adicionarItem(Item, int): void
    + removerItem(int): void
    + getSubtotalItens(): double
    + getValorTaxaServico(): double
    + getTotal(): double
    + getResumoPedido(): String
}

' Classe NotaFiscal
class NotaFiscal {
    - pedido: Pedido
    - cnpj: String
    - endereco: String
    - telefone: String
    
    + getPedido(): Pedido
    + setPedido(Pedido): void
    + getCnpj(): String
    + setCnpj(String): void
    + getEndereco(): String
    + setEndereco(String): void
    + getTelefone(): String
    + setTelefone(String): void
    + gerarNotaFiscal(): String
}

' Classe principal do sistema
class SistemaLanchonete {
    - cardapio: List<Item>
    - pedidos: List<Pedido>
    - scanner: Scanner
    - {static final} CNPJ: String
    - {static final} ENDERECO: String
    - {static final} TELEFONE: String
    - {static final} TAXA_SERVICO_PADRAO: double
    
    + inicializarCardapio(): void
    + exibirCardapio(): void
    + criarPedido(): void
    + gerarNotaFiscal(): void
    + calcularTroco(): void
    + exibirPedidos(): void
    + executarSistema(): void
    + {static} main(String[]): void
}

' Relacionamentos de Herança
Item <|-- Pizza
Item <|-- Lanche
Item <|-- Salgadinho

' Relacionamentos de Composição
Item *-- ItemPedido : contém
ItemPedido *-- Pedido : compõe
Pedido *-- NotaFiscal : gera

' Relacionamentos de Agregação
SistemaLanchonete o-- Item : gerencia (cardápio)
SistemaLanchonete o-- Pedido : gerencia (pedidos)

' Notas sobre conceitos de POO
note right of Item
  **Classe Abstrata**
  - Define interface comum
  - Método abstrato getDescricaoCompleta()
end note

note right of Pizza
  **Herança + Polimorfismo**
  - Implementa getDescricaoCompleta()
  - Atributos específicos de pizza
end note

note right of Pedido
  **Composição**
  - Contém lista de ItemPedido
  - Gerencia itens do pedido
end note

note right of SistemaLanchonete
  **Classe Principal**
  - Gerencia todo o sistema
  - Agrega itens e pedidos
end note

@enduml 