package main;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.DepartamentoDao;
import model.entities.Departamento;

public class Program2 {

	public static void main(String[] args) {
		
		DepartamentoDao departamentoDao = DaoFactory.createDepartamentoDao();
		
		System.out.println("TESTE 1 - findById Departamento");
		Departamento departamento1 = departamentoDao.findById(1);
		System.out.println(departamento1);
	
		System.out.println("\nTESTE 2 - findAll Departamento");
		List<Departamento> listaDepartamento = departamentoDao.findAll();
		for(Departamento obj : listaDepartamento) {
			System.out.println(obj);
		}
		
		System.out.println("\nTESTE 3 - Insert Departamento");
		Departamento departamentoNovo = new Departamento(null, "Music");
		departamentoDao.insert(departamentoNovo);
		System.out.println("Inserido! Novo Id = " + departamentoNovo.getId());
		
		System.out.println("\nTESTE 4 - Update Departamento");
		departamento1 = departamentoDao.findById(1);
		departamento1.setNome("Food");
		departamentoDao.update(departamento1);
		System.out.println("UPDATE finalizado!");
		
		System.out.println("\nTESTE 5 - Delete Departamento");	
		departamentoDao.deleteById(7);
		System.out.println("DELETE finalizado!");
	}

}
