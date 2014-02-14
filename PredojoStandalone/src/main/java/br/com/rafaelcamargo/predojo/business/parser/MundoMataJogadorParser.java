package br.com.rafaelcamargo.predojo.business.parser;

import br.com.rafaelcamargo.predojo.business.Parser;
import br.com.rafaelcamargo.predojo.domain.Assassinato;
import br.com.rafaelcamargo.predojo.domain.Partida;

public class MundoMataJogadorParser implements Parser<Assassinato> {

	@Override
	public Assassinato parse(String linha, Partida partidaAtual) {
		//Deve ser desconsiderado
		return null;
	}

}
