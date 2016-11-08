package co.edu.usa.adf.CineJPA;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.vaadin.annotations.Theme;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Page;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.InlineDateField;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.TextField;

import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Table;

import co.edu.usa.adf.Dao.PeliculaDAO;
import co.edu.usa.adf.Entidades.Pelicula;

@Theme("mytheme")
public class CrudPeliculas extends VerticalLayout implements View {

	final VerticalLayout crear = new VerticalLayout();
	final TextField name = new TextField();
	final TextField genero = new TextField();
	private Grid grid = new Grid();
	InlineDateField date = new InlineDateField("Time: ");
	Table table = new Table();
	Button button = new Button("Crear");
	PeliculaDAO peli;

	Navigator navigator;

	public CrudPeliculas(Navigator navigator) {
		this.navigator = navigator;
		StartView start = new StartView(navigator);
		// TODO Auto-generated constructor stub
		addComponent(start.menubar());
		submenu();
		tabla();
	}

	public void submenu() {
		final HorizontalLayout layout = new HorizontalLayout();
		Button crear = new Button("Crear Pelicula ");
		Button ver = new Button("ver Peliculas ");
		Button actualizar = new Button("Actualizar Peliculas ");
		Button borrar = new Button("borrar Peliculas ");
		crear.addClickListener(e -> {
			formCrear();

		});
		ver.addClickListener(e -> {
			this.crear.setVisible(false);
			grid.setVisible(true);
		});

		layout.addComponents(ver, crear);
		layout.setMargin(true);
		layout.setSpacing(true);
		addComponents(layout);
		
	}

	public void formCrear() {
		crear.setVisible(true);
		grid.setVisible(false);
		
		name.setCaption("Nombre de pelicula");
		genero.setCaption("genero de pelicula");
		date.setResolution(Resolution.MINUTE);
		date.addStyleName("time-only");

		button.addClickListener(e -> {
			peli = new PeliculaDAO();
			Date dur = (Date) date.getValue();
			
			Time tiempo = new Time(dur.getHours(), dur.getMinutes(), dur.getSeconds());

			//System.out.println("nombre " + name.getValue() + " genero " + genero.getValue() + " date " + tiempo);
			peli.crear(tiempo, genero.getValue(), name.getValue());
			new Notification("Ha sido creado Correctamente","", Notification.TYPE_WARNING_MESSAGE, true).show(Page.getCurrent());
		});
		crear.addComponents(name, genero, date, button);
		name.setSizeFull();
		genero.setSizeFull();
		
		crear.setWidth("500px");
		addComponent(crear);
		setComponentAlignment(crear, Alignment.MIDDLE_CENTER);
		
	}
	

	public void tabla() {
		peli = new PeliculaDAO();
		crear.setVisible(false);
		grid.setVisible(true);
		grid.setResponsive(true);
		grid.setSizeFull();
		grid.addColumn("Id", Integer.class);
		grid.addColumn("Pelicula", String.class);
		grid.addColumn("Genero", String.class);
		grid.addColumn("Duracion", String.class);
		final VerticalLayout layout = new VerticalLayout();
		List<Pelicula> peliculas = peli.getPeliculas();
		for(Pelicula a : peliculas){
			grid.addRow(a.getId(),a.getPelicula(),a.getGenero(),a.getDuracion().toString());
		}
		layout.addComponents(grid);
		addComponents(layout);
		System.out.println("tam " + peli.getPeliculas());

	}

	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}

}
