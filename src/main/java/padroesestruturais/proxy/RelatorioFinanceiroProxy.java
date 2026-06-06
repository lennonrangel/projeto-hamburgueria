package padroesestruturais.proxy;

import padroescomportamentais.state.Pedido;

public class RelatorioFinanceiroProxy implements RelatorioFinanceiro {

    private final String perfilUsuario;
    private final RelatorioFinanceiro relatorioReal;

    public RelatorioFinanceiroProxy(String perfilUsuario) {
        this.perfilUsuario = perfilUsuario;
        this.relatorioReal = new RelatorioFinanceiroReal();
    }

    @Override
    public String gerarResumo(Pedido pedido) {
        if (!"gerente".equalsIgnoreCase(perfilUsuario)) {
            return "Acesso negado ao relatorio financeiro.";
        }

        return relatorioReal.gerarResumo(pedido);
    }
}
