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
public interface TableActionEventReturnManage {

    public void onReturned(int row) throws RemoteException;

    public void onDamaged(int row) throws RemoteException;
}
