package hamburgueria.promocao;

import hamburgueria.promocao.condicao.ExpressaoPedido;

import hamburgueria.pedido.Pedido;

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
            return valorBase * (1.0 - percentualDesconto);
        }

        return valorBase;
    }

    public String getNome() {
        return nome;
    }
}
