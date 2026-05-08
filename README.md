# Padrões de Projeto: Abstract Factory + Singleton + Bridge

Projeto desenvolvido para a disciplina de **Arquitetura e Projeto de Software**.

Este projeto demonstra a implementação combinada dos padrões criacionais **Abstract Factory** e **Singleton**, aliados ao padrão estrutural **Bridge**, aplicados para modelar o **cardápio de uma hamburgueria**.

A interface `CardapioFactory` define o contrato para criação de lanches, enquanto cada implementação concreta (`CardapioClassico`, `CardapioFit`, `CardapioGourmet`) é gerenciada como Singleton. O padrão Bridge separa a hierarquia de `Proteina` da hierarquia de `GrauCoccao`, permitindo que ambas variem de forma independente.

## Estrutura

| Classe / Interface | Papel |
|---|---|
| `CardapioFactory` | Interface da fábrica abstrata — define o contrato de criação |
| `CardapioClassico` | Fábrica concreta Singleton — cria lanches da linha Clássica |
| `CardapioFit` | Fábrica concreta Singleton — cria lanches da linha Fit |
| `CardapioGourmet` | Fábrica concreta Singleton — cria lanches da linha Gourmet |
| `ItemCardapio` | Interface do produto — define `getDescricao()` e `getPreco()` |
| `Lanche` | Produto concreto — implementa `ItemCardapio`, compõe `Proteina` |
| `Proteina` | Classe abstrata (Bridge) — delega o grau de cocção via `GrauCoccao` |
| `ProteinaSmash` | Implementação concreta — preço base R$ 14,00 |
| `ProteinaFrango` | Implementação concreta — preço base R$ 12,00 |
| `ProteinaPicanha` | Implementação concreta — preço base R$ 18,00 |
| `GrauCoccao` | Interface do Bridge — define `getDescricao()` e `getAdicionalPreco()` |
| `AoPonto` | Implementação concreta — adicional R$ 0,00 |
| `BemPassado` | Implementação concreta — adicional R$ 1,00 |
| `MalPassado` | Implementação concreta — adicional R$ 0,00 |

## Funcionamento

A `CardapioFactory` expõe dois métodos de criação: `criarLanchePrincipal()` e `criarLancheEspecial()`. Cada fábrica instancia um `Lanche` com proteína e pão específicos da sua linha:

- **CardapioClassico** → `ProteinaSmash` com `AoPonto` (principal) ou `BemPassado` (especial)
- **CardapioFit** → `ProteinaFrango` com `BemPassado` (principal) ou `AoPonto` (especial)
- **CardapioGourmet** → `ProteinaPicanha` com `MalPassado` (principal) ou `AoPonto` (especial)

O preço final de um `Lanche` é calculado como:

```
precoFinal = precoBase (Lanche) + precoBase (Proteina) + adicional (GrauCoccao)
```

Isso evita estruturas como:
- `if (linha == "classica") criarSmashAoPonto()`
- `else if (linha == "fit") criarFrangoFit()`

## Como executar

Abrir o projeto no IntelliJ como um projeto Maven e executar os testes localizados em:

- `HamburgueriaTest`
