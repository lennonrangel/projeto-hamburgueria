package padroesestruturais.proxy;

import padroescomportamentais.state.Pedido;

public interface RelatorioFinanceiro {
    String gerarResumo(Pedido pedido);
}
