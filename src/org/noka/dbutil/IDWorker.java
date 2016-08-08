package org.noka.dbutil;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

public class IDWorker implements IdentifierGenerator{

	@Override
	public Serializable generate(SessionImplementor sessiong, Object arg)throws HibernateException {
		NokaKeyWorker nwk = new NokaKeyWorker(1,2);
		return nwk.nextId();
	}

	public static void main(String[] args){
		NokaKeyWorker nwk = new NokaKeyWorker(1,2);
		System.out.println(nwk.nextId());
		
	}
}
