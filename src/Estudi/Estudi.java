package Estudi;

import java.sql.*;
import java.util.*;


public class Estudi {
	
	private int NUMUSUARIS=73421;
	private int NUMRESTAURANTS=100;
	
	private Statement statement;
	
	
	public Estudi(Statement stmt) {
		this.statement=stmt;
	}
	
	/*
	 * RECOMPTES
	 */
	
	/**
	 * M�tode de recompte que permet con�ixer el numero de restaurants
	 * @return numero de restaurants
	 * @throws Exception SQL EXCEPTION
	 */
	public int comptarRestaurants() throws Exception {
		String sql="SELECT count(*) AS rowcount FROM restaurant";
		ResultSet rs;
		rs=statement.executeQuery(sql);
		rs.next();
		return rs.getInt("rowcount");
		
	}
	
	/**
	 * M�tode de recompte que permet con�ixer el numero d'usuaris
	 * @return numero d'usuaris
	 * @throws Exception SQL EXCEPTION
	 */
	public int comptarUsuaris() throws Exception{
		String sql = "SELECT count(*) AS rowcount FROM usuari";
		ResultSet rs;
		rs=statement.executeQuery(sql);
		rs.next();
		return rs.getInt("rowcount");
	}
	
	/**
	 * M�tode de recompte que permet con�ixer el numero de vots nuls en tota la mostra.
	 * @return n�mero de espais no votats.
	 * @throws Exception SQL EXCEPTION
	 */
	public int comptarNoVotats() throws Exception{
		String sql = "SELECT count(*) AS rowcount FROM relusrrest WHERE puntuacio=99.00";
		ResultSet rs;
		rs=statement.executeQuery(sql);
		rs.next();
		return rs.getInt("rowcount");
	}
	
	/**
	 * M�tode de recompte que permet con�ixer el numero de vots no nuls en tota la mostra.
	 * @return n�mero de vots no nuls
	 * @throws Exception SQL EXCEPTION
	 */
	public int comptarVotats() throws Exception{
		String sql="SELECT count(*) AS rowcount FROM relusrrest WHERE puntuacio!=99.00";
		ResultSet rs;
		rs=statement.executeQuery(sql);
		rs.next();
		return rs.getInt("rowcount");
	}
	
	/**
	 * M�tode de recompte que permet con�ixer el numero de vots negatius en tota la mostra.
	 * @return n�mero de vots negatius
	 * @throws Exception SQL EXCEPTION
	 */
	public int comptarVotsNegatius() throws Exception{
		String sql="SELECT count(*) AS rowcount FROM relusrrest WHERE (puntuacio>=-10 AND puntuacio<=-0.01)";
		ResultSet rs;
		rs=statement.executeQuery(sql);
		rs.next();
		return rs.getInt("rowcount");
	}
	
	/**
	 * M�tode de recompte que permet con�ixer el numero igual a zero en tota la mostra.
	 * @return n�mero de vots equivalents a zero.
	 * @throws Exception SQL EXCEPTION
	 */
	public int comptarVotsZero() throws Exception{
		String sql="SELECT count(*) AS rowcount FROM relusrrest WHERE (puntuacio=0.00)";
		ResultSet rs;
		rs=statement.executeQuery(sql);
		rs.next();
		return rs.getInt("rowcount");
	}
	
	/**
	 * M�tode de recompte que permet con�ixer el numero de vots positius en tota la mostra.
	 * @return vots positius
	 * @throws Exception SQL EXCEPTION
	 */
	public int comptarVotsPositius() throws Exception{
		String sql="SELECT count(*) AS rowcount FROM relusrrest WHERE (puntuacio>0.00 AND puntuacio<=10)";
		ResultSet rs;
		rs=statement.executeQuery(sql);
		rs.next();
		return rs.getInt("rowcount");
	}
	
