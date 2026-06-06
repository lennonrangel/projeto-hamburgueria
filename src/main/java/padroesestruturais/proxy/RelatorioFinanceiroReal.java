package padroesestruturais.proxy;

import padroescomportamentais.state.Pedido;

public class RelatorioFinanceiroReal implements RelatorioFinanceiro {

    @Override
    public String gerarResumo(Pedido pedido) {
        return "Relatorio financeiro do pedido " + pedido.getCodigo()
                + " - Total: R$ " + String.format("%.2f", pedido.calcularTotal());
    }
}
