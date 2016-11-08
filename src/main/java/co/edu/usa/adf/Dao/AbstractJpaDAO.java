package co.edu.usa.adf.Dao;

import java.io.Serializable;
import java.sql.Time;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import co.edu.usa.adf.Entidades.Pelicula;

public class AbstractJpaDAO < T extends Serializable > {
	private Class< T > clazz;
	 
	   @PersistenceContext
	   EntityManager entityManager;
	 
	   public final void setClazz( Class< T > clazzToSet , EntityManager entityManager ){
	      this.clazz = clazzToSet;
	     this.entityManager = entityManager;
	   }
	   
	   public T findOne( long id ){
	      return entityManager.find( clazz, (int)id );
	   }
	   
	   public List< T > findAll(){
	      return entityManager.createQuery( "select p from "+clazz.getSimpleName()+" p", clazz).getResultList();
	   }
	 
	   public void create( T entity ){
		   entityManager.getTransaction().begin();
	      entityManager.persist( entity);
	      entityManager.getTransaction( ).commit( );
	      //entityManager.close();
	      
	   }
	 
	   public T update( T entity ){
	      return entityManager.merge( entity );
	   }
	 
	   public void delete( T entity ){
	      entityManager.remove( entity );
	   }
	   public void deleteById( long entityId ){
	      T entity = findOne( entityId );
	      delete( entity );
	   }
	  
}


