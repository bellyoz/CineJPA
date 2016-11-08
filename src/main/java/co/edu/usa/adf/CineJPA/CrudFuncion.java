package co.edu.usa.adf.CineJPA;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.vaadin.annotations.Theme;
import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;

import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.InlineDateField;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.TextField;

import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Table;

import co.edu.usa.adf.Dao.AdminDAO;
import co.edu.usa.adf.Dao.FuncionDAO;
import co.edu.usa.adf.Dao.PeliculaDAO;
import co.edu.usa.adf.Dao.SalaDAO;
import co.edu.usa.adf.Entidades.Adminsala;
import co.edu.usa.adf.Entidades.Funcion;
import co.edu.usa.adf.Entidades.Pelicula;
import co.edu.usa.adf.Entidades.Sala;

@Theme("mytheme")
public class CrudFuncion extends VerticalLayout implements View {

	final VerticalLayout crear = new VerticalLayout();
	ComboBox sala = new ComboBox("Sala");
	ComboBox select = new ComboBox("Pelicula");
	InlineDateField date = new InlineDateField("Fecha de inicio");
	final TextField capacidad = new TextField();
	private Grid grid = new Grid();
	Table table = new Table();
	Button button = new Button("Crear");
	FuncionDAO funcion;

	Navigator navigator;

	public CrudFuncion(Navigator navigator) {
		this.navigator = navigator;
		StartView start = new StartView(navigator);
		// TODO Auto-generated constructor stub
		addComponent(start.menubar());
		submenu();
		tabla();

	}

	public void submenu() {
		final HorizontalLayout layout = new HorizontalLayout();
		Button crear = new Button("Crear Funcion ");
		Button ver = new Button("ver Funciones ");
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
		PeliculaDAO pel = new PeliculaDAO();
		AdminDAO sal = new AdminDAO();

		date.setResolution(Resolution.MINUTE);
		date.setValue(new Date());
		sala.addContainerProperty(2, Adminsala.class, null);
		List<Adminsala> s = sal.getAdmins();
		for (Adminsala a : s) {

			Item item = sala.addItem(a.getSala().getId());
			if (item != null) {
				item.getItemProperty(2).setValue(a);
			}
		}

		select.addContainerProperty(1, Pelicula.class, null);

		List<Pelicula> peli = pel.getPeliculas();

		for (Pelicula a : peli) {
			String pelicula = a.getPelicula();

			Item item = select.addItem(pelicula);
			if (item != null) {
				item.getItemProperty(1).setValue(a);
			}
		}

		button.addClickListener(e -> {
			funcion = new FuncionDAO();
			Item itemS = sala.getItem(sala.getValue());
			Adminsala admin = new Adminsala();
			admin = (Adminsala) itemS.getItemProperty(2).getValue();
			Pelicula pelu = new Pelicula();
			Item item = select.getItem(select.getValue());
			pelu = (Pelicula) item.getItemProperty(1).getValue();
			int salita = admin.getId();
			Date dur = (Date) date.getValue();
			System.out.println("time " + dur.toString());
			funcion.crear(salita, pelu.getId(), dur);
		});
		crear.addComponents(sala, select, date, button);
		sala.setSizeFull();
		select.setSizeFull();
		crear.setWidth("800px");
		addComponent(crear);
		setComponentAlignment(crear, Alignment.MIDDLE_CENTER);
	}

	public void tabla() {

		funcion = new FuncionDAO();
		crear.setVisible(false);
		grid.setVisible(true);
		grid.setSizeFull();
		grid.addColumn("Id", Integer.class);
		grid.addColumn("Pelicula", String.class);
		grid.addColumn("Sala ", Integer.class);
		grid.addColumn("inicio", String.class);
		grid.addColumn("fin", String.class);
		final VerticalLayout layout = new VerticalLayout();

		List<Funcion> salas = funcion.getFunciones();
		for (Funcion a : salas) {
			Adminsala admin = new Adminsala();
			admin = a.getAdminsala();
			Sala sala = new Sala();
			sala = admin.getSala();
			grid.addRow(a.getId(), a.getPelicula().getPelicula(), sala.getId(), a.getInicio().toString() + "",
					"" + a.getFin().toString());
		}
		layout.addComponents(grid);
		addComponents(layout);

	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}

}
