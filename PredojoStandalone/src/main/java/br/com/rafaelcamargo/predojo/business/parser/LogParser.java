package br.com.rafaelcamargo.predojo.business.parser;

import java.util.HashMap;
import java.util.Map;

import br.com.rafaelcamargo.predojo.business.Parser;
import br.com.rafaelcamargo.predojo.domain.TipoLinha;

/**
 * Flyweight
 *
 */
@SuppressWarnings("rawtypes")
public class LogParser {
	
	public static Map<TipoLinha, Parser> parsers = new HashMap<TipoLinha, Parser>();
	
	static{
		parsers.put(TipoLinha.INICIO_PARTIDA, new InicioPartidaParser());
		parsers.put(TipoLinha.JOGADOR_MATA_JOGADOR, new JogadorMataJogadorParser());
		parsers.put(TipoLinha.MUNDO_MATA_JOGADOR, new MundoMataJogadorParser());
		parsers.put(TipoLinha.FIM_PARTIDA, new FimPartidaParser());
	}
	
	public static synchronized Parser getParser(TipoLinha tipoLinha){
		return parsers.get(tipoLinha);
	}
}
