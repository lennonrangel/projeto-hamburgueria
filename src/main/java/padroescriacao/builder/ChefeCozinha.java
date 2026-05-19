package padroescriacao.builder;

import padroesestruturais.composite.ItemCardapio;
import padroescriacao.abstractfactory.Hamburguer;
import padroesestruturais.decorator.Bacon;
import padroesestruturais.decorator.Molho;
import padroesestruturais.decorator.Salada;
import padroesestruturais.decorator.OnionRings;
import padroesestruturais.decorator.Picles;
import padroesestruturais.bridge.Proteina;

public class ChefeCozinha implements MontagemHamburguer {

    private String nome;
    private String tipoPao;
    private String tipoQueijo;
    private Proteina proteina;
    private double precoBase;
    private boolean baconExtra;
    private boolean molhoExtra;
    private boolean saladaExtra;
    private boolean onionRingsExtra;
    private boolean piclesExtra;

    @Override
    public MontagemHamburguer comNome(String nome) {
        this.nome = nome;
        return this;
    }

    @Override
    public MontagemHamburguer comPao(String tipoPao) {
        this.tipoPao = tipoPao;
        return this;
    }

    @Override
    public MontagemHamburguer comQueijo(String tipoQueijo) {
        this.tipoQueijo = tipoQueijo;
        return this;
    }

    @Override
    public MontagemHamburguer comProteina(Proteina proteina) {
        this.proteina = proteina;
        return this;
    }

    @Override
    public MontagemHamburguer comPrecoBase(double precoBase) {
        this.precoBase = precoBase;
        return this;
    }

    @Override
    public MontagemHamburguer comBacon() {
        this.baconExtra = true;
        return this;
    }

    @Override
    public MontagemHamburguer comMolho() {
        this.molhoExtra = true;
        return this;
    }

    @Override
    public MontagemHamburguer comSalada() {
        this.saladaExtra = true;
        return this;
    }

    @Override
    public MontagemHamburguer comOnionRings() {
        this.onionRingsExtra = true;
        return this;
    }

    @Override
    public MontagemHamburguer comPicles() {
        this.piclesExtra = true;
        return this;
    }

    @Override
    public ItemCardapio montar() {
        System.out.println("Cozinha: Iniciando montagem do lanche: " + nome);
        
        if (proteina != null) {
            System.out.println("• Proteina: " + proteina.getNome());
            System.out.println("• Ponto da carne: " + proteina.getGrauCoccao().getDescricao());
        }

        ItemCardapio hamburguer = new Hamburguer(nome, tipoPao, tipoQueijo, proteina, precoBase);

        if (baconExtra) {
            System.out.println("• Adicionando Bacon");
            hamburguer = new Bacon(hamburguer);
        }

        if (molhoExtra) {
            System.out.println("• Adicionando Molho");
            hamburguer = new Molho(hamburguer);
        }

        if (saladaExtra) {
            System.out.println("• Adicionando Salada");
            hamburguer = new Salada(hamburguer);
        }

        if (piclesExtra) {
            System.out.println("• Adicionando Picles");
            hamburguer = new Picles(hamburguer);
        }

        if (onionRingsExtra) {
            System.out.println("• Adicionando Onion Rings");
            hamburguer = new OnionRings(hamburguer);
        }

        System.out.println("Cozinha: Lanche " + nome + " finalizado. Valor total: R$ " + String.format("%.2f", hamburguer.getPreco()));
        System.out.println();
        return hamburguer;
    }
}
