package padroescomportamentais.memento;

import java.util.ArrayList;
import java.util.List;

public class HistoricoPedido {
    private final List<RegistroPedido> mementos = new ArrayList<>();

    public void adicionarSnapshot(RegistroPedido snapshot) {
        mementos.add(snapshot);
    }

    public RegistroPedido getUltimo() {
        if (mementos.isEmpty()) return null;
        return mementos.get(mementos.size() - 1);
    }

    public void removerUltimo() {
        if (!mementos.isEmpty()) {
            mementos.remove(mementos.size() - 1);
        }
    }
}
