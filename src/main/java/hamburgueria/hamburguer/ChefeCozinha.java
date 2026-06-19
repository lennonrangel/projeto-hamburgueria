package hamburgueria.hamburguer;

import hamburgueria.hamburguer.proteina.Proteina;

import hamburgueria.cardapio.MenuItem;
import hamburgueria.cardapio.adicional.Bacon;
import hamburgueria.cardapio.adicional.Molho;
import hamburgueria.cardapio.adicional.Salada;
import hamburgueria.cardapio.adicional.OnionRings;
import hamburgueria.cardapio.adicional.Picles;

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
    public MenuItem montar() {
        
        if (proteina != null) {
        }

        MenuItem hamburguer = new Hamburguer(nome, tipoPao, tipoQueijo, proteina, precoBase);

        if (baconExtra) {
            hamburguer = new Bacon(hamburguer);
        }

        if (molhoExtra) {
            hamburguer = new Molho(hamburguer);
        }

        if (saladaExtra) {
            hamburguer = new Salada(hamburguer);
        }

        if (piclesExtra) {
            hamburguer = new Picles(hamburguer);
        }

        if (onionRingsExtra) {
            hamburguer = new OnionRings(hamburguer);
        }

        return hamburguer;
    }
}

