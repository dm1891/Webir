import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.jaunt.Element;
import com.jaunt.Elements;
import com.jaunt.JauntException;
import com.jaunt.UserAgent;

public class prueba2 {
	static String multicar = "http://www.multicar.com.uy/";
	
	public static void main(String[] args) {
		
		//Rutas relativas dentro de multicar
		String[] urls = {"economicos",
				"medianos",
				"grandes",
				"pick-ups",
				"carga",
				"automaticos"};
		
		try {
			//Creo conexión
			Connection myConn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/webir", "postgres", "admin");
			
			//Para cada pagina de multicar
			for (int i=0;i<6;i++){
				extraerMulticar(urls[i],myConn);	
			}
			//Cierro conexión
			myConn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void extraerMulticar(String url, Connection conn) {
		try{
			  int pasajeros = 0;
			  String valijas = null;
			  String transmision = null;
			  int puertas = 0;
			  boolean aire = false;
			  int cilindrada = 0;
			  int economico = 0;
			  int caro = 0;
			
			  UserAgent userAgent = new UserAgent();                       //create new userAgent (headless browser).
			  userAgent.visit(multicar+url);                        //visit a url  
			  Elements tables = userAgent.doc.findEach("<article>");
			  int articulo = 1;
			  for(Element table : tables){                               //iterate through Results
				   String titulo = table.findFirst("<h2>").getText();
				   String urlimg = table.findFirst("<img>").getAt("src");
				   Element lis = table.findFirst("<ul class=\"ficha-tecnica list-inline\">");
				   if(articulo==3 && url.contains("economicos")) {
					   lis = lis.findFirst("<ul class=\"ficha-tecnica list-inline\">");
				   }
				   articulo++;
				   int i= 1;
				   for(Element li : lis.getChildElements()) {
					   switch(i) {
					   case 1: pasajeros = extraerPrimerEntero(li.getText());
					   		break;
					   case 2: valijas = li.getText();
					   		break;
					   case 3: transmision = li.getText();
					   		break;
					   case 4: puertas = extraerPrimerEntero(li.getText());
					   		break;
					   case 5: aire = getBoolean(li.getText());
					   		break;
					   case 6: cilindrada = extraerPrimerEntero(li.getText());
					   		break;
					   }
					   i++;
				   }
				   
				   Element tbody = table.findFirst("<tbody>");
				   int z= 1;
				   for(Element lista : tbody.getChildElements()) {
						   
					   switch(z) {
						   case 1:
							   int j= 0;
							   for(Element td : lista.getChildElements()) {
								   if (j == 6) {
									   economico = calcularValor(td.getText());
									   System.out.println("Precio bajo: "+economico);
								   }
								   j++;
							   }
							   break;
						   case 2:
							   int k= 0;
							   for(Element td : lista.getChildElements()) {
								   if (k == 2) {
									   caro = extraerSegundoEntero(td.getText());
									   System.out.println("Precio alto: "+caro);
								   }
								   k++;
							   }
							   break;
						   case 3:break;
						  }
					   z++;
				   }
				   
				   //GUARDO EN BD
				   guardarBD(conn,titulo,url,urlimg,pasajeros,valijas,transmision,puertas,aire,cilindrada,economico,caro,1);
				   
				  }
			}
			catch(JauntException e){         //if an HTTP/connection error occurs, handle JauntException.
			  System.err.println(e);
			}
	}
	
	public static void guardarBD(Connection conn,String titulo,String categoria,String urlimg, int cantpasajeros,String cantvalijas,String transmision,int cantpuertas,boolean aire,int cilindrada,int preciobajo,int precioalto,int idempresa){
		String SSQL 	= "insert into vehiculos (titulo,categoria,urlimg,cantpasajeros,cantvalijas,transmision,cantpuertas,aire,cilindrada,preciobajo,precioalto,idempresa)"+
				  "values (?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement psql = conn.prepareStatement(SSQL);
			psql.setString(1, titulo);
		    psql.setString(2, categoria);
		    psql.setString(3, urlimg);
		    psql.setInt(4, cantpasajeros);
		    psql.setString(5, cantvalijas);
		    psql.setString(6, transmision);
		    psql.setInt(7, cantpuertas);
		    psql.setBoolean(8, aire);
		    psql.setInt(9, cilindrada);
		    psql.setInt(10, preciobajo);
		    psql.setInt(11, precioalto);
		    psql.setInt(12, 1);
		   
		   psql.executeUpdate();
		   System.out.println("Insert complete.");
		   psql.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static int extraerSegundoEntero(String n){
		String m = n.replace("\u00A0"," ");
		String[] parts = m.split(" ");
		int valor = Integer.parseInt(parts[1]);
		return valor;
	}
	
	public static int calcularValor(String n){
		int valor = (int) Math.ceil((double)extraerSegundoEntero(n)/7);
		return valor;
	}
	
	public static int extraerPrimerEntero(String n){
		String m = n.replace("\u00A0"," ");
		String[] parts = m.split(" ");
		int valor = Integer.parseInt(parts[0]);
		return valor;
	}

	public static boolean getBoolean(String es){
		if (es.equals("Si")||es.equals("Sí")){
			return true;
		}	
		else{
			return false;
		}
	}
}
