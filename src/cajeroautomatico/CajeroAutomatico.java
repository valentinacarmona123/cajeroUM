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
            return "El retiro mínimo es de 10.000 y el máximo de 1.000.000."
                    + " "
                    +"Digite de nuevo un monto.";
            
        }

        // la cantidad de dinero a retirar se debe dar en múltiplos de 10
        if (monto % 10 != 0) {
            return "La cantidad debe ser un múltiplo de 10(10.000; 20.000; 50.000; 100.000)."+ " "
                    +"Digite de nuevo un monto.";
        }

        // cajero tenga saldo suficiente
        if (monto > calcularSaldoTotal()) {
            return "El cajero no tiene saldo suficiente."+ " "
                    +"Digite de nuevo un monto.";
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
        //Entregar restante en la menor cantidad de billetes
        if (restante >= 100000) {
            while (restante >= 100000 && cant100 > 0) {
            restante = restante - 100000; // Le restamos al total que falta
            cant100 = cant100 - 1;       // Quitamos un billete del cajero
            n100 = n100 + 1;             // Sumamos uno a los billetes que vamos a entregar
        }
        }
        if (restante >= 50000) {
            while (restante >= 50000 && cant50 > 0) {
            restante = restante - 50000;
            cant50 = cant50 - 1;
            n50 = n50 + 1;
        }
        }
        if (restante >= 20000) {
            while (restante >= 20000 && cant20 > 0) {
            restante = restante - 20000;
            cant20 = cant20 - 1;
            n20 = n20 + 1;
        }
        }
        if (restante >= 10000) {
            while (restante >= 10000 && cant10 > 0) {
            restante = restante - 10000;
            cant10 = cant10 - 1;
            n10 = n10 + 1;
        }
        }

        // Validación por si no hubo una combinación exacta
        if (restante > 0) {
            return "No hay billetes para este monto"+ " "
                    +"Digite de nuevo un monto.";
        }

        // Actualizar el HashMap con .put() con las nuevas cantidades
        billetes.put(100000, cant100);
        billetes.put(50000, cant50);
        billetes.put(20000, cant20);
        billetes.put(10000, cant10);

        // mostrar la distribución de billetes
        return "¡Retiro satisfactorio! \nBilletes entregados:\n" +
               (n100 > 0 ? "- 100.000: " + n100 + "\n" : "") +
               (n50 > 0  ? "- 50.000: " + n50 + "\n" : "") +
               (n20 > 0  ? "- 20.000: " + n20 + "\n" : "") +
               (n10 > 0  ? "- 10.000: " + n10 + "\n" : "") +
                "Limpie la pantalla para realizar un nuevo retiro.";
    }

 
        }
