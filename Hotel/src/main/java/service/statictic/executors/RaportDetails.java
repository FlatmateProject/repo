package service.statictic.executors;

import service.dictionary.MONTH;

public class RaportDetails {

	private MONTH month; 
	
	private int year; 
	
	private String roomType; 
	
	private String serviceTypeName;
	
	
	public RaportDetails(MONTH month, int year, String classRoom, String serviceTypeName) {
		this.month = month;
		this.year = year;
		this.roomType = classRoom;
		this.serviceTypeName = serviceTypeName;
	}


	public MONTH getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}

	public String getRoomType() {
		return roomType;
	}

	public String getServiceTypeName() {
		return serviceTypeName;
	}
}