	/**
	 * M�tode de recompte que retorna els usuaris que han visitat els restaurants (Influ�ncia de gent en cadascun dels restaurants).
	 * @return llistat amb cadascun dels restaurants i la influ�ncia de gent.
	 * @throws Exception SQL EXCEPTION
	 */
	public List<InfluenciaGent> numeroDeVisitesXRestaurant() throws Exception{
		List<InfluenciaGent> influencia = new ArrayList<InfluenciaGent>();
		ResultSet rs;
		String sql="";
		
		for(int i = 1; i<=NUMRESTAURANTS; i++) {
			sql="SELECT count(*) AS vegades FROM relusrrest WHERE (restaurant="+i+" AND puntuacio !=99.00)";
			rs = statement.executeQuery(sql);
			rs.next();
			influencia.add(new InfluenciaGent(i,rs.getInt("vegades")));
		}
		
		Collections.sort(influencia);
		return influencia;
	}
	
	/**
	 * M�tode de recompte que retorna quants restaurants han visitat cadascun dels usuaris. 
	 * @return llistat amb cadascun dels usuaris quants han visitat.
	 * @throws Exception SQL EXCEPTION
	 */
	public List<RestVisitats> numeroDeRestaurantsVisitats()throws Exception{
		List<RestVisitats> numVisitats = new ArrayList<RestVisitats>();
		String sql;
		ResultSet rs;
		
		for(int i = 1; i<=NUMUSUARIS; i++) {
			sql="SELECT count(*) AS visitats FROM relusrrest WHERE (usuari="+i+" AND puntuacio!=99.00)";
			rs=statement.executeQuery(sql);
			rs.next();
			numVisitats.add(new RestVisitats(i,rs.getInt("visitats")));
		}
		
		Collections.sort(numVisitats);
		
		return numVisitats;
	}
	
	/**
	 * M�tode que retorna el percentatge de restaurants aprobats.
	 * @return Percentatge de restaurants aprobats de la mostra.
	 * @throws Exception SQL EXCEPTION
	 */
	public Double percentatgeRestaurantsAprobats() throws Exception{
		int numRestaurants=this.comptarRestaurants();
		List<Double> mitjanes = this.mitjanaPuntuacioCadaRestaurant();
		
		int aprobats=0;
		
		for(Double d:mitjanes) {
			if(d>0) 
				aprobats++;
		}
		double numAprobats=Double.valueOf(aprobats);
		double numRestaurantsT = Double.valueOf(numRestaurants);
		double percentatge=numAprobats/numRestaurantsT;
		
		return percentatge*100;
	}
	
	/**
	 * M�tode que retorna el percentatge de restaurants suspesos.
	 * @return Percentatge de restaurants suspesos en la mostra.
	 * @throws Exception SQL EXCEPTION
	 */
	public Double percentatgeRestaurantsSuspesos() throws Exception{
		int numRestaurants=this.comptarRestaurants();
		List<Double> mitjanes = this.mitjanaPuntuacioCadaRestaurant();
		
		int suspesos=0;
		
		for(Double d:mitjanes) {
			if(d<0) 
				suspesos++;
		}
		double numSuspesos=Double.valueOf(suspesos);
		double numRestaurantsT = Double.valueOf(numRestaurants);
		double percentatge=numSuspesos/numRestaurantsT;
		
		return percentatge*100;
	}
	
	
	/*
	 * MITJANES i DIAGRAMES DE DISPERSI�
	 * 
	 */
	
	/**
	 * M�tode que retorna la mitjana de totes les puntuacions de la mostra.
	 * @return mitjana
	 * @throws Exception SQL EXCEPTION
	 */
	public double mitjanaTotesPuntuacions() throws Exception{
		String sql="SELECT count(*) AS rowcount FROM relusrrest WHERE puntuacio IN (SELECT puntuacio FROM relusrrest WHERE puntuacio!=99.00)";
		ResultSet rs;
		rs=statement.executeQuery(sql);
		rs.next();
		
		int totalEntrades=rs.getInt("rowcount");
		
		double totalEntry=Double.valueOf(totalEntrades);
		
		sql="SELECT SUM(puntuacio) AS rowcount FROM relusrrest WHERE puntuacio IN (SELECT puntuacio FROM relusrrest WHERE puntuacio!=99.00)";
		rs=statement.executeQuery(sql);
		rs.next();
		double suma=rs.getFloat("rowcount");
		
		return suma/totalEntry;
		
	}
	
