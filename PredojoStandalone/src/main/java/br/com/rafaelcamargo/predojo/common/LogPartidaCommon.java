package br.com.rafaelcamargo.predojo.common;

import br.com.rafaelcamargo.predojo.util.DateUtil;

public interface LogPartidaCommon {
	
	public final String LINHA_INVALIDA = "Linha inválida";
	public final DateUtil dateAdapter = new DateUtil( DateUtil.DDMMYYYY_HHMMSS );
	
}
