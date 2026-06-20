package hamburgueria.cardapio.adicional;

import hamburgueria.cardapio.MenuItem;

import hamburgueria.linhaproduto.ClassicoFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Teste do Padrão Decorator - Complementos")
class ComplementoTest {

    private void imprimirSeparador(String titulo) {
    }

    private MenuItem lancheBase;

    @BeforeEach
    void setup() {
        imprimirSeparador("Configuração Inicial");
        lancheBase = ClassicoFactory.getInstancia().criarHamburguerPrincipal();
    }

    @Test
    @DisplayName("Deve adicionar bacon com decorator")
    void deveAdicionarBacon() {
        imprimirSeparador("Adicionando Bacon");
        double precoOriginal = lancheBase.getPreco();
        MenuItem comBacon = new Bacon(lancheBase);
        
        
        assertTrue(comBacon.getDescricao().contains("bacon"));
        assertEquals(precoOriginal + 4.0, comBacon.getPreco(), 0.01);
    }

    @Test
    @DisplayName("Deve adicionar salada com decorator")
    void deveAdicionarSalada() {
        imprimirSeparador("Adicionando Salada");
        double precoOriginal = lancheBase.getPreco();
        MenuItem comSalada = new Salada(lancheBase);
        
        
        assertTrue(comSalada.getDescricao().contains("salada"));
        assertEquals(precoOriginal, comSalada.getPreco(), 0.01);
    }

    @Test
    @DisplayName("Deve adicionar onion rings com decorator")
    void deveAdicionarOnionRings() {
        imprimirSeparador("Adicionando Onion Rings");
        double precoOriginal = lancheBase.getPreco();
        MenuItem comOnion = new OnionRings(lancheBase);
        
        
        assertTrue(comOnion.getDescricao().contains("onion rings"));
        assertEquals(precoOriginal + 3.0, comOnion.getPreco(), 0.01);
    }

    @Test
    @DisplayName("Deve encadear múltiplos complementos")
    void deveEncadearMultiplosComplementos() {
        imprimirSeparador("Encadeando Múltiplos Complementos");
        double precoOriginal = lancheBase.getPreco();
        MenuItem customizado = new Bacon(new Molho(new Salada(lancheBase)));


        assertTrue(customizado.getDescricao().contains("bacon"));
        assertTrue(customizado.getDescricao().contains("molho especial"));
        assertTrue(customizado.getDescricao().contains("salada"));

        double precoEsperado = precoOriginal + 4.0 + 1.5 + 0.0;
        assertEquals(precoEsperado, customizado.getPreco(), 0.01);
    }
}

