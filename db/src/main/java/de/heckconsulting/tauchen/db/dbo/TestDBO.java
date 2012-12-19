package de.heckconsulting.tauchen.db.dbo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import lombok.Data;

import de.heckconsulting.tauchen.db.AbstractDatabaseObject;

@Entity
@Table(name="test")
@Data
@TableGenerator(name="sequence", table="t_pks", initialValue=1, valueColumnName="id")
public class TestDBO extends AbstractDatabaseObject<Long>{
	
	private static final long serialVersionUID = 1464428179898974720L;

	@Id
	@GeneratedValue
	private Long id;
	
	@Column
	private String tabName;

	@Override
	public Long getPrimaryKey() {
		return id;
	}	
}
