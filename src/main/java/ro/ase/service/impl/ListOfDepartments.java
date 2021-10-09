package ro.ase.service.impl;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ro.ase.model.Departments;

@Service
public class ListOfDepartments {

    @Autowired
    ServletContext servletContext;

    public List<String> getDepartments() {
        Departments departments;
        Logger logger = Logger.getLogger(ListOfDepartments.class.getName());
        String fileName = File.separator + "WEB-INF" + File.separator + "xml" + File.separator + "department.xml";
        try {
            JAXBContext context = JAXBContext.newInstance(Departments.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            departments = (Departments) unmarshaller.unmarshal(servletContext.getResourceAsStream(fileName));
            return departments.getDepartments();
        } catch (JAXBException e) {
            logger.log(Level.SEVERE, null, e);
            return new LinkedList<>();
        }
    }
}
