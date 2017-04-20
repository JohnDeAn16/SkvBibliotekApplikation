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
	 * Tar anv�ndare, materiel, och en frivillig String inneh�llande inl�mningsdatum
	 * och returnerar ett L�n. 
	 * 
	 * Om str�ngen specificerar ett datum kollar funktionen att det �r en giltig l�nel�ngd och att meterialet �r tillg�ngligt
	 * under den tiden, om str�ngen �r tom genereras ett inl�mningsdatum fr�n materialets default l�nel�ngd.
	 */
	public Lan makeLoan(Anvandare a, Materiel m, String to) throws LibException;
	 /**
	 * Tar en reservation och returnerar ett l�n.
	 */
	public Lan makeLoanFromReservation(Reservation r);
	/**
	 * Tar bort referenser till l�n fr�n anv�ndare och materiel s� att l�net kan tas bort
	 */
	public Lan returnLoan(Lan l);
}
