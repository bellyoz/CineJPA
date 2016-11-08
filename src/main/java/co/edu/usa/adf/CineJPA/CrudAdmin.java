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
import com.vaadin.ui.Table;

import co.edu.usa.adf.Dao.AdminDAO;
import co.edu.usa.adf.Dao.PeliculaDAO;
import co.edu.usa.adf.Dao.SalaDAO;
import co.edu.usa.adf.Entidades.Adminsala;
import co.edu.usa.adf.Entidades.Pelicula;
import co.edu.usa.adf.Entidades.Sala;
import co.edu.usa.adf.Entidades.Silla;

@Theme("mytheme")
public class CrudAdmin extends VerticalLayout implements View  {
	
	final VerticalLayout crear = new VerticalLayout();
	final TextField sal = new TextField();
	final TextField silla = new TextField();
	private Grid grid = new Grid();
	 Table table = new Table();
	 Button button = new Button("Crear");
	 AdminDAO admin;
	
	Navigator navigator;
	public CrudAdmin(Navigator navigator) {
		this.navigator = navigator;
		StartView start = new StartView(navigator);
		// TODO Auto-generated constructor stub
		addComponent(start.menubar());
		submenu();
		tabla();
	}
	public void submenu(){
		final HorizontalLayout layout = new HorizontalLayout();
		Button crear =  new Button("Crear Sala ");
		Button ver =  new Button("ver Salas ");
		crear.addClickListener(e ->{
			formCrear();
			
		});
		ver.addClickListener(e->{
			this.crear.setVisible(false);
			grid.setVisible(true);
		});
		
		layout.addComponents(ver,crear);
		layout.setMargin(true);
		layout.setSpacing(true);
		addComponents(layout);
	}
	public void formCrear(){
			crear.setVisible(true);
			grid.setVisible(false);
        sal.setCaption("Sala");
        silla.setCaption("Silla");
        

        
        button.addClickListener( e -> {
        	 admin = new AdminDAO();
        	 int  sala = Integer.parseInt(sal.getValue());
        	 int sill = Integer.parseInt(silla.getValue());
        	admin.crear(sala,sill);
        });
        crear.addComponents(sal,silla,button);
        sal.setSizeFull();
		silla.setSizeFull();
		
		crear.setWidth("500px");
		addComponent(crear);
		setComponentAlignment(crear, Alignment.MIDDLE_CENTER);
	}
	public void tabla(){
		
		admin = new AdminDAO();
		crear.setVisible(false);
		grid.setVisible(true);
		grid.setSizeFull();
		grid.addColumn("Id", Integer.class);
		grid.addColumn("Sala", Integer.class);
		grid.addColumn("Silla", String.class);
		grid.addColumn("Disponible" , Boolean.class);
	
		final VerticalLayout layout = new VerticalLayout();

	    List<Adminsala> salas = admin.getAdmins();
	    for(Adminsala a : salas){
	    	Silla silla = new Silla();
	    	silla = a.getSilla();
	    	grid.addRow(a.getId(),a.getSala().getId(),silla.getFila()+" "+silla.getNumero(), silla.getDisponible());
	    }
	    layout.addComponents(grid);
		addComponents(layout);
	 
		
		
		
	}
	    	

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}


}
