package br.edu.ifsuldeminas.mch.sd.rmi.remote;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Operations extends Remote {
    Number sum(Number x, Number y) throws RemoteException;

    Number sub(Number x, Number y) throws RemoteException;

    Number mul(Number x, Number y) throws RemoteException;

    Number div(Number x, Number y) throws RemoteException;

    Number root(Number x, int n) throws RemoteException;

    Number power(Number x, int exponent) throws RemoteException;

    Number percentage(Number x, Number y) throws RemoteException;

    Number modulo(Number x, Number y) throws RemoteException;

    Number factorial(int x) throws RemoteException;

    String toBinary(Number x) throws RemoteException;

    String toHexadecimal(Number x) throws RemoteException;

    Number fromBinary(String binary) throws RemoteException;

    Number fromHexadecimal(String hex) throws RemoteException;

    String binaryToHexadecimal(String binary) throws RemoteException;

    String hexadecimalToBinary(String hex) throws RemoteException;

    List<String> lastOperations(int howMany) throws RemoteException;

    List<String> lastOperations() throws RemoteException;
}
