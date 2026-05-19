package padroescriacao.singleton;

public class GeradorCodigoPedido {

    private static GeradorCodigoPedido instancia;
    private int ultimoCodigo;

    private GeradorCodigoPedido() {
        this.ultimoCodigo = 0;
    }

    public static synchronized GeradorCodigoPedido getInstancia() {
        if (instancia == null) {
            instancia = new GeradorCodigoPedido();
        }
        return instancia;
    }

    public synchronized String proximoCodigo() {
        ultimoCodigo++;
        String novoCodigo = String.format("PED-%03d", ultimoCodigo);
        System.out.println("Código: " + novoCodigo);
        return novoCodigo;
    }

    public int getUltimoCodigo() {
        return ultimoCodigo;
    }
}
