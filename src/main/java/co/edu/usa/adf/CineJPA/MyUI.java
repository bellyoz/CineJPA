package co.edu.usa.adf.CineJPA;

import java.sql.Time;
import java.util.Date;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.Button;
import com.vaadin.ui.InlineDateField;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import co.edu.usa.adf.Dao.PeliculaDAO;


@Theme("mytheme")
public class MyUI extends UI {
	PeliculaDAO peli;
	 Button pelicula = new Button("Administrar peliculas");
	 Navigator navigator;
	 protected static final String MAINVIEW = "main";
	    
    @Override
    protected void init(VaadinRequest vaadinRequest) {
    	getPage().setTitle("Navigation Example");

        // Create a navigator to control the views
        navigator = new Navigator(this, this);

        // Create and register the views
        navigator.addView("", new StartView(navigator));
        navigator.addView(MAINVIEW, new MainView(navigator));
        navigator.addView("pelicula", new CrudPeliculas(navigator));
        navigator.addView("sala", new CrudSalas(navigator));
        navigator.addView("silla", new CrudSilla(navigator));
        navigator.addView("admin", new CrudAdmin(navigator));
        navigator.addView("funcion", new CrudFuncion(navigator));
        navigator.addView("ticket", new CrudTicket(navigator));
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }

	
}
