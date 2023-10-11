package tomadatos;
import org.apache.commons.text.similarity.LevenshteinDistance;
public class proceso {
    public int compara(String busqueda, String texto){
        return LevenshteinDistance.getDefaultInstance().apply(busqueda, texto);
    };
}
