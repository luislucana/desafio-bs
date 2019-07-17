package br.com.bluesoft.desafio.util;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

public class MathUtilTest {

    @Test
    public void deveAplicarOPercentualCorretamente() {
        //given
        BigDecimal valor = BigDecimal.valueOf(200);
        BigDecimal percentual = BigDecimal.valueOf(22.4);

        //when
        BigDecimal resultado = MathUtil.calcularPercentual(valor, percentual);

        //then
        assertEquals(new BigDecimal("44.80"), resultado);
    }

    @Test
    public void deveComplementarParcelaMaisAltaDeUmMapComORestoDaDiferencaDoValorTotal() {

        Map<Integer, Double> valores = new HashMap<>();
        valores.put(1, 23.91);
        valores.put(2, 31.03);
        valores.put(3, 19.76);
        valores.put(4, 10.36);
        valores.put(5, 13.88);

        Double valorTotal = 100.0;

        Map<Integer, Double> valoresReprocessados = MathUtil.complementarUltimaParcelaComRestoDaDiferencaDoValorTotal(valores, valorTotal);

        double somaValoresMapa = valoresReprocessados
            .values()
            .stream()
            .mapToDouble(Number::doubleValue)
            .sum();

        assertThat(valoresReprocessados.size(), is(5));
        assertThat(valoresReprocessados.get(5), is(14.94));
        assertThat(somaValoresMapa, is(valorTotal));
    }

    @Test
    public void deveRetonarOPrimeiroComoMenorValor() {
        //FIXME
        fail("Falta implementar o teste para MathUtil.ehMenor. Crie Quantos testes achar necessário");
    }
}