package br.com.rafaelcamargo.predojo.application;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;
import br.com.rafaelcamargo.predojo.business.AnalisadorDeLogPartida;
import br.com.rafaelcamargo.predojo.business.GeraEstatisticaPartidas;
import br.com.rafaelcamargo.predojo.business.ImprimeEstatisticaPartida;
import br.com.rafaelcamargo.predojo.domain.EstatisticaPartida;
import br.com.rafaelcamargo.predojo.domain.Partida;

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
		} catch (IOException e) {
			log.error(e.getMessage());
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
			AnalisadorDeLogPartida analisadorDeLogPartida = new AnalisadorDeLogPartida(logFile);
			Set<Partida> partidasAnalisadas = analisadorDeLogPartida.getPartidas();
			
			GeraEstatisticaPartidas geraEstatisticaPartidas = new GeraEstatisticaPartidas();
			Collection<EstatisticaPartida> estatisticasPartidas = geraEstatisticaPartidas.gerarEstatisticas(partidasAnalisadas);
			
			ImprimeEstatisticaPartida imprimeEstatisticaPartida = new ImprimeEstatisticaPartida();
			imprimeEstatisticaPartida.imprimir(estatisticasPartidas);
		}else{
			System.out.println("Arquivo inválido: " + caminhoArquivo); 
		}
	}
}
