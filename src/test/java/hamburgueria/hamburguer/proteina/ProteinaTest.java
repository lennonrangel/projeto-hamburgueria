package hamburgueria.hamburguer.proteina;

import hamburgueria.hamburguer.ponto.AoPonto;
import hamburgueria.hamburguer.ponto.BemPassado;
import hamburgueria.hamburguer.ponto.MalPassado;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Teste do Padrão Bridge - Proteínas e Cozimento")
class ProteinaTest {

    private void imprimirSeparador(String titulo) {
    }

    @Test
    @DisplayName("Deve criar proteína de frango com ponto correto")
    void deveCriarFrangoComPonto() {
        imprimirSeparador("Criação de Proteína (Frango)");
        Proteina frango = new ProteinaFrango(new AoPonto());
        assertEquals("frango grelhado ao ponto", frango.getDescricao());
    }

    @Test
    @DisplayName("Deve criar picanha bem passada")
    void deveCriarPicanhaBemPassada() {
        imprimirSeparador("Criação de Proteína (Picanha)");
        Proteina picanha = new ProteinaPicanha(new BemPassado());
        assertEquals("picanha bem passado", picanha.getDescricao());
    }

    @Test
    @DisplayName("Deve calcular preço base da proteína")
    void deveCalcularPrecoBase() {
        imprimirSeparador("Cálculo de Preço da Proteína");
        Proteina smash = new ProteinaSmash(new MalPassado());
        assertEquals(12.0, smash.getPreco(), 0.01);
    }
}
