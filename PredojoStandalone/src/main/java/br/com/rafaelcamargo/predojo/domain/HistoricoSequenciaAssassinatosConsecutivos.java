package br.com.rafaelcamargo.predojo.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(of={"assassino"}, callSuper=false)
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
