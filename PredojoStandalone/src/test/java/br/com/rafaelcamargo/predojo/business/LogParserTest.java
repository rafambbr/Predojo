package br.com.rafaelcamargo.predojo.business;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import br.com.rafaelcamargo.predojo.business.impl.PartidaParserImpl;
import br.com.rafaelcamargo.predojo.domain.Arma;
import br.com.rafaelcamargo.predojo.domain.Assassinato;
import br.com.rafaelcamargo.predojo.domain.HistoricoSequenciaAssassinatosConsecutivos;
import br.com.rafaelcamargo.predojo.domain.Jogador;
import br.com.rafaelcamargo.predojo.domain.Partida;
import br.com.rafaelcamargo.predojo.util.DateUtil;

public class LogParserTest {

	private Partida mockPartida;
	
	private Jogador rafel = new Jogador("Rafael");
	private Jogador pedro = new Jogador("Pedro");
	private Jogador world = new Jogador("<WORLD>");
	
	private Arma m16 = new Arma("M16");
	private Arma ak47 = new Arma("AK46");
	private Arma drown = new Arma("DROWN");
	
	private DateUtil dateUtil;
	
	@Before
	public void setUp(){
		
		//MOCK
		this.mockPartida = mock(Partida.class);
		
		//JOGADORES
		this.rafel = new Jogador("Rafael");
		this.pedro = new Jogador("Pedro");
		this.world = new Jogador("<WORLD>");
		
		//ARMAS
		this.m16 = new Arma("M16");
		this.ak47 = new Arma("AK46");
		this.drown = new Arma("DROWN");
		
		this.dateUtil = new DateUtil( DateUtil.DDMMYYYY_HHMMSS );
	}
	
	@Test
	public void convertePartidaParaEstatisticaPartida(){
		try{
			//Cria partida
			Long idPartida = 55667788L;
			Date dataInicioPartida = getData("23/04/2013 15:34:22");
			Date dataFimPartida = getData("14/02/2014 15:59:04");
			Set<Assassinato> assassinatos = new HashSet<Assassinato>();
			assassinatos.add( new Assassinato( getData("23/04/2013 15:36:04"), rafel, pedro, ak47) );
			assassinatos.add( new Assassinato( getData("23/04/2013 15:36:33"), pedro, rafel, m16) );
			assassinatos.add( new Assassinato( getData("23/04/2013 15:39:22"), rafel, pedro, ak47) );
			assassinatos.add( new Assassinato( getData("14/02/2014 15:01:23"), rafel, pedro, ak47) );
			assassinatos.add( new Assassinato( getData("14/02/2014 15:03:24"), world, pedro, drown) );
			
			//Configura mock
			when( mockPartida.getIdPartida() ).thenReturn( idPartida );
			when( mockPartida.getDataInicio() ).thenReturn( dataInicioPartida );
			when( mockPartida.getDataFim() ).thenReturn( dataFimPartida );
			when( mockPartida.getAssassinatos() ).thenReturn( assassinatos );
			
			
			LogParser<Partida, EstatisticaPartida> logParser = new PartidaParserImpl();
			EstatisticaPartida estatisticaPartida = logParser.parse(mockPartida);
			
			
			assertThat(estatisticaPartida.getIdPartida() , equalTo(idPartida) );
			
			List<Jogador> vencedores = new ArrayList<>( estatisticaPartida.getVencedores() );
			assertThat(vencedores.size() , equalTo(1));
			assertThat(vencedores, contains(rafel));
			
			List<Jogador> jogadoresDaPartida = new ArrayList<>( estatisticaPartida.getJogadoresDaPartida() );
			assertThat(jogadoresDaPartida.size() , equalTo(2));
			assertThat(jogadoresDaPartida, contains(rafel, pedro));
			
			int quantidadeAssassinatosRafael = estatisticaPartida.getQuantidadeAssassinatos(rafel);
			int quantidadeAssassinatosPedro = estatisticaPartida.getQuantidadeAssassinatos(pedro);
			assertEquals(quantidadeAssassinatosRafael, 3);
			assertEquals(quantidadeAssassinatosPedro, 1);
			
			int quantidadeMortesRafael = estatisticaPartida.getQuantidadeMortes(rafel);
			int quantidadeMortesPedro = estatisticaPartida.getQuantidadeMortes(pedro);
			assertEquals(quantidadeMortesRafael, 1);
			assertEquals(quantidadeMortesPedro, 3);
			
			int quantidadePremiosRafael = estatisticaPartida.getQuantidadePremios(rafel);
			int quantidadePremiosPedro = estatisticaPartida.getQuantidadePremios(pedro);
			assertEquals(quantidadePremiosRafael, 0);
			assertEquals(quantidadePremiosPedro, 0);
			
			HistoricoSequenciaAssassinatosConsecutivos jogadorComMaiorNumeroDeMortesConsecutivas = estatisticaPartida.getJogadorComMaiorNumeroDeMortesConsecutivas();
			Jogador assassino = jogadorComMaiorNumeroDeMortesConsecutivas.getAssassino();
			Integer numeroAssassinatosConsecutivos = jogadorComMaiorNumeroDeMortesConsecutivas.getNumeroAssassinatosConsecutivos();
			assertEquals(assassino, rafel);
			assertEquals(numeroAssassinatosConsecutivos, 2, 0);
			
		}catch(Exception e){
			fail();
		}
	}
	
	public Date getData(String data) throws ParseException{
		return this.dateUtil.getData(data);
	}
}
