package br.com.rafaelcamargo.predojo.common;

import br.com.rafaelcamargo.predojo.util.DateAdapter;

public interface LogPartidaCommon {
	
	public final String LINHA_INVALIDA = "Linha inv�lida";
	public final DateAdapter dateAdapter = new DateAdapter( DateAdapter.DDMMYYYY_HHMMSS );
	
}