	/**
	 * M�tode que retorna la mitjana de puntacions de cada restaurant.  (tamb� servir� per a realitzar una grafica de dispersi�!!)
	 * @return Mitjana de cada restaurant.
	 * @throws Exception SQL EXEPTION
	 */
	public List<Double> mitjanaPuntuacioCadaRestaurant() throws Exception{
		
		List<Double> mitjanes = new ArrayList<Double>();
		ResultSet rs;
		String sql;
		for(int i=1; i<=NUMRESTAURANTS; i++) {
			sql="SELECT count(*) AS rowcount FROM relusrrest WHERE (restaurant="+i+" AND puntuacio!=99.00)";
			rs=statement.executeQuery(sql);
			rs.next();
			int totalP=rs.getInt("rowcount");
			double totalPuntuacions=Double.valueOf(totalP);
			sql= "SELECT SUM(puntuacio) AS rowcount FROM relusrrest WHERE (restaurant="+i+" AND puntuacio!=99.00)";
			rs=statement.executeQuery(sql);
			rs.next();
			double suma=rs.getFloat("rowcount");
			mitjanes.add(suma/totalPuntuacions);
			System.out.println(i+"de 100");
		}
		return mitjanes;
	}
	
	/**
	 * M�tode que retorna la mitjana de puntacions de cada usuari.  (tamb� servir� per a realitzar una grafica de dispersi�!!)
	 * @return llistat de mitjanes i usuaris
	 * @throws Exception SQL EXCEPTION
	 */
	public List<RelUsrPunt> mitjanaPuntuacioCadaUsuari() throws Exception{
		List<RelUsrPunt> mitjanesUsuaris = new ArrayList<RelUsrPunt>();
		ResultSet rs;
		String sql;
		for(int i = 1; i<=NUMUSUARIS; i++) {
			sql="SELECT count(*) AS visitats FROM relusrrest WHERE (usuari="+i+" AND puntuacio!=99.00)";
			rs = statement.executeQuery(sql);
			rs.next();
			int compt=rs.getInt("visitats");
			float comptF=Float.valueOf(compt);
			sql="SELECT SUM(puntuacio) AS suma FROM relusrrest WHERE (usuari="+i+" AND puntuacio!=99.00)";
			rs = statement.executeQuery(sql);
			rs.next();
			float sum=rs.getFloat("suma");
			mitjanesUsuaris.add(new RelUsrPunt(i,sum/comptF));
		}
		Collections.sort(mitjanesUsuaris);
		return mitjanesUsuaris;
	}
	
	/*
	 * DESVIACIONS
	 */
	
	/**
	 * M�tode auxiliar que calcula la mediana d'un conjunt de dades passada per par�metre.
	 * Per tal de calcular la mediana, es necessita tenir el conjunt de dades ordenat en sentit ascendent.
	 * Si el n�mero de valors en la mostra �s imparell, llavors la mediana �s el valor que ocupa la posici� n+1/2
	 * en cas que el nombre de valors en la mostra �s parell, llavors la mediana �s el valor mitj� dels dos valors centrals.
	 * @param conjuntdades Les puntuacions.
	 * @return mediana calculada
	 */
	public double calcularMediana(List<Double> conjuntdades) {
		//ordenem la llista en sentit ascendent
		Collections.sort(conjuntdades);
		int midaLlista = conjuntdades.size();
		if(midaLlista%2 != 0) { 
			//conjunt de dades imparell, la qual cosa implica que la mediana �s el valor que ocupa la posici� (n+1)/2
			return conjuntdades.get((midaLlista+1)/2 -1);
		}else {
			//conjunt de dades parell, la qual cosa implica que la mediana �s la mitjana dels dos valors centrals. (Xn/2 + Xn/2+1)/2
			double v1=conjuntdades.get(midaLlista/2 -1);
			double v2=conjuntdades.get((midaLlista/2));
			return (v1+v2)/2;
		}
		
	}
	
