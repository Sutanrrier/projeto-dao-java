package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.VendedorDao;
import model.entities.Departamento;
import model.entities.Vendedor;

public class VendedorDaoJDBC implements VendedorDao {

	private Connection conn;
	
	public VendedorDaoJDBC(Connection conn) {
		this.conn = conn;
	}
	
	@Override
	public void insert(Vendedor obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Vendedor obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Vendedor findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.dp_name AS depname " +
					"FROM seller INNER JOIN department " +
					"ON seller.departmentid = department.dp_id " +
					"WHERE seller.seller_id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if(rs.next()) {
				Departamento dep = new Departamento();
				dep.setId(rs.getInt("departmentid"));
				dep.setNome(rs.getString("depname"));
				
				Vendedor obj = new Vendedor();
				obj.setId(rs.getInt("seller_id"));
				obj.setNome(rs.getString("name_seller"));
				obj.setEmail(rs.getString("email"));
				obj.setDataNascimento(rs.getDate("birthdate"));
				obj.setSalarioBase(rs.getDouble("basesalary"));
				obj.setDepartamento(dep);
				
				return obj;
			}
			return null;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
		
	}

	@Override
	public List<Vendedor> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
