package br.com.rafaelcamargo.predojo.business;

import java.util.Collection;

import br.com.rafaelcamargo.predojo.domain.Arma;
import br.com.rafaelcamargo.predojo.domain.HistoricoSequenciaAssassinatosConsecutivos;
import br.com.rafaelcamargo.predojo.domain.Jogador;

public class ImprimeEstatisticaPartida {

	public void imprimir(Collection<EstatisticaPartida> estatisticasPartidas){
		if(estatisticasPartidas != null){
			for (EstatisticaPartida estatisticaPartida : estatisticasPartidas) {
				imprimir(estatisticaPartida);
			}
		}
	}
	
	public void imprimir(EstatisticaPartida estatisticaPartida){
		StringBuffer sb = new StringBuffer("\n\n\n\n\n=========================== PARTIDA:")
			.append(estatisticaPartida.getIdPartida()).append("==========================");
		System.out.println( sb.toString() );
		
		imprimiVencedoresComArmasFavoritas(estatisticaPartida);
		imprimirListaDeJogadoresComQuantidadeDeAssassinatosEMortes(estatisticaPartida);
		imprimirJogadorComMaiorNumeroDeMortesConsecutivasSemMorrer(estatisticaPartida);
		
		System.out.println("======================================================================\n\n");
	}
	
	private void imprimiVencedoresComArmasFavoritas(EstatisticaPartida estatisticaPartida) {
		System.out.println("Vencedores: ");
		for (Jogador vencedor : estatisticaPartida.getVencedores()) {
			Arma armaFavorita = estatisticaPartida.getArmaFavoritaVencedor(vencedor);
			
			StringBuffer sb = new StringBuffer("Jogador:").append(vencedor.getNome())
					.append(" - Arma Favorita:").append(armaFavorita.getNome());
			
			System.out.println(sb.toString());
		}
	}
	
	private void imprimirListaDeJogadoresComQuantidadeDeAssassinatosEMortes(EstatisticaPartida estatisticaPartida) {
		System.out.println("======================================================================");
		System.out.println("Ranking: ");
		for (Jogador jogador : estatisticaPartida.getJogadoresDaPartida()) {
			Integer quantidadeAssassinatos = estatisticaPartida.getQuantidadeAssassinatos(jogador);
			Integer quantidadeMortes = estatisticaPartida.getQuantidadeMortes(jogador);
			Integer quantidadePremios = estatisticaPartida.getQuantidadePremios(jogador);
			
			StringBuffer sb = new StringBuffer("Assassinatos:").append(quantidadeAssassinatos)
						.append(" - Mortes:").append(quantidadeMortes)
						.append(" - Premios:").append(quantidadePremios)
						.append(" - Jogador:").append(jogador.getNome());
			
			System.out.println(sb.toString());
		}
	}
	
	private void imprimirJogadorComMaiorNumeroDeMortesConsecutivasSemMorrer(EstatisticaPartida estatisticaPartida) {
		HistoricoSequenciaAssassinatosConsecutivos historico = estatisticaPartida.getJogadorComMaiorNumeroDeMortesConsecutivas();
		System.out.println("======================================================================");
		System.out.println("Streak: ");
		
		StringBuffer sb = new StringBuffer("Jogador:").append( historico.getAssassino().getNome() )
				.append(" - Numero de Assassinatos Consecutivos:").append( historico.getNumeroAssassinatosConsecutivos() );
		
		System.out.println(sb.toString());
	}
	
}
