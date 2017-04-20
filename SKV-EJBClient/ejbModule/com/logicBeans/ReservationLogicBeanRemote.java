package com.logicBeans;

import javax.ejb.Remote;

import com.exceptions.LibException;

import entities.Anvandare;
import entities.Materiel;
import entities.Reservation;

@Remote
public interface ReservationLogicBeanRemote 
{
	/**
	 * Liknande funktion som att skapa ett l�n, fast tar �ven ett utl�mningsdatum
	 * Inl�mningsdatum �r fortfarande valfritt
	 */
	public Reservation makeReservation(Anvandare a, Materiel m, String from, String to) throws LibException;
}
