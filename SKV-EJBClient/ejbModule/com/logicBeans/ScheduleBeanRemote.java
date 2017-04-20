package com.logicBeans;

import java.sql.Date;

import javax.ejb.Remote;

import entities.Materiel;

@Remote
public interface ScheduleBeanRemote 
{
	/*
	 * Denna klass hanterar schemaläggning av lån och reservationer
	 * 
	 * Alla datum som returneras är java.sql.Date objekt, men jag insåg senare att entiterna som tilldelas dessa datum
	 * innehåller java.util.Date istället. Trots det så fungerade det när jag testade så jag lät det vara "tillsvidare".
	 */
	
	
	public boolean checkIfAvailable(Materiel m, Date from, Date to);
	public Date getDate();
	
	/**
	 * Skapar ett inlämningsdatum i det fall att användaren inte valt ett själv
	 */
	public Date makeHandinDate(Materiel m, Date from);
	/**
	 * Kollar att en låneperiod inte överskrider maxlängd
	 */
	public boolean lengthIsValid(Materiel m, Date from, Date to);
	public Date stringToDate(String s);
	
}
