package hamburgueria.linhaproduto;

import hamburgueria.cardapio.MenuItem;
import hamburgueria.hamburguer.Hamburguer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Teste do Padrão Abstract Factory - Cardápio")
class HamburguerFactoryTest {

    private void imprimirSeparador(String titulo) {
    }

    @Test
    @DisplayName("Deve garantir instância única do Cardápio Clássico")
    void deveInstanciarSingletonCardapioClassico() {
        imprimirSeparador("Instanciação Singleton Clássico");
        ClassicoFactory f1 = ClassicoFactory.getInstancia();
        ClassicoFactory f2 = ClassicoFactory.getInstancia();
        assertSame(f1, f2);
    }

    @Test
    @DisplayName("Deve garantir instância única do Cardápio Gourmet")
    void deveInstanciarSingletonCardapioGourmet() {
        imprimirSeparador("Instanciação Singleton Gourmet");
        GourmetFactory f1 = GourmetFactory.getInstancia();
        GourmetFactory f2 = GourmetFactory.getInstancia();
        assertSame(f1, f2);
    }

    @Test
    @DisplayName("Deve garantir instância única do Cardápio Fit")
    void deveInstanciarSingletonCardapioFit() {
        imprimirSeparador("Instanciação Singleton Fit");
        FitFactory f1 = FitFactory.getInstancia();
        FitFactory f2 = FitFactory.getInstancia();
        assertSame(f1, f2);
    }

    @Test
    @DisplayName("Deve criar hambúrgueres principais de cada linha")
    void deveCriarHamburgueresPrincipaisComDetalhes() {
        imprimirSeparador("Criação de Hamburgueres Principais");
        
        MenuItem lancheClassicoItem = ClassicoFactory.getInstancia().criarHamburguerPrincipal();
        
        MenuItem lancheGourmetItem = GourmetFactory.getInstancia().criarHamburguerPrincipal();
        
        MenuItem lancheFitItem = FitFactory.getInstancia().criarHamburguerPrincipal();

        assertNotNull(lancheClassicoItem);
        assertNotNull(lancheGourmetItem);
        assertNotNull(lancheFitItem);

        Hamburguer lancheClassico = (Hamburguer) lancheClassicoItem;
        Hamburguer lancheGourmet = (Hamburguer) lancheGourmetItem;
        Hamburguer lancheFit = (Hamburguer) lancheFitItem;

        assertNotNull(lancheClassico.getTipoPao());
        assertNotNull(lancheClassico.getProteina());
        assertTrue(lancheClassico.getPreco() > 0);

        assertNotNull(lancheGourmet.getTipoPao());
        assertNotNull(lancheGourmet.getProteina());
        assertTrue(lancheGourmet.getDescricao().contains("picanha"));
        assertTrue(lancheGourmet.getDescricao().contains("ao ponto"));
        assertTrue(lancheGourmet.getPreco() > 0);

        assertNotNull(lancheFit.getTipoPao());
        assertNotNull(lancheFit.getProteina());
        assertTrue(lancheFit.getPreco() > 0);
    }

    @Test
    @DisplayName("Deve criar hambúrgueres especiais de cada linha")
    void deveCriarHamburgueresEspeciaisDeLinhas() {
        imprimirSeparador("Criação de Hamburgueres Especiais");
        
        MenuItem classico = ClassicoFactory.getInstancia().criarHamburguerEspecial();
        
        MenuItem gourmet = GourmetFactory.getInstancia().criarHamburguerEspecial();
        
        MenuItem fit = FitFactory.getInstancia().criarHamburguerEspecial();

        assertNotNull(classico);
        assertNotNull(gourmet);
        assertNotNull(fit);
        assertTrue(gourmet.getPreco() > classico.getPreco());
    }

    @Test
    @DisplayName("ClassicoFactory deve criar lanches com proteina bovina classica")
    void testClassicoFactoryItensTipo() {
        MenuItem principalItem = ClassicoFactory.getInstancia().criarHamburguerPrincipal();
        Hamburguer principal = (Hamburguer) principalItem;
        assertTrue(principal.getProteina().getDescricao().contains("smash burger"));
    }

    @Test
    @DisplayName("FitFactory deve criar lanche especial mais caro que principal")
    void testFitFactoryPrecoEspecialMaiorQuePrincipal() {
        MenuItem principal = FitFactory.getInstancia().criarHamburguerPrincipal();
        MenuItem especial = FitFactory.getInstancia().criarHamburguerEspecial();
        assertTrue(especial.getPreco() > principal.getPreco());
    }
}

