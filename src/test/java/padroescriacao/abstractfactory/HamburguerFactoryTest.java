package padroescriacao.abstractfactory;

import padroesestruturais.composite.ItemCardapio;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Teste do Padrão Abstract Factory - Cardápio")
class HamburguerFactoryTest {

    private void imprimirSeparador(String titulo) {
        System.out.println("\n========================================================");
        System.out.println(" ABSTRACT FACTORY: " + titulo);
        System.out.println("========================================================\n");
    }

    @Test
    @DisplayName("Deve garantir instância única do Cardápio Clássico")
    void deveInstanciarSingletonCardapioClassico() {
        imprimirSeparador("Instanciação Singleton Clássico");
        System.out.println("Singleton: Instanciando Cardápio Clássico");
        ClassicoFactory f1 = ClassicoFactory.getInstancia();
        ClassicoFactory f2 = ClassicoFactory.getInstancia();
        System.out.println("Verificando se as instâncias são idênticas...");
        assertSame(f1, f2);
        System.out.println("Sucesso: Singleton Clássico garantido.");
    }

    @Test
    @DisplayName("Deve garantir instância única do Cardápio Gourmet")
    void deveInstanciarSingletonCardapioGourmet() {
        imprimirSeparador("Instanciação Singleton Gourmet");
        System.out.println("Singleton: Instanciando Cardápio Gourmet");
        GourmetFactory f1 = GourmetFactory.getInstancia();
        GourmetFactory f2 = GourmetFactory.getInstancia();
        System.out.println("Verificando se as instâncias são idênticas...");
        assertSame(f1, f2);
        System.out.println("Sucesso: Singleton Gourmet garantido.");
    }

    @Test
    @DisplayName("Deve garantir instância única do Cardápio Fit")
    void deveInstanciarSingletonCardapioFit() {
        imprimirSeparador("Instanciação Singleton Fit");
        System.out.println("Singleton: Instanciando Cardápio Fit");
        FitFactory f1 = FitFactory.getInstancia();
        FitFactory f2 = FitFactory.getInstancia();
        System.out.println("Verificando se as instâncias são idênticas...");
        assertSame(f1, f2);
        System.out.println("Sucesso: Singleton Fit garantido.");
    }

    @Test
    @DisplayName("Deve criar hambúrgueres principais de cada linha")
    void deveCriarHamburgueresPrincipaisComDetalhes() {
        imprimirSeparador("Criação de Hamburgueres Principais");
        
        System.out.println("--- Criando Hamburguer Clássico ---");
        System.out.println();
        ItemCardapio lancheClassicoItem = ClassicoFactory.getInstancia().criarHamburguerPrincipal();
        System.out.println("Descrição gerada: " + lancheClassicoItem.getDescricao());
        
        System.out.println("\n--- Criando Hamburguer Gourmet ---");
        System.out.println();
        ItemCardapio lancheGourmetItem = GourmetFactory.getInstancia().criarHamburguerPrincipal();
        System.out.println("Descrição gerada: " + lancheGourmetItem.getDescricao());
        
        System.out.println("\n--- Criando Hamburguer Fit ---");
        System.out.println();
        ItemCardapio lancheFitItem = FitFactory.getInstancia().criarHamburguerPrincipal();
        System.out.println("Descrição gerada: " + lancheFitItem.getDescricao());

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
        
        System.out.println("--- Criando Especial Clássico ---");
        System.out.println();
        ItemCardapio classico = ClassicoFactory.getInstancia().criarHamburguerEspecial();
        System.out.println("Descrição: " + classico.getDescricao());
        
        System.out.println("\n--- Criando Especial Gourmet ---");
        ItemCardapio gourmet = GourmetFactory.getInstancia().criarHamburguerEspecial();
        System.out.println("Descrição: " + gourmet.getDescricao());
        
        System.out.println("\n--- Criando Especial Fit ---");
        ItemCardapio fit = FitFactory.getInstancia().criarHamburguerEspecial();
        System.out.println("Descrição: " + fit.getDescricao());

        assertNotNull(classico);
        assertNotNull(gourmet);
        assertNotNull(fit);
        assertTrue(gourmet.getPreco() > classico.getPreco());
    }
}
