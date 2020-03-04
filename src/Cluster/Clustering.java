package Cluster;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class Clustering {

	private static Scanner teclat = new Scanner(System.in);
	
	/**
	 * Mètode que permet la connexió amb la base de dades Mysql
	 * @return connexió
	 */
	private static Connection connexioDB() {
		System.out.println("Establint la connexió amb la base de dades...");
		try {
			Class.forName("org.mariadb.jdbc.Driver"); 
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/SIO", "root", "");
			System.out.println("Connexió establerta amb la base de dades!");
			return conn;
		}catch(Exception e) {
			System.out.println(e);
			return null;
		}
		
	}
	
	
	
	
	public static void main(String[] args) {
		
		Connection connexio=null;
		Statement stmt=null;
		ResultSet rs;
		connexio = connexioDB();
		Map<Integer, List<Double>> puntuacionsPerRestaurant = new HashMap<Integer, List<Double>>(); 
		try {
			stmt = connexio.createStatement();
			for(int i=1; i<=5; i++) {
				String sql ="SELECT puntuacio FROM relusrrest WHERE restaurant="+i;
				rs = stmt.executeQuery(sql);
				List<Double> aux = new ArrayList<Double>();
				while(rs.next()) {
					aux.add(rs.getDouble("puntuacio"));
				}
				sql = "SELECT AVG(puntuacio) AS mitjana FROM relusrrest WHERE (restaurant="+i+" AND puntuacio!=99.00)";
				rs = stmt.executeQuery(sql);
				rs.next();
				double mitjana = rs.getDouble("mitjana");
				for(int j = 0; j<aux.size(); j++) {
					if(aux.get(j) == 99.00) {
						aux.set(j, mitjana);
					}
				}
				puntuacionsPerRestaurant.put(i, aux);
				System.out.println(i+" de 100");
			}
			Cluster cluster = new Cluster(3);
			List<Point> centroids = new ArrayList<Point>();
			for(int j =0; j<cluster.getK(); j++) {
				int rndrest = (int)(Math.random() * 5 + 1);
				int rndpuntuacio=(int)(Math.random()*73421);
				centroids.add(new Point(rndrest, puntuacionsPerRestaurant.get(rndrest).get(rndpuntuacio)));
			}
			cluster.setCentroids(centroids);
			List<Point> centroidsAnteriors = new ArrayList<Point>();
			Map<Integer, List<Point>> cFinalAnterior = new HashMap<Integer,List<Point>>();
			List<Point> newCentroids = new ArrayList<Point>();
			Map<Integer, List<Point>> distancies = cluster.calcularDist(puntuacionsPerRestaurant);
			Map<Integer,List<Point>> cFinal= cluster.cluster(distancies, puntuacionsPerRestaurant);
			while(true) {
				for(Integer k : cFinal.keySet()) {
					List<Point> aux = cFinal.get(k);
					double sum = 0;
					double nElems = Double.valueOf(aux.size());
					for(Point p : aux) {
						sum+=p.getScore();
					}
					newCentroids.add(new Point(k,sum/nElems));
				}
				centroidsAnteriors = cluster.getCentroids();
				cluster.setCentroids(newCentroids);
				cFinalAnterior = cFinal;
				distancies = cluster.calcularDist(puntuacionsPerRestaurant);
				cFinal= cluster.cluster(distancies, puntuacionsPerRestaurant);
				
				if(cluster.acabat(centroidsAnteriors, cFinalAnterior, cFinal))
					break;
			}
			System.out.println(cFinal);
		}catch(Exception e) {
			System.out.println(e);
		}
		
		
		
		
		
	}
}
