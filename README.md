# Projeto Hamburgueria - Padrões de Projeto

Este projeto foi desenvolvido para a disciplina de **Arquitetura e Projeto de Software**, com o objetivo de demonstrar a aplicação prática de diversos padrões de projeto (Design Patterns) em um sistema de gerenciamento de uma hamburgueria.

O sistema abrange desde a criação do cardápio e montagem de hamburgueres até o processamento de pedidos, notificações e pagamentos, utilizando padrões criacionais, estruturais e comportamentais.

## Padrões de Projeto Implementados

O projeto utiliza **23 padrões de projeto** do GoF (Gang of Four):

### 1. Padrões Criacionais

| Padrão | Descrição no Projeto |
|---|---|
| **Abstract Factory** | A interface `HamburguerFactory` e suas implementações (`ClassicoFactory`, `FitFactory`, `GourmetFactory`) definem famílias de produtos relacionados (hambúrgueres e ingredientes). |
| **Factory Method** | Os métodos `criarHamburguerPrincipal` e `criarHamburguerEspecial` na interface `HamburguerFactory` permitem que as subclasses decidam qual hambúrguer específico instanciar. As fábricas de combos (`ComboClassicoFactory`, etc.) também utilizam este padrão. |
| **Singleton** | O `GeradorCodigoPedido` garante uma instância única para controle de numeração sequencial de todos os pedidos do sistema. Além disso, as fábricas de cardápio são implementadas como Singletons. |
| **Builder** | O `MontagemHamburguer` e `ChefeCozinha` permitem a construção passo a passo de hamburgueres complexos, separando a construção da representação final. |
| **Prototype** | A `ReceitaHamburguerPrototype` permite clonar receitas-base de hambúrguer e customizar cópias sem alterar o protótipo original. |

### 2. Padrões Estruturais

| Padrão | Descrição no Projeto |
|---|---|
| **Bridge** | Separa a abstração `Proteina` de sua implementação `GrauCoccao`, permitindo que ambas variem de forma independente. |
| **Adapter** | O `IngredientePesadoAdapter` adapta um ingrediente externo vendido por peso para a interface interna `ItemCardapio`. |
| **Decorator** | O `Complemento` permite adicionar funcionalidades (ingredientes extras como `Bacon`, `Molho`, `Salada`, `OnionRings`, `Picles`) a um `ItemCardapio` de forma dinâmica. |
| **Composite** | A classe `RefeicaoCompleta` permite tratar itens individuais e grupos de itens (combos) de forma uniforme, ambos implementando a interface `ItemCardapio`. |
| **Facade** | O `GestorPedidos` oferece uma interface simplificada para as funcionalidades complexas do sistema, como abrir pedidos e processar pagamentos. |
| **Flyweight** | A `IngredienteFactory` reutiliza instâncias compartilhadas de ingredientes recorrentes, separando dados intrínsecos do ingrediente do estado externo, como quantidade em estoque. |
| **Proxy** | O `RelatorioFinanceiroProxy` controla o acesso ao relatório financeiro, liberando a consulta somente para usuários com perfil de gerente. |

### 3. Padrões Comportamentais

| Padrão | Descrição no Projeto |
|---|---|
| **Mediator** | A `CentralHamburgueria` atua como mediadora na comunicação entre o `Atendente`, o `Caixa` e os processos internos, reduzindo o acoplamento. |
| **Command** | Os comandos `AdicionarItemCommand` e `RemoverItemCommand` encapsulam ações sobre um pedido e permitem desfazer a última operação. |
| **Interpreter** | As expressões de cupom (`ExpressaoRetiradaBalcao`, `ExpressaoTotalMaiorQue`, `ExpressaoE`) interpretam regras simples para decidir se um desconto deve ser aplicado. |
| **Observer** | O `Pedido` atua como sujeito, notificando observadores como `ClienteNotificador` e `CozinhaNotificador` sobre mudanças em seu estado. |
| **Strategy** | As diferentes formas de pagamento (`PagamentoPix`, `PagamentoCartao`, `PagamentoDinheiro`) são encapsuladas como estratégias que podem ser trocadas em tempo de execução. |
| **Chain of Responsibility** | O sistema de descontos (`DescontoPedido`) utiliza uma corrente de responsabilidade para aplicar múltiplas regras de desconto de forma sequencial. |
| **State** | O estado do pedido (`EstadoPedido`) é gerenciado de forma que o comportamento do objeto mude conforme seu estado transita (ex: `PedidoRecebido` para `PedidoEmPreparo`). |
| **Template Method** | A classe `ProcessoPreparo` define o esqueleto do algoritmo de preparo, permitindo que subclasses customizem etapas específicas (como `prepararHamburguer`). |
| **Memento** | O `HistoricoPedido` e `RegistroPedido` permitem salvar e restaurar estados anteriores de um `Pedido`, possibilitando um histórico de estados. |
| **Visitor** | Permite adicionar novas operações a um `Pedido` (como `CalculadoraTotal` e `ImpressorResumo`) sem alterar sua classe. |
| **Iterator** | O `Cardapio` cria um `IteradorCardapio` para percorrer itens disponíveis sem expor diretamente a lógica de navegação da coleção. |

## Diagrama de Estado
O fluxo de estados de um pedido é representado pelo seguinte diagrama:

<div align="center">
  <img width="700" height="878" alt="Image" src="https://github.com/user-attachments/assets/cf2e57f1-13c4-4638-89cb-adec6d5743ad" />
</div>
  
## Diagrama de Classes

Ilustra a arquitetura do sistema, destacando a integração dos 19 padrões de projeto e as relações de dependência entre as fábricas, produtos e o processamento de pedidos.

<img width="9963" height="9308" alt="Image" src="https://github.com/user-attachments/assets/0d6dca2e-d5c6-4c53-9c50-50428cb5e6c1" />

## Estrutura do Projeto

O código está organizado nos seguintes pacotes:

- `hamburgueria`: Classe principal de execução.
- `padroescriacao`: Implementações de Abstract Factory, Builder, Factory Method, Prototype e Singleton.
- `padroesestruturais`: Implementações de Adapter, Bridge, Composite, Decorator, Facade, Flyweight e Proxy.
- `padroescomportamentais`: Implementações de Chain of Responsibility, Command, Interpreter, Iterator, Mediator, Memento, Observer, State, Strategy, Template Method e Visitor.

## Como Executar

O projeto é baseado em **Maven** e requer o Java 21 ou superior.

1.  Clone o repositório.
2.  Abra o projeto em sua IDE (recomendado IntelliJ IDEA).
3.  Execute os testes unitários localizados em `src/test/java/` para validar todas as implementações e fluxos.
