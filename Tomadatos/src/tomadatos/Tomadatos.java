package tomadatos;
import java.io.File;
import java.util.Scanner;
import java.util.LinkedList;
public class Tomadatos {
    public static void main(String[] args)throws Exception {
        LinkedList<String> lista= new LinkedList<>();
        LinkedList<String> lista2= new LinkedList<>();
        LinkedList<String> lista3= new LinkedList<>();
        LinkedList<String> lista4= new LinkedList<>();
        proceso p=new proceso();
        int x=0, preg, ref=0, paraint=0, contador=0, j=0;
        int [] tipo; int [] guarda=new int [30];
        String check, minusculas, parametro=String.valueOf((paraint+1));
        File doc =new File("C:\\Users\\Equipo\\Desktop\\Pregutas.txt");
        Scanner obj = new Scanner(doc);
        Scanner bus = new Scanner(System.in);
        while (obj.hasNextLine()){
            minusculas=obj.nextLine();
            minusculas=minusculas.toLowerCase();//String texto.tolowercase() lo convierte en minusculas.
            lista.add(x,minusculas);
            x++;
        }
        x=0;
        //Respuestas normales
        doc =new File("C:\\Users\\Equipo\\Desktop\\Respuestasn");
        obj=new Scanner(doc);
        while (obj.hasNextLine()){
            minusculas=obj.nextLine();
            if (minusculas.contains(parametro)){contador++;}
            else {
                guarda[paraint]=contador;paraint++;contador=1;parametro=String.valueOf((paraint+1));
                parametro=parametro.concat(".");
            }
            lista2.add(x,minusculas);
            x++;
        }
        guarda[paraint]=contador; //REPETIR TODO LO DE ARRIBA CON LAS DEMAS RESPUESTAScontador=0;paraint=0;
        //el numero de guarda significa las lineas que hay por cada respuesta
        
        
        //A partir de aca es la IA
        tipo=new int[lista.size()];
        System.out.println("Hola!, en que te puedo ayudar hoy! ");
        while (j==0){
            String busqueda;
            busqueda=bus.nextLine();
            busqueda=busqueda.toLowerCase();
            if (busqueda.equals("salir")){j=1;System.out.println("Gracias por usar esta IA");break;} //con esto se cierra la ia
            
            for(int y=0;y<lista.size();y++){
                check=lista.get(y);
                tipo[y]=p.compara(busqueda,check);
            }
            
            preg=tipo[0];
            ref=0;
            for (int y=1;y<tipo.length;y++){
                if (tipo[y]<preg){preg=tipo[y];ref=y;}
            }
            System.out.println("la mas acertada:"+preg+" y seria la pregunta "+(ref+1)+" en la posición "+ref);//borrar
            int z, z2;
            z=0;z2=0;
            for(int y=0;y<100;y++){
                z2+=guarda[y];
                if (y==ref) break;
                z+=guarda[y];
            }
            for(z=z;z<z2;z++){
                System.out.println(lista2.get(z));
            }
            System.out.println();
       }
    }
}