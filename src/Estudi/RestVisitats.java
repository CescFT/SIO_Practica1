package Estudi;

public class RestVisitats implements Comparable<RestVisitats>{
	private int idUsuari;
	private int numRestaurants;
	
	public RestVisitats() {}
	
	public RestVisitats(int idUsuari, int numRestaurants) {
		this.idUsuari=idUsuari;
		this.numRestaurants=numRestaurants;
	}

	public int getIdUsuari() {
		return idUsuari;
	}

	public void setIdUsuari(int idUsuari) {
		this.idUsuari = idUsuari;
	}

	public int getNumRestaurants() {
		return numRestaurants;
	}

	public void setNumRestaurants(int numRestaurants) {
		this.numRestaurants = numRestaurants;
	}

	@Override
	public String toString() {
		return "RestVisitats [idUsuari=" + idUsuari + ", numRestaurants=" + numRestaurants + "]";
	}

	@Override
	public int compareTo(RestVisitats o) {
		if(this.numRestaurants > o.getNumRestaurants())
			return 1;
		else if (this.numRestaurants < o.getNumRestaurants())
			return -1;
		else return 0;
	}
	
	
	

}
