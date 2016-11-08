package co.edu.usa.adf.Dao;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import co.edu.usa.adf.Entidades.Pelicula;


public class PeliculaDAO extends AbstractJpaDAO< Pelicula >  {
	
	public PeliculaDAO(){
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "CineJPA" );
		EntityManager entitymanager = emfactory.createEntityManager( );
		setClazz(Pelicula.class , entitymanager);
	}
	
	public void crear(Time duracion , String genero  ,String pelicula ){
		Pelicula peli = new Pelicula();
		peli.setDuracion(duracion);
		peli.setGenero(genero);
		peli.setPelicula(pelicula);
		System.out.println(peli.getGenero());
		create(peli);
	}
	public List<Pelicula> getPeliculas(){
		List<Pelicula> salida = findAll();
		
		return salida;
	}
}
