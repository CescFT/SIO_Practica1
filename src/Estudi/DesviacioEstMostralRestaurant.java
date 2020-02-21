package Estudi;

public class DesviacioEstMostralRestaurant {
	
	private int idRestaurant;
	
	private double desviacioEstandardMostral;
	
	public DesviacioEstMostralRestaurant() {}
	
	public DesviacioEstMostralRestaurant(int idRestaurant, double desviacioEstandardMostral) {
		this.idRestaurant=idRestaurant;
		this.desviacioEstandardMostral=desviacioEstandardMostral;
	}

	public int getIdRestaurant() {
		return idRestaurant;
	}

	public void setIdRestaurant(int idRestaurant) {
		this.idRestaurant = idRestaurant;
	}

	public double getDesviacioEstandardMostral() {
		return desviacioEstandardMostral;
	}

	public void setDesviacioEstandardMostral(double desviacioEstandardMostral) {
		this.desviacioEstandardMostral = desviacioEstandardMostral;
	}

	@Override
	public String toString() {
		return "DesviacioEstMostralRestaurant [idRestaurant=" + idRestaurant + ", desviacioEstandardMostral="
				+ desviacioEstandardMostral + "]";
	}
	
	
	

}
