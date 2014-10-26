package com.example.jdbcdemo.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.jdbcdemo.domain.Prawnik2;

public class PrawnikManager2 {

	private Connection connection;

	private String url = "jdbc:hsqldb:hsql://localhost/workdb";

	private String createTablePrawnik = "CREATE TABLE Prawnik(id bigint GENERATED BY DEFAULT AS IDENTITY, imie varchar(20), nazwisko varchar(30) , wiek integer, hobby varchar(50) )";

	private PreparedStatement addPrawnikStmt;
	private PreparedStatement deleteAllPrawnikStmt;
	private PreparedStatement getAllPrawnikStmt;
	private PreparedStatement deleteOnePrawnikStmt;
	private PreparedStatement editPrawnikStmt;
	private PreparedStatement selectPrawnikStmt;
	
	private Statement statement;

	public PrawnikManager2() {
		try {
			connection = DriverManager.getConnection(url);
			statement = connection.createStatement();

			ResultSet rs = connection.getMetaData().getTables(null, null, null,
					null);
			boolean tableExists = false;
			while (rs.next()) {
				if ("Prawnik".equalsIgnoreCase(rs.getString("TABLE_NAME"))) {
					tableExists = true;
					break;
				}
			}

			if (!tableExists)
				statement.executeUpdate(createTablePrawnik);

			addPrawnikStmt = connection
					.prepareStatement("INSERT INTO Prawnik (imie, nazwisko , wiek , hobby) VALUES (?, ?, ?, ?)");
			deleteAllPrawnikStmt = connection
					.prepareStatement("DELETE FROM Prawnik");
			getAllPrawnikStmt = connection
					.prepareStatement("SELECT id, imie, nazwisko , wiek,hobby FROM Prawnik");
			deleteOnePrawnikStmt = connection
					.prepareStatement("DELETE FROM Prawnik where id= ?");
			editPrawnikStmt = connection
					.prepareStatement("UPDATE Prawnik set imie=? where id=? ");
			selectPrawnikStmt = connection
					.prepareStatement("SELECT imie,hobby FROM Prawnik where wiek=? ");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	Connection getConnection() {
		return connection;
	}

	public int clearPrawnik() {
		int liczba =0;
		try {
		 liczba =	deleteAllPrawnikStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return liczba;
	}
	
	public int deletePrawnik(Prawnik2 prawnik)
	{
		int count = 0;
		try {
			deleteOnePrawnikStmt.setLong(1, prawnik.getId());
			
			count = deleteOnePrawnikStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}
	
	public int editPrawnik(String imie,Prawnik2 prawnik)
	{
		int count = 0;
		try {
			editPrawnikStmt.setString(1, imie);
			editPrawnikStmt.setLong(2, prawnik.getId());
			count = editPrawnikStmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return count;
		
	}
	
	
	
	public int addPrawnik(Prawnik2 prawnik) {
		int count = 0;
		try {
			
			addPrawnikStmt.setString(1, prawnik.getImie());
			addPrawnikStmt.setString(2, prawnik.getNazwisko());
			addPrawnikStmt.setInt(3, prawnik.getWiek());
			addPrawnikStmt.setString(4, prawnik.getHobby());
			count = addPrawnikStmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	public List<Prawnik2> getAllPrawnik() {
		List<Prawnik2> prawnik = new ArrayList<Prawnik2>();

		try {
			ResultSet rs = getAllPrawnikStmt.executeQuery();

			while (rs.next()) {
				Prawnik2 p = new Prawnik2();
				p.setId(rs.getInt("id"));
				p.setImie(rs.getString("imie"));
				p.setNazwisko(rs.getString("nazwisko"));
				p.setWiek(rs.getInt("wiek"));
				p.setHobby(rs.getString("hobby"));
				prawnik.add(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prawnik;
	}
	
	public List<Prawnik2> pobierzImie(int wiek)
	{
		List<Prawnik2> prawnik = new ArrayList<Prawnik2>();

		try {
			selectPrawnikStmt.setInt(1,wiek);
			ResultSet rs = selectPrawnikStmt.executeQuery();
			
			while (rs.next()) {
				Prawnik2 p = new Prawnik2();
				p.setImie(rs.getString("imie"));
				p.setHobby(rs.getString("hobby"));
				prawnik.add(p);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return prawnik;
	}
	
	
	
	

}
