package de.heckconsulting.tauchen.db;

import java.io.Serializable;

public abstract class AbstractDatabaseObject<K> implements IDatabaseObject<K>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public abstract K getPrimaryKey();

	@Override
	public String toString(){
		return "";
	}
	
	
}

