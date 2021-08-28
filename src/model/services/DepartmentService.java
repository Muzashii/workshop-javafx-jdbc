package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartmentDao;
import model.entities.Department;

public class DepartmentService {
	
	private DepartmentDao dao = DaoFactory.createDepartmentDao();
	
	public List<Department> findall() {
		return dao.findAll();
	}
	
	public void saveOrUpdate(Department obj) {
		//se for null significa que esta inserindo um novo departamento
		if(obj.getId() == null) {
			dao.insert(obj);
		}
		else {
			dao.update(obj);
		}
	}
	//remove um departamento
	public void remove(Department obj) {
		dao.deleteById(obj.getId());
	}
	
}
