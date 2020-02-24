package Estudi;

public class MitjRestaurant {
	
	private int idRestaurant;
	
	private double mitjana;
	
	public MitjRestaurant() {}
	
	public MitjRestaurant(int idRestaurant, double mitjana) {
		this.idRestaurant=idRestaurant;
		this.mitjana=mitjana;
	}

	public int getIdRestaurant() {
		return idRestaurant;
	}

	public void setIdRestaurant(int idRestaurant) {
		this.idRestaurant = idRestaurant;
	}

	public double getMitjana() {
		return mitjana;
	}

	public void setMitjana(double mitjana) {
		this.mitjana = mitjana;
	}

	@Override
	public String toString() {
		return "MitjRestaurant [idRestaurant=" + idRestaurant + ", mitjana=" + mitjana + "]";
	}
	
	
	

}
