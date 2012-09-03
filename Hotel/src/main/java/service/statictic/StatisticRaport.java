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
	
	public StatisticRaport(List<DiagramElement> points, RaportTemplateBuilder templateBuilder) {
		this.array = createResultArray(points);
		this.resultText = templateBuilder.build();	
	}

	private double[][] createResultArray(List<DiagramElement> points) {
		if(points.size() == 0){
			return null;
		}
		double[][] array = new double[points.size()][];
		int i = 0;
		for (DiagramElement point : points) {
			array[i] = point.getElemnets();
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
