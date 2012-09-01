package service.statictic.templates;

public interface RaportTemplateBuilder {

	void createHeader(Object ...args);
	
	void appendBody(Object ...args);
	
	void createFoot(Object ...args);
	
	String build();
}
