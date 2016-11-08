package co.edu.usa.adf.Dao;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import co.edu.usa.adf.Entidades.Funcion;
import co.edu.usa.adf.Entidades.Pelicula;
import co.edu.usa.adf.Entidades.Sala;
import co.edu.usa.adf.Entidades.Silla;
import co.edu.usa.adf.Entidades.Ticket;


public class TicketDAO extends AbstractJpaDAO< Ticket >  {
	
	public TicketDAO(){
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "CineJPA" );
		EntityManager entitymanager = emfactory.createEntityManager( );
		setClazz( Ticket.class , entitymanager);
	}
	
	public void crear(int funcion , int silla ){
		Ticket ticket = new Ticket();
		FuncionDAO fun = new FuncionDAO();
		SillaDAO sil = new  SillaDAO();
		Silla sill = new Silla();
		Funcion funC = new Funcion();
		funC = fun.findOne(funcion);
		funC.getAdminsala().getSilla().setDisponible(false);
		sill = sil.findOne(silla);
		ticket.setFuncion(funC);
		
		sill.setDisponible(false);
		sil.update(sill);
		ticket.setSilla(sill);
		create(ticket);
	}
	public List<Ticket> getTickets(){
		List<Ticket> salida = findAll();
		return salida;
	}
}
