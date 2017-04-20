package com.logicBeans;

import javax.ejb.Remote;

import com.exceptions.LibException;
import com.messageBeans.MessageContainerBeanRemote;

import entities.Anvandare;

@Remote
public interface MessageHandlerBeanRemote 
{
	/*
	 * Tar emot ett meddelande och delegerar sedan till r�tt funktion baserat p� en int i meddelandet.
	 * Meddelandet inneh�ller f�r n�rvarande allt som kan beh�vas f�r alla funktioner, men tanken var att kanske bryta ner
	 * det med arv
	 */
	public Anvandare handleMessage(MessageContainerBeanRemote mcb) throws LibException;
}
