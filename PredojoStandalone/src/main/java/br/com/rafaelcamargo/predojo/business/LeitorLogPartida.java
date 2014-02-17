package br.com.rafaelcamargo.predojo.business;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;

import lombok.extern.slf4j.Slf4j;
import br.com.rafaelcamargo.predojo.business.parser.LogParser;
import br.com.rafaelcamargo.predojo.business.parser.Parser;
import br.com.rafaelcamargo.predojo.common.CommonDomain;
import br.com.rafaelcamargo.predojo.domain.Assassinato;
import br.com.rafaelcamargo.predojo.domain.Partida;
import br.com.rafaelcamargo.predojo.domain.TipoLinha;
import br.com.rafaelcamargo.predojo.exception.BusinessException;

@Slf4j
public class LeitorLogPartida extends LeitorLog<Partida>{

	private final File arquivo;
	private final static Charset ENCODING = StandardCharsets.UTF_8; 

	public LeitorLogPartida(File arquivo){
		this.arquivo = arquivo;
	}
	
	public final Set<Partida> processaLog() throws IOException {
		
		final Set<Partida> partidas = new HashSet<Partida>();
		Scanner scanner = null;	
		Partida partidaAtual = null;
		
		try{
			
			scanner = new Scanner(this.arquivo, ENCODING.name());
			while (scanner.hasNextLine()) {
				partidaAtual = processaLinha(scanner.nextLine(), partidaAtual);
				partidas.add(partidaAtual);
			}
			
		}catch(Exception e){
			String erroMsg = "Nao foi possivel procesar as linhas do arquivo.";
			log.error(erroMsg, e);
			throw new BusinessException(erroMsg);
		}finally{
			scanner.close();
		}
		
		return partidas;
	}

	@SuppressWarnings("rawtypes")
	private Partida processaLinha(String linha, Partida partidaAtual) {
		
		TipoLinha tipoLinha = getTipoDaLinha(linha);
		Parser parser = LogParser.getParser(tipoLinha);
		
		CommonDomain retorno = parser.parse(linha, partidaAtual);
		if(retorno != null){
			if(tipoLinha == TipoLinha.INICIO_PARTIDA || tipoLinha == TipoLinha.FIM_PARTIDA){
				partidaAtual = (Partida)retorno;
			}else if(tipoLinha == TipoLinha.JOGADOR_MATA_JOGADOR && partidaAtual != null){
				Assassinato assasinato = (Assassinato)retorno;
				partidaAtual.getAssassinatos().add(assasinato);
			}
		}

		return partidaAtual;
	}

	private TipoLinha getTipoDaLinha(String linha) {
		for (TipoLinha tipoLinha : TipoLinha.values()) {
			Matcher matcher = tipoLinha.getPattern().matcher(linha);
			if(matcher.matches()){
				return tipoLinha;
			}
		}
		
		return null;
	}
}