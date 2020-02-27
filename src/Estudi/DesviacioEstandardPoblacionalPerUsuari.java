package Estudi;

/**
 * Classe que emmagatzema la desviació estandard poblacional per usuari
 * @author Cesc
 *
 */
public class DesviacioEstandardPoblacionalPerUsuari {
	
	private int idUsuari;
	
	private double desvestp;
	
	/**
	 * constructor
	 */
	public DesviacioEstandardPoblacionalPerUsuari() {}
	
	/**
	 * constructor
	 * @param idUsuari id usuari
	 * @param desviacio desviacio
	 */
	public DesviacioEstandardPoblacionalPerUsuari(int idUsuari, double desviacio) {
		this.idUsuari=idUsuari;
		this.desvestp = desviacio;
	}

	/**
	 * getter
	 * @return id usuari
	 */
	public int getIdUsuari() {
		return idUsuari;
	}

	/**
	 * setter
	 * @param idUsuari id usuari
	 */
	public void setIdUsuari(int idUsuari) {
		this.idUsuari = idUsuari;
	}

	/**
	 * getter desviacio estandard poblacional
	 * @return desviacio 
	 */
	public double getDesvestp() {
		return desvestp;
	}

	/**
	 * setter desviacio estandard poblacional
	 * @param desvestp desviacio
	 */
	public void setDesvestp(double desvestp) {
		this.desvestp = desvestp;
	}

	@Override
	public String toString() {
		return "DesviacioEstandardPoblacionalPerUsuari [idUsuari=" + idUsuari + ", desvestp=" + desvestp + "]";
	}

	
}
