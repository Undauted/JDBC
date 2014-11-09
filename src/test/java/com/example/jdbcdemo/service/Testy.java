package com.example.jdbcdemo.service;

import static org.junit.Assert.*;


import java.util.List;

import org.junit.Test;

import com.example.jdbcdemo.domain.Adres;
import com.example.jdbcdemo.domain.Prawnik;
import com.example.jdbcdemo.service.AdresManager;
import com.example.jdbcdemo.service.PrawnikManager;

public class Testy {
	
	AdresManager adresManager=new AdresManager();
	PrawnikManager prawnikManager = new PrawnikManager();
	
	
	private final static String IMIE_1 = "Zygmunt";
	//private final static String IMIE_2 = "Stefan";
	private final static String NAZWISKO_1 = "Tak";
	private final static int WIEK_1 = 34;
	private final static int NUMER = 34;
	private final static String ULICA = "Grunwaldzka";
	private final static String MIEJSCOWOSC = "Gdańsk";
	//private final static String ULICA2 = "Kołobrzeska";
	
//---------------------------Sprawdzenie połączenia prawników---------------------------------------------------------	
	@Test
	public void checkConnectionPrawnik(){
		assertNotNull(prawnikManager.getConnection());
	}
//---------------------------Sprawdzenie połączenia adresów---------------------------------------------------------	
	@Test
	public void checkConnectionAdres(){
		assertNotNull(adresManager.getConnection());
	}
//---------------------------Dodanie adresów---------------------------------------------------------
	@Test
	public void sprawdzDodawanieAdresow(){
		
		prawnikManager.usunWszystkich();
		adresManager.usunWszystkich();
		
		Adres adres = new Adres(MIEJSCOWOSC,ULICA,NUMER);
		
		assertEquals(1,adresManager.zapisz(adres));
		
		List<Adres> adresy = adresManager.pobierzWszystkie();
		Adres adresyretrived = adresy.get(0);
		
		assertEquals(MIEJSCOWOSC, adresyretrived.getMiejscowosc());
		assertEquals(ULICA, adresyretrived.getUlica());
		assertEquals(NUMER, adresyretrived.getNr());
		}
//---------------------------Dodanie prawników---------------------------------------------------------	
	@Test
	public void sprawdzDodawaniePrawnik(){

		prawnikManager.usunWszystkich();
		adresManager.usunWszystkich();
		
		Adres adres = new Adres(MIEJSCOWOSC,ULICA,NUMER);
		
		assertEquals(1,adresManager.zapisz(adres));
		
		List<Adres> adresy = adresManager.pobierzWszystkie();
		Adres adre = adresy.get(0);
		
		Prawnik prawnik = new Prawnik(IMIE_1,NAZWISKO_1,WIEK_1);
		prawnik.setAdres(adre);
		
		assertEquals(1,prawnikManager.zapisz(prawnik));
		
		List<Prawnik> prawnicy = prawnikManager.pobierzWszystkich();
		Prawnik pra = prawnicy.get(0);
		
		assertEquals(IMIE_1, pra.getImie());
		assertEquals(NAZWISKO_1, pra.getNazwisko());
		assertEquals(WIEK_1, pra.getWiek());
		
		
		}
//---------------------------Select prawnika po adresach---------------------------------------------------------	
	@Test
	public void sprawdzSelect()
	{	
		prawnikManager.usunWszystkich();
		adresManager.usunWszystkich();
		
		//Dodanie adresu i prawnika do bazy
		Adres adres = new Adres(MIEJSCOWOSC,ULICA,NUMER);
		
		assertEquals(1,adresManager.zapisz(adres));
		
		List<Adres> adresy = adresManager.pobierzWszystkie();
		Adres adre = adresy.get(0);
		
		Prawnik prawnik = new Prawnik(IMIE_1,NAZWISKO_1,WIEK_1);
		prawnik.setAdres(adre);
		
		
		assertEquals(1,prawnikManager.zapisz(prawnik));
		//---------------------------------------------------
		
		List<Prawnik> prawnicy = adresManager.pobierzWszystkichAdresy(adre);
		Prawnik pra = prawnicy.get(0);
		
		assertEquals(IMIE_1,pra.getImie());
		assertEquals(NAZWISKO_1,pra.getNazwisko());
		assertEquals(WIEK_1,pra.getWiek());
		assertEquals(adre.getId(),pra.getAdres().getId());
		
	}
//---------------------------Pobranie selectem adresów---------------------------------------------------------	
	@Test
	public void sprawdzSelectWszystkiechAdresów()
	{
		prawnikManager.usunWszystkich();
		adresManager.usunWszystkich();
		
		Adres adres = new Adres(MIEJSCOWOSC,ULICA,NUMER);
		assertEquals(1,adresManager.zapisz(adres));
		
		List<Adres> adresy = adresManager.pobierzWszystkie();
		Adres adr = adresy.get(0);
		
		assertNotNull(adr);
		assertEquals(adr.getMiejscowosc(),MIEJSCOWOSC);
		assertEquals(adr.getUlica(),ULICA);
		assertEquals(adr.getNr(),NUMER);
		
	}
//---------------------------Pobranie selectem prawników---------------------------------------------------------	
		@Test
		public void sprawdzSelectWszystkichPrawników()
		{
			prawnikManager.usunWszystkich();
			adresManager.usunWszystkich();
			
			Adres adres = new Adres(MIEJSCOWOSC,ULICA,NUMER);
			
			assertEquals(1,adresManager.zapisz(adres));
			
			List<Adres> adresy = adresManager.pobierzWszystkie();
			Adres adre = adresy.get(0);
			
			Prawnik prawnik = new Prawnik(IMIE_1,NAZWISKO_1,WIEK_1);
			prawnik.setAdres(adre);
			
			assertEquals(1,prawnikManager.zapisz(prawnik));
			
			List<Prawnik> praw = prawnikManager.pobierzWszystkich();
			Prawnik pra = praw.get(0);
			
			assertNotNull(pra);
			assertEquals(IMIE_1, pra.getImie());
			assertEquals(NAZWISKO_1, pra.getNazwisko());
			assertEquals(WIEK_1, pra.getWiek());
			assertEquals(adre.getId(), pra.getAdres().getId());
			
			
		}
//---------------------------Usuwanie pojedynczego rekordu---------------------------------------------------------
	@Test
	public void sprawdzUsuwanie() throws Exception
	{
		prawnikManager.usunWszystkich();
		adresManager.usunWszystkich();
		
		Adres adres = new Adres(MIEJSCOWOSC,ULICA,NUMER);
		
		
		assertEquals(1,adresManager.zapisz(adres));
		
		List<Adres> adresy = adresManager.pobierzWszystkie();
		Adres adre = adresy.get(0);
		
		Prawnik prawnik = new Prawnik(IMIE_1,NAZWISKO_1,WIEK_1);
		prawnik.setAdres(adre);
		
		
		assertEquals(1,prawnikManager.zapisz(prawnik));
	
		
		assertEquals(1,adresManager.usun(adre));
		
		assertEquals(0,adresManager.pobierzWszystkie().size());
		assertEquals(0,prawnikManager.pobierzWszystkich().size());
	
		
		
	}
//---------------------------Edycja Prawników---------------------------------------------------------	
	@Test
	public void sprawdzeditPrawnikow()
	{
		
		
		
		adresManager.usunWszystkich();
		Adres adres = new Adres(MIEJSCOWOSC,ULICA,NUMER);
		
		
		assertEquals(1,adresManager.zapisz(adres));
		
		List<Adres> adresy = adresManager.pobierzWszystkie();
		Adres adre = adresy.get(0);
		
		Prawnik prawnik = new Prawnik(IMIE_1,NAZWISKO_1,WIEK_1);
		prawnik.setAdres(adre);
		
		
		assertEquals(1,prawnikManager.zapisz(prawnik));
		
		List<Prawnik> praw = prawnikManager.pobierzWszystkich();
		Prawnik pra = praw.get(0);
		
		assertEquals(1, prawnikManager.editPrawnik(adre,pra));
		
		assertEquals(adre.getId(),pra.getAdres().getId());
		
	}

}
