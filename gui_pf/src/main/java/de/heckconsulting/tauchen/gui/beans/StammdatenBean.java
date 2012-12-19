package de.heckconsulting.tauchen.gui.beans;

import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.SessionScoped;
import javax.inject.Named;

import lombok.Data;

@Named
@SessionScoped
@Data
public class StammdatenBean implements Serializable {

	private static final long serialVersionUID = -3067263337134239402L;
	
    private String test = "123456";
    
    private Date date = new Date();
    
    public List<String> complete( String query ) { 
    	List<String> result = new ArrayList<String>();
    	for( int i = 0; i < 10; i++ ) {
    		result.add( query + i );
    	}
    	return result;
    }
    
    public void submit() {
    	System.out.println( test + date );
    	
    }

}
