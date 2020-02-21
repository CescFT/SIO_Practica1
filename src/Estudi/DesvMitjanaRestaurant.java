package Estudi;

public class DesvMitjanaRestaurant {
	
	private int idRestaurant;
	
	private double desvMitjana;
	
	public DesvMitjanaRestaurant() {}
	
	public DesvMitjanaRestaurant(int idRestaurant, double desvMitjana) {
		this.idRestaurant = idRestaurant;
		
		this.desvMitjana = desvMitjana;
	}

	public int getIdRestaurant() {
		return idRestaurant;
	}

	public void setIdRestaurant(int idRestaurant) {
		this.idRestaurant = idRestaurant;
	}

	public double getDesvMitjana() {
		return desvMitjana;
	}

	public void setDesvMitjana(double desvMitjana) {
		this.desvMitjana = desvMitjana;
	}

	@Override
	public String toString() {
		return "DesvMitjanaRestaurant [idRestaurant=" + idRestaurant + ", desvMitjana=" + desvMitjana + "]";
	}
	
	

}
