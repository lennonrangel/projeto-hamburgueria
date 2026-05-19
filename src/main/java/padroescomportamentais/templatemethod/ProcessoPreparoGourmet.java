package padroescomportamentais.templatemethod;

import padroescomportamentais.state.Pedido;

public class ProcessoPreparoGourmet extends ProcessoPreparo {
    @Override
    protected void prepararHamburguer(Pedido pedido) {
        System.out.println("Preparando lanche gourmet com finalização especial.");
    }
}
