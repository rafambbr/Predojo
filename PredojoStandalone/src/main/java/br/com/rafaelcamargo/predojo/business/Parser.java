package br.com.rafaelcamargo.predojo.business;

import br.com.rafaelcamargo.predojo.common.CommonDomain;
import br.com.rafaelcamargo.predojo.domain.Partida;


public interface Parser<R extends CommonDomain> {

	public R parse(String linha, Partida partidaAtual);

}
