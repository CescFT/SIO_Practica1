package model;

public class Restaurant {
	
	private int id;
	private float puntuacio;
	
	public Restaurant() {}
	
	public Restaurant(int id, float puntuacio) {
		this.id=id;
		this.puntuacio=puntuacio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public float getPuntuacio() {
		return puntuacio;
	}

	public void setPuntuacio(float puntuacio) {
		this.puntuacio = puntuacio;
	}

	@Override
	public String toString() {
		return "Restaurant [id=" + id + ", puntuacio=" + puntuacio + "]";
	}
	
	
	

}
