package padroesestruturais.bridge;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Teste do Padrão Bridge - Proteínas e Cozimento")
class ProteinaTest {

    private void imprimirSeparador(String titulo) {
        System.out.println("\n========================================================");
        System.out.println(" [BRIDGE] " + titulo);
        System.out.println("========================================================");
    }

    @Test
    @DisplayName("Deve criar proteína de frango com ponto correto")
    void deveCriarFrangoComPonto() {
        imprimirSeparador("Criação de Proteína (Frango)");
        Proteina frango = new ProteinaFrango(new AoPonto());
        System.out.println("Descrição: " + frango.getDescricao());
        assertEquals("frango grelhado ao ponto", frango.getDescricao());
    }

    @Test
    @DisplayName("Deve criar picanha bem passada")
    void deveCriarPicanhaBemPassada() {
        imprimirSeparador("Criação de Proteína (Picanha)");
        Proteina picanha = new ProteinaPicanha(new BemPassado());
        System.out.println("Descrição: " + picanha.getDescricao());
        assertEquals("picanha bem passado", picanha.getDescricao());
    }

    @Test
    @DisplayName("Deve calcular preço base da proteína")
    void deveCalcularPrecoBase() {
        imprimirSeparador("Cálculo de Preço da Proteína");
        Proteina smash = new ProteinaSmash(new MalPassado());
        System.out.println("Preço: R$ " + String.format("%.2f", smash.getPreco()));
        assertEquals(12.0, smash.getPreco(), 0.01);
    }
}
