package br.com.rafaelcamargo.predojo.business.impl;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.text.ParseException;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import br.com.rafaelcamargo.predojo.business.EstatisticaPartida;
import br.com.rafaelcamargo.predojo.business.PartidaBuilder;
import br.com.rafaelcamargo.predojo.business.impl.EstatisticaPartidaMemoryImpl;
import br.com.rafaelcamargo.predojo.domain.Arma;
import br.com.rafaelcamargo.predojo.domain.Jogador;
import br.com.rafaelcamargo.predojo.domain.Partida;

public class EstatisticaPartidaMemoryImplTest {
	
	//Jogadores
	private static final Jogador RAFAEL = new Jogador("Rafael");
	private static final Jogador PEDRO = new Jogador("Pedro");
	private static final Jogador JOAO = new Jogador("Joao");
	private static final Jogador THIAGO = new Jogador("Thiago");
	private static final Jogador ANDRE = new Jogador("Andre");
	
	//Armas
	private static final Arma M16 = new Arma("M16");
	private static final Arma AK47 = new Arma("AK47");
	private static final Arma GLOCK = new Arma("Glock");
	private static final Arma KNIFE = new Arma("Knife");
	private static final Arma M21 = new Arma("M21");
	
	//Mapa
	private static final Jogador WORLD = new Jogador("<WORLD>");
	private static final Arma DROWN = new Arma("DROWN");

	private static Partida PARTIDA01;
	private static Partida PARTIDA02;
	private static Partida PARTIDA03;
	
