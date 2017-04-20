package com.logicBeans;

import java.util.List;

import javax.ejb.Remote;

import entities.Anvandare;
import entities.Lan;
import entities.Materiel;
import entities.MaterielTyp;
import entities.Reservation;

@Remote
public interface PersistenceBeanRemote 
{
	/*
	 * Varje funktion relaterad till l�n och reservationer sparar ett nytt objekt
	 * av den typen till databasen och uppdaterar �ven ber�rda anv�ndare och materiel
	 * f�r att reflektera detta
	 * 
	 * Andra funktioner anv�nds endast f�r att l�gga till eller h�mta objekt
	 */
	public void persistLoan(Lan l);
	public void persistReservation(Reservation r);
	public void removeLoan(Lan l);
	public void removeReservation(Reservation r);
	public void persistLoanFromRes(Reservation r, Lan l);
	public void persistAnvandare(Anvandare a);
	public void persistMateriel(Materiel m);
	public void persistMaterielTyp(MaterielTyp t);
	public Anvandare getAnvandare(int id);
	public Anvandare getAnvandareByLogin(String mail, String pass);
	public Anvandare getAnvandareByMail(String mail);
	public List<Materiel> getMateriel();

}
