package br.edu.ifsuldeminas.mch.sd.rmi.server;

import br.edu.ifsuldeminas.mch.sd.rmi.remote.Operations;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Calculator implements Operations {
    private final List<String> lastOperations = new ArrayList<>();

    public Number sum(Number x, Number y) {
        Number result = x.doubleValue() + y.doubleValue();
        log(x, "+", y, result);
        return result;
    }

    public Number sub(Number x, Number y) {
        Number result = x.doubleValue() - y.doubleValue();
        log(x, "-", y, result);
        return result;
    }

    public Number mul(Number x, Number y) {
        Number result = x.doubleValue() * y.doubleValue();
        log(x, "*", y, result);
        return result;
    }

    public Number div(Number x, Number y) {
        Number result = Double.NaN;
        if (y.doubleValue() != 0)
            result = x.doubleValue() / y.doubleValue();
        log(x, "/", y, result);
        return result;
    }

    @Override
    public Number root(Number x, int n) throws RemoteException {
        if (n <= 0) throw new IllegalArgumentException("A ordem da raiz deve ser maior que 0.");
        Number result = Math.pow(x.doubleValue(), 1.0 / n);
        log(x, "√", n, result);
        return result;
    }

    @Override
    public Number power(Number x, int exponent) throws RemoteException {
        Number result = Math.pow(x.doubleValue(), exponent);
        log(x, "^", exponent, result);
        return result;
    }

    @Override
    public Number percentage(Number x, Number y) throws RemoteException {
        Number result = x.doubleValue() * (y.doubleValue() / 100);
        log(x, "% of", y, result);
        return result;
    }

    @Override
    public Number modulo(Number x, Number y) throws RemoteException {
        Number result = x.doubleValue() % y.doubleValue();
        log(x, "%", y, result);
        return result;
    }

    @Override
    public Number factorial(int x) throws RemoteException {
        if (x < 0) throw new IllegalArgumentException("Fatorial não definido para números negativos.");

        int result = factorialWithTailRecursion(x, 1);

        log(String.valueOf(x), "!", String.valueOf(result));
        return result;
    }

    private int factorialWithTailRecursion(int n, int a) {
        if (n <= 0) {
            return a;
        }

        return factorialWithTailRecursion(n - 1, n * a);
    }

    @Override
    public String toBinary(Number x) throws RemoteException {
        String result = Integer.toBinaryString(x.intValue());
        log(String.valueOf(x), "toBinary", result);
        return result;
    }

    @Override
    public String toHexadecimal(Number x) throws RemoteException {
        String result = Integer.toHexString(x.intValue()).toUpperCase();
        log(String.valueOf(x), "toHex", result);
        return result;
    }

    @Override
    public Number fromBinary(String binary) throws RemoteException {
        Number result = Integer.parseInt(binary, 2);
        log(binary, "toDecimal", result.toString());
        return result;
    }

    @Override
    public Number fromHexadecimal(String hex) throws RemoteException {
        Number result = Integer.parseInt(hex, 16);
        log(hex, "toDecimal", result.toString());
        return result;
    }

    @Override
    public String binaryToHexadecimal(String binary) throws RemoteException {
        Number decimal = fromBinary(binary);
        log(binary, "binaryToHexadecimal", decimal.toString());
        return toHexadecimal(decimal);
    }

    @Override
    public String hexadecimalToBinary(String hex) throws RemoteException {
        int decimal = Integer.parseInt(hex, 16);
        String binary = Integer.toBinaryString(decimal);
        log(hex, "hexadecimalToBinary", binary);
        return binary;
    }

    public List<String> lastOperations(int howMany) {
        if (lastOperations.size() < howMany)
            return lastOperations();
        return new ArrayList<>(lastOperations.subList(
                lastOperations.size() - howMany, lastOperations.size()));
    }

    public List<String> lastOperations() {
        return lastOperations;
    }

    private void log(Number operatorOne, String operation, Number operatorTwo,
                     Number result) {

        String formattedOperation = String.format("%s %s %s = %s",
                operatorOne.toString(), operation, operatorTwo.toString(),
                result.toString());
        lastOperations.add(formattedOperation);
        System.out.printf("%s at %s\n", formattedOperation, new Date());
    }

    private void log(String operatorOne, String operation,
                     String result) {

        String formattedOperation = String.format("%s %s = %s",
                operatorOne, operation,
                result);
        lastOperations.add(formattedOperation);
        System.out.printf("%s at %s\n", formattedOperation, new Date());
    }
}
