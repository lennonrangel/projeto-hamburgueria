package padroesestruturais.composite;

import padroescriacao.abstractfactory.ClassicoFactory;
import padroescriacao.abstractfactory.Hamburguer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Teste do Padrão Composite - Refeição Completa")
class RefeicaoCompletaTest {

    private RefeicaoCompleta refeicao;
    private ItemCardapio hamburguerPrincipal;
    private ItemCardapio hamburguerAcompanhamento;

    private void imprimirSeparador(String titulo) {
        System.out.println("\n========================================================");
        System.out.println(" [COMPOSITE] " + titulo);
        System.out.println("========================================================");
    }

    private void setup() {
        refeicao = new RefeicaoCompleta("Refeição Especial");
        hamburguerPrincipal = ClassicoFactory.getInstancia().criarHamburguerPrincipal();
        System.out.println(); // Espaço entre a criação dos itens
        hamburguerAcompanhamento = ClassicoFactory.getInstancia().criarHamburguerEspecial();
    }

    @Test
    @DisplayName("Deve criar refeição vazia")
    void deveCriarRefeicaoVazia() {
        imprimirSeparador("Criação de Refeição Vazia");
        System.out.println();
        setup();
        System.out.println("Descrição: " + refeicao.getDescricao());
        assertTrue(refeicao.getItens().isEmpty());
        assertEquals(0, refeicao.getPreco(), 0.01);
    }

    @Test
    @DisplayName("Deve adicionar itens à refeição")
    void deveAdicionarItensARefeicao() {
        imprimirSeparador("Adição de Itens ao Composite");

        setup();
        refeicao.adicionarItem(hamburguerPrincipal);
        refeicao.adicionarItem(hamburguerAcompanhamento);
        System.out.println("Itens na refeição: " + refeicao.getItens().size());
        assertEquals(2, refeicao.getItens().size());
        assertTrue(refeicao.getItens().contains(hamburguerPrincipal));
        assertTrue(refeicao.getItens().contains(hamburguerAcompanhamento));
    }

    @Test
    @DisplayName("Deve calcular preço total da refeição")
    void deveCalcularPrecoRefeicao() {
        imprimirSeparador("Cálculo de Preço Composto");
        setup();
        refeicao.adicionarItem(hamburguerPrincipal);
        refeicao.adicionarItem(hamburguerAcompanhamento);
        double precoEsperado = hamburguerPrincipal.getPreco() + hamburguerAcompanhamento.getPreco();
        System.out.println("Preço total calculado: R$ " + String.format("%.2f", refeicao.getPreco()));
        assertEquals(precoEsperado, refeicao.getPreco(), 0.01);
    }

    @Test
    @DisplayName("Deve remover itens da refeição")
    void deveRemoverItensDaRefeicao() {
        imprimirSeparador("Remoção de Itens do Composite");
        setup();
        refeicao.adicionarItem(hamburguerPrincipal);
        refeicao.adicionarItem(hamburguerAcompanhamento);
        System.out.println("Itens antes da remoção: " + refeicao.getItens().size());
        refeicao.removerItem(hamburguerPrincipal);
        System.out.println("Itens após a remoção: " + refeicao.getItens().size());
        assertEquals(1, refeicao.getItens().size());
        assertFalse(refeicao.getItens().contains(hamburguerPrincipal));
    }

    @Test
    @DisplayName("Deve retornar descrição composta corretamente")
    void deveRetornarDescricaoComposta() {
        imprimirSeparador("Descrição do Objeto Composto");
        setup();
        refeicao.adicionarItem(new Hamburguer("Burger A", 10.0));
        refeicao.adicionarItem(new Hamburguer("Burger B", 5.0));
        String descricao = refeicao.getDescricao();
        System.out.println("Descrição Hierárquica:\n" + descricao);
        assertTrue(descricao.contains("Refeição Especial"));
        assertTrue(descricao.contains("Burger A"));
        assertTrue(descricao.contains("Burger B"));
    }

    @Test
    @DisplayName("Deve permitir refeições aninhadas (refeição dentro de refeição)")
    void devePermitirRefeicoesAninhadas() {
        imprimirSeparador("Composição Recursiva (Composite aninhado)");
        setup();
        RefeicaoCompleta refeicaoInterna = new RefeicaoCompleta("Refeição Interna");
        refeicaoInterna.adicionarItem(hamburguerPrincipal);
        refeicao.adicionarItem(refeicaoInterna);
        refeicao.adicionarItem(hamburguerAcompanhamento);
        
        System.out.println("Descrição Final:\n" + refeicao.getDescricao());
        assertEquals(2, refeicao.getItens().size());
        assertTrue(refeicao.getPreco() > 0);
    }
}
