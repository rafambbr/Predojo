package br.com.rafaelcamargo.predojo.business.parser;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.extern.slf4j.Slf4j;
import br.com.rafaelcamargo.predojo.business.Parser;
import br.com.rafaelcamargo.predojo.domain.Partida;
import br.com.rafaelcamargo.predojo.domain.TipoLinha;

@Slf4j
public class InicioPartidaParser implements Parser<Partida> {

	private final SimpleDateFormat simpleDateFormat;
	
	public InicioPartidaParser(){
		this.simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}
	
	@Override
	public Partida parse(String linha, Partida partidaAtual) {
		
		Partida partida = null;
		
		try{
			Pattern p = Pattern.compile(TipoLinha.INICIO_PARTIDA.getRegex(), Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(linha);
			if(m.find()) {
				Date dataInicioPartida = this.simpleDateFormat.parse( m.group(1) );
				//String separador = m.group(2);
				//String informacaoNovaPartida = m.group(3);
				Long idPartida = Long.parseLong( m.group(4) );
				//String informacaoPartidaIniciada = m.group(5);
				
				partida = new Partida(idPartida, dataInicioPartida);
			}
		}catch(Exception e){
			String erroMsg = "Linha inválida";
			System.out.println(erroMsg);
			log.warn(erroMsg, e);
		}
		
		return partida;
	}

}
