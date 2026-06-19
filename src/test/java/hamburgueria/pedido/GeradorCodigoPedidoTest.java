package hamburgueria.pedido;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Teste do Padrão Singleton - Gerador de Código")
class GeradorCodigoPedidoTest {

    private void imprimirSeparador(String titulo) {
    }

    @Test
    @DisplayName("Deve garantir instância única do Singleton")
    void deveGarantirInstanciaUnica() {
        imprimirSeparador("Validação de Instância Única");
        
        GeradorCodigoPedido instancia1 = GeradorCodigoPedido.getInstancia();
        GeradorCodigoPedido instancia2 = GeradorCodigoPedido.getInstancia();
        
        
        assertSame(instancia1, instancia2, "As instâncias devem ser as mesmas");
    }

    @Test
    @DisplayName("Deve gerar códigos sequenciais e únicos")
    void deveGerarCodigosSequenciais() {
        imprimirSeparador("Geração de Códigos Únicos");
        
        GeradorCodigoPedido gerador = GeradorCodigoPedido.getInstancia();
        
        String codigo1 = gerador.proximoCodigo();
        String codigo2 = gerador.proximoCodigo();

        assertNotEquals(codigo1, codigo2, "Os códigos devem ser diferentes");
        assertTrue(codigo1.startsWith("PED-"));
        assertTrue(codigo2.startsWith("PED-"));
    }
}
