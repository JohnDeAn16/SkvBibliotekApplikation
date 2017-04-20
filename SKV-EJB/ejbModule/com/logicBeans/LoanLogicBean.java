package com.logicBeans;

import java.sql.Date;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import com.exceptions.ErrorCode;
import com.exceptions.LibException;

import entities.Anvandare;
import entities.Lan;
import entities.Materiel;
import entities.Reservation;

/**
 * Session Bean implementation class LoanLogicBean
 */
@Stateless
@LocalBean
public class LoanLogicBean extends LoanSuperBean implements LoanLogicBeanRemote 
{
       
    /**
     * @see LoanSuperBean#TransactionLogicBean()
     */
    public LoanLogicBean() 
    {
        super();
    }
    

    /**
     * @see com.logicBeans.LoanLogicBeanRemote#makeLoan(entities.Anvandare, entities.Materiel, java.lang.String)
     */
    public Lan makeLoan(Anvandare a, Materiel m, String to) throws LibException
    {
    	Lan l = new Lan();
    	if(m.isArTillganglig())
    	{
        	Date out = sBean.getDate();	
    		Date in = this.getHandinDate(m, out, to);
    	
	    	if(sBean.checkIfAvailable(m, out, in) && sBean.lengthIsValid(m, out, in))
	    	{
	    		m.setArTillganglig(false);
	        	l.setAnv(a);
	        	l.setMat(m);
	        	l.setUtlaningsDatum(out);
	        	l.setInlamningsDatum(in);	    		
	    	}
	    	else
	    	{
	    		throw new LibException(ErrorCode.LOAN_ERROR, "Materiel ar ej tillganglig under hela perioden eller kan ej utlanas sa lange");
	    	}
    	}
    	else
    	{
    		throw new LibException(ErrorCode.LOAN_ERROR, "Materiel redan utlanad");
    	}
    	return l;
    }
    
    public Lan makeLoanFromReservation(Reservation r)
    {
    	Lan l = null;
    	
    	//TODO Vad har hänt här..
    	Date d = new Date(r.getReserveradFran().getTime());
    	if(sBean.getDate().getTime() - d.getTime() <= 0)
    	{
    		l = new Lan();
        	l.setAnv(r.getAnvandare());
        	l.setMat(r.getMateriel());
        	l.setUtlaningsDatum(sBean.getDate());
        	l.setInlamningsDatum(r.getReserveradTill());
    	}

    	return l;
    }
    
    public Lan returnLoan(Lan l)
    {
    	l.getAnv().getLan().remove(l);
    	l.getMat().setArTillganglig(true);
    	l.getMat().setLan(null);
    	return l;
    }
}
