package br.com.rafaelcamargo.predojo.business;

import java.util.Collection;
import java.util.HashSet;

import lombok.extern.slf4j.Slf4j;
import br.com.rafaelcamargo.predojo.domain.Partida;
import br.com.rafaelcamargo.predojo.exception.BusinessException;

@Slf4j
public class PartidaParser {

	public Collection<EstatisticaPartida> parse(Collection<Partida> partidas){
		Collection<EstatisticaPartida> estatisticaPartidas = new HashSet<EstatisticaPartida>();
		try{
			
			for (Partida partida : partidas) {
				EstatisticaPartida estatisticaPartida = parse(partida);
				estatisticaPartidas.add(estatisticaPartida);
			}
			
		}catch(Exception e){
			String erroMsg = "Nao foi possível gerar as estatistícas.";
			log.error(erroMsg, e);
			throw new BusinessException(erroMsg);
		}
		
		return estatisticaPartidas;
	}
	
	public EstatisticaPartida parse(Partida partida){
		EstatisticaPartida estatisticaPartida = null;
		try{
			
			estatisticaPartida = new EstatisticaPartida(partida);
			
		}catch(Exception e){
			String erroMsg = "Nao foi possível gerar a estatística.";
			log.error(erroMsg, e);
			throw new BusinessException(erroMsg);
		}
		
		return estatisticaPartida;
	}
	
}
