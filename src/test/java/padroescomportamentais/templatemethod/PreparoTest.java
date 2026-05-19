package padroescomportamentais.templatemethod;

import padroescomportamentais.state.Pedido;
import padroescriacao.abstractfactory.ClassicoFactory;
import padroescriacao.abstractfactory.GourmetFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Teste do Padrão Template Method - Preparo de Pedidos")
class PreparoTest {

    private void imprimirSeparador(String titulo) {
        System.out.println("\n========================================================");
        System.out.println(" [TEMPLATE METHOD] " + titulo);
        System.out.println("========================================================");
        System.out.println();
    }

    @BeforeEach
    void setup(TestInfo testInfo) {
        imprimirSeparador(testInfo.getDisplayName());
    }

    @Test
    @DisplayName("Deve usar template method clássico para preparar")
    void deveUsarTemplateMethodClassico() {
        Pedido pedido = new Pedido();
        pedido.adicionarItem(ClassicoFactory.getInstancia().criarHamburguerPrincipal());
        pedido.avancar();
        new ProcessoPreparoClassico().preparar(pedido);
        assertEquals("Pronto", pedido.getEstadoAtual());
    }

    @Test
    @DisplayName("Deve usar template method gourmet para preparar")
    void deveUsarTemplateMethodGourmet() {
        Pedido pedido = new Pedido();
        pedido.adicionarItem(GourmetFactory.getInstancia().criarHamburguerPrincipal());
        pedido.avancar();
        new ProcessoPreparoGourmet().preparar(pedido);
        assertEquals("Pronto", pedido.getEstadoAtual());
    }

    @Test
    @DisplayName("Deve usar template method fit para preparar")
    void deveUsarTemplateMethodFit() {
        Pedido pedido = new Pedido();
        pedido.adicionarItem(ClassicoFactory.getInstancia().criarHamburguerPrincipal());
        pedido.avancar();
        new ProcessoPreparoFit().preparar(pedido);
        assertEquals("Pronto", pedido.getEstadoAtual());
    }
}
