package api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Auto;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("search")
public class servicios {

	@GET
	@Path("autos/{pasajeros}/{transmision}/{precioMax}/{valijas}")
	@Produces({MediaType.APPLICATION_JSON})
	public List<Auto> autos(@PathParam("pasajeros")int p, @PathParam("transmision")int t, @PathParam("precioMax")int pr, @PathParam("valijas")int v) {
		List<Auto> listaAutos = new ArrayList<>();
		String SSQL = "SELECT * FROM vehiculos INNER JOIN empresas ON (vehiculos.idempresa = empresas.id) WHERE cantpasajeros >= " + p + " AND (cantvalijas >= " + v + " OR cantvalijas = -1)";
		if (t>-1 && t<2) {
			SSQL = SSQL + " AND transmision = ";
			if (t==0) {
				SSQL = SSQL + "true";
			} else {
				SSQL = SSQL + "false";
			}
		}
		if (pr>0) {
			SSQL = SSQL + " AND preciobajo <= " + pr;
		}
		SSQL = SSQL + ";";
		System.out.println(SSQL);
		String salida = "";
		try {
			try {
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("HOLAAA");
			Connection myConn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/webir", "postgres", "admin");
			Statement st = myConn.createStatement();
			ResultSet result = st.executeQuery(SSQL);
			while (result.next())
			{
				listaAutos.add(new Auto(result.getInt(1), result.getString(2), result.getString(3), result.getString(4), result.getInt(5), result.getInt(6), result.getBoolean(7), result.getInt(8), result.getBoolean(9), result.getInt(10), result.getInt(11), result.getInt(12),	result.getInt(13), result.getString(15), result.getString(16)));
			}
			result.close();
			st.close();
			myConn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return listaAutos;
	}
}
