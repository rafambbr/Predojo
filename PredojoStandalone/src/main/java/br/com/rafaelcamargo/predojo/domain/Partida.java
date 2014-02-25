package br.com.rafaelcamargo.predojo.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(of={"idPartida"}, callSuper=false)
public @Data class Partida implements Serializable{

	private static final long serialVersionUID = 2015982329794446470L;
	private Long idPartida;
	private Date dataInicio;
	private Date dataFim;
	private Set<Assassinato> assassinatos = new LinkedHashSet<Assassinato>();
	
	public Partida(Long idPartida, Date dataInicio){
		this.idPartida = idPartida;
		this.dataInicio = dataInicio;
	}
	
	public Partida(Long idPartida){
		this.idPartida = idPartida;
	}

	public Partida(Long idPartida, Date dataInicio, Date dataFim) {
		this.idPartida = idPartida;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
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
	
	@Override
	public String toString() {
		return "ID: " + this.idPartida + " INICIO:" + dataInicio + " FIM:" + dataFim;
	}
}
