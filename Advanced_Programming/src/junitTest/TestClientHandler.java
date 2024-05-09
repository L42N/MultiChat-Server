package junitTest;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Calendar;
import java.util.Formatter;

import org.junit.Test;

import COMP1549_G4.Client;
import COMP1549_G4.Server;
import COMP1549_G4.ServerGUI;

public class TestClientHandler {

	/*
	 * Test if the server has successfully created a ClientHandler 
	 * thread for a new client.
	 */
	@Test
	public void testClientHandler() throws IOException {
		ServerGUI sG = new ServerGUI(); // Create instance of server
		Server server = new Server(sG.serverScreen);
		server.startServer();
		Client c = new Client("Jake", "127.0.0.1", 7000); // Create a new client called Jake and connect to the server using IP and PORT 
		c.contactServer();
		assertEquals(Thread.State.RUNNABLE, server.currentConnections.get(0).getState()); // Check if clienthandler created
		server.closeServer();
	}

	/*
	 * Test ClientHandler getTime() method
	 * toString() has been implemented to avoid class mismatch 
	 */
	@Test
	public void testGetTime() throws IOException { // Testing the GetTime method
		ServerGUI sG = new ServerGUI(); // Create a new server instance
		Server server = new Server(sG.serverScreen);
		server.startServer();
		Client c = new Client("Jake", "127.0.0.1", 7000); // Connect client Jake to server using IP and PORT
		c.contactServer();
		Formatter fmt = new Formatter();
		Calendar cal = Calendar.getInstance();
		fmt = new Formatter();
		fmt.format("%tl:%tM",cal,cal);
		assertEquals(fmt.toString(),server.currentConnections.get(0).getTime().toString());
	}
}
