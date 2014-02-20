package br.com.rafaelcamargo.predojo.business.impl;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.rafaelcamargo.predojo.business.EstatisticaPartida;
import br.com.rafaelcamargo.predojo.business.LogParser;
import br.com.rafaelcamargo.predojo.domain.Partida;
import br.com.rafaelcamargo.predojo.exception.BusinessException;

public class PartidaParserImplTest {

	//Partidas
	private static Partida PARTIDA01;
	private static Partida PARTIDA02;
	
	@BeforeClass
	public static void setUp() throws ParseException{
		CriadorDePartida criadorDePartida = new CriadorDePartida();
		PARTIDA01 = criadorDePartida.getPartida01();
		PARTIDA02 = criadorDePartida.getPartida02();
	}

	@Test
	public void deveGerarEstatisticaDeDuasPartidas(){
		try {
			Collection<Partida> partidas = Arrays.asList(PARTIDA01, PARTIDA02);
			
			LogParser<Partida, EstatisticaPartida> partidaParser = new PartidaParserImpl();
			Collection<EstatisticaPartida> estatisticasPartidas = partidaParser.parse(partidas);
			
			Partida partida01 = new Partida(91348001L);
			Partida partida02 = new Partida(21345002L);
			
			assertEquals(2, estatisticasPartidas.size() );
			assertThat(partidas, contains(partida01, 
										  partida02));
			
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void deveGerarEstatisticaDeUmPartida(){
		try {
			Collection<Partida> partidas = Arrays.asList(PARTIDA01);
			
			LogParser<Partida, EstatisticaPartida> partidaParser = new PartidaParserImpl();
			Collection<EstatisticaPartida> estatisticasPartidas = partidaParser.parse(partidas);
			
			Partida partida01 = new Partida(91348001L);
			
			assertEquals(1, estatisticasPartidas.size() );
			assertThat(partidas, contains(partida01));
			
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test(expected=BusinessException.class)
	public void deveRetornarBusinessExceptionComAPartidaNula(){
		
		Partida partida = null;
		new PartidaParserImpl().parse(partida);
		
	}
	
	@Test(expected=BusinessException.class)
	public void deveRetornarBusinessExceptionComAListadePartidasContendoUmaPartidaNula(){
		
		Collection<Partida> partidas = new ArrayList<Partida>();
		partidas.add(null);
		
		new PartidaParserImpl().parse(partidas);
		
	}
	
}
