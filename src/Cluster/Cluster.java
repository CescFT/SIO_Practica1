package Cluster;
import java.util.*;

public class Cluster {
	
	private List<Point> points;
	private List<Point> centroids;
	private int k;
	
	public Cluster(int k) {
		points = new ArrayList<Point>();
		centroids = new ArrayList<Point>();
		this.k = k;
	}

	public List<Point> getPoints() {
		return points;
	}

	public void setPoints(List<Point> points) {
		this.points = points;
	}

	public List<Point> getCentroids() {
		return centroids;
	}

	public void setCentroids(List<Point> centroids) {
		this.centroids = centroids;
	}

	public int getK() {
		return k;
	}

	public void setK(int k) {
		this.k = k;
	}

	public Map<Integer,List<Point>> calcularDist(Map<Integer, List<Double>> rawData){
		
		Map<Integer, List<Point>> distancies = new HashMap<Integer,List<Point>>();
		List<Point> aux = new ArrayList<Point>();
		for(int j=0; j<this.getK();j++) {
			Point centroide =this.getCentroids().get(j);
			for(int i=1; i<=5; i++) {
				List<Double> puntuacions = rawData.get(i);
				for (Double puntuacio:puntuacions) {
					Point pActual = new Point(i,puntuacio);
					double distancia=pActual.distance(centroide);
					aux.add(new Point(i,distancia));
				}
				distancies.put(j, aux);
				aux = new ArrayList<Point>();
			}
			
		}
		return distancies;
	}
	
	
	public boolean acabat(List<Point> centroidsAnteriors,Map<Integer,List<Point>> clusterAnt, Map<Integer,List<Point>> clusterAct) {
		
		for(int i=0; i<centroids.size(); i++) {
			if(centroids.get(i)!=centroidsAnteriors.get(i))
				return false;
		}
		
		for(Integer i : clusterAnt.keySet()) {
			List<Point> llAnt = clusterAnt.get(i);
			List<Point> llAct = clusterAct.get(i);
			for(int j = 0; j<llAnt.size(); j++) {
				if(llAnt.get(j).getScore()!=llAct.get(j).getScore())
					return false;
			}
		}
		
		return true;
	}
	
	public Map<Integer,List<Point>> cluster(Map<Integer, List<Point>> mapClustersIntermitjos, Map<Integer, List<Double>> rawData){
		List<Point> resultat = new ArrayList<Point>();
		List<Point> llRes1 = new ArrayList<Point>();
		List<Point> llRes2 = new ArrayList<Point>();
		List<Point> llRes3 = new ArrayList<Point>();
		
		Map<Integer, List<Point>> res = new HashMap<Integer, List<Point>>();
		List<Point> llista1 = mapClustersIntermitjos.get(0);
		List<Point> llista2 = mapClustersIntermitjos.get(1);
		List<Point> llista3 = mapClustersIntermitjos.get(2);
		
		for(int i = 0; i<llista1.size(); i++) {
			Point pLlista1 = llista1.get(i);
			Point pLlista2 = llista2.get(i);
			Point pLlista3 = llista3.get(i);
			
			if(pLlista1.getScore() == pLlista2.getScore() || pLlista1.getScore() == pLlista3.getScore()) {
				resultat.add(new Point(pLlista1.getID(), rawData.get(pLlista1.getID()).get(i),0));
			}else if (pLlista2.getScore() == pLlista1.getScore() || pLlista2.getScore() == pLlista3.getScore()) {
				resultat.add(new Point(pLlista2.getID(), rawData.get(pLlista2.getID()).get(i),1));
			}else if (pLlista3.getScore() == pLlista1.getScore() || pLlista3.getScore() == pLlista2.getScore()) {
				resultat.add(new Point(pLlista3.getID(), rawData.get(pLlista3.getID()).get(i),2));
			}else if(pLlista1.getScore() < pLlista2.getScore() && pLlista1.getScore()<pLlista3.getScore()) {
				resultat.add(new Point(pLlista1.getID(), rawData.get(pLlista1.getID()).get(i),0));
			}else if(pLlista2.getScore()<pLlista1.getScore() && pLlista2.getScore()<pLlista3.getScore()) {
				resultat.add(new Point(pLlista2.getID(), rawData.get(pLlista2.getID()).get(i),1));
			}else if (pLlista3.getScore()<pLlista1.getScore() && pLlista3.getScore()<pLlista2.getScore()) {
				resultat.add(new Point(pLlista3.getID(), rawData.get(pLlista3.getID()).get(i),2));
			}
		}
		
		for(Point p : resultat) {
			if(p.getIdCluster() == 0)
				llRes1.add(new Point(p.getID(), p.getScore(),0));
			if(p.getIdCluster() == 1)
				llRes2.add(new Point(p.getID(), p.getScore(),1));
			if(p.getIdCluster() == 2)
				llRes3.add(new Point(p.getID(), p.getScore(),2));
		}
		
		res.put(0, llRes1);
		res.put(1, llRes2);
		res.put(2, llRes3);
		
		return res;
	}
	
	
	
	@Override
	public String toString() {
		return "Cluster [points=" + points + ", centroids=" + centroids + ", k=" + k + "]";
	}
	
	

	
	
	

}
