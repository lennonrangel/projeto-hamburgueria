package padroescriacao.builder;

import padroesestruturais.composite.ItemCardapio;
import padroesestruturais.bridge.AoPonto;
import padroesestruturais.bridge.ProteinaSmash;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Teste do Padrão Builder - Montagem de Hambúrgueres")
class MontagemHamburguerTest {

    private void imprimirSeparador(String titulo) {
        System.out.println("\n========================================================");
        System.out.println(" [BUILDER] " + titulo);
        System.out.println("========================================================");
    }

    @Test
    @DisplayName("Deve montar hambúrguer completo usando builder")
    void deveConstruirHamburguerComBuilder() {
        imprimirSeparador("Montagem Completa");
        System.out.println();
        ItemCardapio hamburguer = new ChefeCozinha()
                .comNome("Smash Premium")
                .comPao("brioche")
                .comProteina(new ProteinaSmash(new AoPonto()))
                .comPrecoBase(15.0)
                .comQueijo("cheddar")
                .comBacon()
                .comSalada()
                .comPicles()
                .comOnionRings()
                .montar();

        assertNotNull(hamburguer);
        assertTrue(hamburguer.getDescricao().contains("queijo cheddar"));
        assertTrue(hamburguer.getDescricao().contains("bacon"));
        assertTrue(hamburguer.getDescricao().contains("salada"));
        assertTrue(hamburguer.getDescricao().contains("picles"));
        assertTrue(hamburguer.getDescricao().contains("onion rings"));
        assertTrue(hamburguer.getDescricao().contains("smash burger ao ponto"));
        System.out.println("Descrição final: " + hamburguer.getDescricao());
    }

    @Test
    @DisplayName("Deve montar hambúrguer simples com builder")
    void deveConstruirHamburguerSimples() {
        imprimirSeparador("Montagem Simples");
        System.out.println();
        ItemCardapio hamburguer = new ChefeCozinha()
                .comNome("Smash Simples")
                .comPao("pão comum")
                .comProteina(new ProteinaSmash(new AoPonto()))
                .comPrecoBase(10.0)
                .montar();

        assertNotNull(hamburguer);
        assertTrue(hamburguer.getDescricao().contains("Smash Simples"));
        System.out.println("Descrição final: " + hamburguer.getDescricao());
    }

    @Test
    @DisplayName("Deve montar hambúrguer usando receita (diretor)")
    void deveMontarHamburguerComReceita() {
        imprimirSeparador("Montagem via Diretor (Receita)");
        System.out.println();
        ReceitaHamburguer receita = new ReceitaHamburguer();
        ItemCardapio hamburguer = receita.montarHamburguer(
                "Mestre Hambúrguer",
                "Brioche",
                new ProteinaSmash(new AoPonto()),
                20.0
        );

        System.out.println("Valor total calculado: R$ " + String.format("%.2f", hamburguer.getPreco()));
        assertTrue(hamburguer.getDescricao().contains("Mestre Hambúrguer"));
        assertEquals(37.5, hamburguer.getPreco(), 0.01);
    }

    @Test
    @DisplayName("Deve permitir apenas ingredientes selecionados com builder")
    void devePermitirApenasIngredientesDesejados() {
        imprimirSeparador("Seleção de Ingredientes");
        System.out.println();
        ItemCardapio completo = new ChefeCozinha()
                .comNome("Completo")
                .comPao("brioche")
                .comProteina(new ProteinaSmash(new AoPonto()))
                .comPrecoBase(12.0)
                .comSalada()
                .comPicles()
                .comOnionRings()
                .montar();

        assertTrue(completo.getDescricao().contains("salada"));
        assertTrue(completo.getDescricao().contains("picles"));
        assertTrue(completo.getDescricao().contains("onion rings"));
        assertFalse(completo.getDescricao().contains("bacon"));
        assertFalse(completo.getDescricao().contains("queijo"));
        System.out.println("Descrição final: " + completo.getDescricao());
    }

    @Test
    @DisplayName("Deve calcular preço correto com builder e adicionais")
    void deveCalcularPrecoComBuilderEAdicionais() {
        imprimirSeparador("Cálculo de Preço");
        System.out.println();
        double precoBase = 10.0;
        ItemCardapio hamburguer = new ChefeCozinha()
                .comNome("Preço Test")
                .comPao("normal")
                .comProteina(new ProteinaSmash(new AoPonto()))
                .comPrecoBase(precoBase)
                .comBacon()
                .comPicles()
                .comOnionRings()
                .montar();

        System.out.println();
        System.out.println("Valor base: R$ " + String.format("%.2f", precoBase));
        System.out.println("Valor da Proteina (Smash): R$ 12.00");
        System.out.println("Adicional Bacon: R$ 4.00");
        System.out.println("Adicional Picles: R$ 1.00");
        System.out.println("Adicional Onion Rings: R$ 3.00");
        System.out.println("Total calculado: R$ " + String.format("%.2f", hamburguer.getPreco()));
        
        double precoEsperado = 30.0;
        assertEquals(precoEsperado, hamburguer.getPreco(), 0.01);
    }
}
