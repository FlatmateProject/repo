package service.statictic;

import java.util.List;

import service.statictic.templates.RaportTemplateBuilder;


public class StatisticRaport {

	private double array[][];
	
	private String resultText;
	
	public StatisticRaport(double[][] array, String resultText) {
		this.array = array;
		this.resultText = resultText;
	}
	
	public StatisticRaport(List<DiagramElement> elements, RaportTemplateBuilder templateBuilder) {
		this.array = createResultArray(elements);
		this.resultText = templateBuilder.build();	
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
		return resultText;
	}
}
