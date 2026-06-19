package hamburgueria.hamburguer;

import hamburgueria.hamburguer.ponto.AoPonto;
import hamburgueria.hamburguer.ponto.BemPassado;
import hamburgueria.hamburguer.proteina.ProteinaPicanha;
import hamburgueria.hamburguer.proteina.ProteinaSmash;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Teste do Padrão Prototype - Clonagem de Receitas")
class ReceitaHamburguerPrototypeTest {

    private void imprimirSeparador(String titulo) {
    }

    @Test
    @DisplayName("Deve clonar receita base de hambúrguer")
    void deveClonarReceitaBase() {
        imprimirSeparador("Clonagem de Receita Base");

        ReceitaHamburguerPrototype receitaBase = new ReceitaHamburguerPrototype(
                "Smash da Casa",
                "brioche",
                "cheddar",
                new ProteinaSmash(new AoPonto()),
                14.0
        );

        HamburguerPrototype clone = receitaBase.clonar();

        assertNotSame(receitaBase, clone);
        assertEquals(receitaBase.getDescricao(), clone.getDescricao());
        assertEquals(receitaBase.getPreco(), clone.getPreco(), 0.01);
    }

    @Test
    @DisplayName("Deve customizar clone sem alterar receita original")
    void deveCustomizarCloneSemAlterarOriginal() {
        imprimirSeparador("Customização Independente");

        ReceitaHamburguerPrototype receitaBase = new ReceitaHamburguerPrototype(
                "Burger Especial",
                "australiano",
                "prato",
                new ProteinaPicanha(new BemPassado()),
                18.0
        );

        ReceitaHamburguerPrototype clone = (ReceitaHamburguerPrototype) receitaBase.clonar();
        clone.comNome("Burger Especial Cliente")
                .comQueijo("gorgonzola")
                .comPrecoBase(22.0);

        assertEquals("Burger Especial", receitaBase.getNome());
        assertEquals("prato", receitaBase.getTipoQueijo());
        assertEquals("Burger Especial Cliente", clone.getNome());
        assertEquals("gorgonzola", clone.getTipoQueijo());
        assertNotEquals(receitaBase.getPreco(), clone.getPreco());
    }

    @Test
    @DisplayName("Deve gerar descricao correta de clone sem queijo")
    void deveGerarDescricaoSemQueijo() {
        ReceitaHamburguerPrototype receita = new ReceitaHamburguerPrototype("Eco Burger", "integral", null, null, 15.0);
        assertEquals("Eco Burger com pão integral", receita.getDescricao());
        assertEquals(15.0, receita.getPreco(), 0.01);
    }

    @Test
    @DisplayName("Deve gerar preco e descricao corretos de clone sem proteina")
    void deveGerarPrecoEDescricaoSemProteina() {
        ReceitaHamburguerPrototype receita = new ReceitaHamburguerPrototype("Básico", "brioche", "cheddar", null, 10.0);
        assertEquals("Básico com pão brioche, queijo cheddar", receita.getDescricao());
        assertEquals(10.0, receita.getPreco(), 0.01);
    }

    @Test
    @DisplayName("Deve permitir alterar pao e proteina do clone")
    void devePermitirAlterarPaoEProteinaDoClone() {
        ReceitaHamburguerPrototype original = new ReceitaHamburguerPrototype("Base", "brioche", "prato", null, 10.0);
        ReceitaHamburguerPrototype clone = (ReceitaHamburguerPrototype) original.clonar();
        
        clone.comPao("australiano").comProteina(new ProteinaSmash(new AoPonto()));
        
        assertEquals("brioche", original.getTipoPao());
        assertNull(original.getProteina());
        
        assertEquals("australiano", clone.getTipoPao());
        assertNotNull(clone.getProteina());
    }
}
