package br.com.rafaelcamargo.predojo.business.impl;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.File;
import java.net.URL;
import java.util.Set;

import org.junit.Test;

import br.com.rafaelcamargo.predojo.business.LeitorLog;
import br.com.rafaelcamargo.predojo.business.impl.LeitorLogPartidaImpl;
import br.com.rafaelcamargo.predojo.domain.Partida;


public class LeitorLogPartidaImplTest {

	@Test
	public void analisaArquivoComDuasPartidas(){
		try {
			
			URL resource = getClass().getResource("/partida_jogo_02.log");
			File logFile = new File(resource.getFile());
			LeitorLog<Partida> leitorLog = new LeitorLogPartidaImpl(logFile);
			
			Set<Partida> partidas = leitorLog.processaLog();
			
			Partida partida01 = new Partida(11348965L);
			Partida partida02 = new Partida(78348965L);
			
			assertEquals(2, partidas.size());
			assertThat(partidas, contains(partida01, 
										  partida02));
			
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void analisaArquivoComUmaPartida(){
		try {
			
			URL resource = getClass().getResource("/partida_jogo.log");
			File logFile = new File(resource.getFile());
			LeitorLog<Partida> leitorLog = new LeitorLogPartidaImpl(logFile);
			
			Set<Partida> partidas = leitorLog.processaLog();
			
			Partida partida01 = new Partida(11348965L);
			
			assertEquals(1, partidas.size());
			assertThat(partidas, contains(partida01));
			
		} catch (Exception e) {
			fail();
		}
	}
}
