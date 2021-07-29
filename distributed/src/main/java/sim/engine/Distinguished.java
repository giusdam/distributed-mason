/*
  Copyright 2020 by Sean Luke and George Mason University
  Licensed under the Academic Free License version 3.0
  See the file "LICENSE" for more information
*/

package sim.engine;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

/*
 * Interface that should be implemented by a class that needs to be a remote (Distinguish) Object
 * The modeler needs to implement the method of the class based on needs
 */

public interface Distinguished extends Remote {

	/**
	 * Create a remote request asking for some data to a remote DObject.
	 * 
	 * @param tag      is the tag used to understand which method to use to fill the
	 *                 promise
	 * @param argument is the optional argument that could be needed
	 * 
	 */
	public String createRemotePromise(int tag, Serializable args)  throws RemoteException;

	/**
	 * Respond to a remote request of some data. The method is used to fulfill a
	 * RemotePromise.
	 * 
	 * @param tag      is the tag used to understand which method to use to fill the
	 *                 promise
	 * @param argument is the optional argument that could be needed
	 * 
	 */
	public Serializable fillRemotePromise(Integer tag, Serializable argument) throws RemoteException;



}
