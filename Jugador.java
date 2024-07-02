public class Jugador {
    public String nombre;
    public int x, y, inicialX, inicialY, murosRestantes;
    public StringBuilder historialMovimientos;

    //Constructor 
    public Jugador(String nombre, int inicialX, int inicialY, int murosRestantes) {
        this.nombre = nombre;
        this.x = inicialX;
        this.y = inicialY;
        this.inicialX = inicialX;
        this.inicialY = inicialY;
        this.murosRestantes = murosRestantes;
        this.historialMovimientos = new StringBuilder();
    }

    //Obtener el nombre y las posiciones
    public String getNombre() {
        return nombre;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public boolean mover(Tablero tablero, String direccion) {
        int nuevaX = x;
        int nuevaY = y;
        switch (direccion) {
            case "IZQUIERDA":
                nuevaY--;
                break;
            case "DERECHA":
                nuevaY++;
                break;
            case "ARRIBA":
                nuevaX--;
                break;
            case "ABAJO":
                nuevaX++;
                break;
            default:
                System.out.println("Dirección de movimiento inválida. Use: ARRIBA, ABAJO, IZQUIERDA o DERECHA.");
                return false; //False si la direccion del movimiento es invalida
        }
        if (tablero.esMovimientoValido(nuevaX, nuevaY)) {
            tablero.actualizarPosicion(x, y, nuevaX, nuevaY, getSimbolo());
            x = nuevaX;
            y = nuevaY;
            historialMovimientos.append("(").append(x).append(", ").append(y).append(") ");
            return true; //True si el moviemiento es valido.
        } else {
            System.out.println("Movimiento inválido. La posición está fuera de límites o bloqueada.");
            return false; //False si el moviemiento es invalido.
        }
    }

    public boolean colocarMuro(Tablero tablero, int x, int y, char orientacion) {
        if (murosRestantes <= 0) {
            System.out.println("No quedan muros por colocar.");
            return false;
        }
        if (tablero.esColocacionMuroValida(x, y, orientacion)) {
            tablero.colocarMuro(x, y, orientacion);
            murosRestantes--;
            return true;
        } else {
            System.out.println("Colocación de muro inválida. La posición está fuera de límites o se superpone con otro muro.");
            return false;
        }
    }

    public boolean hayGanador(int tamanoTablero) {
        return (nombre.equals("Blanco") && x == tamanoTablero - 1) || (nombre.equals("Rojo") && x == 0);
    }

    public String getPosicionInicial() {
        return "(" + inicialX + ", " + inicialY + ")";
    }

    public String getHistorialMovimientos() {
        return historialMovimientos.toString();
    }

    public char getSimbolo() {
        if (nombre.equals("Blanco")) {
            return 'B';
        } else {
            return 'R';
        }
    }
}