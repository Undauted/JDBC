package com.example.jdbcdemo.service;


import com.example.jdbcdemo.domain.Adres;
import com.example.jdbcdemo.domain.Prawnik;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PrawnikManager {

    public static final String URL = "jdbc:hsqldb:hsql://localhost/workdb";

    public static final String CREATE_Prawnik_TABLE = "CREATE TABLE Prawnik(id bigint GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY , " +
            "imie varchar(20), nazwisko varchar(20), wiek INT, adres_id BIGINT , " +
            "FOREIGN KEY (adres_id) REFERENCES adres(id) ON DELETE CASCADE)";

    public static final String ADD_prawnik = "INSERT INTO prawnik(imie, nazwisko, wiek, adres_id) VALUES(?, ?, ?, ?)";
    public static final String UPDATE_prawnik = "UPDATE Prawnik set imie=? where id=? ";
    public static final String DELETE_prawnik = "DELETE FROM prawnik WHERE id=?";
    public static final String GET_ALL = "SELECT prawnik.id, imie, nazwisko, wiek, adres_id, miejscowosc ,ulica,nr " +
            "FROM prawnik JOIN adres ON adres_id = adres.id";
    public static final String DELETE_ALL = "DELETE FROM prawnik";

    

    private Connection connection;

    public PrawnikManager() {
        try {
            connection = DriverManager.getConnection(URL);
            if(!checkIfTableExists()) {
                connection.createStatement().executeUpdate(CREATE_Prawnik_TABLE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private Boolean checkIfTableExists() {
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = connection.getMetaData().getTables(null, null, null,
                    null);

            while (rs.next()) {
                if ("Prawnik".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
                    return true;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;


    }
    
    public Connection getConnection() {
		return connection;
	}

    public int zapisz(Prawnik prawnik) {
    	int liczba =0;
        try {
            String stmt = "";
           
                stmt = ADD_prawnik;
            
            PreparedStatement dodajPrawnikow = connection.prepareStatement(stmt, Statement.RETURN_GENERATED_KEYS);
            dodajPrawnikow.setString(1, prawnik.getImie());
            dodajPrawnikow.setString(2, prawnik.getNazwisko());
            dodajPrawnikow.setInt(3, prawnik.getWiek());
            dodajPrawnikow.setLong(4, prawnik.getAdres().getId());
            liczba = dodajPrawnikow.executeUpdate();
            
            ResultSet generatedKeys = null;
            try {
                generatedKeys = dodajPrawnikow.getGeneratedKeys();
                if (generatedKeys.next()) {
                    prawnik.setId(generatedKeys.getLong(1));
                }
            } finally {
                if(generatedKeys != null) {
                    generatedKeys.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return liczba;
        }
        return liczba;
    }

    public int usun(Prawnik prawnik) throws Exception {
    	int count = 0;
       
        try {
            PreparedStatement usunStatement = connection.prepareStatement(DELETE_prawnik);
            usunStatement.setLong(1, prawnik.getId());
            count = usunStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    public int usunWszystkich() {
    	int count = 0;
        try {
         count =  connection.prepareStatement(DELETE_ALL).executeUpdate();
        } catch (SQLException e) {}
        return count;
    }

    public List<Prawnik> pobierzWszystkich() {
        try {
            ResultSet rs = connection.prepareStatement(GET_ALL).executeQuery();
            List<Prawnik> uczniowie = new ArrayList<Prawnik>();
            while (rs.next()) {
                Prawnik prawnik = new Prawnik(rs.getString("imie"), rs.getString("nazwisko"), rs.getInt("wiek"));
                prawnik.setId(rs.getLong("id"));
                Adres adres = new Adres(rs.getString("miejscowosc"), rs.getString("ulica") ,rs.getInt("nr"));
                adres.setId(rs.getLong("adres_id"));
                prawnik.setAdres(adres);
                uczniowie.add(prawnik);
            }
            return uczniowie;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public int editPrawnik(String imie,Prawnik prawnik)
	{
		int count = 0;
		try {
			PreparedStatement editPrawnikStmt = connection.prepareStatement(UPDATE_prawnik);
			editPrawnikStmt.setString(1, imie);
			editPrawnikStmt.setLong(2, prawnik.getId());
			count = editPrawnikStmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
		
	}

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
