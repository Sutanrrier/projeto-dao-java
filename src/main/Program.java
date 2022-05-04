package main;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.VendedorDao;
import model.entities.Departamento;
import model.entities.Vendedor;

public class Program {

	public static void main(String[] args) {
		
		VendedorDao vendedorDao = DaoFactory.createVendedorDao();
		
		System.out.println("TESTE 1 - findById Vendedor");
		Vendedor vendedor = vendedorDao.findById(3);
		System.out.println(vendedor);
		
		System.out.println("\nTESTE 2 - findByDepartment Vendedor");
		Departamento dep = new Departamento(2, null);
		List<Vendedor> listaVendedor = vendedorDao.findByDepartamento(dep);
		for(Vendedor obj : listaVendedor) {
			System.out.println(obj);
		}
	
		System.out.println("\nTESTE 3 - findAll Vendedor");
		listaVendedor = vendedorDao.findAll();
		for(Vendedor obj : listaVendedor) {
			System.out.println(obj);
		}
		
	}

}
