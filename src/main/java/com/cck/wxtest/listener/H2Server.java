package com.cck.wxtest.listener;
import org.h2.tools.Server;

public class H2Server {

	private Server webServer;
	private Server tcpServer;

	public String WEB_PORT = "8082";
	public String TCP_PORT = "9092";
	public String WEB_CONSOLE_URL = "http://localhost:8082/";

	public void startServer() {
		try {
			System.out.println("Start H2 database");
			tcpServer = Server.createTcpServer(new String[] { "-tcpPort", TCP_PORT }).start();
			webServer = Server.createWebServer(new String[] { "-webPort", WEB_PORT }).start();
			System.out.println("Web server running on http://localhost:8082 (only local connections)");
			System.out.println("TCP server running on tcp://localhost:9092 (only local connections)");
			System.out.println("Hit Enter to stop H2 database");
			while (true) {
				char c = (char) System.in.read();
				if (c == '\n') {
					System.out.println("Stop H2 database");
					stopServer();
					System.exit(0);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(-1);
		}
	}

	public void stopServer() {
		if (tcpServer != null) {
			tcpServer.stop();
		}
		if (webServer != null) {
			webServer.stop();
		}
	}

	public static void main(String[] args) {
		new H2Server().startServer();
	}
}
