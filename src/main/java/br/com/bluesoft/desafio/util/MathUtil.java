package br.com.bluesoft.desafio.util;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.math.BigDecimal;
import java.util.Map;

public class MathUtil {

    public static BigDecimal calcularPercentual(BigDecimal valor, BigDecimal percentual) {
        //FIXME
        throw new NotImplementedException();
    }

    public static Map<Integer, Double> complementarUltimaParcelaComRestoDaDiferencaDoValorTotal(Map<Integer, Double> valores, Double valorTotal) {
        //FIXME
        throw new NotImplementedException();
    }

    public static boolean ehMenor(BigDecimal valor, BigDecimal valorParaComparar) {
        return valor.compareTo(valorParaComparar) < 1;
    }
}
