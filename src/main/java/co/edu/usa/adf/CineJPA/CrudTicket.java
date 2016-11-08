package co.edu.usa.adf.CineJPA;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.vaadin.annotations.Theme;
import com.vaadin.data.Item;
import com.vaadin.data.Property;
import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;

import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
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
import co.edu.usa.adf.Dao.SillaDAO;
import co.edu.usa.adf.Dao.TicketDAO;
import co.edu.usa.adf.Entidades.Adminsala;
import co.edu.usa.adf.Entidades.Funcion;
import co.edu.usa.adf.Entidades.Pelicula;
import co.edu.usa.adf.Entidades.Sala;
import co.edu.usa.adf.Entidades.Silla;
import co.edu.usa.adf.Entidades.Ticket;

@Theme("mytheme")
public class CrudTicket extends VerticalLayout implements View {

	final VerticalLayout crear = new VerticalLayout();
	ComboBox funcion = new ComboBox("funciones");
	ComboBox silla = new ComboBox("Sillas");
	private Grid grid = new Grid();
	Table table = new Table();
	Button button = new Button("Crear");
	TicketDAO ticket;

	Navigator navigator;

	public CrudTicket(Navigator navigator) {
		this.navigator = navigator;
		StartView start = new StartView(navigator);
		// TODO Auto-generated constructor stub
		addComponent(start.menubar());
		submenu();
		tabla();

	}

	public void submenu() {
		final HorizontalLayout layout = new HorizontalLayout();
		Button crear = new Button("Crear Ticket ");
		Button ver = new Button("ver tickets ");
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
		FuncionDAO fun = new FuncionDAO();
		SillaDAO sil = new SillaDAO();

		List<Funcion> s = fun.getFunciones();

		funcion.addContainerProperty(1, Funcion.class, null);
		for (Funcion a : s) {

			Item item = funcion.addItem(a.getPelicula().getPelicula() + " - " + a.getInicio().toString());
			if (item != null) {
				item.getItemProperty(1).setValue(a);
			}
		}
		funcion.addListener(new Property.ValueChangeListener() {

			@Override
			public void valueChange(ValueChangeEvent event) {
				silla.removeAllItems();
			 
				Item itemF = funcion.getItem(funcion.getValue());
				Funcion f = new Funcion();
				f = (Funcion) itemF.getItemProperty(1).getValue();
				System.out.println("id " + f.getAdminsala().getId());

				// silla

				silla.addContainerProperty(2, Silla.class, null);
				AdminDAO dao = new AdminDAO();

				List<Adminsala> si = dao.getAdmins();
				for (Adminsala a : si) {
					System.out.println("salas "+a.getSala().getId());
					if (a.getSala().getId() == f.getAdminsala().getSala().getId()) {
						System.out.println(" sala "+a.getSala().getId());
						if (a.getSilla().getDisponible()) {
							System.out.println("sit "+a.getSilla().getFila());
							Item itemS = silla.addItem(a.getSilla().getFila() + " - " + a.getSilla().getNumero());
							if (itemS != null) {
								itemS.getItemProperty(2).setValue(a.getSilla());
							}

						}
					}
				}
				
				

			}
		});

		button.addClickListener(e -> {
			ticket = new TicketDAO();
			Item itemF = funcion.getItem(funcion.getValue());
			Funcion f = new Funcion();
			f = (Funcion) itemF.getItemProperty(1).getValue();

			Silla sillaa = new Silla();
			Item itemS = silla.getItem(silla.getValue());
			sillaa = (Silla) itemS.getItemProperty(2).getValue();
			System.out.println("funcion " + f.getPelicula().getPelicula() + " silla " + sillaa.getId());

			ticket.crear(f.getId(), sillaa.getId());
		});

		crear.addComponents(funcion, silla, button);
		funcion.setSizeFull();
		grid.setSizeFull();
		crear.setWidth("500px");
		addComponent(crear);
		setComponentAlignment(crear, Alignment.MIDDLE_CENTER);
	}

	public void tabla() {

		ticket = new TicketDAO();
		crear.setVisible(false);
		grid.setVisible(true);
		grid.setSizeFull();
		grid.addColumn("Id", Integer.class);
		grid.addColumn("Sala", Integer.class);
		grid.addColumn("Silla", String.class);
		grid.addColumn("Pelicula ", String.class);
		grid.addColumn("Hora de inicio", String.class);
		grid.addColumn("Hora final", String.class);

		final VerticalLayout layout = new VerticalLayout();

		List<Ticket> salas = ticket.getTickets();

		for (Ticket a : salas) {
			Silla silla = new Silla();
			Funcion fun = new Funcion();
			fun = a.getFuncion();
			grid.addRow(a.getId(), fun.getAdminsala().getSala().getId(),
					a.getSilla().getFila() + "" + a.getSilla().getNumero(),
					fun.getPelicula().getPelicula(), fun.getInicio().toString(), fun.getFin().toString());
		}

		layout.addComponents(grid);
		addComponents(layout);

	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub

	}

}
