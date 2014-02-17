package br.com.rafaelcamargo.predojo.business.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.Seconds;

import lombok.EqualsAndHashCode;
import br.com.rafaelcamargo.predojo.business.EstatisticaPartida;
import br.com.rafaelcamargo.predojo.business.comparator.AssassinatoComparator;
import br.com.rafaelcamargo.predojo.domain.Arma;
import br.com.rafaelcamargo.predojo.domain.Assassinato;
import br.com.rafaelcamargo.predojo.domain.HistoricoSequenciaAssassinatosConsecutivos;
import br.com.rafaelcamargo.predojo.domain.Jogador;
import br.com.rafaelcamargo.predojo.domain.Partida;

@EqualsAndHashCode(of={"partida"}, callSuper=false)
public class EstatisticaPartidaMemoryImpl implements EstatisticaPartida{
	
	private Partida partida;

	private Map<Jogador, Arma> vencedoresComArmaFavorita = new HashMap<Jogador, Arma>();
	private Set<Jogador> jogadoresDaPartida = new HashSet<Jogador>();
	
	private Map<Jogador, Integer> jogadoresDaPartidaQueNaoMorreram = new HashMap<Jogador, Integer>();

	private Map<Jogador, Integer> quantidadeMortesJogador = new HashMap<Jogador, Integer>();
	private Map<Jogador, Integer> quantidadeAssassinatosJogador = new HashMap<Jogador, Integer>();
	
	private Map<Jogador, Integer> quantidadePremiosJogador = new HashMap<Jogador, Integer>();
	private Map<Jogador, List<Assassinato>> historicoAssassinatosJogador = new HashMap<Jogador, List<Assassinato>>();
	
	private Map<Jogador, Integer> sequenciaAssassinatos = new HashMap<Jogador, Integer>();
	private List<HistoricoSequenciaAssassinatosConsecutivos> historicoSequenciaAssassinato = new ArrayList<HistoricoSequenciaAssassinatosConsecutivos>();
	
	public EstatisticaPartidaMemoryImpl(Partida partida){
		this.partida = partida;
		adicionarAssassinatoAPartida(partida.getAssassinatos());
		analisarPartida();
	}
	
	private void analisarPartida(){
		verificaJogadorQueNaoMorreuNaPartida();
		verificaOGanhadorComMelhorDesempenho();
		daPremioAosJogadoresQueNaoMorreramNaPartida();
	}

	private void adicionarAssassinatoAPartida(Collection<Assassinato> assassinatos){
		for (Assassinato assassinato : assassinatos) {
			adicionarAssassinatoAPartida(assassinato);
		}
	}
	
	private void adicionarAssassinatoAPartida(Assassinato assassinato){
		if( dadosValidos(assassinato) ){
			adicionarJogadoresDaPartida(assassinato);
			adicionaAssassinatoAoJogador(assassinato);
			adicionaMorteAoJogador(assassinato);
			
			adicionaHistoricoDeAssassinatoPorJogador(assassinato);
			verificaSeMatouMaisDeCincoJogadoresEmUmMinuto( assassinato );
			atualizaJogadorComMaiorNumeroDeAssassinatosSemMorrer( assassinato );
		}
	}

	private boolean dadosValidos(Assassinato assassinato) {
		Jogador assassino = assassinato.getAssassino();
		Jogador morto = assassinato.getMorto();
		Arma armaAssassino = assassinato.getArmaAssassino();
		Date dataAssassinato = assassinato.getData();

		return !(assassino == null || morto == null || armaAssassino == null || dataAssassinato  == null || 
				"<WORLD>".equals(assassino.getNome()) || "DROWN".equals(armaAssassino.getNome()) );
	}

	private void daPremioAosJogadoresQueNaoMorreramNaPartida() {
		Set<Jogador> jogadoresQueNaoMorreram = this.jogadoresDaPartidaQueNaoMorreram.keySet();
		for (Jogador jogador : jogadoresQueNaoMorreram) {
			adicionarPremioAoJogador(jogador);
		}
	}

	private void adicionarJogadoresDaPartida(Assassinato assassinato) {
		this.jogadoresDaPartida.add(assassinato.getAssassino());
		this.jogadoresDaPartida.add(assassinato.getMorto());
	}

	private void adicionaAssassinatoAoJogador(Assassinato assassinato){
		Jogador assassino = assassinato.getAssassino();
		Integer quantidadeDeAssassinatos = 0;		
		if(this.quantidadeAssassinatosJogador.containsKey(assassino)){
			quantidadeDeAssassinatos = this.quantidadeAssassinatosJogador.get(assassino);
		}
		
		this.quantidadeAssassinatosJogador.put(assassino, ++quantidadeDeAssassinatos);
	}
	
