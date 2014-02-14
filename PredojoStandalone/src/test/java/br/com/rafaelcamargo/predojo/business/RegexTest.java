package br.com.rafaelcamargo.predojo.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import br.com.rafaelcamargo.predojo.domain.TipoLinha;

public class RegexTest {

	@Test
	public void encontraDadosDoInicioDaPartida(){

		Pattern p = TipoLinha.INICIO_PARTIDA.getPattern();
		Matcher m = p.matcher("23/04/2013 15:34:22 - New match 11348965 has started");
		if(m.find()) {
			String dataInicioPartida = m.group(1);
			String separador = m.group(2);
			String informacaoNovaPartida = m.group(3);
			String idPartida = m.group(4);
			String informacaoPartidaIniciada = m.group(5);
			
			assertEquals(dataInicioPartida, "23/04/2013 15:34:22");
			assertEquals(separador, "-");
			assertEquals(informacaoNovaPartida, "New match");
			assertEquals(idPartida, "11348965");
			assertEquals(informacaoPartidaIniciada, "has started");
			
		}else{
			fail();
		}
	}
	
	@Test
	public void encontraDadosJogadorMatandoJogador(){

		Pattern p = TipoLinha.JOGADOR_MATA_JOGADOR.getPattern();
		Matcher m = p.matcher("23/04/2013 15:36:04 - Roman killed Nick using M16");
		if(m.find()) {
			String dataInicioPartida = m.group(1);
			String separador = m.group(2);
			String jogadorAssasino = m.group(3);
			String informacaoMatou = m.group(4);
			String jogadorMorto = m.group(5);
			String informacaoAcao = m.group(6);
			String armaAssasino = m.group(7);
			
			assertEquals(dataInicioPartida, "23/04/2013 15:36:04");
			assertEquals(separador, "-");
			assertEquals(jogadorAssasino, "Roman");
			assertEquals(informacaoMatou, "killed");
			assertEquals(jogadorMorto, "Nick");
			assertEquals(informacaoAcao, "using");
			assertEquals(armaAssasino, "M16");
			
		}else{
			fail();
		}
	}
	
	@Test
	public void encontraDadosMundoMataJogador(){

		Pattern p = TipoLinha.MUNDO_MATA_JOGADOR.getPattern();
		Matcher m = p.matcher("23/04/2013 15:36:33 - <WORLD> killed Nick by DROWN");
		if(m.find()) {
			String dataInicioPartida = m.group(1);
			String separador = m.group(2);
			String jogadorAssasino = m.group(3);
			String informacaoMatou = m.group(4);
			String jogadorMorto = m.group(5);
			String informacaoAcao = m.group(6);
			String informacaoSobreComoMorreu = m.group(7);
			
			assertEquals(dataInicioPartida, "23/04/2013 15:36:33");
			assertEquals(separador, "-");
			assertEquals(jogadorAssasino, "<WORLD>");
			assertEquals(informacaoMatou, "killed");
			assertEquals(jogadorMorto, "Nick");
			assertEquals(informacaoAcao, "by");
			assertEquals(informacaoSobreComoMorreu, "DROWN");
			
		}else{
			fail();
		}
	}
	
	@Test
	public void encontraDadosDoFimDaPartida(){

		Pattern p = TipoLinha.FIM_PARTIDA.getPattern();
		Matcher m = p.matcher("23/04/2013 15:39:22 - Match 11348965 has ended");
		if(m.find()) {
			String dataFimPartida = m.group(1);
			String separador = m.group(2);
			String informacaoPartida = m.group(3);
			String idPartida = m.group(4);
			String informacaoPartidaTerminada = m.group(5);
			
			assertEquals(dataFimPartida, "23/04/2013 15:39:22");
			assertEquals(separador, "-");
			assertEquals(informacaoPartida, "Match");
			assertEquals(idPartida, "11348965");
			assertEquals(informacaoPartidaTerminada, "has ended");
			
		}else{
			fail();
		}
		
		m = p.matcher("12/01/2014 11:19:23 - Match 91348969 has ended");
		if(m.find()) {
			String dataFimPartida = m.group(1);
			String separador = m.group(2);
			String informacaoPartida = m.group(3);
			String idPartida = m.group(4);
			String informacaoPartidaTerminada = m.group(5);
			
			assertEquals(dataFimPartida, "12/01/2014 11:19:23");
			assertEquals(separador, "-");
			assertEquals(informacaoPartida, "Match");
			assertEquals(idPartida, "91348969");
			assertEquals(informacaoPartidaTerminada, "has ended");
			
		}else{
			fail();
		}
	}
	
}
