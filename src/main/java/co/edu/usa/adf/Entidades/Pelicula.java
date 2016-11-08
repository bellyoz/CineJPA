package co.edu.usa.adf.Entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.List;


/**
 * The persistent class for the pelicula database table.
 * 
 */
@Entity
@Table(name="pelicula")
@NamedQuery(name="Pelicula.findAll", query="SELECT p FROM Pelicula p")
public class Pelicula implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private Time duracion;

	private String genero;

	private String pelicula;

	//bi-directional many-to-one association to Funcion
	@OneToMany(mappedBy="pelicula")
	private List<Funcion> funcions;

	public Pelicula() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Time getDuracion() {
		return this.duracion;
	}

	public void setDuracion(Time duracion) {
		this.duracion = duracion;
	}

	public String getGenero() {
		return this.genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getPelicula() {
		return this.pelicula;
	}

	public void setPelicula(String pelicula) {
		this.pelicula = pelicula;
	}

	public List<Funcion> getFuncions() {
		return this.funcions;
	}

	public void setFuncions(List<Funcion> funcions) {
		this.funcions = funcions;
	}

	public Funcion addFuncion(Funcion funcion) {
		getFuncions().add(funcion);
		funcion.setPelicula(this);

		return funcion;
	}

	public Funcion removeFuncion(Funcion funcion) {
		getFuncions().remove(funcion);
		funcion.setPelicula(null);

		return funcion;
	}

}