package br.com.rafaelcamargo.predojo.business.parser;

import java.io.Serializable;

import br.com.rafaelcamargo.predojo.domain.Partida;


public abstract class Parser<R extends Serializable> {
	public abstract R parse(String linha, Partida partidaAtual);
}
