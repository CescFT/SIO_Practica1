package Estudi;

public class RelUsrRest {
	
	private int idUsuari;
	private int idRestaurant;
	
	public RelUsrRest() {}
	
	public RelUsrRest(int idUsuari, int idRestaurant) {
		this.idUsuari=idUsuari;
		this.idRestaurant=idRestaurant;
	}

	public int getIdUsuari() {
		return idUsuari;
	}

	public void setIdUsuari(int idUsuari) {
		this.idUsuari = idUsuari;
	}

	public int getIdRestaurant() {
		return idRestaurant;
	}

	public void setIdRestaurant(int idRestaurant) {
		this.idRestaurant = idRestaurant;
	}

	@Override
	public String toString() {
		return "RelUsrRest [idUsuari=" + idUsuari + ", idRestaurant=" + idRestaurant + "]";
	}

	
	
}
