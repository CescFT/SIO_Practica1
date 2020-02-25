package ConnectionToDB;

import java.io.*;
import java.sql.*;
import Estudi.*;
import java.util.*;
import java.io.IOException;
 

public class ConnexioDB {
	
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
	
	/**
	 * Mètode que crea les taules a la base de dades SQL relacional. 
	 * @param sentenciaSQL Comunicació per a generar les taules
	 * @param conn Connexió establerta
	 * @throws Exception SQL EXCEPTION
	 */
	private static void crearTaules(Statement sentenciaSQL, Connection conn) throws Exception {
		sentenciaSQL = conn.createStatement();
		String taulaUsuari = "CREATE TABLE USUARI "
                			+ "(id int(5) not NULL UNIQUE, "
                			+ "constraint pkusuari primary key (id)"
                			+")engine=innodb;";
		
		System.out.println(taulaUsuari);
		
		String taulaRestaurant = "CREATE TABLE RESTAURANT"
								+"(id int(3) not NULL UNIQUE,"
								+"constraint pkrestaurant primary key (id)"
								+")engine=innodb;";
		
		System.out.println(taulaRestaurant);
		
		
		String taulaRelacio = "CREATE TABLE RELUSRREST"
							  +"(usuari int(5) not NULL,"
							  +"restaurant int(3) not NULL,"
							  + "puntuacio decimal(5,2) not NULL,"
							  +"constraint fk_usuari foreign key (usuari) references usuari(id),"
							  +"constraint fk_restaurant foreign key (restaurant) references restaurant(id)"
							  +")engine=innodb;";
		
		
		System.out.println(taulaRelacio);
		
		
		sentenciaSQL.executeUpdate(taulaUsuari);
		sentenciaSQL.executeUpdate(taulaRestaurant);
		sentenciaSQL.executeUpdate(taulaRelacio);
		
	}
	
	/**
	 * Mètode del programa principal que permet una certa connexió amb l'usuari i si l'usuari vol, crea les taules.
	 * @param stmt Comunicació que permet la creació de les taules.
	 * @param connexio Connexió amb la base de dades
	 */
	private static void creacioTaules(Statement stmt, Connection connexio) {
		System.out.println("Vols crear taules [S/n]?");
		String op = teclat.nextLine();
		while(!op.toLowerCase().equals("s") && !op.toLowerCase().equals("n")) {
			System.out.println("Siusplau, posa una opció correcta. [S/n]");
			op = teclat.nextLine();
		}
		
		if (op.toLowerCase().equals("s")) {
			System.out.println("Creant les taules...");
			
			try {
				crearTaules(stmt, connexio);
				System.out.println("Taules creades correctament.");
			}catch(Exception e) {
				System.out.println(e);
			}
		}
	}
	
	/**
	 * Mètode del programa principal que permet un cert grau d'interactuament amb l'usuari i que permet guardar les dades a la base de dades.
	 * @param nomF Nom del fitxer a llegir.
	 * @param c Connexió establerta amb la base de dades
	 */
	private static void guardarDadesADB(String nomF, Connection c){
		System.out.println("Vols emmagatzemar les dades?[S/n]");
		String op = teclat.nextLine();
		while(!op.toLowerCase().equals("s") && !op.toLowerCase().equals("n")) {
			System.out.println("Siusplau, posa una opció correcta. [S/n]");
			op=teclat.nextLine();
		}
		
		if(op.toLowerCase().equals("s")) {
			System.out.println("Quin fitxer vols generar?");
			System.out.println("1. Usuaris");
			System.out.println("2. Restaurants");
			System.out.println("3. Relació usuaris - restaurants");
			
			int op1 = Integer.valueOf(teclat.nextLine());
			
			while(op1!=1 && op1!=2 && op1!=3) {
				System.out.println("Siusplau, posa una opció correcta.");
				System.out.println("1. Usuaris");
				System.out.println("2. Restaurants");
				System.out.println("3. Relació usuaris - restaurants");
				op1=Integer.valueOf(teclat.nextLine());
			}
			
			
			try {
				switch(op1) {
				case 1:
					fitxerUsuaris();
					System.out.println("EXECUTA AL SERVIDOR SQL LA SEGÜENT SENTÈNCIA:");
					System.out.println("LOAD DATA LOCAL INFILE 'abspath/usuaris.txt' INTO TABLE usuari;");
					break;
				case 2:
					fitxerRestaurants();
					System.out.println("EXECUTA AL SERVIDOR SQL LA SEGÜENT SENTÈNCIA:");
					System.out.println("LOAD DATA LOCAL INFILE 'abspath/restaurants.txt' INTO TABLE restaurant;");
					break;
				case 3:
					processarFitxerIGenerarFitxerRelacio(nomF);
					System.out.println("EXECUTA AL SERVIDOR SQL LA SEGÜENT SENTÈNCIA:");
					System.out.println("LOAD DATA LOCAL INFILE 'abspath/relacio.txt' INTO TABLE relusrrest CHARACTER SET UTF8 FIELDS TERMINATED BY ',' LINES TERMINATED BY '\r\n';");
					break;
				}
				
			}catch(Exception e) {
				System.out.println(e);
			}
			
		}
		
	}
	
