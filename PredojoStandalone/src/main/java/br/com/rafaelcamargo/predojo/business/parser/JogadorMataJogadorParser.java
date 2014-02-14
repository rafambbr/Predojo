package br.com.rafaelcamargo.predojo.business.parser;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.rafaelcamargo.predojo.business.Parser;
import br.com.rafaelcamargo.predojo.domain.Arma;
import br.com.rafaelcamargo.predojo.domain.Assassinato;
import br.com.rafaelcamargo.predojo.domain.Jogador;
import br.com.rafaelcamargo.predojo.domain.Partida;
import br.com.rafaelcamargo.predojo.domain.TipoLinha;

public class JogadorMataJogadorParser implements Parser<Assassinato> {

	private final SimpleDateFormat simpleDateFormat;
	
	public JogadorMataJogadorParser(){
		this.simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}
	
	@Override
	public Assassinato parse(String linha, Partida partidaAtual) {
		
		Assassinato assasinato = null;
		try{
			Pattern p = Pattern.compile(TipoLinha.JOGADOR_MATA_JOGADOR.getRegex(), Pattern.CASE_INSENSITIVE);
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
			String erroMsg = "Linha inválida";
			System.out.println(erroMsg);
		}
		
		return assasinato;
	}

}
