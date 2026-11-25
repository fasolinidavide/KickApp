package it.unibg.kickapp.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import it.unibg.kickapp.model.enums.TipoAbbonamento;


/**
 * test: calcolo della scadenza e validità
 */
class AbbonamentoTest{
	
	private static final LocalDate INIZIO = LocalDate.of(2026, 1, 10);
	
	@Test
	void ScadenzeDopoUnMese() {
		Abbonamento abb = new Abbonamento(TipoAbbonamento.MENSILE, INIZIO, 70);
		assertEquals(LocalDate.of(2026, 2, 10), abb.getDataScadenza());
	}
	
	@Test
	void ScadenzeDopoTreMesi() {
		Abbonamento abb = new Abbonamento(TipoAbbonamento.TRIMESTRALE, INIZIO, 180);
		assertEquals(LocalDate.of(2026, 4, 10), abb.getDataScadenza());
	}
	
	@Test
	void ScadenzeDopoUnAnno() {
		Abbonamento abb = new Abbonamento(TipoAbbonamento.ANNUALE, INIZIO, 360);
		assertEquals(LocalDate.of(2027, 1, 10), abb.getDataScadenza());
	}
	
	@Test
	void ValidoPrimoEUltmo() {
        Abbonamento a = new Abbonamento(TipoAbbonamento.MENSILE, INIZIO, 70);
        assertTrue(a.isValido(INIZIO));
        assertTrue(a.isValido(a.getDataScadenza()));
	}
	
	@Test
	void NonValidoPrimoEUltmo() {
        Abbonamento a = new Abbonamento(TipoAbbonamento.MENSILE, INIZIO, 70);
        assertFalse(a.isValido(INIZIO.minusDays(1)));
        assertFalse(a.isValido(a.getDataScadenza().plusDays(1)));
	}
	
}
