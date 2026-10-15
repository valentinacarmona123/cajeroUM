package cajeroautomatico;

import java.util.HashMap;

public class CajeroAutomatico {
 
    HashMap<Integer, Integer> billetes = new HashMap<>();

    public CajeroAutomatico() {

        //100 billetes de cada valor usando .put
        billetes.put(10000, 100);
        billetes.put(20000, 100);
        billetes.put(50000, 100);
        billetes.put(100000, 100);
    }

    public long calcularSaldoTotal() {
        int cant100 = billetes.get(100000);
        int cant50 = billetes.get(50000);
        int cant20 = billetes.get(20000);
        int cant10 = billetes.get(10000);

        long total = (100000 * cant100) + (50000 * cant50) + (20000 * cant20) + (10000 * cant10);

        return total;
    }

    public String Retirar(double monto) {

        // retiro mínimo de 10.000 y máximo de 1.000.000
        if (monto < 10000 || monto > 1000000) {
            return "Error: El retiro mínimo es de 10.000 y el máximo de 1.000.000.";
        }

        // la cantidad de dinero a retirar se debe dar en múltiplos de 10
        if (monto % 10 != 0) {
            return "Error: La cantidad debe ser un múltiplo de 10(10.000; 20.000; 50.000; 100.000).";
        }

        // cajero tenga saldo suficiente
        if (monto > calcularSaldoTotal()) {
            return "Error El cajero no tiene saldo suficiente.";
        }

        // cantidad de billetes
        int cant100 = billetes.get(100000);
        int cant50 = billetes.get(50000);
        int cant20 = billetes.get(20000);
        int cant10 = billetes.get(10000);

        int n100 = 0, n50 = 0, n20 = 0, n10 = 0;
        double restante = monto;

        // 11.reglas especiales
        if (monto == 50000) {
            n20 = 2;
            n10 = 1;
            restante = 0;
        } else if (monto == 100000) {
            n50 = 1;
            n20 = 2;
            n10 = 1;
            restante = 0;
        } else if (monto > 100000) {

            // 1 billete de 50.000, 2 de 20.000 y 1 de 10.000
            boolean BillSufi = (cant50 >= 1 && cant20 >= 2 && cant10 >= 1);

            if (BillSufi) {
                n50 = 1;
                n20 = 2;
                n10 = 1;
                cant50 = cant50 - 1;
                cant20 = cant20 - 2;
                cant10 = cant10 - 1;

                restante = restante - 100000;
            }

        }
        return "¡Retiro exitoso!";
    }
}
