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
import co.edu.usa.adf.Dao.SillaDAO;
import co.edu.usa.adf.Entidades.Pelicula;
import co.edu.usa.adf.Entidades.Sala;
import co.edu.usa.adf.Entidades.Silla;

@Theme("mytheme")
public class CrudSilla extends VerticalLayout implements View  {
	
	final VerticalLayout crear = new VerticalLayout();
	final TextField numero = new TextField();
	final TextField fila = new TextField();
	private Grid grid = new Grid();
	 Table table = new Table();
	 Button button = new Button("Crear");
	SillaDAO sala;
	
	Navigator navigator;
	public CrudSilla(Navigator navigator) {
		this.navigator = navigator;
		StartView start = new StartView(navigator);
		// TODO Auto-generated constructor stub
		addComponent(start.menubar());
		submenu();
		tabla();
	}
	public void submenu(){
		final HorizontalLayout layout = new HorizontalLayout();
		Button crear =  new Button("Crear Silla ");
		Button ver =  new Button("ver Sillas ");
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
        numero.setCaption("Numero");
        fila.setCaption("fila");
       

        
        button.addClickListener( e -> {
        	 sala = new SillaDAO();
        	 int num = Integer.parseInt(numero.getValue());
        	 String fil = fila.getValue();
        	sala.crear(num,fil);
        });
        crear.addComponents(numero,fila,button);
        
        numero.setSizeFull();
        fila.setSizeFull();
        crear.setWidth("500px");
        addComponent(crear);
        setComponentAlignment(crear, Alignment.MIDDLE_CENTER);
	}
	public void tabla(){
		
		sala = new SillaDAO();
		crear.setVisible(false);
		grid.setVisible(true);
		grid.setSizeFull();
		grid.addColumn("Id" , Integer.class);
		grid.addColumn("Fila", String.class);
		grid.addColumn("Numero", Integer.class);
		grid.addColumn("Disponible" , Boolean.class);
		
		
		final VerticalLayout layout = new VerticalLayout();

	    List<Silla> sillas = sala.getSillas();

	    for(Silla a: sillas){
	    	grid.addRow(a.getId(), a.getFila() , a.getNumero() ,a.getDisponible());
	    }
	    layout.addComponents(grid);
		addComponents(layout);
	
		 
		
		
		
	}
	 
	 @Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}


}
