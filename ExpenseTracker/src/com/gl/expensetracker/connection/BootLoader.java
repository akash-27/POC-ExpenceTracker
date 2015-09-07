package com.gl.expensetracker.connection;

import java.io.IOException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class BootLoader implements ServletContextListener {


	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("Context destroyed....");
	}


	@Override
	public void contextInitialized(ServletContextEvent arg0) {

		try {
			ApplicationUtilities.EXPENSE_TRACKER_PROPERTIES.load(this.getClass().getResourceAsStream("/expensetracker.properties"));
			System.out.println("Application started successfully...");
		} catch (IOException e) {
			System.out.println("EXception");
			e.printStackTrace();
		}
	}
}
