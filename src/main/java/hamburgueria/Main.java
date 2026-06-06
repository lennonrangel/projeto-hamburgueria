package hamburgueria;

import padroescomportamentais.chainofresponsability.DescontoPedidoGrande;
import padroescomportamentais.chainofresponsability.DescontoRetiradaBalcao;
import padroescomportamentais.command.AdicionarItemCommand;
import padroescomportamentais.command.GerenciadorComandosPedido;
import padroescomportamentais.interpreter.*;
import padroescomportamentais.iterator.Cardapio;
import padroescomportamentais.iterator.IteradorCardapio;
import padroescomportamentais.mediator.Atendente;
import padroescomportamentais.mediator.Caixa;
import padroescomportamentais.mediator.CentralHamburgueria;
import padroescomportamentais.observer.ClienteNotificador;
import padroescomportamentais.observer.CozinhaNotificador;
import padroescomportamentais.state.Pedido;
import padroescomportamentais.strategy.PagamentoPix;
import padroescomportamentais.strategy.ProcessadorPagamento;
import padroescomportamentais.templatemethod.ProcessoPreparo;
import padroescomportamentais.templatemethod.ProcessoPreparoGourmet;
import padroescriacao.abstractfactory.*;
import padroescriacao.prototype.ReceitaHamburguerPrototype;
import padroesestruturais.adapter.IngredientePesadoAdapter;
import padroesestruturais.adapter.IngredientePesadoExterno;
import padroesestruturais.bridge.AoPonto;
import padroesestruturais.bridge.ProteinaPicanha;
import padroesestruturais.composite.ItemCardapio;
import padroesestruturais.flyweight.IngredienteFactory;
import padroesestruturais.flyweight.ItemEstoque;
import padroesestruturais.proxy.RelatorioFinanceiro;
import padroesestruturais.proxy.RelatorioFinanceiroProxy;

public class Main {

    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE GESTÃO DA HAMBURGUERIA ===");
        System.out.println();

        // 1. Configuração Inicial e Estoque (FLYWEIGHT)
        IngredienteFactory ingredienteFactory = new IngredienteFactory();
        ItemEstoque alfaceEstoque = new ItemEstoque(ingredienteFactory.getIngrediente("Alface", "Vegetal", 0.50), 100);
        System.out.println("[Estoque] " + alfaceEstoque.getDescricao() + " preparado.");

        // 2. Preparação do Cardápio (ABSTRACT FACTORY & ITERATOR)
        Cardapio cardapio = new Cardapio();
        cardapio.adicionarItem(GourmetFactory.getInstancia().criarHamburguerPrincipal());
        cardapio.adicionarItem(ClassicoFactory.getInstancia().criarHamburguerPrincipal());
        cardapio.adicionarItem(FitFactory.getInstancia().criarHamburguerPrincipal());

        System.out.println("\n--- Cliente olhando o Cardápio ---");
        IteradorCardapio iterador = cardapio.criarIterador();
        while (iterador.temProximo()) {
            imprimirHamburguer(iterador.proximo());
        }

        // 3. Customização de Pedido (PROTOTYPE)
        System.out.println("\n--- Cliente solicita uma customização ---");
        ReceitaHamburguerPrototype basePicanha = new ReceitaHamburguerPrototype(
                "Picanha Premium", "Brioche", "Cheddar",
                new ProteinaPicanha(new AoPonto()), 40.0);
        
        ReceitaHamburguerPrototype picanhaCustomizada = (ReceitaHamburguerPrototype) basePicanha.clonar();
        picanhaCustomizada.comNome("Picanha Premium do Cliente").comQueijo("Suíço");
        System.out.println("Criado a partir de protótipo: " + picanhaCustomizada.getDescricao());

        // 4. Abertura do Pedido e Monitoramento (STATE & OBSERVER)
        Pedido pedido = new Pedido();
        pedido.adicionarObservador(new ClienteNotificador());
        pedido.adicionarObservador(new CozinhaNotificador());
        pedido.marcarRetiradaBalcao();
        System.out.println("\n[Pedido] Novo pedido iniciado (Código: " + pedido.getCodigo() + ")");

        // 5. Adição de Itens via Comandos (COMMAND & ADAPTER)
        GerenciadorComandosPedido gerenciadorComandos = new GerenciadorComandosPedido();
        
        // Adicionando item padrão
        ItemCardapio burguerGourmet = GourmetFactory.getInstancia().criarHamburguerEspecial();
        gerenciadorComandos.executar(new AdicionarItemCommand(pedido, burguerGourmet));

        // Adicionando ingrediente externo via Adaptador
        IngredientePesadoExterno baconExterno = new IngredientePesadoExterno("Bacon Extra", 200, 50.0);
        IngredientePesadoAdapter baconAdaptado = new IngredientePesadoAdapter(baconExterno);
        gerenciadorComandos.executar(new AdicionarItemCommand(pedido, baconAdaptado));

        // 6. Mediação e Atendimento (MEDIATOR)
        CentralHamburgueria central = new CentralHamburgueria();
        Atendente atendente = new Atendente(central);
        Caixa caixa = new Caixa(central);

        System.out.println("\n--- Processando Pedido ---");
        atendente.receberPedido(pedido);

        // 7. Preparação na Cozinha (TEMPLATE METHOD)
        ProcessoPreparo preparo = new ProcessoPreparoGourmet();
        preparo.preparar(pedido);

        // 8. Processamento de Pagamento e Descontos (STRATEGY & CHAIN OF RESPONSIBILITY)
        DescontoPedidoGrande chainDescontos = new DescontoPedidoGrande();
        chainDescontos.setProximo(new DescontoRetiradaBalcao());

        ProcessadorPagamento processadorPagamento = new ProcessadorPagamento(new PagamentoPix());
        processadorPagamento.setDescontoPedido(chainDescontos);

        double totalComDescontosFixos = processadorPagamento.pagar(pedido);

        // 9. Validação de Cupom Especial (INTERPRETER)
        System.out.println("\n--- Validando Cupom Promocional ---");
        ExpressaoPedido regraCupom = new ExpressaoE(
                new ExpressaoTotalMaiorQue(30.0),
                new ExpressaoRetiradaBalcao()
        );
        CupomDesconto cupom = new CupomDesconto("PROMO_OPEN", 0.15, regraCupom);
        double valorFinal = cupom.aplicar(pedido, totalComDescontosFixos); // Aplica sobre o valor já com descontos fixos

        caixa.finalizarPagamento(pedido, valorFinal);

        // 10. Auditoria e Relatórios (PROXY)
        System.out.println("\n--- Auditoria Final ---");
        RelatorioFinanceiro relatorio = new RelatorioFinanceiroProxy("gerente");
        System.out.println(relatorio.gerarResumo(pedido));

        System.out.println("\n=== RESUMO FINAL DO ATENDIMENTO ===");
        System.out.println("Itens: " + pedido.getResumo());
        System.out.println("Forma de Pagamento: " + processadorPagamento.getDescricaoPagamento());
        System.out.println("Total Final Pago: R$ " + String.format("%.2f", valorFinal));
    }

    private static void imprimirHamburguer(ItemCardapio hamburguer) {
        System.out.println(" -> " + hamburguer.getDescricao() + " [R$ " + String.format("%.2f", hamburguer.getPreco()) + "]");
    }
}
