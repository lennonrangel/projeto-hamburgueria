package hamburgueria.hamburguer;

import hamburgueria.hamburguer.proteina.Proteina;

import hamburgueria.cardapio.MenuItem;

public class Hamburguer implements MenuItem {
    private final String nome;
    private final String tipoPao;
    private final String tipoQueijo;
    private final Proteina proteina;
    private final double precoBase;

    public Hamburguer(String nome, double precoBase) {
        this(nome, "", "", null, precoBase);
    }

    public Hamburguer(String nome, String tipoPao, String tipoQueijo, Proteina proteina, double precoBase) {
        this.nome = nome;
        this.tipoPao = tipoPao;
        this.tipoQueijo = tipoQueijo;
        this.proteina = proteina;
        this.precoBase = precoBase;
    }

    @Override
    public String getDescricao() {
        if (proteina == null || tipoPao == null || tipoPao.isBlank()) {
            return nome;
        }
        String descQueijo = (tipoQueijo != null && !tipoQueijo.isBlank()) ? ", queijo " + tipoQueijo : "";
        return nome + " com pão " + tipoPao + descQueijo + ", " + proteina.getDescricao();
    }

    @Override
    public double getPreco() {
        return precoBase + (proteina != null ? proteina.getPreco() : 0.0);
    }

    public String getNome() {
        return nome;
    }

    public String getTipoPao() {
        return tipoPao;
    }

    public String getTipoQueijo() {
        return tipoQueijo;
    }

    public Proteina getProteina() {
        return proteina;
    }

}

