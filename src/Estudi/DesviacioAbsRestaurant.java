package Estudi;

public class DesviacioAbsRestaurant {
	
	private int idRestaurant;
	
	private double desviacioAbsMitjana;
	
	public DesviacioAbsRestaurant() {}
	
	public DesviacioAbsRestaurant(int idRestaurant, double desviacioAbsMitjana) {
		this.idRestaurant=idRestaurant;
		this.desviacioAbsMitjana=desviacioAbsMitjana;
	}

	public int getIdRestaurant() {
		return idRestaurant;
	}

	public void setIdRestaurant(int idRestaurant) {
		this.idRestaurant = idRestaurant;
	}

	public double getDesviacioAbsMitjana() {
		return desviacioAbsMitjana;
	}

	public void setDesviacioAbsMitjana(double desviacioAbsMitjana) {
		this.desviacioAbsMitjana = desviacioAbsMitjana;
	}

	@Override
	public String toString() {
		return "DesviacioAbsRestaurant [idRestaurant=" + idRestaurant + ", desviacioAbsMitjana=" + desviacioAbsMitjana
				+ "]";
	}
	
	
	

}
