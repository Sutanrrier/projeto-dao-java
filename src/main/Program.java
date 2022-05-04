package main;

import java.util.Date;
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
		
		System.out.println("\nTESTE 4 - Insert Vendedor");
		Vendedor vendedorNovo = new Vendedor(null, "Greg", "greg@gmail.com", new Date(), 4000.0, dep);
		//vendedorDao.insert(vendedorNovo);
		System.out.println("Inserido! Novo Id = " + vendedorNovo.getId());
		
		System.out.println("\nTESTE 5 - Update Vendedor");
		vendedor = vendedorDao.findById(1);
		vendedor.setNome("Martha Wayne");
		vendedorDao.update(vendedor);
		System.out.println("UPDATE finalizado!");
		
		System.out.println("\nTESTE 6 - Delete Vendedor");	
		vendedorDao.deleteById(9);
		System.out.println("DELETE finalizado!");
	}

}