	/**
	 * M�tode que calcula la desviaci� absoluta de puntuacions pel cas dels restaurants. Permet con�ixer la dist�ncia dels punts respecte la mediana (punt mig de les dades)
	 * F�rmula --> Di = SUM(abs(Xi - m(X)))
	 * On:
	 * 	-> Xi: valor de la mostra
	 * 	-> m(X): mediana de la mostra, per tal de veure la tend�ncia de les dades del conjunt de valors.
	 * FONT: https://es.wikipedia.org/wiki/Desviaci%C3%B3n_(estad%C3%ADstica)
	 * @return llistat de desviacions absolutes
	 * @throws Exception SQL EXCEPTION
	 */
	public List<DesviacioAbsRestaurant> desviacioAbsolutaPuntuacioRestaurants() throws Exception{
		String sql;
		ResultSet rs;
		List<Double> valors = new ArrayList<Double>();
		List<Double> resultatIntermig = new ArrayList<Double>();
		List<DesviacioAbsRestaurant> resultatFinal = new ArrayList<DesviacioAbsRestaurant>();
		
		for(int i = 1; i<=NUMRESTAURANTS;i++) {
			sql="SELECT puntuacio FROM relusrrest WHERE restaurant="+i+" AND puntuacio!=99.00";
			rs = statement.executeQuery(sql);
			while(rs.next()) {
				valors.add(rs.getDouble("puntuacio"));
			}
			double mediana = this.calcularMediana(valors);
			for(Double d : valors) {
				resultatIntermig.add(Math.abs(d - mediana));
			}
			double resultatF = 0;
			for(Double d : resultatIntermig) {
				resultatF+=d;
			}
			resultatFinal.add(new DesviacioAbsRestaurant(i,resultatF));
			resultatIntermig = new ArrayList<Double>();
		}
		
		return resultatFinal;
	}
	
	/**
	 * M�tode auxiliar que calcula la mitjana de valors donat un conjunt.
	 * @param conjuntValors llistat de valors
	 * @return mitjana
	 */
	public double calcularMitjana(List<Double> conjuntValors) {
		int numValors = conjuntValors.size();
		double numValorsD = Double.valueOf(numValors);
		
		double suma=0;
		for(Double d: conjuntValors)
			suma+=d;
		
		return suma/numValorsD;
		
	}
	
	/**
	 * M�tode que retorna la desviaci� est�ndard mostral per restaurants. S'agafa una mostra de X restaurants. El c�lcul consisteix en calcular la mitjana de la mostra,
	 * i aplicar la seguent f�rmula:
	 * s = sqrt(1/n-1*SUM((xi-mitjana)^2))
	 * FONT: https://es.wikipedia.org/wiki/Desviaci%C3%B3n_t%C3%ADpica#Desviaci%C3%B3n_est%C3%A1ndar_muestral_de_las_edades_de_seis_ni%C3%B1os
	 * @return desviaci� estandard mostral per a el subgrup dels restaurants 1 fins al 50.
	 * @throws Exception SQL EXCEPTION
	 */
	public List<DesviacioEstMostralRestaurant> desviacioEstandardMostralPerRestaurants() throws Exception{
		String sql;
		ResultSet rs;
		List<Double> valors = new ArrayList<Double>();
		List<DesviacioEstMostralRestaurant> desviacioS = new ArrayList<DesviacioEstMostralRestaurant>();
		
		for(int i = 1; i<=50; i++) {
			sql = "SELECT puntuacio FROM relusrrest WHERE (restaurant ="+i+"AND puntuacio!= 99.00 )";
			rs = statement.executeQuery(sql);
			while(rs.next()) {
				valors.add(rs.getDouble("puntuacio"));
			}
			double mitjana = this.calcularMitjana(valors);
			
			double v=0;
			for(Double d : valors) {
				double base= d-mitjana;
				v+=Math.pow((base), 2);
			}
			double numElems = Double.valueOf(valors.size());
			double numElemsXLaFormula = numElems -1;
			double a = 1/numElemsXLaFormula;
			
			desviacioS.add(new DesviacioEstMostralRestaurant(i, Math.sqrt(a*v)));
		}
		
		return desviacioS;
	}
	