	private void adicionaMorteAoJogador(Assassinato assassinato){
		Jogador morto = assassinato.getMorto();
		Integer quantidadeDeMortes = 0;	
		if(this.quantidadeMortesJogador.containsKey(morto)){
			quantidadeDeMortes = this.quantidadeMortesJogador.get(morto);
		}
		
		this.quantidadeMortesJogador.put(morto, ++quantidadeDeMortes);
	}
	
	private void adicionaHistoricoDeAssassinatoPorJogador(Assassinato assassinato) {
		Jogador assassino = assassinato.getAssassino();
		if( !this.historicoAssassinatosJogador.containsKey(assassino) ){
			this.historicoAssassinatosJogador.put(assassino, new ArrayList<Assassinato>());
		}
		this.historicoAssassinatosJogador.get(assassino).add(assassinato);
	}
	
	private void verificaSeMatouMaisDeCincoJogadoresEmUmMinuto(Assassinato assassinato) {
		
		Jogador assassino = assassinato.getAssassino();
		List<Assassinato> assassinatosJogador = this.historicoAssassinatosJogador.get(assassino);
		if(assassinatosJogador.size() >= 5){
			
			Collections.sort(assassinatosJogador, new AssassinatoComparator());
			
			int index = assassinatosJogador.size() - 5;
			Date dataHistorico = assassinatosJogador.get(index).getData();
			Date dataAtual = assassinato.getData();

			DateTime dataInicial = new DateTime(dataHistorico);
			DateTime dataFinal = new DateTime(dataAtual);
			
			BigDecimal segundos = new BigDecimal(Seconds.secondsBetween(dataInicial, dataFinal).getSeconds());
			if(segundos.intValue() <= 60){
				adicionarPremioAoJogador(assassino);
			}
		}
	}

	private void adicionarPremioAoJogador(Jogador assasino) {
		Integer quantidadeDeMedalhas = 0;		
		if(this.quantidadePremiosJogador.containsKey(assasino)){
			quantidadeDeMedalhas = this.quantidadePremiosJogador.get(assasino);
		}
		
		this.quantidadePremiosJogador.put(assasino, ++quantidadeDeMedalhas);
	}

	private void verificaJogadorQueNaoMorreuNaPartida() {
		for (Jogador jogador : this.jogadoresDaPartida) {
			if(!this.quantidadeMortesJogador.containsKey(jogador)){
				Integer quantidadeDeAssassinatos = this.quantidadeAssassinatosJogador.get(jogador);
				quantidadeDeAssassinatos = (quantidadeDeAssassinatos != null) ? quantidadeDeAssassinatos : 0;
				this.jogadoresDaPartidaQueNaoMorreram.put(jogador, quantidadeDeAssassinatos);
			}
		}
	}
	
	private void verificaOGanhadorComMelhorDesempenho(){
		Map<Jogador, Integer> jogadoresEQuantidadeDeAssassinatos = null;
		if(this.jogadoresDaPartidaQueNaoMorreram.size() > 0 ){
			//O vencedor será o jogador com maior número de assassinatos e que não morreu
			jogadoresEQuantidadeDeAssassinatos = this.jogadoresDaPartidaQueNaoMorreram;
		}else{
			//Caso contrario sera levado em conta o jogador com menor número de mortes que matou mais pessoas
			jogadoresEQuantidadeDeAssassinatos = this.quantidadeAssassinatosJogador;
		}
		
		Set<Jogador> vencedores = new HashSet<Jogador>();
		Integer quantidadeMaximaDeAssassinatosPartida = 0;
		for (Jogador jogador : jogadoresEQuantidadeDeAssassinatos.keySet()) {
			Integer quantidadeAssassinatosJogador = jogadoresEQuantidadeDeAssassinatos.get(jogador);
			if(quantidadeMaximaDeAssassinatosPartida < quantidadeAssassinatosJogador){
				quantidadeMaximaDeAssassinatosPartida = quantidadeAssassinatosJogador;
				vencedores.clear();
				vencedores.add(jogador);
			}else if(quantidadeMaximaDeAssassinatosPartida == quantidadeAssassinatosJogador){
				vencedores.add(jogador);
			}
		}
		
		verificaArmaPreferiaDoGanhador(vencedores);
	}
	
