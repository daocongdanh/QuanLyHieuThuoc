/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package gui.common;

import java.rmi.RemoteException;

/**
 *
 * @author Hoang
 */
public interface TableActionEventOneAction {
    public void onAction( int row) throws RemoteException;
}
