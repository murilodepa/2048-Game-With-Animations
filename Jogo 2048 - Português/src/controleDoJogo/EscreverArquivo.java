/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleDoJogo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author Murilo Araujo
 */
public class EscreverArquivo {

    public static int recorde;

    public EscreverArquivo() throws IOException {
        try {
            FileOutputStream arquivo = new FileOutputStream("SalvarArquivo\\Recorde.txt");

            PrintWriter printar = new PrintWriter(arquivo);

            printar.print("" + recorde);

            printar.close();
            arquivo.close();

        } catch (FileNotFoundException e) {
            System.out.println("Erro ao inicializar o Arquivo");
        }
    }
 
    public static int getRecorde() {
        return recorde;
    }

    public static void setRecorde(int recorde) {
        EscreverArquivo.recorde = recorde;
    }
}
