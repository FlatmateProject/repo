package service.statictic.executors;

import service.dictionary.MONTH;

public class RaportDetails {

	private MONTH month; 
	
	private int year; 
	
	private String classRoom; 
	
	private String serve;
	
	
	public RaportDetails(MONTH month, int year, String classRoom, String serve) {
		this.month = month;
		this.year = year;
		this.classRoom = classRoom;
		this.serve = serve;
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

	public String getServe() {
		return serve;
	}
}
