package br.com.rafaelcamargo.predojo.business;

import java.util.Collection;
import java.util.HashSet;

import lombok.extern.slf4j.Slf4j;
import br.com.rafaelcamargo.predojo.domain.Assassinato;
import br.com.rafaelcamargo.predojo.domain.EstatisticaPartida;
import br.com.rafaelcamargo.predojo.domain.Partida;
import br.com.rafaelcamargo.predojo.exception.BusinessException;

@Slf4j
public class GeraEstatisticaPartidas {

	public Collection<EstatisticaPartida> gerarEstatisticas(Collection<Partida> partidas){
		Collection<EstatisticaPartida> estatisticaPartidas = new HashSet<EstatisticaPartida>();
		
		try{
			for (Partida partida : partidas) {
				EstatisticaPartida estatisticaPartida = new EstatisticaPartida(partida);
				for (Assassinato assassinato : partida.getAssassinatos()) {
					estatisticaPartida.adicionarAssassinatoAPartida(assassinato);
				}
				
				estatisticaPartida.finalizaPartida();
				estatisticaPartidas.add(estatisticaPartida);
			}
		}catch(Exception e){
			String erroMsg = "Nao foi possivel gerar as estatisticas.";
			log.error(erroMsg, e);
			throw new BusinessException(erroMsg);
		}
		
		return estatisticaPartidas;
	}
}