	/**
	 * M�tode que calcula la desviaci� estandard poblacional de les puntuacions dels 100 restaurants.
	 * La desviaci� est�ndard poblacional es determina calculant l'arrel quadrada de la mitjana de les desviacions dels valors restats del seu valor mitj�, elevat al quadrat.
	 * FONT: https://es.wikipedia.org/wiki/Desviaci%C3%B3n_t%C3%ADpica#Desviaci%C3%B3n_est%C3%A1ndar_poblacional_de_las_calificaciones_de_ocho_alumnos
	 * @return desviaci� estandard poblacional
	 * @throws Exception SQL EXCEPTION
	 */
	public List<DesviacioEstandardPoblacionalPerRestaurant> desviacioEstandardPoblacionalPerRestaurants() throws Exception{
		
		List<DesviacioEstandardPoblacionalPerRestaurant> resultat = new ArrayList<DesviacioEstandardPoblacionalPerRestaurant>();
		String sql;
		ResultSet rs;
		
		List<Double> valors = new ArrayList<Double>();
		
		for(int i=1; i<=NUMRESTAURANTS; i++) {
			
			sql="SELECT puntuacio FROM relusrrest WHERE(restaurant="+i+" AND puntuacio!=99.00)";
			rs = statement.executeQuery(sql);
			while(rs.next()) {
				valors.add(rs.getDouble("puntuacio"));
			}
			double mitjana = this.calcularMitjana(valors);
			double numElems = Double.valueOf(valors.size());
			double v = 0;
			double base;
			for(Double d : valors) {
				base = d-mitjana;
				v+= Math.pow(base, 2);
			}
			
			double res = v/numElems;
			
			resultat.add(new DesviacioEstandardPoblacionalPerRestaurant(i, Math.sqrt(res)));
		}
		
		return resultat;
	}
	
	/**
	 * M�tode que permet el c�lcul de la desviaci� mitjana per restaurants. En estad�stica la desviaci� absoluta mitjana �s la mitjana de les desviacions absolutes --> dispersi�.
	 * FONT: https://es.wikipedia.org/wiki/Desviaci%C3%B3n_media
	 * @return llistat de les dispersions
	 * @throws Exception SQL EXCEPTION
	 */
	public List<DesvMitjanaRestaurant> desviacioMitjanaPerRestaurants() throws Exception{
		
		List<DesvMitjanaRestaurant> resultat = new ArrayList<DesvMitjanaRestaurant>();
		List<Double> valors = new ArrayList<Double>();
		List<Double> resIntermig = new ArrayList<Double>();
		String sql;
		ResultSet rs;
		
		for(int i=1; i<=NUMRESTAURANTS;i++) {
			sql = "SELECT puntuacio FROM relusrrest WHERE (restaurant="+i+" AND puntuacio!=99.00)";
			rs = statement.executeQuery(sql);
			while(rs.next()) {
				valors.add(rs.getDouble("puntuacio"));
			}
			
			double mitjana = this.calcularMitjana(valors);
			
			for(Double d : valors) {
				resIntermig.add(Math.abs(d-mitjana));
			}
			
			double suma = 0;
			for(Double d : resIntermig)
				suma+=d;
			
			resultat.add(new DesvMitjanaRestaurant(i, suma/Double.valueOf(valors.size())));
		}
		
		return resultat;
	}
	
	/*
	 * HISTOGRAMA
	 */
	
