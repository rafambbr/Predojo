package br.com.rafaelcamargo.predojo.business.comparator;

import java.util.Comparator;

import br.com.rafaelcamargo.predojo.domain.Assassinato;

public class AssassinatoComparator implements Comparator<Assassinato>{

	@Override
	public int compare(Assassinato ass1, Assassinato ass2) {
		int resultado = 0;
		long time = ass1.getData().getTime();
		long time2 = ass2.getData().getTime();
		if(time != time2 ){
			resultado = (time < time2) ? -1 : 1; 
		}
		return resultado;
	}

}
