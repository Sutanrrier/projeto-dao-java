package model.entities;

import java.io.Serializable;
import java.util.Objects;

public class Departamento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String nome;
	
	//Construtores
	public Departamento() {
		
	}
	public Departamento(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	//Getters and Setters
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	//Hash-code e Equals
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Departamento other = (Departamento) obj;
		return Objects.equals(id, other.id);
	}
	
	//toString
	@Override
	public String toString() {
		return "Departamento [id=" + id + ", name=" + nome + "]";
	}

}
