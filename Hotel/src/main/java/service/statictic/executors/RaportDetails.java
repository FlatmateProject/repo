package service.statictic.executors;

import service.dictionary.MONTH;

public class RaportDetails {

	private MONTH month; 
	
	private int year; 
	
	private String classRoom; 
	
	private String serveTypeName;
	
	
	public RaportDetails(MONTH month, int year, String classRoom, String serveTypeName) {
		this.month = month;
		this.year = year;
		this.classRoom = classRoom;
		this.serveTypeName = serveTypeName;
	}


	public MONTH getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}

	public String getClassRoom() {
		return classRoom;
	}

	public String getServeTypeName() {
		return serveTypeName;
	}
}
