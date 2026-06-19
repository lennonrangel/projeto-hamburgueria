package hamburgueria.hamburguer;

import hamburgueria.hamburguer.proteina.Proteina;


public class ReceitaHamburguerPrototype implements HamburguerPrototype {
    private String nome;
    private String tipoPao;
    private String tipoQueijo;
    private Proteina proteina;
    private double precoBase;

    public ReceitaHamburguerPrototype(String nome, String tipoPao, String tipoQueijo, Proteina proteina, double precoBase) {
        this.nome = nome;
        this.tipoPao = tipoPao;
        this.tipoQueijo = tipoQueijo;
        this.proteina = proteina;
        this.precoBase = precoBase;
    }

    protected ReceitaHamburguerPrototype(ReceitaHamburguerPrototype receita) {
        this(receita.nome, receita.tipoPao, receita.tipoQueijo, receita.proteina, receita.precoBase);
    }

    @Override
    public HamburguerPrototype clonar() {
        return new ReceitaHamburguerPrototype(this);
    }

    public ReceitaHamburguerPrototype comNome(String nome) {
        this.nome = nome;
        return this;
    }

    public ReceitaHamburguerPrototype comPao(String tipoPao) {
        this.tipoPao = tipoPao;
        return this;
    }

    public ReceitaHamburguerPrototype comQueijo(String tipoQueijo) {
        this.tipoQueijo = tipoQueijo;
        return this;
    }

    public ReceitaHamburguerPrototype comProteina(Proteina proteina) {
        this.proteina = proteina;
        return this;
    }

    public ReceitaHamburguerPrototype comPrecoBase(double precoBase) {
        this.precoBase = precoBase;
        return this;
    }

    @Override
    public String getDescricao() {
        String descQueijo = (tipoQueijo != null && !tipoQueijo.isBlank()) ? ", queijo " + tipoQueijo : "";
        String descProteina = proteina != null ? ", " + proteina.getDescricao() : "";
        return nome + " com pão " + tipoPao + descQueijo + descProteina;
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
