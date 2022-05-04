package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
				"INSERT INTO seller "
				+ "(name_seller, email, birthdate, basesalary, departmentid) "
				+ "VALUES (?, ?, ?, ?, ?)",
				Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, obj.getNome());
			st.setString(2, obj.getEmail());
			st.setDate(3, new java.sql.Date(obj.getDataNascimento().getTime()));
			st.setDouble(4, obj.getSalarioBase());
			st.setInt(5, obj.getDepartamento().getId());
			
			int linhas = st.executeUpdate();
			
			if (linhas > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if(rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			}
			else {
				throw new DbException("Erro! Nenhuma linha foi afetada!");
			}
			System.out.println(linhas + " linhas afetadas");
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
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
				Departamento dep = instantiateDepartamento(rs);
				Vendedor obj = instantiateVendendor(rs, dep);
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

	private Vendedor instantiateVendendor(ResultSet rs, Departamento dep) throws SQLException {
		Vendedor obj = new Vendedor();
		obj.setId(rs.getInt("seller_id"));
		obj.setNome(rs.getString("name_seller"));
		obj.setEmail(rs.getString("email"));
		obj.setDataNascimento(rs.getDate("birthdate"));
		obj.setSalarioBase(rs.getDouble("basesalary"));
		obj.setDepartamento(dep);
		return obj;
	}

	private Departamento instantiateDepartamento(ResultSet rs) throws SQLException {
		Departamento dep = new Departamento();
		dep.setId(rs.getInt("departmentid"));
		dep.setNome(rs.getString("depname"));
		return dep;
	}

	@Override
	public List<Vendedor> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.dp_name as depname "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.departmentid = department.dp_id "
					+ "ORDER BY name_seller");
			
			rs = st.executeQuery();
			
			List<Vendedor> list = new ArrayList<>();
			Map<Integer, Departamento> map = new HashMap<>();
			
			while(rs.next()) {
				Departamento dep = map.get(rs.getInt("departmentid"));
				
				if(dep == null) {
					dep = instantiateDepartamento(rs);
					map.put(rs.getInt("departmentid"), dep);
				}
				
				Vendedor obj = instantiateVendendor(rs, dep);
				list.add(obj);
			}
			return list;
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
	public List<Vendedor> findByDepartamento(Departamento departamento) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT seller.*,department.dp_name as depname "
					+ "FROM seller INNER JOIN department "
					+ "ON seller.departmentid = department.dp_id "
					+ "WHERE departmentid = ? "
					+ "ORDER BY name_seller");
			
			st.setInt(1, departamento.getId());
			rs = st.executeQuery();
			
			List<Vendedor> list = new ArrayList<>();
			Map<Integer, Departamento> map = new HashMap<>();
			
			while(rs.next()) {
				Departamento dep = map.get(rs.getInt("departmentid"));
				
				if(dep == null) {
					dep = instantiateDepartamento(rs);
					map.put(rs.getInt("departmentid"), dep);
				}
				
				Vendedor obj = instantiateVendendor(rs, dep);
				list.add(obj);
			}
			return list;
		}
		catch(SQLException e) {
			throw new DbException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

}