	@BeforeClass
	public static void setUp() throws ParseException{
		gerarPartidas();
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
	
	private static void gerarPartidas() throws ParseException {
		PartidaBuilder partidaBuilder = new PartidaBuilder();
		PARTIDA01 = partidaBuilder.addInicioPartida(91348001L, "23/04/2013 15:34:22")
						  .addAssassinato("23/04/2013 15:36:04", RAFAEL, PEDRO, KNIFE)
						  .addAssassinato("23/04/2013 15:36:33", JOAO, ANDRE, AK47)
						  .addAssassinato("23/04/2013 15:39:22", PEDRO, JOAO, M16)
						  .addAssassinato("14/02/2014 15:01:23", JOAO, THIAGO, GLOCK)
						  .addAssassinato("14/02/2014 15:03:24", WORLD, ANDRE, DROWN)
						  .addAssassinato("14/02/2014 15:05:16", RAFAEL, JOAO, KNIFE)
						  .addAssassinato("14/02/2014 15:07:33", THIAGO, ANDRE, M16)
						  .addAssassinato("14/02/2014 15:11:02", JOAO, JOAO, AK47)
						  .addAssassinato("14/02/2014 15:10:02", RAFAEL, THIAGO, KNIFE)
						  .addAssassinato("14/02/2014 15:10:08", RAFAEL, ANDRE, KNIFE)
						  .addAssassinato("14/02/2014 15:10:09", RAFAEL, PEDRO, KNIFE)
						  .addAssassinato("14/02/2014 15:10:12", RAFAEL, THIAGO, KNIFE)
						  .addAssassinato("14/02/2014 15:10:22", RAFAEL, ANDRE, KNIFE)
						  .addAssassinato("14/02/2014 15:10:24", RAFAEL, PEDRO, KNIFE)
						  .addAssassinato("14/02/2014 15:10:25", THIAGO, PEDRO, M16)
						  .addAssassinato("14/02/2014 15:10:38", PEDRO, ANDRE, GLOCK)
						  .addAssassinato("14/02/2014 15:10:43", THIAGO, JOAO, M16)
						  .addAssassinato("14/02/2014 15:10:53", RAFAEL, PEDRO, KNIFE)
						  .addAssassinato("14/02/2014 15:12:14", JOAO, PEDRO, AK47)
						  .addAssassinato("14/02/2014 15:14:22", THIAGO, ANDRE, M21)
						  .addAssassinato("14/02/2014 15:26:04", RAFAEL, THIAGO, KNIFE)
						  .addAssassinato("14/02/2014 15:36:33", WORLD, JOAO, DROWN)
						  .addAssassinato("14/02/2014 15:38:36", THIAGO, PEDRO, AK47)
						  .addAssassinato("14/02/2014 15:43:27", PEDRO, THIAGO, GLOCK)
						  .addAssassinato("14/02/2014 15:49:47", THIAGO, JOAO, AK47)
						  .addAssassinato("14/02/2014 15:54:22", THIAGO, ANDRE, GLOCK)
						  .addDataFimPartida("14/02/2014 15:59:04")
						  .build();
		
		PARTIDA02 = partidaBuilder.addInicioPartida(21345002L, "23/04/2013 15:34:22")
				  .addAssassinato("23/04/2013 15:36:04", WORLD, PEDRO, DROWN)
				  .addAssassinato("23/04/2013 15:36:33", WORLD, ANDRE, DROWN)
				  .addAssassinato("23/04/2013 15:39:22", WORLD, JOAO, DROWN)
				  .addAssassinato("14/02/2014 15:01:23", WORLD, THIAGO, DROWN)
				  .addAssassinato("14/02/2014 15:03:24", WORLD, ANDRE, DROWN)
				  .addAssassinato("14/02/2014 15:05:16", RAFAEL, JOAO, KNIFE)
				  .addAssassinato("14/02/2014 15:07:33", WORLD, ANDRE, DROWN)
				  .addAssassinato("14/02/2014 15:11:02", WORLD, JOAO, DROWN)
				  .addAssassinato("14/02/2014 15:10:02", WORLD, THIAGO, DROWN)
				  .addAssassinato("14/02/2014 15:10:08", RAFAEL, ANDRE, KNIFE)
				  .addAssassinato("14/02/2014 15:10:09", WORLD, PEDRO, DROWN)
				  .addAssassinato("14/02/2014 15:10:12", WORLD, THIAGO, DROWN)
				  .addAssassinato("14/02/2014 15:10:22", RAFAEL, ANDRE, KNIFE)
				  .addAssassinato("14/02/2014 15:10:24", RAFAEL, PEDRO, KNIFE)
				  .addAssassinato("14/02/2014 15:10:25", THIAGO, PEDRO, M16)
				  .addAssassinato("14/02/2014 15:10:38", PEDRO, ANDRE, GLOCK)
				  .addAssassinato("14/02/2014 15:10:43", WORLD, JOAO, DROWN)
				  .addAssassinato("14/02/2014 15:10:53", RAFAEL, PEDRO, KNIFE)
				  .addAssassinato("14/02/2014 15:12:14", WORLD, PEDRO, DROWN)
				  .addAssassinato("14/02/2014 15:14:22", THIAGO, ANDRE, M21)
				  .addAssassinato("14/02/2014 15:26:04", RAFAEL, THIAGO, KNIFE)
				  .addAssassinato("14/02/2014 15:36:33", WORLD, JOAO, DROWN)
				  .addAssassinato("14/02/2014 15:38:36", WORLD, PEDRO, DROWN)
				  .addAssassinato("14/02/2014 15:43:27", WORLD, THIAGO, DROWN)
				  .addAssassinato("14/02/2014 15:49:47", WORLD, JOAO, DROWN)
				  .addAssassinato("14/02/2014 15:54:22", WORLD, ANDRE, DROWN)
				  .addDataFimPartida("14/02/2014 15:59:04")
				  .build();
		
		PARTIDA03 = partidaBuilder.addInicioPartida(61322003L, "23/04/2013 15:34:22")
				  .addAssassinato("23/04/2013 15:36:33", WORLD, ANDRE, DROWN)
				  .addAssassinato("23/04/2013 15:39:22", WORLD, JOAO, DROWN)
				  .addAssassinato("14/02/2014 15:01:23", WORLD, THIAGO, DROWN)
				  .addAssassinato("14/02/2014 15:03:24", WORLD, ANDRE, DROWN)
				  .addAssassinato("14/02/2014 15:05:16", RAFAEL, JOAO, KNIFE)
				  .addAssassinato("14/02/2014 15:05:16", PEDRO, JOAO, KNIFE)
				  .addAssassinato("14/02/2014 15:07:33", WORLD, ANDRE, DROWN)
				  .addAssassinato("14/02/2014 15:11:02", WORLD, JOAO, DROWN)
				  .addAssassinato("14/02/2014 15:10:02", WORLD, THIAGO, DROWN)
				  .addDataFimPartida("14/02/2014 15:59:04")
				  .build();
		
	}
	
}
