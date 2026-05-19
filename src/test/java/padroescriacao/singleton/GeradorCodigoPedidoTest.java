package padroescriacao.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Teste do Padrão Singleton - Gerador de Código")
class GeradorCodigoPedidoTest {

    private void imprimirSeparador(String titulo) {
        System.out.println("\n========================================================");
        System.out.println(" [SINGLETON] " + titulo);
        System.out.println("========================================================");
    }

    @Test
    @DisplayName("Deve garantir instância única do Singleton")
    void deveGarantirInstanciaUnica() {
        imprimirSeparador("Validação de Instância Única");
        System.out.println();
        
        GeradorCodigoPedido instancia1 = GeradorCodigoPedido.getInstancia();
        GeradorCodigoPedido instancia2 = GeradorCodigoPedido.getInstancia();
        
        System.out.println("Instância 1: " + instancia1.hashCode());
        System.out.println("Instância 2: " + instancia2.hashCode());
        
        assertSame(instancia1, instancia2, "As instâncias devem ser as mesmas");
        System.out.println("Sucesso: Ambas as variáveis apontam para o mesmo endereço de memória.");
    }

    @Test
    @DisplayName("Deve gerar códigos sequenciais e únicos")
    void deveGerarCodigosSequenciais() {
        imprimirSeparador("Geração de Códigos Únicos");
        System.out.println();
        
        GeradorCodigoPedido gerador = GeradorCodigoPedido.getInstancia();
        
        String codigo1 = gerador.proximoCodigo();
        String codigo2 = gerador.proximoCodigo();

        assertNotEquals(codigo1, codigo2, "Os códigos devem ser diferentes");
        assertTrue(codigo1.startsWith("PED-"));
        assertTrue(codigo2.startsWith("PED-"));
    }
}
