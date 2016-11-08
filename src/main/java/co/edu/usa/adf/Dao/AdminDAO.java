package co.edu.usa.adf.Dao;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import co.edu.usa.adf.Entidades.Adminsala;
import co.edu.usa.adf.Entidades.Pelicula;
import co.edu.usa.adf.Entidades.Sala;


public class AdminDAO extends AbstractJpaDAO< Adminsala >  {
	
	public AdminDAO(){
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "CineJPA" );
		EntityManager entitymanager = emfactory.createEntityManager( );
		setClazz( Adminsala.class , entitymanager);
	}
	
	public void crear(long sala , long silla){
		Adminsala admin = new Adminsala();
		SalaDAO sal = new SalaDAO();
		SillaDAO sil = new SillaDAO();
		admin.setSala(sal.findOne(sala));
		admin.setSilla(sil.findOne(silla));
		create(admin);
	}
	public List<Adminsala> getAdmins(){
		List<Adminsala> salida = findAll();
		return salida;
	}
}
