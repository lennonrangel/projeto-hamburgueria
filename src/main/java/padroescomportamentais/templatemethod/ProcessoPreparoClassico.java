package padroescomportamentais.templatemethod;

import padroescomportamentais.state.Pedido;

public class ProcessoPreparoClassico extends ProcessoPreparo {
    @Override
    protected void prepararHamburguer(Pedido pedido) {
        System.out.println("Preparando lanche clássico na chapa.");
    }
}
