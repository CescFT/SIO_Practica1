package Estudi;

public class Histograma {
	
	private String interval;
	private int numVegades;
	
	public Histograma() {}
	
	public Histograma(String interval, int numVegades) {
		this.interval=interval;
		this.numVegades = numVegades;
	}

	public String getInterval() {
		return interval;
	}

	public void setInterval(String interval) {
		this.interval = interval;
	}

	public int getNumVegades() {
		return numVegades;
	}

	public void setNumVegades(int numVegades) {
		this.numVegades = numVegades;
	}

	@Override
	public String toString() {
		return "Histograma [interval=" + interval + ", numVegades=" + numVegades + "]";
	}

	

	
	
}
