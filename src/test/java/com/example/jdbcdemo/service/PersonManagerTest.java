
//package com.example.jdbcdemo.service;
//
//import static org.junit.Assert.*;
//
//import java.sql.SQLException;
//import java.util.List;
//
//import org.junit.Test;
//
//import com.example.jdbcdemo.domain.Prawnik2;
//
//public class PersonManagerTest {
//	
//	
//	PrawnikManager2 prawnikManager = new PrawnikManager2();
//	
//	
//	private final static String IMIE_1 = "Zygmunt";
//	private final static String IMIE_2 = "Stefan";
//	private final static String NAZWISKO_1 = "Tak";
//	private final static int WIEK_1 = 34;
//	private final static String HOBBY_1 = "Picie";
//	
//	@Test
//	public void checkConnection(){
//		assertNotNull(prawnikManager.getConnection());
//	}
//	
//	@Test
//	public void checkAdding(){
//		
//		Prawnik2 prawnik = new Prawnik2(IMIE_1, NAZWISKO_1, WIEK_1, HOBBY_1);
//		
//		prawnikManager.clearPrawnik();
//		assertEquals(1,prawnikManager.addPrawnik(prawnik));
//		
//		List<Prawnik2> persons = prawnikManager.getAllPrawnik();
//		Prawnik2 personRetrieved = persons.get(0);
//		
//		assertEquals(IMIE_1, personRetrieved.getImie());
//		assertEquals(NAZWISKO_1, personRetrieved.getNazwisko());
//		assertEquals(WIEK_1, personRetrieved.getWiek());
//		assertEquals(HOBBY_1, personRetrieved.getHobby());
//		
//	}
//	@Test
//	public void sprawdzedit()
//	{
//		
//		
//		prawnikManager.clearPrawnik();
//		Prawnik2 prawnik = new Prawnik2(IMIE_1, NAZWISKO_1, WIEK_1, HOBBY_1);
//		assertEquals(1,prawnikManager.addPrawnik(prawnik));
//		
//		List<Prawnik2> praw = prawnikManager.getAllPrawnik();
//		Prawnik2 pra = praw.get(0);
//		
//		assertEquals(1, prawnikManager.editPrawnik(IMIE_2,pra));
//		
//		List<Prawnik2> prawn = prawnikManager.getAllPrawnik();
//		Prawnik2 pr = prawn.get(0);
//		
//		assertEquals(IMIE_2,pr.getImie());
//		
//	}
//	
//	@Test
//	public void sprawdzUsuwanie() throws SQLException
//	{
//		
//		Prawnik2 prawnik = new Prawnik2(IMIE_1, NAZWISKO_1, WIEK_1, HOBBY_1);
//		
//		prawnikManager.clearPrawnik();
//		assertEquals(1,prawnikManager.addPrawnik(prawnik));
//	
//		List<Prawnik2> praw = prawnikManager.getAllPrawnik();
//		Prawnik2 pra = praw.get(0);
//		
//		assertEquals(1,prawnikManager.deletePrawnik(pra));
//		
//		assertEquals(0,prawnikManager.pobierzImie(pra.getWiek()).size());
//		
//		
//		
//	}
//	
//	@Test
//	public void sprawdzUsuwawniewszystkiego() 
//	{	
//		prawnikManager.clearPrawnik();
//		Prawnik2 prawnik = new Prawnik2(IMIE_1, NAZWISKO_1, WIEK_1, HOBBY_1);
//		assertEquals(1,prawnikManager.addPrawnik(prawnik));
//		int liczba = prawnikManager.clearPrawnik();
//		assertEquals(1, liczba);
//	}
//	@Test
//	public void sprawdzSelect()
//	{	
//		prawnikManager.clearPrawnik();
//		Prawnik2 prawnik = new Prawnik2(IMIE_1, NAZWISKO_1, WIEK_1, HOBBY_1);
//		assertEquals(1,prawnikManager.addPrawnik(prawnik));
//		List<Prawnik2> praw = prawnikManager.pobierzImie(WIEK_1);
//		Prawnik2 pra = praw.get(0);
//	
//		assertEquals(IMIE_1,pra.getImie());
//		assertEquals(HOBBY_1,pra.getHobby());
//		
//		
//	}
//
//}

