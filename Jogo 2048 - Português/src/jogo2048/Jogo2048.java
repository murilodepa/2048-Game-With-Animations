/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo2048;

import controleDoJogo.LerArquivo;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import telasEFuncoesComplementares.TelasDoJogo;

/**
 *
 * @author Murilo Araujo
 */
public abstract class Jogo2048 {
 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException exception) {
                JOptionPane.showMessageDialog(null, "Erro ao criar a frame! " + exception.getMessage());
            }

            new LerArquivo();
            TelasDoJogo telasDoJogo = new TelasDoJogo();
        });
    }
}