package Estudi;

/**
 * Classe que emmagatzema la informacio per l'histograma
 * @author Cesc
 *
 */
public class Histograma {
	
	private String interval;
	private int numVegades;
	
	/**
	 * constructor
	 */
	public Histograma() {}
	
	/**
	 * constructor
	 * @param interval interval
	 * @param numVegades vegades
	 */
	public Histograma(String interval, int numVegades) {
		this.interval=interval;
		this.numVegades = numVegades;
	}

	/**
	 * getter
	 * @return interval
	 */
	public String getInterval() {
		return interval;
	}

	/**
	 * setter
	 * @param interval interval
	 */
	public void setInterval(String interval) {
		this.interval = interval;
	}

	/**
	 * getter
	 * @return vegades
	 */
	public int getNumVegades() {
		return numVegades;
	}

	/**
	 * setter
	 * @param numVegades vegades
	 */
	public void setNumVegades(int numVegades) {
		this.numVegades = numVegades;
	}

	@Override
	public String toString() {
		return "Histograma [interval=" + interval + ", numVegades=" + numVegades + "]";
	}

	

	
	
}
