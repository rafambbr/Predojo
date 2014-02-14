package br.com.rafaelcamargo.predojo.business.parser;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.extern.slf4j.Slf4j;
import br.com.rafaelcamargo.predojo.domain.Arma;
import br.com.rafaelcamargo.predojo.domain.Assassinato;
import br.com.rafaelcamargo.predojo.domain.Jogador;
import br.com.rafaelcamargo.predojo.domain.Partida;
import br.com.rafaelcamargo.predojo.domain.TipoLinha;

@Slf4j
public class JogadorMataJogadorParser extends Parser<Assassinato> {
	
	@Override
	public Assassinato parse(String linha, Partida partidaAtual) {
		
		Assassinato assasinato = null;
		try{
			Pattern p = TipoLinha.JOGADOR_MATA_JOGADOR.getPattern();
			Matcher m = p.matcher(linha);
			if( m.find() ) {
				Date data = this.simpleDateFormat.parse( m.group(1) );
				//String separador = m.group(2);
				Jogador jogadorAssasino = new Jogador( m.group(3) );
				//String informacaoMatou = m.group(4);
				Jogador jogadorMorto = new Jogador( m.group(5) );
				//String informacaoAcao = m.group(6);
				Arma armaAssasino = new Arma( m.group(7) );
				
				assasinato = new Assassinato(data, jogadorAssasino, jogadorMorto, armaAssasino);
			}
		}catch(Exception e){
			log.warn(LINHA_INVALIDA);
		}
		
		return assasinato;
	}

}
