package main;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import ar.com.todopago.api.model.GBRDTParameters;
import mocks.GetByRangeDateTimeMockParameters;
import mocks.TodoPagoParametersMock;

public class GBRDTParametersTest {
	private String merchant=TodoPagoParametersMock.MERCHANT;
	private String pageNumber=GetByRangeDateTimeMockParameters.PAGENUMBER;
	private Date date1=GetByRangeDateTimeMockParameters.STARTDATEWITHHOUR;//2017-03-03T16:15:00
	private Date date2=GetByRangeDateTimeMockParameters.ENDDATEWITHHOUR;//2017-08-07T22:34:00
	
	@Test
	public void getDateOKTest() {
		GBRDTParameters params=new GBRDTParameters(this.merchant,this.pageNumber,this.date1,this.date2);
		
		assertEquals("2017-03-03T16:15:00",params.getStartDate());
		assertEquals("2017-08-07T22:34:00",params.getEndDate());
	}
	
	@Test
	public void getDateErrorTest() {
		GBRDTParameters params=new GBRDTParameters(this.merchant,this.pageNumber,this.date1,this.date2);
		
		assertEquals("2017-03-03T16:15:00",params.getStartDate());
		assertNotEquals("2016-08-07T22:34:00",params.getEndDate());
	}
}