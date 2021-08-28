package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

public class SellerService {
	
	private SellerDao dao = DaoFactory.createSellerDao();
	
	public List<Seller> findall() {
		return dao.findAll();
	}
	
	public void saveOrUpdate(Seller obj) {
		//se for null significa que esta inserindo um novo departamento
		if(obj.getId() == null) {
			dao.insert(obj);
		}
		else {
			dao.update(obj);
		}
	}
	//remove um departamento
	public void remove(Seller obj) {
		dao.deleteById(obj.getId());
	}
	
}