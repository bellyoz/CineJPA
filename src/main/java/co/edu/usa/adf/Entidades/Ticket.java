package co.edu.usa.adf.Entidades;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ticket database table.
 * 
 */
@Entity
@Table(name="ticket")
@NamedQuery(name="Ticket.findAll", query="SELECT t FROM Ticket t")
public class Ticket implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	//bi-directional many-to-one association to Silla
	@ManyToOne
	@JoinColumn(name="id_silla")
	private Silla silla;

	//bi-directional many-to-one association to Funcion
	@ManyToOne
	@JoinColumn(name="id_funcion")
	private Funcion funcion;

	public Ticket() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Silla getSilla() {
		return this.silla;
	}

	public void setSilla(Silla silla) {
		this.silla = silla;
	}

	public Funcion getFuncion() {
		return this.funcion;
	}

	public void setFuncion(Funcion funcion) {
		this.funcion = funcion;
	}

}