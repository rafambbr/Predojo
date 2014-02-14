package br.com.rafaelcamargo.predojo.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import br.com.rafaelcamargo.predojo.common.CommonDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(of={"idPartida"}, callSuper=false)
public @Data class Partida implements CommonDomain{

	private static final long serialVersionUID = 2015982329794446470L;
	private Long idPartida;
	private Date dataInicio;
	private Date dataFim;
	private Set<Assassinato> assassinatos = new HashSet<Assassinato>();
	
	public Partida(Long idPartida, Date dataInicio){
		this.idPartida = idPartida;
		this.dataInicio = dataInicio;
	}
	
	public Partida(Long idPartida){
		this.idPartida = idPartida;
	}

	public StatusPartida getStatusPartida(){
		StatusPartida statusPartida = null;
		if( dataInicio !=null && dataFim != null){
			statusPartida = StatusPartida.FINALIZADA;
		}else if(dataInicio != null){
			statusPartida = StatusPartida.INICIADA;
		}else{
			statusPartida = StatusPartida.NAO_INICIOU;
		}
		
		return statusPartida;
	}
}
