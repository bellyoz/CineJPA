import static org.junit.Assert.assertEquals;

import java.sql.Time;
import java.util.Date;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.vaadin.data.Item;

import co.edu.usa.adf.Dao.AdminDAO;
import co.edu.usa.adf.Dao.FuncionDAO;
import co.edu.usa.adf.Dao.PeliculaDAO;
import co.edu.usa.adf.Dao.SalaDAO;
import co.edu.usa.adf.Dao.SillaDAO;

public class crearPeliculasTest {

	PeliculaDAO peli;
	@Before
	public void setUp() throws Exception {
		peli = new PeliculaDAO();
	}


    @Test
    public void test_Crear_Pelicula() {
      String name = "Miss Peregrine";
      String genero = "Terror";
      Date dur = new Date();
         java.sql.Date sqlDate = new java.sql.Date(dur.getTime());
         Time tiempo = new Time(dur.getHours(),dur.getMinutes(),dur.getSeconds());
         peli.crear(tiempo, genero, name);
         int pos = peli.findAll().size()-1;
         assertEquals("Miss Peregrine", peli.findOne(pos).getPelicula());
    }
   
     
     @Test
    @Ignore
    public void test_GetAll(){
      int totalMovies = peli.findAll().size();
      assertEquals(6, totalMovies);
    }
   
     @Test
    public void test_Crear_Silla(){
          SillaDAO sala = new SillaDAO();
          int num = 1;
          String fil = "A";
          sala.crear(num, fil);
          int pos = sala.findAll().size()-1;
          assertEquals("A", sala.findOne(pos).getFila());
    }
   
     @Test
    public void test_CreatSalas_y_validar_disponibilidad(){
          SalaDAO sala = new SalaDAO();
          int capa = 20;
          sala.crear(capa, capa);
          int pos = sala.findAll().size()-1;
          assertEquals(20, sala.findOne(pos).getCapacidad());
         
     }
   
     @Test
    public void test_Asignar_Admin(){
          AdminDAO admin = new AdminDAO();
          int sala = 1;
          int sill = 1;
          admin.crear(sala, sill);
          int pos = admin.findAll().size()-1;
          assertEquals(1, admin.findOne(pos).getSala().getId());
         
     }
  

}
