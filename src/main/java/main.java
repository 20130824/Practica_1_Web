/**
 * Created by Isaac Perez on 23/5/2017.
 */

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.Scanner;

import static jdk.nashorn.internal.objects.Global.print;

public class main {
    public static void main(String[] args) throws IOException{


        System.out.println("Inserte la url a la que desea acceder");
        Scanner entrada = new Scanner(System.in);
        String url = entrada.nextLine();
        Document doc = Jsoup.connect(url).get();

        BufferedWriter writer = null;
        try
        {
           writer = new BufferedWriter( new FileWriter("test.txt"));
            writer.write(doc.toString());

        }
        catch ( IOException e)
        {
        }

        FileReader in = new FileReader("test.txt");
        BufferedReader br = new BufferedReader(in);
        int n = 0;
        while (br.readLine() != null) {
            n++;
        }

        System.out.println("El recuso tiene " + n + " Lineas." );
        in.close();



        Elements parrafos = doc.select("p");
        System.out.println("El documento tiene " + parrafos.size() + " parrafos.");



        Elements imagenes = doc.select("img");
        System.out.println(String.format("El documento tiene " + imagenes.size() + " imagenes."));

        Elements formulariosGet = doc.select("form[method=get]");
        System.out.println(String.format("El documento tiene " + formulariosGet.size() + " formularios get."));

        Elements formulariosPost = doc.select("form[method=post]");
        System.out.println(String.format("El documento tiene " + formulariosPost.size() + " formularios Post."));


        for (Element formGet : formulariosGet){
            Elements inputs = formGet.select("input");
            for (Element input : inputs){
                System.out.println("Input type: " + input.attr("type") + "\n");
            }

        }

        for (Element formPost : formulariosPost){
            Elements inputs = formPost.select("input");
            for (Element input : inputs){
                System.out.println("Input type: " + input.attr("type") + "\n");
            }

            String ActionURL = (formPost.attr("action"));
            Document docF = Jsoup.connect(url + ActionURL).data("asignatura", "practica1").post();
            System.out.println(docF.toString());


        }




    }


}
