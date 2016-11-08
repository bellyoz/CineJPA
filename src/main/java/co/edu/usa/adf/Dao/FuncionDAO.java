package co.edu.usa.adf.Dao;




import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import co.edu.usa.adf.Entidades.Funcion;
import co.edu.usa.adf.Entidades.Pelicula;



public class FuncionDAO extends AbstractJpaDAO< Funcion >  {
		
	public FuncionDAO(){
		EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "CineJPA" );
		EntityManager entitymanager = emfactory.createEntityManager( );
		setClazz( Funcion.class , entitymanager);
	}
	
	public void crear(long sala, long pelicula , Date ini){
		System.out.println("sala "+sala);
		Funcion funcion = new Funcion();
		AdminDAO sal = new AdminDAO();
		PeliculaDAO peli  = new PeliculaDAO();
		funcion.setPelicula(peli.findOne(pelicula));
		funcion.setAdminsala(sal.findOne(sala));
		funcion.setInicio(ini);
		Pelicula p = new Pelicula();
		p = peli.findOne(pelicula);
		
	 	funcion.setFin(getFin(ini, p.getDuracion().getHours(),p.getDuracion().getMinutes()));
	 	
	 	if(validarFuncion(ini, sal.findOne(sala).getSala().getId() ,funcion.getFin())){
	 		
	 		create(funcion);
	 		System.out.println("sala "+ sala);
	 	}else{
	 		System.out.println("Error de fechas");
	 	}
	}
	public Date getFin(Date fecha , int hora , int minutos){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(fecha);
		calendar.add(Calendar.HOUR, hora);
		calendar.add(Calendar.MINUTE, minutos);
		
		Date rta = new Date(calendar.getTimeInMillis());

		return rta;
	}
	
	public boolean validarFuncion(Date ini , long sala , Date fin){
		List<Funcion> funcion = getFunciones();
	
		for(Funcion a : funcion){
			System.out.println("salaaaa "+a.getAdminsala().getSala().getId());
			if(a.getAdminsala().getSala().getId() == sala){
			
				if(a.getInicio().compareTo(ini)< 0 && a.getFin().compareTo(ini) > 0){
					return false;
				}else{
					return true;
				}
			}else{
				return true;
			}
		}
		return false;
	}
	public List<Funcion> getFunciones(){
		List<Funcion> salida = findAll();
		return salida;
	}
}
