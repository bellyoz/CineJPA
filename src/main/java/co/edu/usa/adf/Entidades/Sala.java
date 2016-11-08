package co.edu.usa.adf.Entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the sala database table.
 * 
 */
@Entity
@Table(name="sala")
@NamedQuery(name="Sala.findAll", query="SELECT s FROM Sala s")
public class Sala implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private int capacidad;

	private int disponibles;

	//bi-directional many-to-one association to Adminsala
	@OneToMany(mappedBy="sala")
	private List<Adminsala> adminsalas;

	public Sala() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCapacidad() {
		return this.capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public int getDisponibles() {
		return this.disponibles;
	}

	public void setDisponibles(int disponibles) {
		this.disponibles = disponibles;
	}

	public List<Adminsala> getAdminsalas() {
		return this.adminsalas;
	}

	public void setAdminsalas(List<Adminsala> adminsalas) {
		this.adminsalas = adminsalas;
	}

	public Adminsala addAdminsala(Adminsala adminsala) {
		getAdminsalas().add(adminsala);
		adminsala.setSala(this);

		return adminsala;
	}

	public Adminsala removeAdminsala(Adminsala adminsala) {
		getAdminsalas().remove(adminsala);
		adminsala.setSala(null);

		return adminsala;
	}

}