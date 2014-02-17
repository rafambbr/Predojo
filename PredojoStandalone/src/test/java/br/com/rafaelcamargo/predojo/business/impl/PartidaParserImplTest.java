package br.com.rafaelcamargo.predojo.business.impl;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.junit.Test;

import br.com.rafaelcamargo.predojo.business.EstatisticaPartida;
import br.com.rafaelcamargo.predojo.business.LeitorLog;
import br.com.rafaelcamargo.predojo.business.LogParser;
import br.com.rafaelcamargo.predojo.business.impl.LeitorLogPartidaImpl;
import br.com.rafaelcamargo.predojo.business.impl.PartidaParserImpl;
import br.com.rafaelcamargo.predojo.domain.Partida;
import br.com.rafaelcamargo.predojo.exception.BusinessException;

public class PartidaParserImplTest {

	@Test
	public void deveGerarEstatisticaDeDuasPartidas(){
		try {
			URL resource = getClass().getResource("/partida_jogo_02.log");
			File logFile = new File(resource.getFile());
			LeitorLog<Partida> analisadorDeLogPartida = new LeitorLogPartidaImpl(logFile);
		
			Set<Partida> partidas = analisadorDeLogPartida.processaLog();
			
			LogParser<Partida, EstatisticaPartida> partidaParser = new PartidaParserImpl();
			Collection<EstatisticaPartida> estatisticasPartidas = partidaParser.parse(partidas);
			
			Partida partida01 = new Partida(11348965L);
			Partida partida02 = new Partida(78348965L);
			
			assertEquals(2, estatisticasPartidas.size() );
			assertThat(partidas, contains(partida01, 
										  partida02));
			
		} catch (IOException e) {
			fail();
		}
	}
	
	@Test
	public void deveGerarEstatisticaDeUmPartida(){
		try {
			URL resource = getClass().getResource("/partida_jogo.log");
			File logFile = new File(resource.getFile());
			LeitorLog<Partida> analisadorDeLogPartida = new LeitorLogPartidaImpl(logFile);
		
			Set<Partida> partidas = analisadorDeLogPartida.processaLog();
			
			LogParser<Partida, EstatisticaPartida> partidaParser = new PartidaParserImpl();
			Collection<EstatisticaPartida> estatisticasPartidas = partidaParser.parse(partidas);
			
			Partida partida01 = new Partida(11348965L);
			
			assertEquals(1, estatisticasPartidas.size() );
			assertThat(partidas, contains(partida01));
			
		} catch (IOException e) {
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
