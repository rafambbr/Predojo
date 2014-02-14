package br.com.rafaelcamargo.predojo.business.parser;

import java.text.SimpleDateFormat;

import br.com.rafaelcamargo.predojo.common.CommonDomain;
import br.com.rafaelcamargo.predojo.domain.Partida;


public abstract class Parser<R extends CommonDomain> {
	
	public final String LINHA_INVALIDA = "Linha inválida";
	public final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	
	public abstract R parse(String linha, Partida partidaAtual);
	
}
