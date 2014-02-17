package br.com.rafaelcamargo.predojo.application;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;
import br.com.rafaelcamargo.predojo.business.EstatisticaPartida;
import br.com.rafaelcamargo.predojo.business.ImprimeDados;
import br.com.rafaelcamargo.predojo.business.LeitorLog;
import br.com.rafaelcamargo.predojo.business.LogParser;
import br.com.rafaelcamargo.predojo.business.impl.ImprimeEstatisticaPartidaConsoleImpl;
import br.com.rafaelcamargo.predojo.business.impl.LeitorLogPartidaImpl;
import br.com.rafaelcamargo.predojo.business.impl.PartidaParserImpl;
import br.com.rafaelcamargo.predojo.domain.Partida;
import br.com.rafaelcamargo.predojo.exception.BusinessException;

@Slf4j
public class App {

	public static void main(String[] args) {
		try {
			
			imprimirMenuAjuda();
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
			
	        while (true) {  
	        	
	        	String comando = reader.readLine().replace("\\", "/");  
	        	if("ajuda".equals(comando)){
	        		imprimirMenuAjuda();
	        	}else if("sair".equals(comando)){
	        		break;
	        	}else{
	        		leArquivo(comando);
	        	}
	        } 
	        
	        System.out.println("Aplicação finalizada com sucesso");
		} catch(BusinessException e){
			String erroMsg = e.getMessage();
			log.error(erroMsg, e);
			System.out.println("Erro: " + erroMsg);
		} catch (Exception e) {
			String erroMsg = e.getMessage();
			log.error(erroMsg, e);
			System.out.println("Erro inesperado ao processar o arquivo");
		}
	}

	private static void imprimirMenuAjuda() {
		System.out.println("============================ MENU ===============================");
		System.out.println("= 'sair': sai do programa                                       =");
		System.out.println("= 'ajuda': Mostra menu com opcoes de comando                    =");
		System.out.println("= '/nome/arquivo.log': Le arquivo de log da partida do jogo     =");
		System.out.println("=================================================================");
	}

	public static void leArquivo(String caminhoArquivo) throws IOException{
		File logFile = new File(caminhoArquivo);
		if(logFile.isFile()){
			System.out.println("Lendo arquivo: " + caminhoArquivo);  
			LeitorLog<Partida> analisadorDeLogPartida = new LeitorLogPartidaImpl(logFile);
			Set<Partida> partidasAnalisadas = analisadorDeLogPartida.processaLog();
			
			LogParser<Partida, EstatisticaPartida> geraEstatisticaPartidas = new PartidaParserImpl();
			Collection<EstatisticaPartida> estatisticasPartidas = geraEstatisticaPartidas.parse(partidasAnalisadas);
			
			ImprimeDados<EstatisticaPartida> imprimeEstatisticaPartida = new ImprimeEstatisticaPartidaConsoleImpl();
			imprimeEstatisticaPartida.imprimir(estatisticasPartidas);
		}else{
			System.out.println("Arquivo inválido: " + caminhoArquivo); 
		}
	}
}
