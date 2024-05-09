package junitTest;

import COMP1549_G4.*;
import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class TestServer {

	/*
	 * Test server-client connection
	 */
	@Test
	public void testConnection() throws IOException { //Test the server-client connection can be made
		ServerGUI sG = new ServerGUI(); // Create new instance of server
		Server server = new Server(sG.serverScreen);
		server.startServer();
		Client c = new Client("Jake", "127.0.0.1", 7000); // Connect client Jake to the server using the IP and the PORT
		c.contactServer();
		assertEquals(false, server.serverSocket.isClosed());
		server.closeServer();
	}
	
	/* 
	 * Test if the server is closed properly
	 */
	@Test
	public void closeConnection() throws IOException { // Test if the server is closed
		ServerGUI sG = new ServerGUI(); // Create a new instance of the server
		Server server = new Server(sG.serverScreen);
		server.startServer();
		Client c = new Client("Jake", "127.0.0.1", 7000); // Connect client called Jake to the server using IP and the PORT
		c.contactServer();
		server.closeServer(); // Close the server
		assertEquals(true, server.serverSocket.isClosed()); // Check if the server is closed successfully 
	}

}
