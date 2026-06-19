package hamburgueria.cardapio;

import hamburgueria.linhaproduto.ClassicoFactory;
import hamburgueria.hamburguer.Hamburguer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Teste do Padrão Composite - Refeição Completa")
class RefeicaoCompletaTest {

    private RefeicaoCompleta refeicao;
    private MenuItem hamburguerPrincipal;
    private MenuItem hamburguerAcompanhamento;

    private void imprimirSeparador(String titulo) {
    }

    private void setup() {
        refeicao = new RefeicaoCompleta("Refeição Especial");
        hamburguerPrincipal = ClassicoFactory.getInstancia().criarHamburguerPrincipal();
        hamburguerAcompanhamento = ClassicoFactory.getInstancia().criarHamburguerEspecial();
    }

    @Test
    @DisplayName("Deve criar refeição vazia")
    void deveCriarRefeicaoVazia() {
        imprimirSeparador("Criação de Refeição Vazia");
        setup();
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
        assertEquals(precoEsperado, refeicao.getPreco(), 0.01);
    }

    @Test
    @DisplayName("Deve remover itens da refeição")
    void deveRemoverItensDaRefeicao() {
        imprimirSeparador("Remoção de Itens do Composite");
        setup();
        refeicao.adicionarItem(hamburguerPrincipal);
        refeicao.adicionarItem(hamburguerAcompanhamento);
        refeicao.removerItem(hamburguerPrincipal);
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
        
        assertEquals(2, refeicao.getItens().size());
        assertTrue(refeicao.getPreco() > 0);
    }
}