	/**
	 * Mètode que escriu el fitxer amb tots els identificadors dels restaurants.
	 * @throws IOException En cas que hi hagi algun error en la comunicació Entrada/Sortida
	 */
	private static void fitxerRestaurants() throws IOException{
		BufferedWriter bOut = new BufferedWriter(new FileWriter("restaurants.txt"));
		
		for(int i=1; i<=100; i++) {
			bOut.write(String.valueOf(i));
			bOut.newLine();
		}
		
		bOut.close();
	}
	
	/**
	 * Mètode que escriu el fitxer amb tots els identificadors dels usuaris
	 * @throws IOException En cas que hi hagi algun problema en la comunicació Entrada/Sortida
	 */
	private static void fitxerUsuaris() throws IOException{
		BufferedWriter bOut = new BufferedWriter(new FileWriter("usuaris.txt"));
		
		for(int i=1; i<=73421; i++) {
			bOut.write(String.valueOf(i));
			bOut.newLine();
		}
		bOut.close();
	}
	
	/**
	 * Mètode que permet processar les dades de la relació del fitxer i crea el fitxer amb les dades.
	 * @param nomFitxer Fitxer a llegir
	 * @throws IOException En cas que hi hagi algun problema en la Entrada/Sortida amb els fitxers.
	 */
	private static void processarFitxerIGenerarFitxerRelacio(String nomFitxer) throws IOException{
		BufferedReader bIn = new BufferedReader(new FileReader(nomFitxer));
		BufferedWriter bOut = new BufferedWriter(new FileWriter("relacio.txt"));
		String linia=bIn.readLine();
		int usuari = 1;
		int restaurant = 1;
		while(linia!=null) {
			String[] liniaT = linia.split(";");
			if(!liniaT[0].equals("DATASET")) {
					String sortida=String.valueOf(usuari)+",";
					for(restaurant=1; restaurant<=100; restaurant++) {
						if(restaurant==1) {
							sortida+=String.valueOf(restaurant)+",";
							sortida+=String.valueOf(liniaT[restaurant]);
							System.out.println(sortida);
							bOut.write(sortida);
						}
						
						if(restaurant>1) {
							sortida=String.valueOf(usuari)+","+String.valueOf(restaurant)+","+String.valueOf(liniaT[restaurant]);
							System.out.println(sortida);
							bOut.write(sortida);
						}
						bOut.newLine();
					}
					restaurant=1;
					usuari++;
			}
			
			linia=bIn.readLine();
		}
		
		bIn.close();
		bOut.close();
	}
	
	/**
	 * Mètode que permet escriure el resultat de la execució del estudi: numeroDeVisitesXRestaurant
	 * @param resultat llistat solucio
	 * @throws Exception Error Entrada/Sortida
	 */
	private static void escriureResultat_numeroDeVisitesXRestaurant(List<InfluenciaGent> resultat) throws Exception{
		BufferedWriter b = new BufferedWriter(new FileWriter("influenciaGent.txt"));
		b.write("idRestaurant,vegadesVisitat");
		b.newLine();
		for(InfluenciaGent i :resultat) {
			b.write(String.valueOf(i.getIdRestaurant())+","+String.valueOf(i.getNumPuntuacions()));
			b.newLine();
		}
		b.close();
		System.out.println("Fitxer influenciaGent.txt creat amb la informació requerida.");
	}
	
	/**
	 * Mètode que permet escriure el resultat de la execució del estudi: numeroDeRestaurantsVisitats
	 * @param resultat llistat solucio
	 * @throws Exception Error Entrada/Sortida
	 */
	private static void escriureResultat_numeroDeRestaurantsVisitats(List<RestVisitats> resultat) throws Exception{
		BufferedWriter b = new BufferedWriter(new FileWriter("restaurantsVisitats.txt"));
		b.write("idUsuari, restaurantsVisitats");
		b.newLine();
		for(RestVisitats i : resultat) {
			b.write(String.valueOf(i.getIdUsuari())+","+String.valueOf(i.getNumRestaurants()));
			b.newLine();
		}
		b.close();
		System.out.println("Fitxer restaurantsVisitatsPerUsuari.txt creat amb la informació requerida.");
	}
	
