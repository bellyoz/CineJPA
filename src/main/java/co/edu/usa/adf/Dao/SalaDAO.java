package co.edu.usa.adf.Dao;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import co.edu.usa.adf.Entidades.Pelicula;
import co.edu.usa.adf.Entidades.Sala;


public class SalaDAO extends AbstractJpaDAO< Sala >  {
	
	public SalaDAO(){
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "CineJPA" );
		EntityManager entitymanager = emfactory.createEntityManager( );
		setClazz( Sala.class , entitymanager);
	}
	
	public void crear(int capacidad , int disponible){
		Sala sala = new Sala();
		sala.setDisponibles(disponible);
		sala.setCapacidad(disponible);
		create(sala);
	}
	public List<Sala> getSalas(){
		List<Sala> salida = findAll();
		return salida;
	}
}
