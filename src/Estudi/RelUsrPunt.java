package Estudi;

public class RelUsrPunt implements Comparable<RelUsrPunt>{
	
	private int idUsuari;
	private float puntuacio;
	
	public RelUsrPunt() {}
	
	public RelUsrPunt(int idUsuari, float puntuacio) {
		this.idUsuari = idUsuari;
		this.puntuacio = puntuacio;
	}

	public int getIdUsuari() {
		return idUsuari;
	}

	public void setIdUsuari(int idUsuari) {
		this.idUsuari = idUsuari;
	}

	public float getPuntuacio() {
		return puntuacio;
	}

	public void setPuntuacio(float puntuacio) {
		this.puntuacio = puntuacio;
	}

	@Override
	public String toString() {
		return "RelUsrPunt [idUsuari=" + idUsuari + ", puntuacio=" + puntuacio + "]";
	}
	

	@Override
	public int compareTo(RelUsrPunt o) {
		if (this.puntuacio > o.getPuntuacio())
			return 1;
		else if (this.puntuacio<o.getPuntuacio())
			return -1;
		else return 0;
	}

}