	/**
	 * Mètode que permet escriure el resultat de la execució del estudi: mitjanaPuntuacioCadaRestaurant
	 * @param resultat llista solucio
	 * @throws Exception Error Entrada/Sortida
	 */
	private static void escriureResultat_mitjanaPuntuacioCadaRestaurant(List<MitjRestaurant> resultat) throws Exception{
		BufferedWriter b = new BufferedWriter(new FileWriter("mitjanaRestaurants.txt"));
		b.write("idRestaurant, mitjana");
		b.newLine();
		for(MitjRestaurant m : resultat) {
			b.write(String.valueOf(m.getIdRestaurant())+","+String.valueOf(m.getMitjana()));
			b.newLine();
		}
		b.close();
		System.out.println("Fitxer mitjanaRestaurants.txt creat amb la informació requerida");
	}
	
	/**
	 * Mètode que permet escriure el resultat de la execució del estudi: mitjanaPuntuacioCadaUsuari
	 * @param resultat llistat solució
	 * @throws Exception Error Entrada/Sortida
	 */
	private static void escriureResultat_mitjanaPuntuacioCadaUsuari(List<RelUsrPunt> resultat) throws Exception{
		BufferedWriter b = new BufferedWriter(new FileWriter("mitjanaUsuaris.txt"));
		b.write("idUsuari, mitjana");
		b.newLine();
		for(RelUsrPunt r : resultat) {
			b.write(String.valueOf(r.getIdUsuari())+","+String.valueOf(r.getPuntuacio()));
			b.newLine();
		}
		b.close();
		System.out.println("Fitxer mitjanaUsuaris.txt s'ha creat correctament amb les dades requerides.");
		
	}
	
	/**
	 * Mètode que permet escriure el resultat de la execució del estudi: desviacioAbsolutaPuntuacioRestaurants
	 * @param resultat llistat solució
	 * @throws Exception Error Entrada/Sortida
	 */
	private static void escriureResultat_desviacioAbsolutaPuntuacioRestaurants(List<DesviacioAbsRestaurant> resultat) throws Exception{
		BufferedWriter b = new BufferedWriter(new FileWriter("desviacionsAbsolutesPerRestaurant.txt"));
		b.write("idRestaurant, deviacioAbsoluta");
		b.newLine();
		for(DesviacioAbsRestaurant desv : resultat) {
			b.write(String.valueOf(desv.getIdRestaurant())+","+String.valueOf(desv.getDesviacioAbsMitjana()));
			b.newLine();
		}
		b.close();
		System.out.println("Fitxer desviacionsAbsolutesPerRestaurant.txt creat correctament.");
	}
	
	/**
	 * Mètode que permet escriure el resultat de la execució del estudi: desviacioEstandardMostralPerRestaurants
	 * @param resultat llistat solucio
	 * @throws Exception Error Entrada/Sortida
	 */
	private static void escriureResultat_desviacioEstandardMostralPerRestaurants(List<DesviacioEstMostralRestaurant> resultat) throws Exception{
		BufferedWriter b = new BufferedWriter(new FileWriter("desviacionsEstandardsMostralsPerRestaurant.txt"));
		b.write("idRestaurant, desviacioEstMostral");
		b.newLine();
		for(DesviacioEstMostralRestaurant desvestm:resultat) {
			b.write(String.valueOf(desvestm.getIdRestaurant())+","+String.valueOf(desvestm.getDesviacioEstandardMostral()));
			b.newLine();
		}
		b.close();
		System.out.println("El fitxer desviacionsEstandardsMostralsPerRestaunrant.txt creat correctament.");
	}
	
	/**
	 * Mètode que permet escriure el resultat de la execució del estudi: desviacioEstandardPoblacionalPerRestaurants
	 * @param resultat llistat solucio
	 * @throws Exception Error Entrada/Sortida
	 */
	private static void escriureResultat_desviacioEstandardPoblacionalPerRestaurants(List<DesviacioEstandardPoblacionalPerRestaurant> resultat) throws Exception{
		BufferedWriter b = new BufferedWriter(new FileWriter("desviacionsEstandardsPoblacionalsPerRestaurant.txt"));
		b.write("idRestaurant, desviacioEstPoblacional");
		b.newLine();
		for(DesviacioEstandardPoblacionalPerRestaurant desvestp : resultat) {
			b.write(String.valueOf(desvestp.getIdRestaurant())+","+String.valueOf(desvestp.getDesvEstPob()));
			b.newLine();
		}
		b.close();
		System.out.println("El fitxer desviacionsEstandardsPoblacionalsPerRestaurant.txt s'ha creat correctament.");
	}
	
