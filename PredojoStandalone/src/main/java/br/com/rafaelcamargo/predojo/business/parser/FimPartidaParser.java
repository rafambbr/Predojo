package br.com.rafaelcamargo.predojo.business.parser;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.extern.slf4j.Slf4j;
import br.com.rafaelcamargo.predojo.common.LogPartidaCommon;
import br.com.rafaelcamargo.predojo.domain.Partida;
import br.com.rafaelcamargo.predojo.domain.TipoLinha;

@Slf4j
public class FimPartidaParser extends Parser<Partida> implements LogPartidaCommon{
	
	@Override
	public Partida parse(String linha, Partida partidaAtual){
		try{
			Pattern p = TipoLinha.FIM_PARTIDA.getPattern();
			Matcher m = p.matcher(linha);
			if(m.find()) {
				Date dataFimPartida = dateAdapter.getData( m.group(1) );
				//String separador = m.group(2);
				//String informacaoPartida = m.group(3);
				//Long idPartida = Long.parseLong( m.group(4) );
				//String informacaoPartidaTerminada = m.group(5);
				
				partidaAtual.setDataFim(dataFimPartida);
			}
		}catch(Exception e){
			log.warn(LINHA_INVALIDA);
		}
		
		return partidaAtual;
	}

}
