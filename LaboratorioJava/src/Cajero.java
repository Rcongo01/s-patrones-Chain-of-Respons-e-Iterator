import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Paso 2: Se crea una nueva clase llamada BilleteDisponible
class BilleteDisponible {
    private int valor;

    public BilleteDisponible(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}

// Paso 3: Se crea la clase principal CajeroAutomatico
class CajeroAutomatico {
    private List<BilleteDisponible> billetes;
    private int saldoTotal;

    public CajeroAutomatico() {
        this.billetes = new ArrayList<>();
        this.saldoTotal = 0;
    }

    // Paso 4: Implementando el método agregarBillete()
    public void agregarBillete(BilleteDisponible billete) {
        this.billetes.add(billete);
        this.saldoTotal += billete.getValor();
    }

    // Paso 5: Implementando el método dispensarDinero()
    public void dispensarDinero(int monto) {
        Iterator<BilleteDisponible> iterator = this.billetes.iterator();
        int montoRestante = monto;

        while (iterator.hasNext() && montoRestante > 0) {
            BilleteDisponible billete = iterator.next();
            if (billete.getValor() <= montoRestante) {
                montoRestante -= billete.getValor();
                iterator.remove();
            }
        }

        if (montoRestante > 0) {
            System.out.println("No hay suficientes billetes para dispensar el monto solicitado");
        } else {
            this.saldoTotal -= monto;
            System.out.println("Se ha dispensado el monto correctamente");
        }
    }
}

// Paso 6: funcionamiento del cajero automático
public class Cajero {
    public static void main(String[] args) {
        // Crear una instancia de CajeroAutomatico
        CajeroAutomatico cajero = new CajeroAutomatico();

        // Paso 6: Probar el funcionamiento del cajero automático
        // Agregar billetes al cajero
        cajero.agregarBillete(new BilleteDisponible(10));
        cajero.agregarBillete(new BilleteDisponible(20));
        cajero.agregarBillete(new BilleteDisponible(50));
        cajero.agregarBillete(new BilleteDisponible(100));

        // Probar dispensar dinero
        cajero.dispensarDinero(30); // Se espera que se dispensen billetes de 20 y 10
        cajero.dispensarDinero(100); // Se espera que se imprima un mensaje de error
    }
}
