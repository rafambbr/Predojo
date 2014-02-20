package br.com.rafaelcamargo.predojo.business.impl;

import java.text.ParseException;

import lombok.Data;
import br.com.rafaelcamargo.predojo.domain.Arma;
import br.com.rafaelcamargo.predojo.domain.Jogador;
import br.com.rafaelcamargo.predojo.domain.Partida;

public @Data class CriadorDePartida {

	//Jogadores
	private final Jogador RAFAEL = new Jogador("Rafael");
	private final Jogador PEDRO = new Jogador("Pedro");
	private final Jogador JOAO = new Jogador("Joao");
	private final Jogador THIAGO = new Jogador("Thiago");
	private final Jogador ANDRE = new Jogador("Andre");
	
	//Armas
	private final Arma M16 = new Arma("M16");
	private final Arma AK47 = new Arma("AK47");
	private final Arma GLOCK = new Arma("Glock");
	private final Arma KNIFE = new Arma("Knife");
	private final Arma M21 = new Arma("M21");
	
	//Mapa
	private final Jogador WORLD = new Jogador("<WORLD>");
	private final Arma DROWN = new Arma("DROWN");

	private Partida partida01;
	private Partida partida02;
	private Partida partida03;
	
	private PartidaBuilder partidaBuilder;
	
	public CriadorDePartida(){
		partidaBuilder = new PartidaBuilder();
		criarPartidas();
	}

	private void criarPartidas() {
		
		try {
			
			this.partida01 = this.partidaBuilder.addInicioPartida(91348001L, "23/04/2013 15:34:22")
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
		
			this.partida02 = this.partidaBuilder.addInicioPartida(21345002L, "23/04/2013 15:34:22")
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
		
		this.partida03 = this.partidaBuilder.addInicioPartida(61322003L, "23/04/2013 15:34:22")
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
		
		
		} catch (ParseException e) {
			System.out.println("Erro ao criar partidas teste " + e.getMessage() );
		}
	}
	
	/**
  	=========================== PARTIDA:91348001==========================
	Vencedores: Jogador:Rafael - Arma Favorita:Knife
	======================================================================
	Ranking: 
	Assassinatos:0 - Mortes:7 - Premios:0 - Jogador:Andre
	Assassinatos:10 - Mortes:0 - Premios:7 - Jogador:Rafael
	Assassinatos:4 - Mortes:5 - Premios:0 - Jogador:Joao
	Assassinatos:3 - Mortes:7 - Premios:0 - Jogador:Pedro
	Assassinatos:7 - Mortes:5 - Premios:3 - Jogador:Thiago
	======================================================================
	Streak: 
	Jogador:Rafael - Numero de Assassinatos Consecutivos:10
	======================================================================
	 */
	public Partida getPartida01(){
		return this.partida01;
	}
	
	/**
	 =========================== PARTIDA:21345002==========================
	Vencedores: Jogador:Rafael - Arma Favorita:Knife
	======================================================================
	Ranking: 
	Assassinatos:0 - Mortes:4 - Premios:0 - Jogador:Andre
	Assassinatos:6 - Mortes:0 - Premios:3 - Jogador:Rafael
	Assassinatos:0 - Mortes:1 - Premios:0 - Jogador:Joao
	Assassinatos:1 - Mortes:3 - Premios:0 - Jogador:Pedro
	Assassinatos:2 - Mortes:1 - Premios:0 - Jogador:Thiago
	======================================================================
	Streak: 
	Jogador:Rafael - Numero de Assassinatos Consecutivos:6
	======================================================================
	 */
	public Partida getPartida02(){
		return this.partida02;
	}
	
	/**
	=========================== PARTIDA:61322003==========================
	Vencedores: Jogador:Rafael - Arma Favorita:Knife
	Jogador:Pedro - Arma Favorita:Knife
	======================================================================
	Ranking: 
	Assassinatos:1 - Mortes:0 - Premios:1 - Jogador:Rafael
	Assassinatos:0 - Mortes:2 - Premios:0 - Jogador:Joao
	Assassinatos:1 - Mortes:0 - Premios:1 - Jogador:Pedro
	======================================================================
	Streak: 
	Jogador:Rafael - Numero de Assassinatos Consecutivos:1
	======================================================================
	 */
	public Partida getPartida03(){
		return this.partida03;
	}
	
}
