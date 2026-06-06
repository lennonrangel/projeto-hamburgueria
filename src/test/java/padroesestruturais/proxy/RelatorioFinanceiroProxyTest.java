package padroesestruturais.proxy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import padroescomportamentais.state.Pedido;
import padroescriacao.abstractfactory.Hamburguer;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Teste do Padrao Proxy - Relatorio Financeiro")
class RelatorioFinanceiroProxyTest {

    private void imprimirSeparador(String titulo) {
        System.out.println("\n========================================================");
        System.out.println(" [PROXY] " + titulo);
        System.out.println("========================================================");
    }

    @Test
    @DisplayName("Deve bloquear relatorio para atendente")
    void deveBloquearRelatorioParaAtendente() {
        imprimirSeparador("Acesso Bloqueado");
        System.out.println();
        Pedido pedido = new Pedido();
        pedido.adicionarItem(new Hamburguer("Burger Proxy", 30.0));
        RelatorioFinanceiro relatorio = new RelatorioFinanceiroProxy("atendente");

        String resumo = relatorio.gerarResumo(pedido);

        System.out.println("Perfil solicitado: atendente");
        System.out.println("Total do pedido: R$ " + String.format("%.2f", pedido.calcularTotal()));
        System.out.println("Resposta do proxy: " + resumo);

        assertEquals("Acesso negado ao relatorio financeiro.", resumo);
    }

    @Test
    @DisplayName("Deve liberar relatorio para gerente")
    void deveLiberarRelatorioParaGerente() {
        imprimirSeparador("Acesso Liberado");
        System.out.println();
        Pedido pedido = new Pedido();
        pedido.adicionarItem(new Hamburguer("Burger Proxy", 30.0));
        RelatorioFinanceiro relatorio = new RelatorioFinanceiroProxy("gerente");

        String resumo = relatorio.gerarResumo(pedido);

        System.out.println("Perfil solicitado: gerente");
        System.out.println("Total do pedido: R$ " + String.format("%.2f", pedido.calcularTotal()));
        System.out.println("Resposta do proxy: " + resumo);

        assertTrue(resumo.contains("Relatorio financeiro do pedido"));
        assertTrue(resumo.contains("30,00") || resumo.contains("30.00"));
    }
}
