package br.edu.ifsuldeminas.mch.sd.rmi.client;

import br.edu.ifsuldeminas.mch.sd.rmi.remote.Operations;

import java.net.MalformedURLException;
import java.rmi.ConnectException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        try {
            Operations calc = (Operations) Naming.lookup("rmi://localhost/CalculatorService");

            System.out.println(calc.sum(100, 5));
            System.out.println(calc.sub(1040, 5));
            System.out.println(calc.mul(10, 200));
            System.out.println(calc.div(10.5, 0.1));
            System.out.println(calc.root(4, 2));
            System.out.println(calc.power(2, 10));
            System.out.println(calc.percentage(10, 100));
            System.out.println(calc.modulo(2, 3));
            System.out.println(calc.factorial(5));
            System.out.println(calc.toBinary(255));
            System.out.println(calc.toHexadecimal(10));
            System.out.println(calc.fromBinary("11111111"));
            System.out.println(calc.fromHexadecimal("1A"));

            List<String> lastOperations = calc.lastOperations(9);
            for (String operation : lastOperations) {
                System.out.printf("Operação: %s\n", operation);
            }
        } catch (MalformedURLException murle) {
            System.out.println("Erro na URL de acesso ao serviço.");
        } catch (NotBoundException nbe) {
            System.out.println("Erro ao se ligar ao stub remoto. Nome de serviço inválido.");
        } catch (ConnectException re) {
            System.out.println("Erro ao se conectar. Servidor não iniciado.");
        } catch (RemoteException re) {
            System.out.println("Erro na chamada remota: " + re.getMessage());
        }
    }
}
