package de.heckconsulting.tauchen.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import de.heckconsulting.tauchen.core.service.AccordionService;
import de.heckconsulting.tauchen.db.dao.TestDAO;
import de.heckconsulting.tauchen.db.dbo.TestDBO;

@Service("accordionService")
public class AccordionServiceImpl implements AccordionService {

	@Autowired
	private TestDAO dao;

	@Override
	public String getAccordionText(Long id) {
		TestDBO dbo = dao.findById( id );		
		return id + ": " + (dbo == null  ? "id not found" : dbo.getTabName() ) + " " + new java.util.Date();
	}

}
