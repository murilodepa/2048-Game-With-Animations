/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telasEFuncoesComplementares;

import controleDoJogo.MovimentacaoDosBlocos;
import static controleDoJogo.MovimentacaoDosBlocos.copiarMatrizESetarVariaveis;
import controleDoJogo.TelaGraficaEDeControle;
import java.awt.Graphics2D;

/**
 *
 * @author Murilo Araujo
 */
public class FuncoesComplementares extends TelaGraficaEDeControle {

    // Flag para se caso o usuário decidir Retornar o jogo
    public static void retornar() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                matrizDoJogo[i][j] = MovimentacaoDosBlocos.matrizRetornar[i][j];
                flagRetornar = false;
                flagSePodeRetornar = false;
                pontuacao = pontuacaoAnterior;
            }
        }
    }

    // Função para se caso o usuário decidir reiniciar o jogo
    public static void reiniciar() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                matrizDoJogo[i][j] = 0;
                flagInicio = true;
                flagReiniciar = false;
                pontuacao = 0;
            }
        }
    }
    
    
    // Vejo a quantidade de digitos para centralizar a posicao da pontuação onde ela aparece
    public static void OrganizarDigitosParaImpressao(Graphics2D g, String valor, int pontos, int y) {
        if (pontos < 10) {
            g.drawString(valor, 170, y);
        } else {
            if (pontos < 100) {
                g.drawString(valor, 161, y);
            } else {
                if (pontos < 1000) {
                    g.drawString(valor, 152, y);
                } else {
                    if (pontos < 10000) {
                        g.drawString(valor, 143, y);
                    } else {
                        if (pontos < 100000) {
                            g.drawString(valor, 134, y);
                        } else {
                            g.drawString(valor, 125, y);
                        }
                    }
                }
            }
        }
    }
}
