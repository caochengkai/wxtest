package com.cck.wxtest.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebListener
public class H2ServerListener implements ServletContextListener {

	private H2Server h2Server = new H2Server();
	
	private Logger logger=LoggerFactory.getLogger(H2ServerListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		h2Server.stopServer();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		logger.info("start a thread to  enable h2server");
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				h2Server.startServer();
			}
		});
		thread.start();
	}

}
