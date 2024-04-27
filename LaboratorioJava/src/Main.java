import java.util.ArrayList;
import java.util.List;

// Paso 1:  Se crea la interfaz Manejador
interface Manejador {
    void procesarSolicitud(Transaccion transaccion);
    void establecerSiguienteManejador(Manejador siguienteManejador);
}

// Paso 2:Se crea la enumeración TipoTransaccion
enum TipoTransaccion {
    RETIRO,
    DEPOSITO,
    CONSULTA_SALDO
}

// Paso 3: Implementando los manejadores concretos
class ManejadorRetiro implements Manejador {
    private Manejador siguienteManejador;

    @Override
    public void procesarSolicitud(Transaccion transaccion) {
        if (transaccion.obtenerTipo() == TipoTransaccion.RETIRO) {
            System.out.println("Procesando un retiro");
        } else {
            siguienteManejador.procesarSolicitud(transaccion);
        }
    }

    @Override
    public void establecerSiguienteManejador(Manejador siguienteManejador) {
        this.siguienteManejador = siguienteManejador;
    }
}

class ManejadorDeposito implements Manejador {
    private Manejador siguienteManejador;

    @Override
    public void procesarSolicitud(Transaccion transaccion) {
        if (transaccion.obtenerTipo() == TipoTransaccion.DEPOSITO) {
            System.out.println("Procesando un depósito");
        } else {
            siguienteManejador.procesarSolicitud(transaccion);
        }
    }

    @Override
    public void establecerSiguienteManejador(Manejador siguienteManejador) {
        this.siguienteManejador = siguienteManejador;
    }
}

class ManejadorConsultaSaldo implements Manejador {
    private Manejador siguienteManejador;

    @Override
    public void procesarSolicitud(Transaccion transaccion) {
        if (transaccion.obtenerTipo() == TipoTransaccion.CONSULTA_SALDO) {
            System.out.println("Procesando una consulta de saldo");
        } else {
            siguienteManejador.procesarSolicitud(transaccion);
        }
    }

    @Override
    public void establecerSiguienteManejador(Manejador siguienteManejador) {
        this.siguienteManejador = siguienteManejador;
    }
}

// Paso 4: Creando la clase ServicioCAJERO
class ServicioCAJERO {
    private Manejador manejadorRetiro;
    private Manejador manejadorDeposito;
    private Manejador manejadorConsultaSaldo;

    public ServicioCAJERO() {
        // Inicializar los manejadores concretos
        manejadorRetiro = new ManejadorRetiro();
        manejadorDeposito = new ManejadorDeposito();
        manejadorConsultaSaldo = new ManejadorConsultaSaldo();

        // Establecer la cadena de responsabilidad
        manejadorRetiro.establecerSiguienteManejador(manejadorDeposito);
        manejadorDeposito.establecerSiguienteManejador(manejadorConsultaSaldo);
    }

    // Método para procesar la transacción
    public void procesarTransaccion(Transaccion transaccion) {
        manejadorRetiro.procesarSolicitud(transaccion);
    }
}

// Paso 5: Se crea la clase Transaccion
class Transaccion {
    private TipoTransaccion tipo;

    public Transaccion(TipoTransaccion tipo) {
        this.tipo = tipo;
    }

    public TipoTransaccion obtenerTipo() {
        return tipo;
    }
}

// Paso 6: Probando la implementación
public class Main {
    public static void main(String[] args) {
        // Crear una instancia de ServicioCAJERO
        ServicioCAJERO servicioCAJERO = new ServicioCAJERO();

        // Crear diferentes transacciones y procesarlas
        Transaccion transaccionRetiro = new Transaccion(TipoTransaccion.RETIRO);
        servicioCAJERO.procesarTransaccion(transaccionRetiro);

        Transaccion transaccionDeposito = new Transaccion(TipoTransaccion.DEPOSITO);
        servicioCAJERO.procesarTransaccion(transaccionDeposito);

        Transaccion transaccionConsultaSaldo = new Transaccion(TipoTransaccion.CONSULTA_SALDO);
        servicioCAJERO.procesarTransaccion(transaccionConsultaSaldo);
    }
}
