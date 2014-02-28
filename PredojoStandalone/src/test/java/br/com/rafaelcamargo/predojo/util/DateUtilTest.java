package br.com.rafaelcamargo.predojo.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Test;

public class DateUtilTest {

	private static DateUtil dateUtil;
	
	@BeforeClass
	public static void setUp(){
		dateUtil = new DateUtil( DateUtil.DDMMYYYY_HHMMSS );
	}
	
	@Test
	public void calculaDiferencaEmSegundosEntreDuasDatas(){
		try {
		
			Date dataInicio01 = dateUtil.getData("14/02/2014 15:10:02");
			Date dataFim01 = dateUtil.getData("14/02/2014 15:10:08");
			
			Long segundos01 = dateUtil.getDiferencaEmSegundos(dataInicio01, dataFim01);
			assertEquals(6L, segundos01, 0);
			
			
			Date dataInicio02 = dateUtil.getData("14/02/2014 15:10:00");
			Date dataFim02 = dateUtil.getData("14/02/2014 15:11:00");
			
			Long segundos02 = dateUtil.getDiferencaEmSegundos(dataInicio02, dataFim02);
			assertEquals(60, segundos02, 0);
			
		} catch (ParseException e) {
			fail( e.getMessage() );
		}
	}
}
