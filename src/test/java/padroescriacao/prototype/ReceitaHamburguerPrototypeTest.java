package padroescriacao.prototype;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import padroesestruturais.bridge.AoPonto;
import padroesestruturais.bridge.BemPassado;
import padroesestruturais.bridge.ProteinaPicanha;
import padroesestruturais.bridge.ProteinaSmash;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Teste do Padrão Prototype - Clonagem de Receitas")
class ReceitaHamburguerPrototypeTest {

    private void imprimirSeparador(String titulo) {
        System.out.println("\n========================================================");
        System.out.println(" [PROTOTYPE] " + titulo);
        System.out.println("========================================================");
    }

    @Test
    @DisplayName("Deve clonar receita base de hambúrguer")
    void deveClonarReceitaBase() {
        imprimirSeparador("Clonagem de Receita Base");
        System.out.println();

        ReceitaHamburguerPrototype receitaBase = new ReceitaHamburguerPrototype(
                "Smash da Casa",
                "brioche",
                "cheddar",
                new ProteinaSmash(new AoPonto()),
                14.0
        );

        HamburguerPrototype clone = receitaBase.clonar();

        System.out.println("Original: " + receitaBase.getDescricao());
        System.out.println("Clone: " + clone.getDescricao());
        assertNotSame(receitaBase, clone);
        assertEquals(receitaBase.getDescricao(), clone.getDescricao());
        assertEquals(receitaBase.getPreco(), clone.getPreco(), 0.01);
    }

    @Test
    @DisplayName("Deve customizar clone sem alterar receita original")
    void deveCustomizarCloneSemAlterarOriginal() {
        imprimirSeparador("Customização Independente");
        System.out.println();

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

        System.out.println("Original: " + receitaBase.getDescricao());
        System.out.println("Clone customizado: " + clone.getDescricao());
        assertEquals("Burger Especial", receitaBase.getNome());
        assertEquals("prato", receitaBase.getTipoQueijo());
        assertEquals("Burger Especial Cliente", clone.getNome());
        assertEquals("gorgonzola", clone.getTipoQueijo());
        assertNotEquals(receitaBase.getPreco(), clone.getPreco());
    }
}
