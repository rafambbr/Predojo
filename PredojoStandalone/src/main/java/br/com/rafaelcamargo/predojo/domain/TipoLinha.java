package br.com.rafaelcamargo.predojo.domain;

import java.util.regex.Pattern;

import lombok.Getter;

public enum TipoLinha {
	
	INICIO_PARTIDA("^([\\w/]+\\s[\\w:]+) (-) (New match) ([0-9]{5,20}) (has started)"),
	JOGADOR_MATA_JOGADOR("^([\\w/]+\\s[\\w:]+) (-) ([\\w]+) (killed) ([\\w]+) (using) ([\\w]+)"),
	MUNDO_MATA_JOGADOR("^([\\w/]+\\s[\\w:]+) (-) (<WORLD>) (killed) ([\\w]+) (by) ([\\w]+)"),
	FIM_PARTIDA("^([\\w/]+\\s[\\w:]+) (-) (Match) ([0-9]{5,20}) (has ended)");
	
//	@Getter private String grupoRegex;
	@Getter private Pattern pattern;
	
	private TipoLinha(String regex){
//		this.grupoRegex = regex;
		this.pattern = Pattern.compile( regex, Pattern.CASE_INSENSITIVE );
	}
}
