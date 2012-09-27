package service.statictic.templates;

public interface RaportTemplateBuilder {

	String DEFAULT_FOOT   = "W danym miesiącu nie wprowadzano danych z zakresu.\n";
	
	void createHeader(Object ...args);
	
	void appendBodyBlock(Object ...args);
	
	void createFoot(Object ...args);
	
	String build();
}
