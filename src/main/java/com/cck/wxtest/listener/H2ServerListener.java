package com.cck.wxtest.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class H2ServerListener implements ServletContextListener {

	private H2Server h2Server = new H2Server();

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		h2Server.stopServer();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				h2Server.startServer();
			}
		});
		thread.start();
	}

}
