package padroesestruturais.decorator;

import padroescriacao.abstractfactory.ClassicoFactory;
import padroesestruturais.composite.ItemCardapio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Teste do Padrão Decorator - Complementos")
class ComplementoTest {

    private void imprimirSeparador(String titulo) {
        System.out.println("\n========================================================");
        System.out.println(" [DECORATOR] " + titulo);
        System.out.println("========================================================\n");
    }

    private ItemCardapio lancheBase;

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
        ItemCardapio comBacon = new Bacon(lancheBase);
        
        System.out.println("Lanche Original: " + lancheBase.getDescricao());
        System.out.println("Lanche com Bacon: " + comBacon.getDescricao());
        System.out.println("Acréscimo no preço: R$ 4,00");
        System.out.println("Valor final: R$ " + String.format("%.2f", comBacon.getPreco()));
        
        assertTrue(comBacon.getDescricao().contains("bacon"));
        assertEquals(precoOriginal + 4.0, comBacon.getPreco(), 0.01);
    }

    @Test
    @DisplayName("Deve adicionar salada com decorator")
    void deveAdicionarSalada() {
        imprimirSeparador("Adicionando Salada");
        double precoOriginal = lancheBase.getPreco();
        ItemCardapio comSalada = new Salada(lancheBase);
        
        System.out.println("Lanche Original: " + lancheBase.getDescricao());
        System.out.println("Lanche com Salada: " + comSalada.getDescricao());
        System.out.println("Acréscimo no preço: R$ 0,00 (Grátis)");
        System.out.println("Valor final: R$ " + String.format("%.2f", comSalada.getPreco()));
        
        assertTrue(comSalada.getDescricao().contains("salada"));
        assertEquals(precoOriginal, comSalada.getPreco(), 0.01);
    }

    @Test
    @DisplayName("Deve adicionar onion rings com decorator")
    void deveAdicionarOnionRings() {
        imprimirSeparador("Adicionando Onion Rings");
        double precoOriginal = lancheBase.getPreco();
        ItemCardapio comOnion = new OnionRings(lancheBase);
        
        System.out.println("Lanche Original: " + lancheBase.getDescricao());
        System.out.println("Lanche com Onion Rings: " + comOnion.getDescricao());
        System.out.println("Acréscimo no preço: R$ 3,00");
        System.out.println("Valor final: R$ " + String.format("%.2f", comOnion.getPreco()));
        
        assertTrue(comOnion.getDescricao().contains("onion rings"));
        assertEquals(precoOriginal + 3.0, comOnion.getPreco(), 0.01);
    }

    @Test
    @DisplayName("Deve encadear múltiplos complementos")
    void deveEncadearMultiplosComplementos() {
        imprimirSeparador("Encadeando Múltiplos Complementos");
        double precoOriginal = lancheBase.getPreco();
        ItemCardapio customizado = new Bacon(new Molho(new Salada(lancheBase)));

        System.out.println("Lanche Original: " + lancheBase.getDescricao());
        System.out.println("Lanche Customizado: " + customizado.getDescricao());
        System.out.println("Valor total com complementos: R$ " + String.format("%.2f", customizado.getPreco()));

        assertTrue(customizado.getDescricao().contains("bacon"));
        assertTrue(customizado.getDescricao().contains("molho especial"));
        assertTrue(customizado.getDescricao().contains("salada"));

        double precoEsperado = precoOriginal + 4.0 + 1.5 + 0.0;
        assertEquals(precoEsperado, customizado.getPreco(), 0.01);
    }
}