	private void verificaArmaPreferiaDoGanhador(Set<Jogador> vencedores){
		for(Jogador vencedor : vencedores){
			
			Map<Arma, Integer> quantidadeDeUsoDeCadaArma = new HashMap<Arma, Integer>();
			for (Assassinato assassinato : this.historicoAssassinatosJogador.get(vencedor)) {
				Arma armaAssassino = assassinato.getArmaAssassino();
				Integer quantidadeDeMortesPelaArma = 0;
				if(quantidadeDeUsoDeCadaArma.containsKey(armaAssassino)){
					quantidadeDeMortesPelaArma = quantidadeDeUsoDeCadaArma.get(armaAssassino);
				}
				quantidadeDeUsoDeCadaArma.put(armaAssassino, ++quantidadeDeMortesPelaArma);
			}
			
			int quantidadeMaximaDeMortes = 0;
			Arma armaFavorita = null;
			for(Arma arma : quantidadeDeUsoDeCadaArma.keySet()){
				Integer quantidadeMortesPelaArma = quantidadeDeUsoDeCadaArma.get(arma);
				if(quantidadeMaximaDeMortes < quantidadeMortesPelaArma){
					quantidadeMaximaDeMortes = quantidadeMortesPelaArma;
					armaFavorita = arma;
				}
			}
			
			this.vencedoresComArmaFavorita.put(vencedor, armaFavorita);
		}
	}
	
	private void atualizaJogadorComMaiorNumeroDeAssassinatosSemMorrer(Assassinato assassinato){
		
		Jogador assassino = assassinato.getAssassino();
		Integer numeroAssassinatosConsecutivos = 0;
		if( this.sequenciaAssassinatos.containsKey(assassino) ){
			numeroAssassinatosConsecutivos = this.sequenciaAssassinatos.get(assassino);
		}
		this.sequenciaAssassinatos.put(assassino, ++numeroAssassinatosConsecutivos);
		
		Jogador morto = assassinato.getMorto();
		if( this.sequenciaAssassinatos.containsKey(morto) ){
			numeroAssassinatosConsecutivos = this.sequenciaAssassinatos.get(morto);
			HistoricoSequenciaAssassinatosConsecutivos historico = new HistoricoSequenciaAssassinatosConsecutivos(morto, numeroAssassinatosConsecutivos);
			this.historicoSequenciaAssassinato.add(historico);
			
			this.sequenciaAssassinatos.put(morto, 0);
		}		
	}
	
	@Override
	public HistoricoSequenciaAssassinatosConsecutivos getJogadorComMaiorNumeroDeMortesConsecutivas(){
		
		Integer sequenciaMaximaPartida = 0;
		Jogador jogadorComMaiorSequencia = null;
		
		for (Jogador jogador : this.sequenciaAssassinatos.keySet()) {
			Integer sequenciaMaximaJogador = this.sequenciaAssassinatos.get(jogador);
			if(sequenciaMaximaPartida < sequenciaMaximaJogador){
				sequenciaMaximaPartida = sequenciaMaximaJogador;
				jogadorComMaiorSequencia = jogador;
			}
		}
		
		for (HistoricoSequenciaAssassinatosConsecutivos historicoSequenciaAssassinatosConsecutivos : this.historicoSequenciaAssassinato) {
			Integer numeroAssassinatosConsecutivos = historicoSequenciaAssassinatosConsecutivos.getNumeroAssassinatosConsecutivos();
			if(sequenciaMaximaPartida < numeroAssassinatosConsecutivos){
				sequenciaMaximaPartida = numeroAssassinatosConsecutivos;
				jogadorComMaiorSequencia = historicoSequenciaAssassinatosConsecutivos.getAssassino();
			}
		}
		
		return new HistoricoSequenciaAssassinatosConsecutivos(jogadorComMaiorSequencia, sequenciaMaximaPartida);
	}
	
	@Override
	public int getQuantidadeAssassinatos(Jogador jogador){
		return nullToInt( this.quantidadeAssassinatosJogador.get(jogador) );
	}
	
	@Override
	public int getQuantidadeMortes(Jogador jogador){
		return nullToInt( this.quantidadeMortesJogador.get(jogador) );
	}
	
	@Override
	public int getQuantidadePremios(Jogador jogador){
		return nullToInt( this.quantidadePremiosJogador.get(jogador) );
	}
	
	@Override
	public Arma getArmaFavoritaVencedor(Jogador jogador){
		return this.vencedoresComArmaFavorita.get(jogador);
	}
	
	@Override
	public Set<Jogador> getVencedores(){
		 return this.vencedoresComArmaFavorita.keySet();
	}
	
	@Override
	public Long getIdPartida(){
		return this.partida.getIdPartida();
	}
	
	@Override
	public Set<Jogador> getJogadoresDaPartida(){
		return this.jogadoresDaPartida;
	}
	
	private int nullToInt(Integer numero){
		return numero != null ? numero : 0;
	}
}
