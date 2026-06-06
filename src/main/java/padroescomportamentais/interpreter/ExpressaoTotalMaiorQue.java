package padroescomportamentais.interpreter;

import padroescomportamentais.state.Pedido;

public class ExpressaoTotalMaiorQue implements ExpressaoPedido {

    private final double valorMinimo;

    public ExpressaoTotalMaiorQue(double valorMinimo) {
        this.valorMinimo = valorMinimo;
    }

    @Override
    public boolean interpretar(Pedido pedido) {
        return pedido.calcularTotal() > valorMinimo;
    }
}
