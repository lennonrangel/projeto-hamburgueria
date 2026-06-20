package hamburgueria.financeiro;

import hamburgueria.pedido.Pedido;

public interface RelatorioFinanceiro {
    String gerarResumo(Pedido pedido);
}
