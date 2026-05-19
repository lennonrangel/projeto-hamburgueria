package padroescomportamentais.templatemethod;

import padroescomportamentais.state.Pedido;

public class ProcessoPreparoFit extends ProcessoPreparo {
    @Override
    protected void prepararHamburguer(Pedido pedido) {
        System.out.println("Preparando lanche fit com menos gordura.");
    }
}
