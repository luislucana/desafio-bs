package br.com.bluesoft.desafio.service;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import br.com.bluesoft.desafio.model.Fornecedor;
import br.com.bluesoft.desafio.model.Item;

import static org.junit.Assert.*;

public class SeparaItensDeUmFornecedorTest {

    private Fornecedor cocaCola = new Fornecedor();
    private Fornecedor aguaDeLindoia = new Fornecedor();
    private Fornecedor pampers = new Fornecedor();
    private List<Item> itens = new ArrayList<>();

    private Item cocaCola2l = new Item();
    private Item fraldaDaMonica = new Item();
    private Item shampoInfantil = new Item();
    private Item aguaComGas = new Item();
    private Item aguaMineralDaNoruega = new Item();

    private Map<Fornecedor, List<Item>> itensSeparadosPorFornecedor;

    @Test
    public void separa(){
        criaItensDaCocaCola();
        criaItensDaAguaDeLindoia();
        criaItensDaPampers();

        aoSepararOsItens();

        garanteOTamanhoDoMapa();
        garanteItensDaCocaCola();
        garanteItensDaAguaDeLindoia();
        garanteItensDaPampers();

    }

    private void aoSepararOsItens() {
        SeparaItensDeUmFornecedor separador = new SeparaItensDeUmFornecedor();
        itensSeparadosPorFornecedor = separador.separa(itens);
    }

    private void garanteOTamanhoDoMapa() {
        Assert.assertTrue(itensSeparadosPorFornecedor.size() == 3);
    }

    private void garanteItensDaPampers() {
        final List<Item> itensDaCocaColaSeparados = itensSeparadosPorFornecedor.get(pampers);
        Assert.assertTrue(itensDaCocaColaSeparados.size() == 2);
        assertListaContemObjeto(itensDaCocaColaSeparados, shampoInfantil);
        assertListaContemObjeto(itensDaCocaColaSeparados, fraldaDaMonica);
    }

    private void garanteItensDaAguaDeLindoia() {
        final List<Item> itensDaCocaColaSeparados = itensSeparadosPorFornecedor.get(aguaDeLindoia);
        Assert.assertTrue(itensDaCocaColaSeparados.size() == 2);
        assertListaContemObjeto(itensDaCocaColaSeparados, aguaComGas);
        assertListaContemObjeto(itensDaCocaColaSeparados, aguaMineralDaNoruega);
    }

    private void garanteItensDaCocaCola() {
        final List<Item> itensDaCocaColaSeparados = itensSeparadosPorFornecedor.get(cocaCola);
        Assert.assertTrue(itensDaCocaColaSeparados.size() == 1);
        assertListaContemObjeto(itensDaCocaColaSeparados, cocaCola2l);
    }

    private void criaItensDaPampers() {
        pampers.setNome("Pampers LTDA");
        pampers.setCnpj("2123455");

        fraldaDaMonica.setFornecedor(pampers);
        fraldaDaMonica.setId(123l);
        fraldaDaMonica.setPreco(BigDecimal.TEN);
        fraldaDaMonica.setQuantidade(10);
        fraldaDaMonica.setTotal(BigDecimal.valueOf(100d));


        shampoInfantil.setFornecedor(pampers);
        shampoInfantil.setId(453l);
        shampoInfantil.setPreco(BigDecimal.ONE);
        shampoInfantil.setQuantidade(10);
        shampoInfantil.setTotal(BigDecimal.valueOf(10d));

        itens.add(fraldaDaMonica);
        itens.add(shampoInfantil);
    }

    private void criaItensDaAguaDeLindoia() {
        aguaDeLindoia.setNome("Agua de lindoia LTDA");
        aguaDeLindoia.setCnpj("21334425354");

        aguaComGas.setFornecedor(aguaDeLindoia);
        aguaComGas.setId(123654l);
        aguaComGas.setPreco(BigDecimal.TEN);
        aguaComGas.setQuantidade(10);
        aguaComGas.setTotal(BigDecimal.valueOf(100d));


        aguaMineralDaNoruega.setFornecedor(aguaDeLindoia);
        aguaMineralDaNoruega.setId(21345l);
        aguaMineralDaNoruega.setPreco(BigDecimal.valueOf(1000d));
        aguaMineralDaNoruega.setQuantidade(10);
        aguaMineralDaNoruega.setTotal(BigDecimal.valueOf(10000d));

        itens.add(aguaComGas);
        itens.add(aguaMineralDaNoruega);
    }

    private void criaItensDaCocaCola() {
        cocaCola.setCnpj("1231234314");
        cocaCola.setNome("Coca-cola LTDA");

        cocaCola2l.setFornecedor(cocaCola);
        cocaCola2l.setId(12393l);
        cocaCola2l.setPreco(BigDecimal.ONE);
        cocaCola2l.setQuantidade(5);
        cocaCola2l.setTotal(BigDecimal.valueOf(5d));

        itens.add(cocaCola2l);
    }

    private void assertListaContemObjeto(List<Item> itens, Item itemAGarantir) {
        assertTrue(itens.stream().anyMatch(item -> item.equals(itemAGarantir)));
    }

}