package tomadatos;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextField;
import org.apache.commons.text.similarity.LevenshteinDistance;
public class Hilodatos extends Thread{
    private JTextField textos;
    private String busquedapro;
    public Hilodatos(JTextField textos) {
        this.textos=textos;
    }
    public String getBusquedapro() {
        return busquedapro;
    }
    @Override
        public void run(){
        LinkedList<String> lista= new LinkedList<>();
        LinkedList<String> lista2= new LinkedList<>();
        LinkedList<String> lista3= new LinkedList<>();
        LinkedList<String> lista4= new LinkedList<>();
        int x=0,preg,ref,paraint=0,contador=0,j=0,subidon,x1,x2,x3,x4,referir,indi, quet;
        int [] tipo; int [] guarda=new int [30];int [] guarda2=new int [30];
        String check, minusculas, parametro=String.valueOf((paraint+1));
        String [][] respg;
        File doc =new File("Pregutas.txt");
        Scanner obj;
        Scanner bus = new Scanner(System.in);
        try {
            obj = new Scanner(doc);
            while (obj.hasNextLine()){
                minusculas=obj.nextLine();
                minusculas=minusculas.toLowerCase();//String texto.tolowercase() lo convierte en minusculas.
                lista.add(x,minusculas);
                x++;
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Hilodatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        x=0;
        //Respuestas normales
        doc =new File("Respuestasn");
        try {
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
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Hilodatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        guarda[paraint]=contador;
        x=0;contador=0;paraint=0;
        parametro=String.valueOf((paraint+1));
        
        //respuestas java
        doc =new File("RespuestasJava.txt");
        try {
            obj=new Scanner(doc);
            while (obj.hasNextLine()){
            minusculas=obj.nextLine();
            if (minusculas.contains(parametro)){contador++;}
            else {
                guarda2[paraint]=contador;paraint++;contador=1;parametro=String.valueOf((paraint+1));
                parametro=parametro.concat(".");
            }
            lista3.add(x,minusculas);
            x++;
        }
        guarda2[paraint]=contador; //REPETIR TODO LO DE ARRIBA CON LAS DEMAS RESPUESTAScontador=0;paraint=0;
        //el numero de guarda significa las lineas que hay por cada respuesta
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Hilodatos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        //A partir de aca es la IA
        quet=0;
        tipo=new int[lista.size()];
        //System.out.println("Hola!, en que te puedo ayudar hoy! ");
            String busqueda;
            busqueda=textos.getText();
            busqueda=busqueda.toLowerCase();
            if (busqueda.equals("salir")){System.out.println("Gracias por usar esta IA");} //con esto se cierra la ia
            if (busqueda.contains("java")) {
                quet=1;
                busqueda=busqueda.replaceAll("java", "").replaceAll("\\s+", " ");} 
            else if (busqueda.contains("python")) {
                quet=2;
                busqueda=busqueda.replaceAll("python", "").replaceAll("\\s+", " ");}
            //System.out.println(busqueda);
            for(int y=0;y<lista.size();y++){
                check=lista.get(y);
                tipo[y]=LevenshteinDistance.getDefaultInstance().apply(busqueda, check);
            }
            
            preg=tipo[0];
            ref=0;
            for (int y=1;y<tipo.length;y++){
                if (tipo[y]<preg){preg=tipo[y];ref=y;}
            }
            if (ref==1 && busqueda.contains("desventajas"))ref=9;
            if (ref>=9) indi=1; else indi=0;
            //System.out.println("la mas acertada:"+preg+" y seria la pregunta "+(ref+1)+" en la posición "+ref);//borrar
            int z, z2;
            z=0;z2=0;subidon=0;
            respg=new String [5][400];
            switch (quet){
                case 0:
                for(int y=0;y<100;y++){
                    z2+=guarda[y];
                    if (y==ref) break;
                    z+=guarda[y];
                }
                x=0;x1=0;x2=0;x3=0;x4=0;
                for(z=z;z<z2;z++){
                    if (lista2.get(z).contains(".1")){respg[1][x1]=lista2.get(z).substring(4+indi);x1++;}
                    else if (lista2.get(z).contains(".2")){respg[2][x2]=lista2.get(z).substring(4+indi);x2++;}
                    else if (lista2.get(z).contains(".3")){respg[3][x3]=lista2.get(z).substring(4+indi);x3++;}
                    else if (lista2.get(z).contains(".4")){respg[4][x4]=lista2.get(z).substring(4+indi);x4++;}
                    else {respg[0][x]=lista2.get(z).substring(3+indi);x++;
                    }
                }
            break;
                case 1:
                    for(int y=0;y<100;y++){
                    z2+=guarda2[y];
                    if (y==ref) break;
                    z+=guarda2[y];
                }
                x=0;x1=0;x2=0;x3=0;x4=0;
                for(z=z;z<z2;z++){
                    if (lista3.get(z).contains(".1")){respg[1][x1]=lista3.get(z).substring(4+indi);x1++;}
                    else if (lista3.get(z).contains(".2")){respg[2][x2]=lista3.get(z).substring(4+indi);x2++;}
                    else if (lista3.get(z).contains(".3")){respg[3][x3]=lista3.get(z).substring(4+indi);x3++;}
                    else if (lista3.get(z).contains(".4")){respg[4][x4]=lista3.get(z).substring(4+indi);x4++;}
                    else {respg[0][x]=lista3.get(z).substring(3+indi);x++;
                    }
                }
                    break;
        }
            referir=5;
            for (int v=0;v<respg.length;v++) if (respg[v][0]==null)referir--;
            subidon=(int)(Math.random() * referir);
            for (int v=0;v<respg[0].length;v++){
                if (respg[subidon][v]!=null){/*System.out.println(respg[subidon][v]);*/
                busquedapro+=respg[subidon][v]+"\n";}
                else if (busqueda.equals("salir")) {busquedapro="Gracias por usar esta IA!";break;}
                }
            }
        
    }

