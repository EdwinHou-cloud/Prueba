import java.io.IOException;

public class MainQuoridor {
    public static void main(String[] args) throws IOException{
        try {
            JuegoQuoridor juego = new JuegoQuoridor();
            juego.iniciar();
        } catch (IOException e) {
            System.err.println("Error al iniciar el juego: " + e.getMessage());
        }
    }
}
