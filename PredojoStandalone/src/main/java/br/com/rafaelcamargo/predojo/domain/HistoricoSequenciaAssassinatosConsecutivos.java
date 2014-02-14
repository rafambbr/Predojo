package br.com.rafaelcamargo.predojo.domain;

import lombok.Data;

public @Data class HistoricoSequenciaAssassinatosConsecutivos {

	private Jogador assassino;
	private Integer numeroAssassinatosConsecutivos;
	
	public HistoricoSequenciaAssassinatosConsecutivos(){
		//No-op
	}
	
	public HistoricoSequenciaAssassinatosConsecutivos(Jogador assassino, Integer numeroAssassinatosConsecutivos){
		this.assassino = assassino;
		this.numeroAssassinatosConsecutivos = numeroAssassinatosConsecutivos;
	}
}
