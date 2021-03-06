/*
  Copyright 2006 by Ankur Desai, Sean Luke, and George Mason University
  Licensed under the Academic Free License version 3.0
  See the file "LICENSE" for more information
*/

package sim.app.dpso;

/**
   @author Ankur Desai and Joey Harrison
*/
public class Rosenbrock implements Evaluatable 
    {
    private static final long serialVersionUID = 1;

    public double calcFitness(double x, double y) 
        {
        double expr1 = (x*x - y);
        double expr2 = 1 - x;
        return (1000 - (100 * expr1*expr1 + expr2*expr2)); 
        }
    }
