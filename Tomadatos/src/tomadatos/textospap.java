package tomadatos;
import javax.swing.*;
class textospap extends SwingWorker<Void, Character> {
    private String texto ;
    private final JTextArea textArea;

    public textospap(JTextArea textArea, String texto) {
        this.textArea = textArea;
        this.texto=texto;
    }

    @Override
    protected Void doInBackground() throws Exception {
        textArea.append("Jorward: ");
        for (char c : texto.toCharArray()) {
            Thread.sleep(10); // Pausa para dar un efecto de escritura más lento
            publish(c);
        }
        return null;
    }

    @Override
    protected void process(java.util.List<Character> chunks) {
        // Este método se ejecuta en el hilo de despacho de eventos de Swing
        // Actualizar la interfaz de usuario con el nuevo caracter
        for (Character chunk : chunks) {
            textArea.append(chunk.toString());
        }
    }
}





