/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleDoJogo;

import static controleDoJogo.EscreverArquivo.setRecorde;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 *
 * @author Murilo Araujo
 */
public class LerArquivo {

    String pontuacao;
    int lerPontuacao;

    public LerArquivo() {
        try {
            FileInputStream arquivo = new FileInputStream("SalvarArquivo\\Recorde.txt");
            InputStreamReader input = new InputStreamReader(arquivo);
            BufferedReader buffer = new BufferedReader(input);

            pontuacao = buffer.readLine();
            lerPontuacao = Integer.parseInt(pontuacao);
   
            setRecorde(lerPontuacao);

        } catch (Exception e) {
            System.out.println("Erro ao inicializar o Arquivo");
        }
    }
}
