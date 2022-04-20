package main;

import java.util.Date;

import model.dao.DaoFactory;
import model.dao.VendedorDao;
import model.entities.Departamento;
import model.entities.Vendedor;

public class Program {

	public static void main(String[] args) {
		
		Departamento obj = new Departamento(1, "Livros");
		System.out.println(obj);
		
		Vendedor vendedor = new Vendedor(1, "Alex", "alex@gmail.com", new Date(), 3000.0, obj);
		System.out.println(vendedor);
		
		VendedorDao vendedorDao = DaoFactory.createVendedorDao();
	}

}
