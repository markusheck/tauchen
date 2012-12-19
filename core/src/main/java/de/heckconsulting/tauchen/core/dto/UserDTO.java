package de.heckconsulting.tauchen.core.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Data
@XmlRootElement
public class UserDTO  extends BaseDTO implements Serializable  {

	private static final long serialVersionUID = -406774340076252764L;

	private Long id;

	private String username;
	
	private String password;

}
