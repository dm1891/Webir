import java.util.List;

import com.jaunt.Element;
import com.jaunt.Elements;
import com.jaunt.JauntException;
import com.jaunt.UserAgent;

public class prueba2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		extraerMulticar("http://www.multicar.com.uy/automaticos/");

	}
	
	public static void extraerMulticar(String url) {
		try{
			  String pasajeros;
			  String valijas;
			  String transmision;
			  String puertas;
			  String aire;
			  String cilindrada;
			  String economico;
			  String caro;
			
			  UserAgent userAgent = new UserAgent();                       //create new userAgent (headless browser).
			  userAgent.visit(url);                        //visit a url  
			  Elements tables = userAgent.doc.findEach("<article>");
			  int articulo = 1;
			  for(Element table : tables){                               //iterate through Results
				   System.out.println("ARTICLEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
				   String titulo = table.findFirst("<h2>").getText();
				   System.out.println("Titulo: "+titulo);
				   String urlimg = table.findFirst("<img>").getAt("src");
				   System.out.println("URLimg: "+urlimg);
				   //System.out.println(table.innerHTML() + "\n----\n");
				   Element lis = table.findFirst("<ul class=\"ficha-tecnica list-inline\">");
				   if(articulo==3 && url.contains("economicos")) {
					   lis = lis.findFirst("<ul class=\"ficha-tecnica list-inline\">");
				   }
				   articulo++;
				   int i= 1;
				   for(Element li : lis.getChildElements()) {
					   switch(i) {
					   case 1: pasajeros = li.getText();
					   		System.out.println("Pasajeros: "+li.getText());
					   		break;
					   case 2: valijas = li.getText();
					   		System.out.println("Valijas: "+li.getText());
					   		break;
					   case 3: transmision = li.getText();
					   		System.out.println("Transmision: "+li.getText());
					   		break;
					   case 4: puertas = li.getText();
					   		System.out.println("Puertas: "+li.getText());
					   		break;
					   case 5: aire = li.getText();
					   		System.out.println("Aire: "+li.getText());
					   		break;
					   case 6: cilindrada = li.getText();
					   		System.out.println("Cilindrada: "+li.getText());
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
									   economico = td.getText();
									   System.out.println("Precio bajo: "+td.getText());
								   }
								   j++;
							   }
							   break;
						   case 2:
							   int k= 0;
							   for(Element td : lista.getChildElements()) {
								   if (k == 2) {
									   caro = td.getText();
									   System.out.println("Precio alto: "+td.getText());
								   }
								   k++;
							   }
							   break;
						   case 3:break;
						  }
					   z++;
				   }
				   
				   //System.out.println("ACAAAAA: "+table.findFirst("<h2>").getText());
				   /*String titulo = table.findFirst("<h2>").getText();
				   System.out.println("Titulo: "+titulo);
				   String urlimg = table.findFirst("<img>").getAt("src");
				   System.out.println("URLimg: "+urlimg);*/
				   
				    //System.out.println(listurlimg + "\n----\n");      //print each element and its contents
				  }   
			  //System.out.println(userAgent.doc.innerHTML());               //print the document as HTML
			}
			catch(JauntException e){         //if an HTTP/connection error occurs, handle JauntException.
			  System.err.println(e);
			}
	}

}
