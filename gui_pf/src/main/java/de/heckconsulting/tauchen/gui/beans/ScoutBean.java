package de.heckconsulting.tauchen.gui.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import lombok.Data;

import org.primefaces.event.TabChangeEvent;

@ManagedBean
@ViewScoped
@Data
public class ScoutBean implements Serializable {
	
	private static final long serialVersionUID = 5550996352639033812L;
	
	private List<Scouts> scouts;
	
	private Scouts scout;
	
	public ScoutBean() {
		scouts = new ArrayList<Scouts>();
		scouts.add( new Scouts( Long.valueOf( 1 ), "xx" ) );
		scouts.add( new Scouts( Long.valueOf( 2 ), "Java-Projekte" ) );
		scouts.add( new Scouts( Long.valueOf( 3 ), "Test" ) );		
		scout = scouts.get(0);
	}
	
    public void onTabChange(TabChangeEvent event) {  
        scout = (Scouts)event.getData();
    }  	    
}