	/**
	 * M�tode que retorna el numero de vegades que surt cada nota i els ordena en intervals de dos.
	 * [-10,-8] <-> (-8,-6] <-> (-6, -4] <-> (-4,-2] <-> (-2,0] <-> (0,2] <-> (2,4] <-> (4,6] <-> (6,8] <-> (8,10] 
	 * @return la quantitat de vegades que hi ha la puntuaci� x en cadascun dels int�rvals corresponents.
	 * @exception SQL EXCEPTION
	 */
	public List<Histograma> histogramaXintervalsRestaurants() throws Exception{
		List<Histograma> resultat = new ArrayList<Histograma>();
		String sql;
		ResultSet rs;
		
		sql="SELECT count(puntuacio) AS interval"
		 + " FROM relusrrest"
		 + " WHERE (puntuacio!=99.00 AND"
		 		+ " puntuacio>=-10 AND puntuacio<=-8"
		 		+ " AND puntuacio IN (SELECT DISTINCT puntuacio FROM relusrrest WHERE puntuacio !=99.00))";
		
		rs = statement.executeQuery(sql);
		rs.next();
		resultat.add(new Histograma("Interval[-10, -8]",rs.getInt("interval")));
		
		sql="SELECT count(puntuacio) AS interval"
				 + " FROM relusrrest"
				 + " WHERE (puntuacio!=99.00 AND"
				 		+ " puntuacio>-8 AND puntuacio<=-6"
				 		+ " AND puntuacio IN (SELECT DISTINCT puntuacio FROM relusrrest WHERE puntuacio !=99.00))";
				
		rs = statement.executeQuery(sql);
		rs.next();
		resultat.add(new Histograma("Interval(-8, -6]",rs.getInt("interval")));
		
		sql="SELECT count(puntuacio) AS interval"
				 + " FROM relusrrest"
				 + " WHERE (puntuacio!=99.00 AND"
				 		+ " puntuacio>-6 AND puntuacio<=-4"
				 		+ " AND puntuacio IN (SELECT DISTINCT puntuacio FROM relusrrest WHERE puntuacio !=99.00))";
				
		rs = statement.executeQuery(sql);
		rs.next();
		resultat.add(new Histograma("Interval(-6, -4]",rs.getInt("interval")));
		
		sql="SELECT count(puntuacio) AS interval"
				 + " FROM relusrrest"
				 + " WHERE (puntuacio!=99.00 AND"
				 		+ " puntuacio>-4 AND puntuacio<=-2"
				 		+ " AND puntuacio IN (SELECT DISTINCT puntuacio FROM relusrrest WHERE puntuacio !=99.00))";
				
		rs = statement.executeQuery(sql);
		rs.next();
		resultat.add(new Histograma("Interval(-4, -2]",rs.getInt("interval")));
		
		sql="SELECT count(puntuacio) AS interval"
				 + " FROM relusrrest"
				 + " WHERE (puntuacio!=99.00 AND"
				 		+ " puntuacio>-2 AND puntuacio<=0"
				 		+ " AND puntuacio IN (SELECT DISTINCT puntuacio FROM relusrrest WHERE puntuacio !=99.00))";
				
		rs = statement.executeQuery(sql);
		rs.next();
		resultat.add(new Histograma("Interval(-2, 0]",rs.getInt("interval")));
		
		sql="SELECT count(puntuacio) AS interval"
				 + " FROM relusrrest"
				 + " WHERE (puntuacio!=99.00 AND"
				 		+ " puntuacio>0 AND puntuacio<=2"
				 		+ " AND puntuacio IN (SELECT DISTINCT puntuacio FROM relusrrest WHERE puntuacio !=99.00))";
				
		rs = statement.executeQuery(sql);
		rs.next();
		resultat.add(new Histograma("Interval(0, 2]",rs.getInt("interval")));
		
		sql="SELECT count(puntuacio) AS interval"
				 + " FROM relusrrest"
				 + " WHERE (puntuacio!=99.00 AND"
				 		+ " puntuacio>2 AND puntuacio<=4"
				 		+ " AND puntuacio IN (SELECT DISTINCT puntuacio FROM relusrrest WHERE puntuacio !=99.00))";
				
		rs = statement.executeQuery(sql);
		rs.next();
		resultat.add(new Histograma("Interval(2, 4]",rs.getInt("interval")));
		
		sql="SELECT count(puntuacio) AS interval"
				 + " FROM relusrrest"
				 + " WHERE (puntuacio!=99.00 AND"
				 		+ " puntuacio>4 AND puntuacio<=6"
				 		+ " AND puntuacio IN (SELECT DISTINCT puntuacio FROM relusrrest WHERE puntuacio !=99.00))";
				
		rs = statement.executeQuery(sql);
		rs.next();
		resultat.add(new Histograma("Interval(4, 6]",rs.getInt("interval")));
		
		sql="SELECT count(puntuacio) AS interval"
				 + " FROM relusrrest"
				 + " WHERE (puntuacio!=99.00 AND"
				 		+ " puntuacio>6 AND puntuacio<=8"
				 		+ " AND puntuacio IN (SELECT DISTINCT puntuacio FROM relusrrest WHERE puntuacio !=99.00))";
				
		rs = statement.executeQuery(sql);
		rs.next();
		resultat.add(new Histograma("Interval(6, 8]",rs.getInt("interval")));
		
		sql="SELECT count(puntuacio) AS interval"
				 + " FROM relusrrest"
				 + " WHERE (puntuacio!=99.00 AND"
				 		+ " puntuacio>8 AND puntuacio<=10"
				 		+ " AND puntuacio IN (SELECT DISTINCT puntuacio FROM relusrrest WHERE puntuacio !=99.00))";
				
		rs = statement.executeQuery(sql);
		rs.next();
		resultat.add(new Histograma("Interval(8, 10]",rs.getInt("interval")));
		
		return resultat;
	}
	
	
	/*
	 * ALTRES
	 */
	
