package ro.ase.service.impl;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.ase.entity.Products;

@Service
public class ProductsXml {
	
	@Autowired
	ServletContext servletContext;
	
	Logger logger = Logger.getLogger(ProductsXml.class.getName());
	String fileName = File.separator + "WEB-INF" + File.separator + "xml" + File.separator + "setProductToDatabase.xml";
	
	public Products getProductsFromXml() {
		Products products = null;
		try {
			JAXBContext context = JAXBContext.newInstance(Products.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			products = (Products) unmarshaller.unmarshal(servletContext.getResourceAsStream(fileName));
			return products;
		} catch (JAXBException e) {
			logger.log(Level.SEVERE, null, e);
			return products;
		}		
	}

	public void setProductToXml(Products products) {
		try {
			JAXBContext context = JAXBContext.newInstance(Products.class);
			Marshaller marshaller = context.createMarshaller();			
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.marshal(products, new File(servletContext.getRealPath(fileName)));
		} catch (JAXBException e) {
			logger.log(Level.SEVERE, null, e);
		}
	}
}
