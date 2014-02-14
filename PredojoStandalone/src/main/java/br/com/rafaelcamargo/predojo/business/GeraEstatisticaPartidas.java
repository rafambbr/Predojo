package br.com.rafaelcamargo.predojo.business;

import java.util.Collection;
import java.util.HashSet;

import br.com.rafaelcamargo.predojo.domain.Assassinato;
import br.com.rafaelcamargo.predojo.domain.EstatisticaPartida;
import br.com.rafaelcamargo.predojo.domain.Partida;

public class GeraEstatisticaPartidas {

	public Collection<EstatisticaPartida> gerarEstatisticas(Collection<Partida> partidas){
		Collection<EstatisticaPartida> estatisticaPartidas = new HashSet<EstatisticaPartida>();
		for (Partida partida : partidas) {
			EstatisticaPartida estatisticaPartida = new EstatisticaPartida(partida);
			for (Assassinato assassinato : partida.getAssassinatos()) {
				estatisticaPartida.adicionarAssassinatoAPartida(assassinato);
			}
			
			estatisticaPartida.finalizaPartida();
			estatisticaPartidas.add(estatisticaPartida);
		}
		
		return estatisticaPartidas;
	}
}
