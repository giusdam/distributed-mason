/*
  Copyright 2006 by Ankur Desai, Sean Luke, and George Mason University
  Licensed under the Academic Free License version 3.0
  See the file "LICENSE" for more information
*/

package sim.app.dpso;

/**
   @author Ankur Desai and Joey Harrison
*/
public class Rastrigin implements Evaluatable 
    {
    private static final long serialVersionUID = 1;

    public double calcFitness(double x, double y) 
        {
        return (1000 - (20 + x*x - 10*Math.cos(2*Math.PI*x) + y*y - 10*Math.cos(2*Math.PI*y)));
        }
    }
