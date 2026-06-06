package padroescomportamentais.interpreter;

import padroescomportamentais.state.Pedido;

public class CupomDesconto {

    private final String nome;
    private final double percentualDesconto;
    private final ExpressaoPedido regra;

    public CupomDesconto(String nome, double percentualDesconto, ExpressaoPedido regra) {
        this.nome = nome;
        this.percentualDesconto = percentualDesconto;
        this.regra = regra;
    }

    public double aplicar(Pedido pedido, double valorBase) {
        if (regra.interpretar(pedido)) {
            System.out.println("Cupom aplicado: " + nome);
            return valorBase * (1.0 - percentualDesconto);
        }

        System.out.println("Cupom nao aplicado: " + nome);
        return valorBase;
    }
}
