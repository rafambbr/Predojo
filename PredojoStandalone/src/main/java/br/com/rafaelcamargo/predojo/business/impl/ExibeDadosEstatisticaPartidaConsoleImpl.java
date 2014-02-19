package br.com.rafaelcamargo.predojo.business.impl;

import java.util.Collection;

import br.com.rafaelcamargo.predojo.business.EstatisticaPartida;
import br.com.rafaelcamargo.predojo.business.ExibeDados;
import br.com.rafaelcamargo.predojo.domain.Arma;
import br.com.rafaelcamargo.predojo.domain.HistoricoSequenciaAssassinatosConsecutivos;
import br.com.rafaelcamargo.predojo.domain.Jogador;

public class ExibeDadosEstatisticaPartidaConsoleImpl implements ExibeDados<EstatisticaPartida> {

	@Override
	public void exibirDados(Collection<EstatisticaPartida> estatisticasPartidas){
		if(estatisticasPartidas != null){
			for (EstatisticaPartida estatisticaPartida : estatisticasPartidas) {
				exibirDados(estatisticaPartida);
			}
		}
	}
	
	public void exibirDados(EstatisticaPartida estatisticaPartida){
		imprimir("\n\n\n\n\n=========================== PARTIDA:");
		imprimir( estatisticaPartida.getIdPartida() );
		imprimir("==========================\n");
		
		exibeVencedoresComArmasFavoritas(estatisticaPartida);
		exibeListaDeJogadoresComQuantidadeDeAssassinatosEMortes(estatisticaPartida);
		exibeJogadorComMaiorNumeroDeMortesConsecutivasSemMorrer(estatisticaPartida);
		
		imprimir("======================================================================\n\n");
	}
	
	private void exibeVencedoresComArmasFavoritas(EstatisticaPartida estatisticaPartida) {
		imprimir("Vencedores: ");
		for (Jogador vencedor : estatisticaPartida.getVencedores()) {
			Arma armaFavorita = estatisticaPartida.getArmaFavoritaVencedor(vencedor);

			imprimir("Jogador:");
			imprimir(vencedor.getNome());
			imprimir(" - Arma Favorita:");
			imprimir(armaFavorita.getNome());
			imprimir("\n");
		}
	}
	
	private void exibeListaDeJogadoresComQuantidadeDeAssassinatosEMortes(EstatisticaPartida estatisticaPartida) {
		imprimir("======================================================================\n");
		imprimir("Ranking: \n");
		for (Jogador jogador : estatisticaPartida.getJogadoresDaPartida()) {
			Integer quantidadeAssassinatos = estatisticaPartida.getQuantidadeAssassinatos(jogador);
			Integer quantidadeMortes = estatisticaPartida.getQuantidadeMortes(jogador);
			Integer quantidadePremios = estatisticaPartida.getQuantidadePremios(jogador);
			
			imprimir("Assassinatos:").imprimir(quantidadeAssassinatos);
			imprimir(" - Mortes:").imprimir(quantidadeMortes);
			imprimir(" - Premios:").imprimir(quantidadePremios);
			imprimir(" - Jogador:").imprimir(jogador.getNome()).imprimir("\n");
		}
	}
	
	private void exibeJogadorComMaiorNumeroDeMortesConsecutivasSemMorrer(EstatisticaPartida estatisticaPartida) {
		HistoricoSequenciaAssassinatosConsecutivos historico = estatisticaPartida.getJogadorComMaiorNumeroDeMortesConsecutivas();
		if(historico != null){
			imprimir("======================================================================\n");
			imprimir("Streak: \n");

			Jogador assassino = historico.getAssassino();
			imprimir("Jogador:").imprimir( assassino.getNome() );
			
			Integer numeroAssassinatosConsecutivos = historico.getNumeroAssassinatosConsecutivos();
			imprimir(" - Numero de Assassinatos Consecutivos:").imprimir( numeroAssassinatosConsecutivos ).imprimir("\n");
		}
	}
	
	private ExibeDadosEstatisticaPartidaConsoleImpl imprimir(Object obj){
		if(obj != null){
			System.out.print(obj);
		}
		return this;
	}
}
