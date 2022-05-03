package main;

import model.dao.DaoFactory;
import model.dao.VendedorDao;
import model.entities.Vendedor;

public class Program {

	public static void main(String[] args) {
		
		VendedorDao vendedorDao = DaoFactory.createVendedorDao();
		
		System.out.println("TESTE 1 - findById Vendedor");
		Vendedor vendedor = vendedorDao.findById(3);
		System.out.println(vendedor);
	}

}
