package com.logicBeans;

import java.sql.Date;

import javax.ejb.Remote;

import entities.Materiel;

@Remote
public interface ScheduleBeanRemote 
{
	/*
	 * Denna klass hanterar schemal�ggning av l�n och reservationer
	 * 
	 * Alla datum som returneras �r java.sql.Date objekt, men jag ins�g senare att entiterna som tilldelas dessa datum
	 * inneh�ller java.util.Date ist�llet. Trots det s� fungerade det n�r jag testade s� jag l�t det vara "tillsvidare".
	 */
	
	
	public boolean checkIfAvailable(Materiel m, Date from, Date to);
	public Date getDate();
	
	/**
	 * Skapar ett inl�mningsdatum i det fall att anv�ndaren inte valt ett sj�lv
	 */
	public Date makeHandinDate(Materiel m, Date from);
	/**
	 * Kollar att en l�neperiod inte �verskrider maxl�ngd
	 */
	public boolean lengthIsValid(Materiel m, Date from, Date to);
	public Date stringToDate(String s);
	
}
