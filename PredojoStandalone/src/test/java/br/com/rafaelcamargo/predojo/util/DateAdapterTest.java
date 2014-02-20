package br.com.rafaelcamargo.predojo.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

public class DateAdapterTest {

	private static DateAdapter dateAdapter;
	
	@BeforeClass
	public static void setUp(){
		dateAdapter = new DateAdapter( DateAdapter.DDMMYYYY_HHMMSS );
	}
	
	@Test
	public void calculaDiferencaEmSegundosEntreDuasDatas(){
		try {
		
			Date dataInicio01 = dateAdapter.getData("14/02/2014 15:10:02");
			Date dataFim01 = dateAdapter.getData("14/02/2014 15:10:08");
			
			Long segundos01 = dateAdapter.getDiferencaEmSegundos(dataInicio01, dataFim01);
			assertEquals(6L, segundos01, 0);
			
			
			Date dataInicio02 = dateAdapter.getData("14/02/2014 15:10:00");
			Date dataFim02 = dateAdapter.getData("14/02/2014 15:11:00");
			
			Long segundos02 = dateAdapter.getDiferencaEmSegundos(dataInicio02, dataFim02);
			assertEquals(60, segundos02, 0);
			
		} catch (ParseException e) {
			fail( e.getMessage() );
		}
	}
}
