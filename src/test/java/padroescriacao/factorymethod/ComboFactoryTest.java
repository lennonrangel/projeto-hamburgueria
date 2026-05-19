package padroescriacao.factorymethod;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import padroesestruturais.composite.ItemCardapio;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Teste do Padrão Factory Method - Combos")
class ComboFactoryTest {

    private void imprimirSeparador(String titulo) {
        System.out.println("\n========================================================");
        System.out.println(" [FACTORY METHOD] " + titulo);
        System.out.println("========================================================\n");
    }

    @Test
    @DisplayName("Deve criar combo gourmet via factory method")
    void deveCriarComboGourmet() {
        imprimirSeparador("Criação de Combo Gourmet");
        ComboFactory factory = new ComboGourmetFactory();
        ItemCardapio combo = factory.criarCombo();
        
        assertTrue(combo.getDescricao().contains("Combo Gourmet"));
        assertTrue(combo.getDescricao().contains("Milkshake de Morango"));
        assertTrue(combo.getPreco() > 30.0);
    }

    @Test
    @DisplayName("Deve criar combo fit via factory method")
    void deveCriarComboFit() {
        imprimirSeparador("Criação de Combo Fit");
        ComboFactory factory = new ComboFitFactory();
        ItemCardapio combo = factory.criarCombo();
        
        assertTrue(combo.getDescricao().contains("Combo Fit"));
        assertTrue(combo.getDescricao().contains("Suco Natural"));
    }

    @Test
    @DisplayName("Deve criar combo clássico via factory method")
    void deveCriarComboClassico() {
        imprimirSeparador("Criação de Combo Clássico");
        ComboFactory factory = new ComboClassicoFactory();
        ItemCardapio combo = factory.criarCombo();

        assertTrue(combo.getDescricao().contains("Combo Clássico"));
        assertTrue(combo.getDescricao().contains("Refrigerante"));
        assertTrue(combo.getDescricao().contains("Batata Frita"));
    }
}