	/**
	 * M�tode que retorna l'usuari i el restaurant el qual ha puntuat amb un 10 (m�xima nota).
	 * @return llistat amb tots i cadascun dels usuaris que han puntuat amb un 10 algun dels restaurants.
	 * @throws Exception SQL EXCEPTION
	 */
	public List<RelUsrRest> relacioUsuariPuntuacio10Restaurant() throws Exception{
		String sql="SELECT usuari,restaurant"
				+ " FROM relusrrest"
				+ " WHERE (puntuacio=(SELECT MAX(puntuacio)"
								  + " FROM relusrrest"
								  + " WHERE (puntuacio!=99.00))"
				+ ")";
		ResultSet rs;
		List<RelUsrRest> rel = new ArrayList<RelUsrRest>();
		rs = statement.executeQuery(sql);
		while(rs.next()) {
			rel.add(new RelUsrRest(rs.getInt("usuari"),rs.getInt("restaurant")));
		}
		
		return rel;
	}
	
	/**
	 * M�tode que retorna l'usuari i el restaurant el qual ha puntuat amb la pitjor nota.
	 * @return llistat amb tots i cadascun dels usuaris amb el restaurant el qual han puntuat amb la pitjor nota.
	 * @throws Exception SQL EXCEPTION
	 */
	public List<RelUsrRest> relacioUsuariPuntuacioMesBaixaRestaurant() throws Exception{
		String sql="SELECT usuari,restaurant"
				+ " FROM relusrrest"
				+ " WHERE (puntuacio=(SELECT MIN(puntuacio)"
								  + " FROM relusrrest"
								  + " WHERE (puntuacio!=99.00))"
						+ ")";
		ResultSet rs;
		List<RelUsrRest> rel = new ArrayList<RelUsrRest>();
		rs = statement.executeQuery(sql);
		while(rs.next()) {
			rel.add(new RelUsrRest(rs.getInt("usuari"), rs.getInt("restaurant")));
		}
		return rel;
	}
	
	/**
	 * M�tode que retorna el ID del usuari que ha visitat m�s restaurants.
	 * @return id del usuari
	 * @throws Exception SQL EXCEPTION
	 */
	public String usuariAmbMesRestaurantsVisitats() throws Exception{
		String resultat="", sql;
		int resultatMax=-9999;
		ResultSet rs;
		for(int i=1; i<=NUMUSUARIS; i++) {
				sql="SELECT count(*) AS restaurantsVisitats FROM relusrrest WHERE (usuari="+i+" AND puntuacio!=99.00)";
				rs = statement.executeQuery(sql);
				rs.next();
				int compt=rs.getInt("restaurantsVisitats");
				if(resultatMax < compt) {
					resultat="L'usuari "+i+" ha visitat: "+compt+" vegades.";
					resultatMax=compt;
				}
		}
		
		return resultat;
	}
	
