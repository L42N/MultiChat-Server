package junitTest;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import COMP1549_G4.Client;
import COMP1549_G4.Server;
import COMP1549_G4.ServerGUI;

public class TestClient {

	/*
	 * Test client correct ID
	 */
	@Test
	public void clientTest() {
		Client c = new Client("Jake", "127.0.0.1", 7000); // Create new Client called Jake and connect to the server IP and PORT
		assertEquals("Jake",c.getUsername()); // Retrieve ID from username Jake
	}
	
	/*
	 * Test if the server saves a connected client in the array currentConnections
	 */
	@Test
	public void connectedClient() {
		ServerGUI sG = new ServerGUI(); // Create new server instance
		Server server = new Server(sG.serverScreen);
		try {
			server.startServer();
			Client c = new Client("Jake", "127.0.0.1", 7000); // Connect client Jake to server using IP and PORT
			c.contactServer();
			assertEquals(1,server.currentConnections.size()); // Check if user is added to currentConnections
			server.serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Test to check if first client is assigned as server's coordinator
	 */
	@Test
	public void coordinatorTest() {
		ServerGUI sG = new ServerGUI();
		Server server = new Server(sG.serverScreen); // Create a new server instance
		try {
			server.startServer();
			Client c = new Client("Jake", "127.0.0.1", 7000); // Add client Jake to the server via IP and PORT
			c.contactServer();
			Client c2 = new Client("Ben", "127.0.0.1", 7000); // Add client Ben to the server via IP and PORT
			c2.contactServer();
			assertEquals("Jake",server.getCoordinator()); // First member to join the server recieves the coordinator role
			server.serverSocket.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
