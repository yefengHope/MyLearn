package org.noka.dbutil;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.engine.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.noka.constvar.ConstVar;

public class IDWorker implements IdentifierGenerator{

	@Override
	public Serializable generate(SessionImplementor sessiong, Object arg)throws HibernateException {
		NokaKeyWorker nwk = NokaKeyWorker.Init(ConstVar.SERVER_MAIN_ID,ConstVar.SERVER_SUB_ID);
		return nwk.nextId();
	}

	public static void main(String[] args){
		NokaKeyWorker nwk = NokaKeyWorker.Init(1,2);
		System.out.println(nwk.nextId());
		
		NokaKeyWorker nwk2 = NokaKeyWorker.Init(1,2);
		System.out.println(nwk2.nextId());
		
	}
}
