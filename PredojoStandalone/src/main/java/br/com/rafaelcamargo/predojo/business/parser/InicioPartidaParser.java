package br.com.rafaelcamargo.predojo.business.parser;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.extern.slf4j.Slf4j;
import br.com.rafaelcamargo.predojo.common.LogPartidaCommon;
import br.com.rafaelcamargo.predojo.domain.Partida;
import br.com.rafaelcamargo.predojo.domain.TipoLinha;

@Slf4j
public class InicioPartidaParser extends Parser<Partida> implements LogPartidaCommon{

	@Override
	public Partida parse(String linha, Partida partidaAtual) {
		
		Partida partida = null;
		
		try{
			Pattern p = TipoLinha.INICIO_PARTIDA.getPattern();
			Matcher m = p.matcher(linha);
			if(m.find()) {
				Date dataInicioPartida = dateUtil.getData( m.group(1) ); 
				//String separador = m.group(2);
				//String informacaoNovaPartida = m.group(3);
				Long idPartida = Long.parseLong( m.group(4) );
				//String informacaoPartidaIniciada = m.group(5);
				
				partida = new Partida(idPartida, dataInicioPartida);
			}
		}catch(Exception e){
			log.warn(LINHA_INVALIDA);
		}
		
		return partida;
	}

}