	/**
	 * M�tode retorna l'usuari que ha votat pitjor. 
	 * @return id del usuari
	 * @throws Exception SQL EXCEPTION
	 */
	public int usuariQueVotaPitjor() throws Exception{
		List<RelUsrPunt> mitjanesUsuaris = new ArrayList<RelUsrPunt>();
		ResultSet rs;
		String sql;
		for(int i=1; i<=NUMUSUARIS; i++) {
			sql="SELECT count(*) AS visitats FROM relusrrest WHERE (usuari="+i+" AND puntuacio!=99.00)";
			rs = statement.executeQuery(sql);
			rs.next();
			int compt=rs.getInt("visitats");
			float comptF=Float.valueOf(compt);
			sql="SELECT SUM(puntuacio) AS suma FROM relusrrest WHERE (usuari="+i+" AND puntuacio!=99.00)";
			rs = statement.executeQuery(sql);
			rs.next();
			float sum=rs.getFloat("suma");
			mitjanesUsuaris.add(new RelUsrPunt(i,sum/comptF));
		}
		
		Collections.sort(mitjanesUsuaris);
		Collections.reverse(mitjanesUsuaris);
		
		return mitjanesUsuaris.get(0).getIdUsuari();
	}
	
	/**
	 * M�tode que retorna l'usuari que ha votat millor.
	 * @return id del usuari que ha votat millor
	 * @throws Exception SQL EXCEPTION
	 */
	public int usuariQueVotaMillor() throws Exception{
		List<RelUsrPunt> mitjanesPuntsXUsr = new ArrayList<RelUsrPunt>();
		ResultSet rs;
		String sql;
		for(int i = 1; i<=NUMUSUARIS; i++) {
			sql="SELECT count(*) AS visitats FROM relusrrest WHERE (usuari="+i+" AND puntuacio!=99.00)";
			rs = statement.executeQuery(sql);
			rs.next();
			int compt=rs.getInt("visitats");
			float comptF=Float.valueOf(compt);
			sql="SELECT SUM(puntuacio) AS suma FROM relusrrest WHERE (usuari="+i+" AND puntuacio!=99.00)";
			rs = statement.executeQuery(sql);
			rs.next();
			float sum=rs.getFloat("suma");
			mitjanesPuntsXUsr.add(new RelUsrPunt(i,sum/comptF));
		}
		
		Collections.sort(mitjanesPuntsXUsr);
		
		return mitjanesPuntsXUsr.get(0).getIdUsuari();
	}
	
	
	/**
	 * M�tode que retorna la puntuaci� m�s alta de totes les presents.
	 * @return puntuaci� m�s alta.
	 * @throws Exception SQL EXCEPTION
	 */
	public float puntuacioMesAlta() throws Exception{
		String sql="SELECT MAX(puntuacio) AS maxValue"
				+ " FROM relusrrest"
				+ " WHERE (puntuacio!=99.00)";
		ResultSet rs;
		rs = statement.executeQuery(sql);
		rs.next();
		return rs.getFloat("maxValue");
		
	}
	
	/**
	 * M�tode que retorna la puntuaci� m�s baixa de totes de la mostra.
	 * @return puntuaci� m�s baixa
	 * @throws Exception SQL EXCEPTION
	 */ 
	public float puntuacioMesBaixa() throws Exception{
		String sql="SELECT MIN(puntuacio) AS minValue"
				+ " FROM relusrrest"
				+ " WHERE (puntuacio!=99.00)";
		ResultSet rs;
		rs = statement.executeQuery(sql);
		rs.next();
		return rs.getFloat("minValue");
	}

}
