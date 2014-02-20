package br.com.rafaelcamargo.predojo.business.impl;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.text.ParseException;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.rafaelcamargo.predojo.business.EstatisticaPartida;
import br.com.rafaelcamargo.predojo.domain.Jogador;
import br.com.rafaelcamargo.predojo.domain.Partida;

public class EstatisticaPartidaMemoryImplTest {
	
	//Jogadores
	private static Jogador RAFAEL;
	private static Jogador PEDRO;
	private static Jogador JOAO;
	private static Jogador THIAGO;

	//Partidas
	private static Partida PARTIDA01;
	private static Partida PARTIDA02;
	private static Partida PARTIDA03;
	
	@BeforeClass
	public static void setUp() throws ParseException{
		CriadorDePartida criadorDePartida = new CriadorDePartida();
		PARTIDA01 = criadorDePartida.getPartida01();
		PARTIDA02 = criadorDePartida.getPartida02();
		PARTIDA03 = criadorDePartida.getPartida03();
		
		RAFAEL = criadorDePartida.getRAFAEL();
		PEDRO = criadorDePartida.getPEDRO();
		JOAO = criadorDePartida.getJOAO();
		THIAGO = criadorDePartida.getTHIAGO();
	}
	
	@Test
	public void quemVenceuAPartida(){
		
		EstatisticaPartida estatisticaPartida01 = new EstatisticaPartidaMemoryImpl(PARTIDA01);
		Set<Jogador> vencedoresPartida01 = estatisticaPartida01.getVencedores();
		
		EstatisticaPartida estatisticaPartida02 = new EstatisticaPartidaMemoryImpl(PARTIDA02);
		Set<Jogador> vencedoresPartida02 = estatisticaPartida02.getVencedores();
		
		EstatisticaPartida estatisticaPartida03 = new EstatisticaPartidaMemoryImpl(PARTIDA03);
		Set<Jogador> vencedoresPartida03 = estatisticaPartida03.getVencedores();
		
		assertEquals(1, vencedoresPartida01.size());
		assertThat(vencedoresPartida01, contains(RAFAEL));
		
		assertEquals(1, vencedoresPartida02.size());
		assertThat(vencedoresPartida02, contains(RAFAEL));
		
		assertEquals(2, vencedoresPartida03.size());
		assertThat(vencedoresPartida03, contains(RAFAEL, PEDRO));
	}
	
	@Test
	public void deveGanharPremioAoMatarCincoJogadoresEmMenosDeUmMinuto(){
		
		EstatisticaPartida estatisticaPartida01 = new EstatisticaPartidaMemoryImpl(PARTIDA01);
		int quantidadePremiosRafael = estatisticaPartida01.getQuantidadePremios(RAFAEL);
		int quantidadePremiosThiago = estatisticaPartida01.getQuantidadePremios(THIAGO);
		int quantidadePremiosPedro = estatisticaPartida01.getQuantidadePremios(PEDRO);
		int quantidadePremiosJoao = estatisticaPartida01.getQuantidadePremios(JOAO);
		
		assertEquals(1, quantidadePremiosRafael);
		assertEquals(0, quantidadePremiosThiago);
		assertEquals(0, quantidadePremiosPedro);
		assertEquals(0, quantidadePremiosJoao);
	}
	
}
