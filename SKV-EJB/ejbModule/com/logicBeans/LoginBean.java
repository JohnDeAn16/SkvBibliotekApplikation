package com.logicBeans;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.exceptions.ErrorCode;
import com.exceptions.LibException;

import entities.Anvandare;

/**
 * Session Bean implementation class LoginBean
 */
@Stateless
@Named
public class LoginBean implements LoginBeanRemote 
{

	PersistenceBeanRemote pBean;
	
	@Inject
    public LoginBean() 
    {
    	try
		{
			pBean = (PersistenceBeanRemote)getInitialContext().lookup("java:global/SKVApp/SKV-EJB/PersistenceBean!com.logicBeans.PersistenceBean");
		}
		catch (NamingException e)
		{
			e.printStackTrace();
		}
    }
    
    private Context getInitialContext()
    {
    	InitialContext ctx = null;
    	try
		{
			ctx = new InitialContext();
		}
		catch (NamingException e)
		{
			e.printStackTrace();
		}
    	return ctx;
    }

	@Override
	public Anvandare createUser(String email, String pass) throws LibException
	{
		Anvandare a = new Anvandare();
		
		if(pBean.getAnvandareByMail(email) == null)
		{
			a.setEmail(email);
			a.setPass(pass);
			
			pBean.persistAnvandare(a);
		}
		else
		{
			throw new LibException(ErrorCode.LOGIN_ERROR, "Anvandaren finns redan");
		}
		return login(email, pass);
		
	}

	@Override
	public Anvandare login(String email, String pass) throws LibException
	{
		//Hyper secure login solution
		Anvandare a = pBean.getAnvandareByLogin(email, pass);
		if(a == null)
		{
			throw new LibException(ErrorCode.LOGIN_ERROR, "Felaktigt anvandarnamn eller losen");
		}
		return a;
	}

}
