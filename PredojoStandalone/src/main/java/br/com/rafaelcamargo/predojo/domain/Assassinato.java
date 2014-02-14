package br.com.rafaelcamargo.predojo.domain;

import java.util.Date;

import br.com.rafaelcamargo.predojo.common.CommonDomain;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(of={"data", "assassino", "morto"}, callSuper=false)
public @Data class Assassinato implements CommonDomain{

	private static final long serialVersionUID = 7920164672591673600L;
	private Long idAssasinato;
	private Date data;
	private Jogador assassino;
	private Jogador morto;
	private Arma armaAssassino;
	
	public Assassinato(){
		//No-op
	}
	
	public Assassinato(Date data, Jogador assassino, Jogador morto, Arma armaAssassino){
		this.data = data;
		this.assassino = assassino;
		this.morto = morto;
		this.armaAssassino = armaAssassino;
	}
	
}
