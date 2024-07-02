public class Tablero {
    public char[][] tablero;
    public int tamañoTablero;

    public Tablero(int tamañoTablero) {
        this.tamañoTablero = tamañoTablero;
        this.tablero = new char[tamañoTablero][tamañoTablero];
        for (int i = 0; i < tamañoTablero; i++) {
            for (int j = 0; j < tamañoTablero; j++) {
                tablero[i][j] = '.';
            }
        }
    }

    public boolean esMovimientoValido(int x, int y) {
        return x >= 0 && x < tamañoTablero && y >= 0 && y < tamañoTablero && tablero[x][y] == '.';
    }

    public boolean esColocacionMuroValida(int x, int y, char orientacion) {
        if (orientacion == 'H') {
            return x >= 0 && x < tamañoTablero - 1 && y >= 0 && y < tamañoTablero && tablero[x][y] == '.' && tablero[x + 1][y] == '.';
        } else {
            return x >= 0 && x < tamañoTablero && y >= 0 && y < tamañoTablero - 1 && tablero[x][y] == '.' && tablero[x][y + 1] == '.';
        }
    }
    
    public void colocarMuro(int fila, int columna, char orientacion) {
        int x = fila - 1;  
        int y = columna - 1;
    
        if (orientacion == 'H') {
            if (!esColocacionMuroValida(x, y, 'H')) {
                System.out.println("No se puede colocar un muro horizontal aquí.");
                return;
            }
            tablero[x][y] = '_';
            tablero[x][y + 1] = '_';
        } else {
            if (!esColocacionMuroValida(x, y, 'V')) {
                System.out.println("No se puede colocar un muro vertical aquí.");
                return;
            }
            tablero[x][y] = '|';
            tablero[x + 1][y] = '|';
        }
    }

    public void actualizarPosicion(int viejaX, int viejaY, int nuevaX, int nuevaY, char simbolo) {
        tablero[viejaX][viejaY] = '.';
        tablero[nuevaX][nuevaY] = simbolo;
    }

    public void imprimirTablero(Jugador jugadorBlanco, Jugador jugadorRojo) {
        char[][] tableroTemporal = new char[tamañoTablero][tamañoTablero];
        for (int i = 0; i < tamañoTablero; i++) {
            System.arraycopy(tablero[i], 0, tableroTemporal[i], 0, tamañoTablero);
        }
        tableroTemporal[jugadorBlanco.getX()][jugadorBlanco.getY()] = 'B';
        tableroTemporal[jugadorRojo.getX()][jugadorRojo.getY()] = 'R';

        // Imprimir la línea de letras de las columnas
        System.out.print("    ");
        for (int c = 1; c <= tamañoTablero; c++) {
            System.out.print(c + " ");
        }
        System.out.println();

        // Imprimir el borde superior
        System.out.print("  ");
        for (int j = 0; j < 21; j++) {
            System.out.print("-");
        }
        System.out.println();

        // Imprimir las filas del tablero con bordes verticales y números de fila
        for (int j = 0; j < tamañoTablero; j++) {
            System.out.print((j + 1) + " | ");
            for (int i = 0; i < tamañoTablero; i++) {
                System.out.print(tableroTemporal[j][i] + " ");
            }
            System.out.print("|");
            System.out.println();
        }

        // Imprimir el borde inferior
        System.out.print("  ");
        for (int j = 0; j < 21; j++) {
            System.out.print("-");
        }
        System.out.println();
}
}