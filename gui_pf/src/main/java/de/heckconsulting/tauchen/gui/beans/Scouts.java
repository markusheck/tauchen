package de.heckconsulting.tauchen.gui.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class Scouts implements Serializable {
	
	private Long id;

	private String scoutName;
	
	private Date validFrom;
	
	private Date validTo;
	
	private Date createdAt = new Date();
	
	private String userName;
	
	private Boolean active = Boolean.FALSE;
	
	private String query;
	
	private Integer crawlDepth = 1;
	
	private Integer numberOfCrawlers = 1;
	
	private Integer amountOfResult = 5;
	
	private Boolean visibleInUserGroup = Boolean.FALSE;
	
	private Boolean filterDoubleResultsByTitle;
	
	private Boolean onlyInDomain;
	
	private Date nextRun;
	
	private Date lastStart;
	
	private Date lastExec;
		
	private ArrayList<String> dailyCycleDays;
	
	private Integer cycle;
	
	private Boolean monday;
	private Boolean tuesday;
	private Boolean wednesday;
	private Boolean thursday;
	private Boolean friday;
	private Boolean saturday;
	private Boolean sunday;
	
	
	
	private Integer dailyCycleTime = 0;
	
	private Date periodicCycleStart;
	
	public Scouts( Long id, String name ) {
		this.id = id;
		this.scoutName = name;
	}

}
