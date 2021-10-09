package ro.ase.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import ro.ase.entity.LineItem;
import ro.ase.jdbc.LineItemDAO;
import ro.ase.service.LineItemServiceInterface;

@Service
public class LineItemServiceImp implements LineItemServiceInterface {

	@Override
	public Long insert(LineItem lineitem) {
		LineItemDAO dao = new LineItemDAO();
		return dao.insert(lineitem);
	}

	@Override
	public void delete(Long id) {
		LineItemDAO dao = new LineItemDAO();		
		dao.delete(id);
	}

	@Override
	public void update(LineItem lineitem) {
		LineItemDAO dao = new LineItemDAO();		
		dao.update(lineitem);
	}

	@Override
	public LineItem find(Long id) {
		return null;
	}

	@Override
	public List<LineItem> findAll() {
		return null;
	}

	@Override
	public List<LineItem> findAllByIdOrder(Long idOrder) {
		return null;
	}

}
