package co.edu.usa.adf.CineJPA;

import javax.swing.plaf.basic.BasicRadioButtonMenuItemUI;

import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

public class StartView extends VerticalLayout implements View {
	Navigator navigator;

	public StartView(Navigator navigator) {
		this.navigator = navigator;
		setSizeFull();
		addComponent(menubar());
	}
	

	public MenuBar menubar() {

		MenuBar barmenu = new MenuBar();
		
		MenuItem pelicula = barmenu.addItem("Administrar Peliculas", null, null);
		MenuItem sala = barmenu.addItem("Administrar Salas", null, null);
		MenuItem silla = barmenu.addItem("Administrar silla", null, null);
		MenuItem admin = barmenu.addItem("Administrar Sala-Silla", null, null);
		MenuItem funcion = barmenu.addItem("Administrar funciones", null, null);
		MenuItem ticket = barmenu.addItem("Administrar tikects", null, null);

		pelicula.setCommand(new MenuBar.Command() {

			@Override
			public void menuSelected(MenuItem selectedItem) {
				// TODO Auto-generated method stub
				navigator.navigateTo("pelicula");
			}
		});
		sala.setCommand(new MenuBar.Command() {

			@Override
			public void menuSelected(MenuItem selectedItem) {
				// TODO Auto-generated method stub
				navigator.navigateTo("sala");

			}
		});
		silla.setCommand(new MenuBar.Command() {

			@Override
			public void menuSelected(MenuItem selectedItem) {
				// TODO Auto-generated method stub
				navigator.navigateTo("silla");

			}
		});
		admin.setCommand(new MenuBar.Command() {

			@Override
			public void menuSelected(MenuItem selectedItem) {
				// TODO Auto-generated method stub
				navigator.navigateTo("admin");

			}
		});
		funcion.setCommand(new MenuBar.Command() {

			@Override
			public void menuSelected(MenuItem selectedItem) {
				// TODO Auto-generated method stub
				navigator.navigateTo("funcion");

			}
		});
		ticket.setCommand(new MenuBar.Command() {

			@Override
			public void menuSelected(MenuItem selectedItem) {
				// TODO Auto-generated method stub
				navigator.navigateTo("ticket");

			}
		});
		return barmenu;
	}

	@Override
	public void enter(ViewChangeEvent event) {
		Notification.show("Welcome to the Animal Farm");
	}

}
