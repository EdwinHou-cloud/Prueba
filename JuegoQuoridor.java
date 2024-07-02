import java.io.*;

public class JuegoQuoridor {
    public int tamañoTablero = 9;
    public int maxMuros = 10;
    public Tablero tablero;
    public Jugador jugadorBlanco;
    public Jugador jugadorRojo;
    public Jugador jugadorActual;

    public JuegoQuoridor() {
        this.tablero = new Tablero(tamañoTablero);
        this.jugadorBlanco = new Jugador("Blanco", 0, tamañoTablero / 2, maxMuros); 
        this.jugadorRojo = new Jugador("Rojo", tamañoTablero - 1, tamañoTablero / 2, maxMuros); 
        this.jugadorActual = jugadorBlanco;
    }

    public void iniciar() throws IOException{
    BufferedReader leer = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            try {
            tablero.imprimirTablero(jugadorBlanco, jugadorRojo);
            System.out.println("Turno del jugador " + jugadorActual.getNombre());
            System.out.print("Ingrese su movimiento (ARRIBA, ABAJO, IZQUIERDA, DERECHA) o coloque muro (MURO x y H/V) o EXIT: ");
            
                String entrada = leer.readLine().toUpperCase();
                if (entrada.equals("EXIT")) {
                    finalizarJuego("Partida interrumpida");
                    break;
                }

                boolean movimientoExitoso;
                if (entrada.startsWith("MURO")) {
                    movimientoExitoso = colocarMuro(entrada);
                } else {
                    movimientoExitoso = moverJugador(entrada);
                }

                if (!movimientoExitoso) {
                    continue;
                }
                if (jugadorActual.hayGanador(tamañoTablero)) {
                    tablero.imprimirTablero(jugadorBlanco, jugadorRojo);
                    finalizarJuego(jugadorActual.getNombre() + " gana!");
                    break;
                }

                cambiarTurno();
            } catch (IOException e) {
                System.out.println("Error leyendo entrada: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public boolean colocarMuro(String comando) {
        String[] partes = comando.split(" ");
        if (partes.length != 4 || !partes[0].equals("MURO")) {
            System.out.println("Comando de muro inválido. Use: MURO x y H/V. Ejemplo: Muro 5 5 H");
            return false;
        }
        int x = Integer.parseInt(partes[1]) ;
        int y = Integer.parseInt(partes[2]) ;
        char orientacion = partes[3].charAt(0);
        boolean muroColocado = jugadorActual.colocarMuro(tablero, x, y, orientacion);
        return muroColocado;
    }
    
    public boolean moverJugador(String entrada) {
        boolean movimientoExitoso = jugadorActual.mover(tablero, entrada);
        return movimientoExitoso;
    }

    public void cambiarTurno() {
        if (jugadorActual == jugadorBlanco) {
            jugadorActual = jugadorRojo;
        } else {
            jugadorActual = jugadorBlanco;
        }
    }

    public void finalizarJuego(String mensaje) {
        System.out.println(mensaje);
        System.out.println("Posición inicial del Jugador Blanco: " + jugadorBlanco.getPosicionInicial());
        System.out.println("Posición inicial del Jugador Rojo: " + jugadorRojo.getPosicionInicial());
        System.out.println("Recorrido final del Jugador Blanco: " + jugadorBlanco.getHistorialMovimientos());
        System.out.println("Recorrido final del Jugador Rojo: " + jugadorRojo.getHistorialMovimientos());
    }
}