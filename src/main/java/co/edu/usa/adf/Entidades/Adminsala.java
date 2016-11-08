package co.edu.usa.adf.Entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the adminsala database table.
 * 
 */
@Entity
@Table(name="adminsala")
@NamedQuery(name="Adminsala.findAll", query="SELECT a FROM Adminsala a")
public class Adminsala implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	//bi-directional many-to-one association to Sala
	@ManyToOne
	@JoinColumn(name="id_sala")
	private Sala sala;

	//bi-directional many-to-one association to Silla
	@ManyToOne
	@JoinColumn(name="id_silla")
	private Silla silla;

	//bi-directional many-to-one association to Funcion
	@OneToMany(mappedBy="adminsala")
	private List<Funcion> funcions;

	public Adminsala() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Sala getSala() {
		return this.sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public Silla getSilla() {
		return this.silla;
	}

	public void setSilla(Silla silla) {
		this.silla = silla;
	}

	public List<Funcion> getFuncions() {
		return this.funcions;
	}

	public void setFuncions(List<Funcion> funcions) {
		this.funcions = funcions;
	}

	public Funcion addFuncion(Funcion funcion) {
		getFuncions().add(funcion);
		funcion.setAdminsala(this);

		return funcion;
	}

	public Funcion removeFuncion(Funcion funcion) {
		getFuncions().remove(funcion);
		funcion.setAdminsala(null);

		return funcion;
	}

}