package br.com.rafaelcamargo.predojo.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAdapter {
	
	public static final String DDMMYYYY_HHMMSS = "dd/MM/yyyy HH:mm:ss";
	private SimpleDateFormat simpleDateFormat;
	
	public DateAdapter(String pattern){
		this.simpleDateFormat = new SimpleDateFormat(pattern);
	}

	public int getDiferencaEmSegundos(Date dataInicio, Date dataFim){
		BigDecimal milisegundosInicio = new BigDecimal( dataInicio.getTime() );
		BigDecimal milisegundosFim = new BigDecimal( dataFim.getTime() );
		
		BigDecimal diferenca = milisegundosInicio.subtract(milisegundosFim);
		BigDecimal divisor = new BigDecimal(1000);
		
		BigDecimal segundos = diferenca.divide( divisor ); 
		
		return segundos.intValue();
	}
	
	public Date getData(String data) throws ParseException{
		return this.simpleDateFormat.parse(data);
	}
}
