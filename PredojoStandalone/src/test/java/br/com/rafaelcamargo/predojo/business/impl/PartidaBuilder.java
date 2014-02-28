package br.com.rafaelcamargo.predojo.business.impl;

import java.text.ParseException;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import br.com.rafaelcamargo.predojo.domain.Arma;
import br.com.rafaelcamargo.predojo.domain.Assassinato;
import br.com.rafaelcamargo.predojo.domain.Jogador;
import br.com.rafaelcamargo.predojo.domain.Partida;
import br.com.rafaelcamargo.predojo.util.DateUtil;

/**
 * Builder
 * @author rafael
 *
 */
public class PartidaBuilder {

	private Partida partida;
	private final Set<Assassinato> assassinatos = new LinkedHashSet<Assassinato>();
	public final DateUtil dateUtil = new DateUtil( DateUtil.DDMMYYYY_HHMMSS );
	
	public PartidaBuilder addInicioPartida(Long idPartida, Date dataInicio){
		this.partida = new Partida(idPartida, dataInicio);
		return this;
	}
	
	public PartidaBuilder addInicioPartida(Long idPartida, String dataInicio) throws ParseException{
		Date dataInicioPartida = this.dateUtil.getData(dataInicio);		
		this.partida = new Partida(idPartida, dataInicioPartida);
		return this;
	}
	
	public PartidaBuilder addAssassinato(String dataAssassinato, Jogador assassino, Jogador morto, Arma armaAssassino) throws ParseException{
		Date data = this.dateUtil.getData(dataAssassinato);
		return addAssassinato(data, assassino, morto, armaAssassino);
	}
	
	public PartidaBuilder addAssassinato(Date dataAssassinato, Jogador assassino, Jogador morto, Arma armaAssassino){
		Assassinato assassinato = new Assassinato(dataAssassinato, assassino, morto, armaAssassino);
		this.assassinatos.add(assassinato);
		return this;
	}
	
	public PartidaBuilder addDataFimPartida(String dataFim) throws ParseException{
		Date data = this.dateUtil.getData(dataFim);
		return addDataFimPartida(data);
	}
	
	public PartidaBuilder addDataFimPartida(Date dataFim){
		this.partida.setDataFim(dataFim);
		return this;
	}
	
	public Partida build(){

		if(this.partida.getDataFim() == null || this.assassinatos.size() <= 0){
			throw new RuntimeException("Você não acabou de criar a partida");
		}
		
		Partida partida = this.partida;
		for (Assassinato assassinato : this.assassinatos) {
			partida.getAssassinatos().add(assassinato);
		}
		
		this.partida = null;
		this.assassinatos.clear();
		
		return partida;
	}
	
	
}
