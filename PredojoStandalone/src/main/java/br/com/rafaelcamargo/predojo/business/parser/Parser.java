package br.com.rafaelcamargo.predojo.business.parser;

import br.com.rafaelcamargo.predojo.common.CommonDomain;
import br.com.rafaelcamargo.predojo.domain.Partida;


public abstract class Parser<R extends CommonDomain> {
	public abstract R parse(String linha, Partida partidaAtual);
}
