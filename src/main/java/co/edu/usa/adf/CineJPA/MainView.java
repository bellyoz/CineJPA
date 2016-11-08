package co.edu.usa.adf.CineJPA;

import javax.swing.ButtonModel;

import com.vaadin.annotations.DesignRoot;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.declarative.Design;

@DesignRoot
public class MainView extends VerticalLayout implements View {
	
	  public MainView( Navigator navigator) {
	         
	        setSizeFull();
	        Button button = new Button("Ir a la vista inicial (StartView)");
	       	button.addClickListener(e -> {
	       		navigator.navigateTo("");
	       	});
	        addComponent(button);
	        setComponentAlignment(button, Alignment.TOP_LEFT);
	         
	    }
	     
	    @Override
	    public void enter(ViewChangeEvent event) {
	       
	    }
	 
	    
	 

}
