/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telasEFuncoesComplementares;

import controleDoJogo.TelaGraficaEDeControle;
import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import jogo2048.Jogo2048;

/**
 *
 * @author Murilo Araujo
 */
public class TelasDoJogo extends Jogo2048 {

    // Declaração da JFrame utilzada
    public static JFrame telaDoJogo = new JFrame();

    // Declarando corFundo e cor das linhas
    private static Color corFundo, corLinhas;

    public TelasDoJogo() {

        corFundo = new Color(102, 178, 255);
        corLinhas = new Color(0, 128, 255);

        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException exception) {
                JOptionPane.showMessageDialog(null, "Erro ao criar a frame! " + exception.getMessage());
            }
            editarJanelaDoJogo();
        });
    }

    public void editarJanelaDoJogo() {
        telaDoJogo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telaDoJogo.setTitle("JOGO 2048");
        telaDoJogo.setResizable(false);
        telaDoJogo.add(new TelaGraficaEDeControle(), BorderLayout.CENTER);
        telaDoJogo.pack();
        telaDoJogo.setLocationRelativeTo(null);
        telaDoJogo.setBackground(new Color(102, 178, 255));
        telaDoJogo.setVisible(true);
    }

    // Desenhar o logo do JOGO, já que aparece em 3 telas
    public static void imprimirCabeçalho(Graphics2D g) {
        // Desenhar o logo 2048
        g.setColor(Color.red); //Escolhendo Cor Vermelha 
        g.fillRoundRect(265, 14, 135, 135, 25, 25); //Desenhar o LOGO
        g.setColor(Color.BLACK); //Escolhendo Cor Preta 
        g.drawRoundRect(265, 14, 135, 135, 25, 25); //Desenhar a borda do LOGO
        g.setFont(new Font("Arial", Font.BOLD, 50)); //Escrever 2048
        g.drawString("2048", 275, 98); //Escrever 2048

        // Desenhar RECORDE
        g.setColor(Color.red);
        g.fillRoundRect(100, 14, 153, 65, 25, 25);
        g.setColor(Color.BLACK);
        g.drawRoundRect(100, 14, 153, 65, 25, 25);
        g.setColor(corFundo);
        g.fillRoundRect(120, 40, 113, 35, 25, 25);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 21)); //RECORDE
        g.drawString("RECORDE", 124, 35); //RECORDE

        // Desenhar Pontuação
        g.setColor(Color.red);
        g.fillRoundRect(100, 84, 153, 65, 25, 25);
        g.setColor(Color.BLACK);
        g.drawRoundRect(100, 84, 153, 65, 25, 25);
        g.setColor(corFundo);
        g.fillRoundRect(120, 110, 113, 35, 25, 25);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 21)); //PONTUACAO
        g.drawString("PONTUAÇÃO", 111, 105); //PONTUACAO

        // Desenhar NOVO JOGO
        g.setColor(Color.red);
        g.fillRoundRect(412, 79, 70, 70, 25, 25);
        g.setColor(Color.BLACK);
        g.drawRoundRect(412, 79, 70, 70, 25, 25);
        g.setFont(new Font("Arial", Font.BOLD, 22)); //NOVO JOGO
        g.drawString("NOVO", 415, 110); //NOVO
        g.drawString("JOGO", 415, 135); //JOGO
    }

    // Desenhar a tela inicial antes de mostrar a principal
    public static void imprimirTelaInicial(Graphics2D g) {
        // Escolhendo a espessura da borda
        g.setStroke(new BasicStroke(7));

        // Desenhando o fundo para colocar os blocos sobre ele
        g.setColor(Color.BLACK);
        g.drawRoundRect(100, 160, 460, 475, 25, 25);
        g.setColor(corLinhas);
        g.fillRoundRect(100, 160, 460, 475, 25, 25);

        // Desenhando o bloco para escrever jogo 2048
        g.setStroke(new BasicStroke(7));
        g.setColor(Color.BLACK);
        g.drawRoundRect(130, 180, 400, 150, 25, 25);
        g.setColor(Color.red);
        g.fillRoundRect(130, 180, 400, 150, 25, 25);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 72)); //Escrever Jogo 2048
        g.drawString("JOGO 2048", 135, 280); //Escrever Jogo 2048

        // Escolhendo a espessura da borda
        g.setStroke(new BasicStroke(4));

        // Desenhando o bloco para escrever Novo Jogo
        g.drawRoundRect(206, 360, 250, 70, 25, 25);
        g.setColor(Color.red);
        g.fillRoundRect(206, 360, 250, 70, 25, 25);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 40)); //Escrever Novo Jogo
        g.drawString("NOVO JOGO", 208, 408); //Escrever Novo Jogo

        // Desenhando o bloco para escrever GitHub
        g.drawRoundRect(250, 455, 160, 70, 25, 25);
        g.setColor(Color.red);
        g.fillRoundRect(250, 455, 160, 70, 25, 25);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 40)); //Escrever GitHub
        g.drawString("GITHUB", 253, 505); //Escrever GitHub

        // Desenhando o bloco para escrever SAIR
        g.setStroke(new BasicStroke(4));
        g.drawRoundRect(250, 550, 160, 70, 25, 25);
        g.setColor(Color.red);
        g.fillRoundRect(250, 550, 160, 70, 25, 25);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 40)); //Escrever SAIR
        g.drawString("SAIR", 284, 600); //Escrever SAIR

    }

    // Tela Ganhar, caso o usuário consiga um bloco com 2048 
    public static void imprimirTelaGanhou(Graphics2D g) {
        // Escolhendo a espessura da borda
        g.setStroke(new BasicStroke(7));

        // Desenhando o fundo para colocar os blocos sobre ele
        g.setColor(Color.BLACK);
        g.drawRoundRect(100, 160, 460, 475, 25, 25);
        g.setColor(corLinhas);
        g.fillRoundRect(100, 160, 460, 475, 25, 25);

        // Desenhando o bloco para escrever PARABÉNS
        g.setStroke(new BasicStroke(7));
        g.setColor(Color.BLACK);
        g.drawRoundRect(130, 180, 400, 150, 25, 25);
        g.setColor(Color.red);
        g.fillRoundRect(130, 180, 400, 150, 25, 25);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 72)); //PARABÉNS
        g.drawString("PARABÉNS", 130, 280); //Escrever PARABÉNS

        // Desenhando o bloco para escrever VOCÊ GANHOU
        g.setStroke(new BasicStroke(5));
        g.drawRoundRect(130, 385, 400, 70, 25, 25);
        g.setColor(Color.red);
        g.fillRoundRect(130, 385, 400, 70, 25, 25);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 50)); //Escrever VOCê GANHOU
        g.drawString("VOCÊ GANHOU", 140, 438); //Escrever VOCê GANHOU

        // Desenhando o bloco para escrever JOGAR NOVAMENTE
        g.drawRoundRect(130, 510, 160, 80, 25, 25);
        g.setColor(Color.red);
        g.fillRoundRect(130, 510, 160, 80, 25, 25);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 24)); //Escrever JOGAR NOVAMENTE
        g.drawString("JOGAR", 167, 540); //Escrever JOGAR
        g.drawString("NOVAMENTE", 133, 572); //Escrever NOVAMENTE

        // Desenhando o bloco para escrever SAIR
        g.drawRoundRect(390, 515, 140, 70, 25, 25);
        g.setColor(Color.red);
        g.fillRoundRect(390, 515, 140, 70, 25, 25);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 25)); //Escrever SAIR
        g.drawString("SAIR", 431, 556); //Escrever SAIR 
    }

    // Simulação da tela de derrota caso não haja mais movimentos no jogo
    public static void imprimirTelaPerdeu(Graphics2D g) {
        // Choose a thickness to draw as borders on blocks
        g.setStroke(new BasicStroke(7));

        // Drawing the background frame background to put options on it
        g.setColor(Color.BLACK);
        g.drawRoundRect(100, 160, 460, 475, 25, 25);
        g.setColor(corLinhas);
        g.fillRoundRect(100, 160, 460, 475, 25, 25);

        // Bloco para escrever QUE PENA
        g.setColor(Color.BLACK);
        g.drawRoundRect(130, 180, 400, 150, 25, 25);
        g.setColor(Color.red);
        g.fillRoundRect(130, 180, 400, 150, 25, 25);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 72)); //Escrever QUE PENA
        g.drawString("QUE PENA", 140, 280); //Escrever QUE PENA

        // Bloco para escrever VOCÊ PERDEU
        g.setStroke(new BasicStroke(5));
        g.drawRoundRect(130, 385, 400, 70, 25, 25);
        g.setColor(Color.red);
        g.fillRoundRect(130, 385, 400, 70, 25, 25);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 50)); //Escrever VOCÊ PERDEU
        g.drawString("VOCÊ PERDEU", 147, 438); //Escrever VOCÊ PERDEU

        // Bloco para escrever JOGAR NOVAMENTE
        g.setColor(Color.BLACK);
        g.drawRoundRect(130, 510, 160, 80, 25, 25);
        g.setColor(Color.red);
        g.fillRoundRect(130, 510, 160, 80, 25, 25);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 24)); //Escrever JOGAR NOVAMENTE
        g.drawString("JOGAR", 167, 540); //Escrever JOGAR
        g.drawString("NOVAMENTE", 133, 572); //Escrever NOVAMENTE

        // Bloco para escrever SAIR
        g.drawRoundRect(390, 515, 140, 70, 25, 25);
        g.setColor(Color.red);
        g.fillRoundRect(390, 515, 140, 70, 25, 25);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 25)); //Escrever SAIR
        g.drawString("SAIR", 431, 556); //Escrever SAIR
    }
}