	/**
	 * Mètode que permet escriure el resultat de la execució del estudi: desviacioMitjanaPerRestaurants
	 * @param resultat llistat solució
	 * @throws Exception Error Entrada/Sortida
	 */
	private static void escriureResultat_desviacioMitjanaPerRestaurants(List<DesvMitjanaRestaurant> resultat) throws Exception{
		BufferedWriter b = new BufferedWriter(new FileWriter("desviacionsMitjanesPerRestaurant.txt"));
		b.write("idRestaurant, desviacioMitjRestaurant");
		b.newLine();
		for(DesvMitjanaRestaurant desvestmitj : resultat) {
			b.write(String.valueOf(desvestmitj.getIdRestaurant())+","+desvestmitj.getDesvMitjana());
			b.newLine();
		}
		b.close();
		
		System.out.println("El fitxer desviacionsMitjanesPerRestaurant.txt s'ha creat correctament.");
	}
	
	/**
	 * Mètode que permet escriure el resultat de la execució del estudi: histogramaXintervalsRestaurants
	 * @param resultat llistat solució
	 * @throws Exception Error Entrada/Sortida
	 */
	private static void escriureResultat_histogramaXintervalsRestaurants(List<Histograma> resultat) throws Exception{
		BufferedWriter b = new BufferedWriter(new FileWriter("histograma.txt"));
		b.write("interval,comptador");
		b.newLine();
		for(Histograma h : resultat) {
			b.write(h.getInterval()+","+String.valueOf(h.getNumVegades()));
			b.newLine();
		}
		b.close();
		System.out.println("El fitxer histograma.txt s'ha creat correctament. ");
	}
	
	/**
	 * Mètode que permet escriure el resultat de la execució del estudi: probabilitatDeCadaPuntuacio
	 * @param resultat llistat solució
	 * @throws Exception Error Entrada/Sortida
	 */
	private static void escriureResultat_probabilitatDeCadaPuntuacio(List<Probabilitat> resultat) throws Exception{
		BufferedWriter b = new BufferedWriter(new FileWriter("probabilitats.txt"));
		b.write("nota, probabilitat");
		b.newLine();
		for(Probabilitat p : resultat) {
			b.write(p.getProbNum()+","+String.valueOf(p.getProbabilitat()));
			b.newLine();
		}
		b.close();
		System.out.println("El fitxer de probabilitats.txt s'ha creat correctament.");
	}
	
	/**
	 * Mètode que permet escriure el resultat de la execució del estudi: relacioUsuariPuntuacio10Restaurant
	 * @param resultat llistat solució
	 * @throws Exception Error Entrada/Sortida
	 */
	private static void escriureResultat_relacioUsuariPuntuacio10Restaurant(List<RelUsrRest> resultat) throws Exception{
		BufferedWriter b = new BufferedWriter(new FileWriter("usuarisQueHanVotat10.txt"));
		b.write("idUsuari, idRestaurant");
		b.newLine();
		for(RelUsrRest r : resultat) {
			b.write(String.valueOf(r.getIdUsuari())+","+String.valueOf(r.getIdRestaurant()));
			b.newLine();
		}
		b.close();
		
		System.out.println("El fitxer usuarisQueHanVotat10.txt s'ha creat correctament.");
	}
	
	/**
	 * Mètode que permet escriure el resultat de la execució del estudi: relacioUsuariPuntuacioMesBaixaRestaurant
	 * @param resultat llistat solució
	 * @throws Exception Error Entrada/Sortida
	 */
	private static void escriureResultat_relacioUsuariPuntuacioMesBaixaRestaurant(List<RelUsrRest> resultat) throws Exception{
		BufferedWriter b = new BufferedWriter(new FileWriter("usuarisQueHanVotatMesBaix.txt"));
		b.write("idUsuari, idRestaurant");
		b.newLine();
		for(RelUsrRest r : resultat) {
			b.write(String.valueOf(r.getIdUsuari())+","+String.valueOf(r.getIdRestaurant()));
			b.newLine();
		}
		b.close();
		
		System.out.println("El fitxer usuarisQueHanVotatMesBaix.txt s'ha creat correctament.");
	}
	
