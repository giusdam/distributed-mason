/*
  Copyright 2006 by Sean Luke and George Mason University
  Licensed under the Academic Free License version 3.0
  See the file "LICENSE" for more information
*/

package sim.app.dwanderer;

import java.io.Serializable;
import java.rmi.RemoteException;

import sim.engine.*;
import sim.engine.Promised;
import sim.util.*;

public class Wanderer extends DSteppable implements Distinguished {

	private static final long serialVersionUID = 1;

	// position of the agent in the field
	public Double2D loc;

	public int myPID = DSimState.getPID();

	public String name;

	Promised remoteResult;

	public Wanderer(final Double2D location, String name) {
		this.loc = location;
		this.name = name;
	}
	
	public void step(final SimState state) {
		final Wanderers wanderersState = (Wanderers) state;

		String otherAgentID = name.equals("A")?"B":"A";

		// moving the agents clockwise to go to the center of another area
		// in this way the agents are always in different processors

		if ((loc.x >= 50 && loc.x < 150) && (loc.y == 50)) {
			loc = new Double2D(loc.x + 5, loc.y);
		} else if ((loc.x == 150) && (loc.y >= 50 && loc.y < 150)) {
			loc = new Double2D(loc.x, loc.y + 5);
		} else if ((loc.x > 50 && loc.x <= 150) && (loc.y == 150)) {
			loc = new Double2D(loc.x - 5, loc.y);
		} else if ((loc.x == 50) && (loc.y > 50 && loc.y <= 150)) {
			loc = new Double2D(loc.x, loc.y - 5);
		}
	
		try {
			//check if i have a message to read
			if(remoteResult != null && remoteResult.isReady()) {
				System.out.println(
					state.schedule.getSteps() + "]" +
					"I am " + name + " my friend " + otherAgentID +
					" was on proc " + remoteResult.get());
			}
			
			//send remote message to another agent 
			remoteResult = ((DSimState)state).sendRemoteMessage(otherAgentID, 0, null);
			System.out.println("wanderer from " + this.loc + " to " + loc);
			wanderersState.wanderers.moveAgent(loc, this);
		} catch (Exception e) {
			System.err.println("Error on agent " + this + " in step " + wanderersState.schedule.getSteps() + "on PID "
					+ wanderersState.getPartition().getPID());
			throw new RuntimeException(e);
		}
	}

	// method implemented by the modeler that will fill the remoteMessage
	public Serializable remoteMessage(int tag, Serializable argument) throws RemoteException {
		switch (tag) {
			case 0:
				return DSimState.getPID();
			default:
				return null;
		}
	}
	
	public String distinguishedName() {
		return name;
	}
}