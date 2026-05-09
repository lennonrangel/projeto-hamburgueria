# Projeto Hamburgueria - Padrões de Projeto

Este projeto foi desenvolvido para a disciplina de **Arquitetura e Projeto de Software**, com o objetivo de demonstrar a aplicação prática de diversos padrões de projeto (Design Patterns) em um sistema de gerenciamento de uma hamburgueria.

O sistema abrange desde a criação do cardápio e montagem de lanches até o processamento de pedidos, notificações e pagamentos, utilizando padrões criacionais, estruturais e comportamentais.

## Padrões de Projeto Implementados

O projeto utiliza **14 padrões de projeto** do GoF (Gang of Four):

### 1. Padrões Criacionais

| Padrão | Descrição no Projeto |
|---|---|
| **Abstract Factory** | A interface `CardapioFactory` e suas implementações (`CardapioClassico`, `CardapioFit`, `CardapioGourmet`) definem famílias de produtos relacionados (lanches e ingredientes). |
| **Factory Method** | Os métodos `criarLanchePrincipal` e `criarLancheEspecial` na interface `CardapioFactory` permitem que as subclasses decidam qual lanche específico instanciar. |
| **Singleton** | As fábricas de cardápio são implementadas como Singletons para garantir uma única instância global de cada tipo de cardápio. |
| **Builder** | O `LancheBuilder` e `MontadorLanche` permitem a construção passo a passo de lanches complexos, separando a construção da representação final. |

### 2. Padrões Estruturais

| Padrão | Descrição no Projeto |
|---|---|
| **Bridge** | Separa a abstração `Proteina` de sua implementação `GrauCoccao`, permitindo que ambas variem de forma independente. |
| **Decorator** | O `IngredienteDecorator` permite adicionar funcionalidades (ingredientes extras como `BaconExtra`, `QueijoExtra`) a um `ItemCardapio` de forma dinâmica. |
| **Composite** | A classe `Combo` permite tratar itens individuais e grupos de itens (combos) de forma uniforme, ambos implementando a interface `ItemCardapio`. |
| **Facade** | O `SistemaHamburgueria` oferece uma interface simplificada para as funcionalidades complexas do sistema, como abrir pedidos e processar pagamentos. |

### 3. Padrões Comportamentais

| Padrão | Descrição no Projeto |
|---|---|
| **Mediator** | A `CentralHamburgueria` atua como mediadora na comunicação entre o `Atendente`, o `Caixa` e os processos internos, reduzindo o acoplamento. |
| **Observer** | O `Pedido` atua como sujeito, notificando observadores como `ClienteNotificador` e `CozinhaNotificador` sobre mudanças em seu estado. |
| **Strategy** | As diferentes formas de pagamento (`PagamentoPix`, `PagamentoCartao`, `PagamentoDinheiro`) são encapsuladas como estratégias que podem ser trocadas em tempo de execução. |
| **Chain of Responsibility** | O sistema de descontos (`DescontoPedido`) utiliza uma corrente de responsabilidade para aplicar múltiplas regras de desconto de forma sequencial. |
| **State** | O estado do pedido (`EstadoPedido`) é gerenciado de forma que o comportamento do objeto mude conforme seu estado transita (ex: `PedidoRecebido` para `PedidoEmPreparo`). |
| **Template Method** | O `PreparoPedidoTemplate` define o esqueleto do algoritmo de preparo, permitindo que subclasses customizem etapas específicas (como `prepararLanche`). |

## Diagrama de Estado
O fluxo de estados de um pedido é representado pelo seguinte diagrama:

<div align="center">
  <img width="600" height="878" alt="Image" src="https://github.com/user-attachments/assets/81995b5e-75ee-4075-b987-2cbf6b83325a" />
</div>
  
## Diagrama de Classes

Ilustra a arquitetura do sistema, destacando a integração dos 14 padrões de projeto e as relações de dependência entre as fábricas, produtos e o processamento de pedidos.

<img width="14828" height="8054" alt="Image" src="https://github.com/user-attachments/assets/24d5699f-6c53-4a19-88f8-e5e041b88d8f" />

## Estrutura do Projeto

O código está organizado nos seguintes pacotes:

- `atendimento`: Contém a lógica de mediação e os atores do sistema (Atendente, Caixa).
- `cardapio`: Define os itens, fábricas, construtores e decoradores de ingredientes.
- `notificacao`: Implementa o padrão Observer para alertas de sistema.
- `pagamento`: Gerencia as estratégias de pagamento e a cadeia de descontos.
- `pedido`: Contém a entidade principal, o gerenciamento de estados e o template de preparo.

## Como Executar

O projeto é baseado em **Maven** e requer o Java 21 ou superior.

1.  Clone o repositório.
2.  Abra o projeto em sua IDE (recomendado IntelliJ IDEA).
3.  Execute os testes unitários em `src/test/java/br/com/hamburgueria/HamburgueriaTest.java` para validar todas as implementações e fluxos.
