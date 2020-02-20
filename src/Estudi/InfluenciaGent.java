package Estudi;

public class InfluenciaGent implements Comparable<InfluenciaGent>{
	
	private int idRestaurant;
	
	private int numPuntuacions;
	
	public InfluenciaGent() {}
	
	public InfluenciaGent(int idRestaurant, int numPuntuacions) {
		this.idRestaurant=idRestaurant;
		this.numPuntuacions=numPuntuacions;
	}

	public int getIdRestaurant() {
		return idRestaurant;
	}

	public void setIdRestaurant(int idRestaurant) {
		this.idRestaurant = idRestaurant;
	}

	public int getNumPuntuacions() {
		return numPuntuacions;
	}

	public void setNumPuntuacions(int numPuntuacions) {
		this.numPuntuacions = numPuntuacions;
	}

	@Override
	public String toString() {
		return "InfluenciaGent [idRestaurant=" + idRestaurant + ", numPuntuacions=" + numPuntuacions + "]";
	}

	@Override
	public int compareTo(InfluenciaGent o) {
		if(this.numPuntuacions>o.getNumPuntuacions())
			return 1;
		else if (this.numPuntuacions<o.getNumPuntuacions())
			return -1;
		else return 0;
	}

}
