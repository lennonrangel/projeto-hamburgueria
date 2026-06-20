package hamburgueria.financeiro;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import hamburgueria.pedido.Pedido;
import hamburgueria.hamburguer.Hamburguer;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Teste do Padrao Proxy - Relatorio Financeiro")
class RelatorioFinanceiroProxyTest {

    private void imprimirSeparador(String titulo) {
    }

    @Test
    @DisplayName("Deve bloquear relatorio para atendente")
    void deveBloquearRelatorioParaAtendente() {
        imprimirSeparador("Acesso Bloqueado");
        Pedido pedido = new Pedido();
        pedido.adicionarItem(new Hamburguer("Burger Proxy", 30.0));
        RelatorioFinanceiro relatorio = new RelatorioFinanceiroProxy("atendente");

        String resumo = relatorio.gerarResumo(pedido);


        assertEquals("Acesso negado ao relatorio financeiro.", resumo);
    }

    @Test
    @DisplayName("Deve liberar relatorio para gerente")
    void deveLiberarRelatorioParaGerente() {
        imprimirSeparador("Acesso Liberado");
        Pedido pedido = new Pedido();
        pedido.adicionarItem(new Hamburguer("Burger Proxy", 30.0));
        RelatorioFinanceiro relatorio = new RelatorioFinanceiroProxy("gerente");

        String resumo = relatorio.gerarResumo(pedido);


        assertTrue(resumo.contains("Relatorio financeiro do pedido"));
        assertTrue(resumo.contains("30,00") || resumo.contains("30.00"));
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Proxy deve negar acesso a usuario nulo")
    void testRelatorioProxyAcessoNegadoParaUsuarioNulo() {
        Pedido pedido = new Pedido();
        RelatorioFinanceiro relatorio = new RelatorioFinanceiroProxy(null);
        String resumo = relatorio.gerarResumo(pedido);
        assertEquals("Acesso negado ao relatorio financeiro.", resumo);
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("RelatorioFinanceiroReal deve gerar resumo corretamente")
    void testRelatorioRealGerarResumoDiretamente() {
        Pedido pedido = new Pedido();
        pedido.adicionarItem(new Hamburguer("H", 10.0));
        String resumo = new RelatorioFinanceiroReal().gerarResumo(pedido);
        assertTrue(resumo.contains("Relatorio financeiro do pedido"));
        assertTrue(resumo.contains("Total: R$ 10,00") || resumo.contains("Total: R$ 10.00"));
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Proxy deve negar acesso a usuario com perfil vazio")
    void testRelatorioProxyAcessoNegadoParaPerfilVazio() {
        Pedido pedido = new Pedido();
        RelatorioFinanceiro relatorio = new RelatorioFinanceiroProxy("");
        String resumo = relatorio.gerarResumo(pedido);
        assertEquals("Acesso negado ao relatorio financeiro.", resumo);
    }

    @org.junit.jupiter.api.Test
    @org.junit.jupiter.api.DisplayName("Proxy deve liberar acesso para papel gerente independente de caixa alta/baixa")
    void testRelatorioProxyAcessoLiberadoParaGerenteCaseInsensitive() {
        Pedido pedido = new Pedido();
        pedido.adicionarItem(new Hamburguer("Burger Proxy", 30.0));
        RelatorioFinanceiro relatorio = new RelatorioFinanceiroProxy("GERENTE");
        String resumo = relatorio.gerarResumo(pedido);
        assertTrue(resumo.contains("Relatorio financeiro do pedido"));
    }
}
