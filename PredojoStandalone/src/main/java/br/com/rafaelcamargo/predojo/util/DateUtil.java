package br.com.rafaelcamargo.predojo.util;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public static final String DDMMYYYY_HHMMSS = "dd/MM/yyyy HH:mm:ss";
	private SimpleDateFormat simpleDateFormat;
	
	public DateUtil(String pattern){
		this.simpleDateFormat = new SimpleDateFormat(pattern);
	}

	public Long getDiferencaEmSegundos(Date dataInicio, Date dataFim){
		BigDecimal milisegundosInicio = new BigDecimal( dataInicio.getTime() );
		BigDecimal milisegundosFim = new BigDecimal( dataFim.getTime() );
		
		BigDecimal diferenca = milisegundosFim.subtract(milisegundosInicio);
		BigDecimal divisor = new BigDecimal(1000);
		
		BigDecimal segundos = diferenca.divide( divisor ); 
		
		return segundos.longValue();
	}
	
	public Date getData(String data) throws ParseException{
		return this.simpleDateFormat.parse(data);
	}
}
