import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.PrintWriter;

public class App {
    public static void main(String[] args) {
        try {
            Document doc = Jsoup.connect("https://libreriasoriano.com/es/index.php")
                    .userAgent("Mozilla/5.0")
                    .get();

            Elements libros = doc.select(".listado_libros li");
            PrintWriter pw = new PrintWriter(new FileWriter("libros.txt"));

            for (Element libro : libros) {
                String titulo = libro.select(".title a").text();
                String autor = libro.select(".creator").text().trim();
                String precio = libro.select(".precio strong").text();
                String sinopsis = libro.select(".mulsinop").text();

                if (!titulo.isEmpty()) {
                    String lineaLibro = "Titulo: " + titulo + "\n" +
                            "Autor: " + autor + "\n" +
                            "Precio: " + precio + "\n" +
                            "Sinopsis: " + sinopsis + "\n" +
                            "--------------------------------------------------";

                    // Muestra cada libro directamente en la consola
                    System.out.println(lineaLibro);

                    // Guarda el mismo formato en el fichero
                    pw.println(lineaLibro);
                }
            }

            pw.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}