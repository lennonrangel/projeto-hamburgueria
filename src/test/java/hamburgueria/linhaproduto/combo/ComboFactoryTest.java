package hamburgueria.linhaproduto.combo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import hamburgueria.cardapio.MenuItem;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Teste do Padrão Factory Method - Combos")
class ComboFactoryTest {

    private void imprimirSeparador(String titulo) {
    }

    @Test
    @DisplayName("Deve criar combo gourmet via factory method")
    void deveCriarComboGourmet() {
        imprimirSeparador("Criação de Combo Gourmet");
        ComboFactory factory = new ComboGourmetFactory();
        MenuItem combo = factory.criarCombo();
        
        assertTrue(combo.getDescricao().contains("Combo Gourmet"));
        assertTrue(combo.getDescricao().contains("Milkshake de Morango"));
        assertTrue(combo.getPreco() > 30.0);
    }

    @Test
    @DisplayName("Deve criar combo fit via factory method")
    void deveCriarComboFit() {
        imprimirSeparador("Criação de Combo Fit");
        ComboFactory factory = new ComboFitFactory();
        MenuItem combo = factory.criarCombo();
        
        assertTrue(combo.getDescricao().contains("Combo Fit"));
        assertTrue(combo.getDescricao().contains("Suco Natural"));
    }

    @Test
    @DisplayName("Deve criar combo clássico via factory method")
    void deveCriarComboClassico() {
        imprimirSeparador("Criação de Combo Clássico");
        ComboFactory factory = new ComboClassicoFactory();
        MenuItem combo = factory.criarCombo();

        assertTrue(combo.getDescricao().contains("Combo Clássico"));
        assertTrue(combo.getDescricao().contains("Refrigerante"));
        assertTrue(combo.getDescricao().contains("Batata Frita"));
    }

    @Test
    @DisplayName("Deve garantir que o preco do Combo Classico e menor que o Gourmet")
    void testPrecoComboClassicoMaisBaratoQueGourmet() {
        MenuItem comboClassico = new ComboClassicoFactory().criarCombo();
        MenuItem comboGourmet = new ComboGourmetFactory().criarCombo();
        assertTrue(comboClassico.getPreco() < comboGourmet.getPreco());
    }

    @Test
    @DisplayName("Deve retornar instancias corretas das classes de combo concretas")
    void testInstanciasDasClassesDeComboConcretas() {
        MenuItem comboClassico = new ComboClassicoFactory().criarCombo();
        MenuItem comboFit = new ComboFitFactory().criarCombo();
        MenuItem comboGourmet = new ComboGourmetFactory().criarCombo();

        assertTrue(comboClassico instanceof ComboClassico);
        assertTrue(comboFit instanceof ComboFit);
        assertTrue(comboGourmet instanceof ComboGourmet);
    }
}

