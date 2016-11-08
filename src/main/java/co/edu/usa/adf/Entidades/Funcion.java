package co.edu.usa.adf.Entidades;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the funcion database table.
 * 
 */
@Entity
@Table(name="funcion")
@NamedQuery(name="Funcion.findAll", query="SELECT f FROM Funcion f")
public class Funcion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fin;

	@Temporal(TemporalType.TIMESTAMP)
	private Date inicio;

	//bi-directional many-to-one association to Pelicula
	@ManyToOne
	@JoinColumn(name="id_pelicula")
	private Pelicula pelicula;

	//bi-directional many-to-one association to Adminsala
	@ManyToOne
	@JoinColumn(name="id_sala")
	private Adminsala adminsala;

	//bi-directional many-to-one association to Ticket
	@OneToMany(mappedBy="funcion")
	private List<Ticket> tickets;

	public Funcion() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFin() {
		return this.fin;
	}

	public void setFin(Date fin) {
		this.fin = fin;
	}

	public Date getInicio() {
		return this.inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Pelicula getPelicula() {
		return this.pelicula;
	}

	public void setPelicula(Pelicula pelicula) {
		this.pelicula = pelicula;
	}

	public Adminsala getAdminsala() {
		return this.adminsala;
	}

	public void setAdminsala(Adminsala adminsala) {
		this.adminsala = adminsala;
	}

	public List<Ticket> getTickets() {
		return this.tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Ticket addTicket(Ticket ticket) {
		getTickets().add(ticket);
		ticket.setFuncion(this);

		return ticket;
	}

	public Ticket removeTicket(Ticket ticket) {
		getTickets().remove(ticket);
		ticket.setFuncion(null);

		return ticket;
	}

}