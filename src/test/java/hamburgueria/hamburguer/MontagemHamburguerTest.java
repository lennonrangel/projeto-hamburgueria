package hamburgueria.hamburguer;

import hamburgueria.hamburguer.ponto.AoPonto;
import hamburgueria.hamburguer.proteina.ProteinaSmash;

import hamburgueria.cardapio.MenuItem;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Teste do Padrão Builder - Montagem de Hambúrgueres")
class MontagemHamburguerTest {

    private void imprimirSeparador(String titulo) {
    }

    @Test
    @DisplayName("Deve montar hambúrguer completo usando builder")
    void deveConstruirHamburguerComBuilder() {
        imprimirSeparador("Montagem Completa");
        MenuItem hamburguer = new ChefeCozinha()
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
    }

    @Test
    @DisplayName("Deve montar hambúrguer simples com builder")
    void deveConstruirHamburguerSimples() {
        imprimirSeparador("Montagem Simples");
        MenuItem hamburguer = new ChefeCozinha()
                .comNome("Smash Simples")
                .comPao("pão comum")
                .comProteina(new ProteinaSmash(new AoPonto()))
                .comPrecoBase(10.0)
                .montar();

        assertNotNull(hamburguer);
        assertTrue(hamburguer.getDescricao().contains("Smash Simples"));
    }

    @Test
    @DisplayName("Deve montar hambúrguer usando receita (diretor)")
    void deveMontarHamburguerComReceita() {
        imprimirSeparador("Montagem via Diretor (Receita)");
        ReceitaHamburguer receita = new ReceitaHamburguer();
        MenuItem hamburguer = receita.montarHamburguer(
                "Mestre Hambúrguer",
                "Brioche",
                new ProteinaSmash(new AoPonto()),
                20.0
        );

        assertTrue(hamburguer.getDescricao().contains("Mestre Hambúrguer"));
        assertEquals(37.5, hamburguer.getPreco(), 0.01);
    }

    @Test
    @DisplayName("Deve permitir apenas ingredientes selecionados com builder")
    void devePermitirApenasIngredientesDesejados() {
        imprimirSeparador("Seleção de Ingredientes");
        MenuItem completo = new ChefeCozinha()
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
    }

    @Test
    @DisplayName("Deve calcular preço correto com builder e adicionais")
    void deveCalcularPrecoComBuilderEAdicionais() {
        imprimirSeparador("Cálculo de Preço");
        double precoBase = 10.0;
        MenuItem hamburguer = new ChefeCozinha()
                .comNome("Preço Test")
                .comPao("normal")
                .comProteina(new ProteinaSmash(new AoPonto()))
                .comPrecoBase(precoBase)
                .comBacon()
                .comPicles()
                .comOnionRings()
                .montar();

        
        double precoEsperado = 30.0;
        assertEquals(precoEsperado, hamburguer.getPreco(), 0.01);
    }

    @Test
    @DisplayName("Deve montar hamburguer com molho extra")
    void testBuilderComMolho() {
        MenuItem hamburguer = new ChefeCozinha()
                .comNome("Molho Especial")
                .comPao("australiano")
                .comMolho()
                .comPrecoBase(12.0)
                .montar();

        assertTrue(hamburguer.getDescricao().contains("molho"));
        assertEquals(13.5, hamburguer.getPreco(), 0.01);
    }

    @Test
    @DisplayName("Deve calcular preco correto com molho extra e bacon")
    void testBuilderPrecoComMolhoEBacon() {
        MenuItem hamburguer = new ChefeCozinha()
                .comNome("Molho e Bacon")
                .comPao("brioche")
                .comMolho()
                .comBacon()
                .comPrecoBase(10.0)
                .montar();

        assertTrue(hamburguer.getDescricao().contains("molho"));
        assertTrue(hamburguer.getDescricao().contains("bacon"));
        // Preço: 10 (base) + 1.5 (molho) + 4.0 (bacon) = 15.5
        assertEquals(15.5, hamburguer.getPreco(), 0.01);
    }
}

