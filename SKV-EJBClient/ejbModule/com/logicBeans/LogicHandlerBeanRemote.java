package com.logicBeans;

import javax.ejb.Remote;

import com.exceptions.LibException;

import entities.Anvandare;
import entities.Lan;
import entities.Materiel;
import entities.Reservation;

@Remote
public interface LogicHandlerBeanRemote 
{
	/*
	 * Tar emot data och skickar det till reservation/l�n klass som bygger ett objekt av den typen.
	 * Skickar sedan detta objekt till persistens b�nan som sparar det i databasen.
	 * Returnerar sedan en uppdaterad version av anv�ndaren.
	 */
	public Anvandare makeLoan(Anvandare a, Materiel m, String to) throws LibException;
	public Anvandare makeReservation(Anvandare a, Materiel m, String from, String to) throws LibException;
	public Anvandare makeLoanFromRes(Reservation r);
	public void returnLoan(Lan l);
}
