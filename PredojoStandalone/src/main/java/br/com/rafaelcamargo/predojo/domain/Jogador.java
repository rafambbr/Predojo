package br.com.rafaelcamargo.predojo.domain;

import br.com.rafaelcamargo.predojo.common.CommonDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(of={"nome"}, callSuper=false)
public @Data class Jogador implements CommonDomain{

	private static final long serialVersionUID = 8680144369685545214L;
	private String nome;
	
	public Jogador(){
		//No-op
	}
	
	public Jogador(String nome){
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return this.nome;
	}
}
