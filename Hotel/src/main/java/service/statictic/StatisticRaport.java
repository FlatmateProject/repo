package service.statictic;

import java.util.List;


public class StatisticRaport {

	private double array[][];
	
	private String resultText;
	
	public StatisticRaport(double[][] array, String resultText) {
		this.array = array;
		this.resultText = resultText;
	}
	
	public StatisticRaport(List<PlotPoint> points, String resultText) {
		this.array = createResultArray(points);
		this.resultText = resultText;	// TODO Auto-generated constructor stub
	}

	private double[][] createResultArray(List<PlotPoint> points) {
		double[][] array = new double[points.size()][2];
		int i = 0;
		for (PlotPoint point : points) {
			array[i][0] = point.getX();
			array[i][1] = point.getY();
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
