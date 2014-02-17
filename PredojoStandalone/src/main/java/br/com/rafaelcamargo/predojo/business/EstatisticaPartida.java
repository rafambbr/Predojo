package br.com.rafaelcamargo.predojo.business;

import java.util.Set;

import br.com.rafaelcamargo.predojo.domain.Arma;
import br.com.rafaelcamargo.predojo.domain.HistoricoSequenciaAssassinatosConsecutivos;
import br.com.rafaelcamargo.predojo.domain.Jogador;

public interface EstatisticaPartida {

	public HistoricoSequenciaAssassinatosConsecutivos getJogadorComMaiorNumeroDeMortesConsecutivas();
	public int getQuantidadeAssassinatos(Jogador jogador);
	public int getQuantidadeMortes(Jogador jogador);
	public int getQuantidadePremios(Jogador jogador);
	public Arma getArmaFavoritaVencedor(Jogador jogador);
	public Set<Jogador> getVencedores();
	public Long getIdPartida();
	public Set<Jogador> getJogadoresDaPartida();
	
}
