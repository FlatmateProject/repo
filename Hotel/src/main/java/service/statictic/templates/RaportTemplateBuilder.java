package service.statictic.templates;

public interface RaportTemplateBuilder {

	void createHeader(Object ...args);
	
	void appendBodyBlock(Object ...args);
	
	void createFoot(Object ...args);
	
	String build();
}