	public static void main (String[] args) {
		
		Connection connexio=null;
		Statement stmt=null;
		connexio = connexioDB();
		System.out.println("Vols fer les inicialitzacions de la base de dades? (crear taules i generar fitxers auxiliars que permetin inserir informació en la bd) [S/n]");
		String op=teclat.nextLine();
		while(!op.toLowerCase().equals("s") && !op.toLowerCase().equals("n")) {
			System.out.println("Siusplau, posa una opció correcta.[S/n]");
			op=teclat.nextLine();
		}
		
		
		if(op.toLowerCase().equals("s"))
		{
			creacioTaules(stmt, connexio);
			guardarDadesADB("dataset.csv", connexio);
		}
		
		System.out.println("ESTUDI:");
		try {
			stmt = connexio.createStatement();
			Estudi estudi = new Estudi(stmt);
			
			System.out.println("Nombre de restaurants: "+estudi.comptarRestaurants()+" restaurants."); //100
			
			System.out.println("Nombre d'usuaris: "+estudi.comptarUsuaris()+" usuaris.");//73421
			
			System.out.println("Nombre de puntuacions buides(99.00): "+estudi.comptarNoVotats()+" restaurants no votats.");//3245740
			
			System.out.println("Nombre de puntuacions no buides: "+estudi.comptarVotats()+" restaurants votats.");//4096360
			
			System.out.println("Nombre de vots negatius [-10,-0.01]: "+estudi.comptarVotsNegatius()+" vots negatius.");//16889608
			
			System.out.println("Nombre de vots neutres (0.00): "+estudi.comptarVotsZero()+" vots zero."); //12482
			
			System.out.println("Nombre de vots positius (0,10]: "+estudi.comptarVotsPositius()+" vots positius."); //2394970
			
			System.out.println(estudi.usuariAmbMesRestaurantsVisitats()+" usuari amb mes restaurants visitats."); //L'usuari 9 ha visitat: 100 vegades.
			
			System.out.println(estudi.usuariQueVotaPitjor()+" usuari que ha avaluat pitjor."); //34628 usuari que ha avaluat pitjor.
			
			System.out.println(estudi.percentatgeRestaurantsAprobats()); //70%
			
			System.out.println(estudi.percentatgeRestaurantsSuspesos()); //30%
			
			System.out.println(estudi.mitjanaTotesPuntuacions()+" de mitjana de totes les puntuacions."); // 0.741872
			
			System.out.println(estudi.usuariQueVotaMillor()+" usuari que ha votat millor."); //36945 usuari que ha votat millor.
			
			System.out.println(estudi.puntuacioMesAlta()+" puntuacio mes alta de totes. "); //10 mes alta
			
			System.out.println(estudi.puntuacioMesBaixa()+" puntuacio mes baixa de totes ."); // -9.95 mes baixa
			
			escriureResultat_mitjanaPuntuacioCadaRestaurant(estudi.mitjanaPuntuacioCadaRestaurant());
			
			escriureResultat_desviacioAbsolutaPuntuacioRestaurants(estudi.desviacioAbsolutaPuntuacioRestaurants());
			
			escriureResultat_desviacioEstandardMostralPerRestaurants(estudi.desviacioEstandardMostralPerRestaurants());
			
			escriureResultat_desviacioEstandardPoblacionalPerRestaurants(estudi.desviacioEstandardPoblacionalPerRestaurants());
			
			escriureResultat_desviacioMitjanaPerRestaurants(estudi.desviacioMitjanaPerRestaurants());
			
			escriureResultat_histogramaXintervalsRestaurants(estudi.histogramaXintervalsRestaurants());
			
			escriureResultat_numeroDeVisitesXRestaurant(estudi.numeroDeVisitesXRestaurant());
			
			escriureResultat_numeroDeRestaurantsVisitats(estudi.numeroDeRestaurantsVisitats());
			
			escriureResultat_mitjanaPuntuacioCadaUsuari(estudi.mitjanaPuntuacioCadaUsuari());
			
			escriureResultat_probabilitatDeCadaPuntuacio(estudi.probabilitatDeCadaPuntuacio());
			
			escriureResultat_relacioUsuariPuntuacio10Restaurant(estudi.relacioUsuariPuntuacio10Restaurant());
			
			escriureResultat_relacioUsuariPuntuacioMesBaixaRestaurant(estudi.relacioUsuariPuntuacioMesBaixaRestaurant());
		}catch(Exception e) {
			System.out.println(e);
		}
	}

}
