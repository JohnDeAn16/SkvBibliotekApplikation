package com.logicBeans;

import javax.ejb.Remote;

import com.exceptions.LibException;
import com.messageBeans.MessageContainerBeanRemote;

import entities.Anvandare;

@Remote
public interface MessageHandlerBeanRemote 
{
	/*
	 * Tar emot ett meddelande och delegerar sedan till rätt funktion baserat på en int i meddelandet.
	 * Meddelandet innehåller för närvarande allt som kan behövas för alla funktioner, men tanken var att kanske bryta ner
	 * det med arv
	 */
	public Anvandare handleMessage(MessageContainerBeanRemote mcb) throws LibException;
}
