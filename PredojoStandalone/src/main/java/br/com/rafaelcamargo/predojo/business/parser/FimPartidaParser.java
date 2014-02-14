package br.com.rafaelcamargo.predojo.business.parser;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.rafaelcamargo.predojo.business.Parser;
import br.com.rafaelcamargo.predojo.domain.Partida;
import br.com.rafaelcamargo.predojo.domain.TipoLinha;

public class FimPartidaParser implements Parser<Partida> {
	
	private final SimpleDateFormat simpleDateFormat;
	
	public FimPartidaParser(){
		this.simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}
	
	@Override
	public Partida parse(String linha, Partida partidaAtual){
		try{
			Pattern p = Pattern.compile(TipoLinha.FIM_PARTIDA.getRegex(), Pattern.CASE_INSENSITIVE);
			Matcher m = p.matcher(linha);
			if(m.find()) {
				Date dataFimPartida = this.simpleDateFormat.parse( m.group(1) );
				//String separador = m.group(2);
				//String informacaoPartida = m.group(3);
				//Long idPartida = Long.parseLong( m.group(4) );
				//String informacaoPartidaTerminada = m.group(5);
				
				partidaAtual.setDataFim(dataFimPartida);
			}
		}catch(Exception e){
			String erroMsg = "Linha inválida";
			System.out.println(erroMsg);
		}
		
		return partidaAtual;
	}

}
