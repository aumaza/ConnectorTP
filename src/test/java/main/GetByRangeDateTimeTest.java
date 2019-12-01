package main;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Test;

import java.util.Date;
import java.util.Map;
import ar.com.todopago.api.TodoPagoConector;
import ar.com.todopago.api.model.GBRDTParameters;
import mocks.GetByRangeDateTimeMockParameters;
import mocks.TodoPagoParametersMock;

public class GetByRangeDateTimeTest {

	private String merchant=TodoPagoParametersMock.MERCHANT;
	private String pageNumber=GetByRangeDateTimeMockParameters.PAGENUMBER;
	private Date date1=GetByRangeDateTimeMockParameters.STARTDATEWITHHOUR;//2017-03-03T16:15:00
	private Date date2=GetByRangeDateTimeMockParameters.ENDDATEWITHHOUR;//2017-08-07T22:34:00
	private Map<String,String> paramsGetByRangeDateTime=GetByRangeDateTimeMockParameters.getBRYParameters("2016-01-01","2017-03-03");
	private Map<String,Object> getByRangeDateTimeOKMocked=GetByRangeDateTimeMockParameters.getBRYOKResponse();
	private Map<String,Object> getByRangeDateTimeModifiedOKMocked=GetByRangeDateTimeMockParameters.getBRYModifiedOKResponse();
	
	@Test
	public void GetByRangeDateTimeOkTest(){
		TodoPagoConector tpc = mock(TodoPagoConector.class);
	
		when(tpc.getByRangeDateTime(paramsGetByRangeDateTime)).thenReturn(getByRangeDateTimeOKMocked);
			
		assertEquals(true,tpc.getByRangeDateTime(paramsGetByRangeDateTime).containsKey("OperationsCollections"));
		
		@SuppressWarnings("unchecked")
		Map<String,Object> oc=(Map<String,Object>)tpc.getByRangeDateTime(paramsGetByRangeDateTime).get("OperationsCollections");
		
		assertEquals(true,oc.containsKey("RESULTCODE"));
		assertEquals(true,oc.containsKey("RESULTMESSAGE"));		
		assertEquals(-1,oc.get("RESULTCODE"));
		assertEquals("APROBADA",oc.get("RESULTMESSAGE"));
	}
	
	@Test
	public void GetByRangeDateTimeOModifiedOkTest(){
		TodoPagoConector tpc = mock(TodoPagoConector.class);
				
		GBRDTParameters params=new GBRDTParameters(this.merchant,this.pageNumber,this.date1,this.date2);

		when(tpc.getByRangeDateTime(params)).thenReturn(getByRangeDateTimeModifiedOKMocked);
		
		assertEquals(true,tpc.getByRangeDateTime(params).containsKey("OperationsCollections"));
		
		@SuppressWarnings("unchecked")
		Map<String,Object> oc=(Map<String,Object>)tpc.getByRangeDateTime(params).get("OperationsCollections");

		assertEquals(true,oc.containsKey("RESULTCODE"));
		assertEquals(true,oc.containsKey("RESULTMESSAGE"));		
		assertEquals(-1,oc.get("RESULTCODE"));
		assertEquals("APROBADA",oc.get("RESULTMESSAGE"));
	}
}