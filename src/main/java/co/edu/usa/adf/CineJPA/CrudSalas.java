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

import co.edu.usa.adf.Dao.PeliculaDAO;
import co.edu.usa.adf.Dao.SalaDAO;
import co.edu.usa.adf.Entidades.Pelicula;
import co.edu.usa.adf.Entidades.Sala;

@Theme("mytheme")
public class CrudSalas extends VerticalLayout implements View  {
	
	final VerticalLayout crear = new VerticalLayout();
	final TextField capacidad = new TextField();
	private Grid grid = new Grid();
	 Table table = new Table();
	 Button button = new Button("Crear");
	SalaDAO sala;
	
	Navigator navigator;
	public CrudSalas(Navigator navigator) {
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
        capacidad.setCaption("Capacidad");
        

        
        button.addClickListener( e -> {
        	 sala = new SalaDAO();
        	 int capa = Integer.parseInt(capacidad.getValue());
        	sala.crear(capa,capa);
        });
        crear.addComponents(capacidad,button);
        capacidad.setSizeFull();
		
		crear.setWidth("500px");
		addComponent(crear);
		setComponentAlignment(crear, Alignment.MIDDLE_CENTER);
	}
	public void tabla(){
		
		sala = new SalaDAO();
		
		grid.setSizeFull();
		grid.addColumn("Id", Integer.class);
		grid.addColumn("Capacidad" , Integer.class);
		grid.addColumn("Sillas disponibles", Integer.class);
		
		final VerticalLayout layout = new VerticalLayout();

	    List<Sala> salas = sala.getSalas();
	    for(Sala a : salas){
	    	grid.addRow(a.getId(),a.getCapacidad(),a.getDisponibles());
	    }
	    layout.addComponents(grid);
		addComponents(layout);
		 
		 
		
		
		
	}
	
	 @Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}


}
