package br.com.rafaelcamargo.predojo.domain;

import br.com.rafaelcamargo.predojo.common.CommonDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(of={"nome"}, callSuper=false)
public @Data class Arma implements CommonDomain{

	private static final long serialVersionUID = 6732868267302893147L;
	private String nome;
	
	public Arma(){
		//No-op
	}
	
	public Arma(String nome){
		this.nome = nome;
	}
	
}
