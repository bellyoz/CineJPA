package co.edu.usa.adf.Dao;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import co.edu.usa.adf.Entidades.Pelicula;
import co.edu.usa.adf.Entidades.Sala;
import co.edu.usa.adf.Entidades.Silla;


public class SillaDAO extends AbstractJpaDAO<Silla >  {
	
	public SillaDAO(){
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "CineJPA" );
		EntityManager entitymanager = emfactory.createEntityManager( );
		setClazz( Silla.class , entitymanager);
	}
	
	public void crear(int numero, String fila ){
		Silla silla = new Silla();
		silla.setNumero(numero);
		silla.setFila(fila);
		silla.setDisponible(true);
		create(silla);
	}
	public List<Silla> getSillas(){
		List<Silla> salida = findAll();
		return salida;
	}
}
