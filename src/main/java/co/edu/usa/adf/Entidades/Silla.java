package co.edu.usa.adf.Entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the silla database table.
 * 
 */
@Entity
@Table(name="silla")
@NamedQuery(name="Silla.findAll", query="SELECT s FROM Silla s")
public class Silla implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private boolean disponible;

	private String fila;

	private int numero;

	//bi-directional many-to-one association to Adminsala
	@OneToMany(mappedBy="silla")
	private List<Adminsala> adminsalas;

	//bi-directional many-to-one association to Ticket
	@OneToMany(mappedBy="silla")
	private List<Ticket> tickets;

	public Silla() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getDisponible() {
		return this.disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public String getFila() {
		return this.fila;
	}

	public void setFila(String fila) {
		this.fila = fila;
	}

	public int getNumero() {
		return this.numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	public List<Adminsala> getAdminsalas() {
		return this.adminsalas;
	}

	public void setAdminsalas(List<Adminsala> adminsalas) {
		this.adminsalas = adminsalas;
	}

	public Adminsala addAdminsala(Adminsala adminsala) {
		getAdminsalas().add(adminsala);
		adminsala.setSilla(this);

		return adminsala;
	}

	public Adminsala removeAdminsala(Adminsala adminsala) {
		getAdminsalas().remove(adminsala);
		adminsala.setSilla(null);

		return adminsala;
	}

	public List<Ticket> getTickets() {
		return this.tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Ticket addTicket(Ticket ticket) {
		getTickets().add(ticket);
		ticket.setSilla(this);

		return ticket;
	}

	public Ticket removeTicket(Ticket ticket) {
		getTickets().remove(ticket);
		ticket.setSilla(null);

		return ticket;
	}

}