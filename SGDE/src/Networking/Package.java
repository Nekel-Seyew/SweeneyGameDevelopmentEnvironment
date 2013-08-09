/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Networking;

import java.io.Serializable;

/**
 * A Class to help send packages with the servers.
 * @author Kyle Sweeney
 */
public abstract class Package implements Serializable {
    @Override
    public abstract boolean equals(Object p);

    @Override
    public abstract int hashCode();
}
