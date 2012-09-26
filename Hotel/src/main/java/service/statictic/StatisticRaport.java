package service.statictic;

import java.util.List;

import service.statictic.templates.RaportTemplateBuilder;


public class StatisticRaport {

	private double array[][];
	
	private String textResult;
	
	private RAPORT_KIND raportKind;
	
	public StatisticRaport(RAPORT_KIND raportKind, double[][] array, String resultText) {
		this.array = array;
		this.textResult = resultText;
		this.raportKind = raportKind;
	}
	
	public StatisticRaport(RAPORT_KIND raportKind, List<DiagramElement> elements, RaportTemplateBuilder templateBuilder) {
		this.array = createResultArray(elements);
		this.textResult = templateBuilder.build();
		this.raportKind = raportKind;
	}

	private double[][] createResultArray(List<DiagramElement> elements) {
		if(elements.size() == 0){
			return null;
		}
		double[][] array = new double[elements.size()][];
		int i = 0;
		for (DiagramElement point : elements) {
			array[i] = point.getBars();
			i++;
		}
		return array;
	}
	
	public double[][] getArrayResult() {
		return array;
	}

	public String getTextResult() {
		return textResult;
	}

	public RAPORT_KIND getRaportKind() {
		return raportKind;
	}
}
