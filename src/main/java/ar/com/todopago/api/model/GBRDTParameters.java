package ar.com.todopago.api.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GBRDTParameters {
	private String merchant;
	private String pageNumber;
	private String format;
	private Date startDate;
	private Date endDate;
	
	public GBRDTParameters(String merchant,String pageNumber,Date startDate,Date endDate){
		this.merchant=merchant;
		this.pageNumber=pageNumber;
		this.startDate=startDate;
		this.endDate=endDate;
		this.format="yyyy-MM-dd'T'HH:mm:ss";
	}
	
	public void setMerchant(String merchant) {
		this.merchant = merchant;
	}

	public void setPageNumber(String pageNumber) {
		this.pageNumber = pageNumber;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getMerchant() {
		return merchant;
	}
	
	public String getPageNumber() {
		return pageNumber;
	}

	public String getStartDate() {
		return this.formatDate(this.startDate);
	}
	
	public String getEndDate() {
		return this.formatDate(this.endDate);
	}
	
	//Auxiliary methods
	private String formatDate(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat(this.format);
		
		return sdf.format(date);
	}
}