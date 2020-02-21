package Estudi;

public class DesviacioEstandardPoblacionalPerRestaurant {
	
	private int idRestaurant;
	
	private double desvEstPob;
	
	public DesviacioEstandardPoblacionalPerRestaurant() {}
	
	public DesviacioEstandardPoblacionalPerRestaurant(int idRestaurant, double desviacio) {
		this.idRestaurant=idRestaurant;
		this.desvEstPob=desviacio;
	}

	public int getIdRestaurant() {
		return idRestaurant;
	}

	public void setIdRestaurant(int idRestaurant) {
		this.idRestaurant = idRestaurant;
	}

	public double getDesvEstPob() {
		return desvEstPob;
	}

	public void setDesvEstPob(double desvEstPob) {
		this.desvEstPob = desvEstPob;
	}

	@Override
	public String toString() {
		return "DesviacioEstandardPoblacionalPerRestaurant [idRestaurant=" + idRestaurant + ", desvEstPob=" + desvEstPob
				+ "]";
	}
	
	

}
