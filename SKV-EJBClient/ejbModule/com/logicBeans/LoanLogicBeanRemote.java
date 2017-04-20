package com.logicBeans;

import javax.ejb.Remote;

import com.exceptions.LibException;

import entities.Anvandare;
import entities.Lan;
import entities.Materiel;
import entities.Reservation;

@Remote
public interface LoanLogicBeanRemote 
{
	 /**
	 * Tar användare, materiel, och en frivillig String innehållande inlämningsdatum
	 * och returnerar ett Lån. 
	 * 
	 * Om strängen specificerar ett datum kollar funktionen att det är en giltig lånelängd och att meterialet är tillgängligt
	 * under den tiden, om strängen är tom genereras ett inlämningsdatum från materialets default lånelängd.
	 */
	public Lan makeLoan(Anvandare a, Materiel m, String to) throws LibException;
	 /**
	 * Tar en reservation och returnerar ett lån.
	 */
	public Lan makeLoanFromReservation(Reservation r);
	/**
	 * Tar bort referenser till lån från användare och materiel så att lånet kan tas bort
	 */
	public Lan returnLoan(Lan l);
}
