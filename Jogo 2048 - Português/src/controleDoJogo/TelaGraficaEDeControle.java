package controleDoJogo;

import telasEFuncoesComplementares.TelasDoJogo;
import static controleDoJogo.EscreverArquivo.getRecorde;
import static controleDoJogo.MovimentacaoDosBlocos.acionandoAEaster;
import static controleDoJogo.MovimentacaoDosBlocos.colunaChoque;
import static controleDoJogo.MovimentacaoDosBlocos.colunaSoma;
import static controleDoJogo.MovimentacaoDosBlocos.copiarMatrizESetarVariaveis;
import static controleDoJogo.MovimentacaoDosBlocos.linhaChoque;
import static controleDoJogo.MovimentacaoDosBlocos.linhaSoma;
import static controleDoJogo.MovimentacaoDosBlocos.matrizRetornar;
import static controleDoJogo.MovimentacaoDosBlocos.zerarMatriz;
import java.awt.event.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import static java.lang.Thread.sleep;
import java.net.URI;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import static telasEFuncoesComplementares.FuncoesComplementares.OrganizarDigitosParaImpressao;
import static telasEFuncoesComplementares.FuncoesComplementares.reiniciar;
import static telasEFuncoesComplementares.FuncoesComplementares.retornar;
import static telasEFuncoesComplementares.TelasDoJogo.imprimirTelaGanhou;
import static telasEFuncoesComplementares.TelasDoJogo.imprimirTelaPerdeu;

/**
 *
 * @author Murilo Araujo
 */
public class TelaGraficaEDeControle extends JPanel implements MouseListener {

    // Declarando a matriz do jogoDoJogo do tipo int matriz 4x4 
    protected static int[][] matrizDoJogo = new int[4][4];

    // Declarando todas as variáveis do tipo int utilizadas;
    protected static int pontuacao = 0, pontuacaoAnterior = 0, k = 0, x = 97, y = 167, direcao = 0, flagCimaOuBaixo = 0;

    // Declarando todas as imagens utilizadas no Jogo 2048 na forma de matriz 4x4, podendo ter uma imagem em cada bloco
    private final BufferedImage[][] imagemRaio = new BufferedImage[4][3];
    private final BufferedImage[][] imagemBoloco4 = new BufferedImage[4][4];
    private final BufferedImage[][] imagemBoloco8 = new BufferedImage[4][4];
    private final BufferedImage[][] imagemBoloco16 = new BufferedImage[4][4];
    private final BufferedImage[][] imagemBoloco32 = new BufferedImage[4][4];
    private final BufferedImage[][] imagemBoloco64 = new BufferedImage[4][4];
    private final BufferedImage[][] imagemBoloco128 = new BufferedImage[4][4];
    private final BufferedImage[][] imagemBoloco256 = new BufferedImage[4][4];
    private final BufferedImage[][] imagemBoloco512 = new BufferedImage[4][4];
    private final BufferedImage[][] imagemBoloco1024 = new BufferedImage[4][4];
    private final BufferedImage[][] imagemBoloco2048 = new BufferedImage[4][4];

    // Declarando a imagem de retornar a jogada, utilizando somente uma imagem dessa 
    private BufferedImage imagemRetornar;

    // Declarando todas as variáveis do tipo boolean utilizadas
    protected static boolean flagInicio = true, flagPerdeu = false, flagReiniciar = false, flagRetornar = false, flagTelaDoJogo = false;
    protected static boolean flagEasterEgg = false, flagTelaInicial = true, flagGanhar = false, flagSePodeRetornar = false;

    // Declarando todas as variáveis do ttipo Integer
    private Integer clickX, clickY, soltarX, soltarY;

    // Declarando todas as variáveis do tipo Color
    private Color corFundo, corLinhas, corBloco2, corBloco4, corBloco8, corBloco16, corBloco32, corBloco64;
    private Color corBloco128, corBloco256, corBloco512, corBloco1024, corBloco2048;

    // Declarando todas as variáveis do tipo String
    private String valorPontuacao, valorMaiorPontuacao;

    private Player musica;
    Musica mp3 = new Musica();

    // Criando um construtor para a classe TelaGraficaEDeControle
    public TelaGraficaEDeControle() {
        setPreferredSize(new Dimension(660, 660)); // Dimensão da minha JFrame
        setFocusable(true);                        // setando a opção "focalizável"
        instanciarComponentes();                   // Chamando a minha função para instanciar todos os meus componentes no construtor
        new verificarChoqueOuSoma().start();       // Verificar ocorrências de choques entre blocos
        movimentar();                              // Verificar a movimentação ou acionamento do teclado
        addMouseListener(this);                    // Verificar clicks e gestos do mouse
    }

    // Instanciando todos os componentes e elementos utilizados no jogo 2048
    public void instanciarComponentes() {
        corFundo = new Color(102, 178, 255);
        corLinhas = new Color(0, 128, 255);
        corBloco2 = new Color(220, 220, 220);
        corBloco4 = new Color(192, 192, 192);
        corBloco8 = new Color(255, 180, 68);
        corBloco16 = new Color(255, 64, 64);
        corBloco32 = new Color(255, 29, 11);
        corBloco64 = new Color(255, 0, 0);
        corBloco128 = new Color(251, 236, 93);
        corBloco256 = new Color(253, 233, 16);
        corBloco512 = new Color(255, 215, 0);
        corBloco1024 = new Color(255, 255, 64);
        corBloco2048 = new Color(255, 255, 0);

        try {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 3; j++) {
                    imagemRaio[i][j] = ImageIO.read(getClass().getResourceAsStream("Imagens\\RAIO.gif"));
                }
            }
        } catch (IOException e) {
        }

        // Instanciando todas as imagens utilizadas no jogo para cada posição da matriz e setando cada imagem para cada junção de blocos
        try {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    imagemBoloco4[i][j] = ImageIO.read(getClass().getResourceAsStream("Imagens\\estrelas_4.gif"));
                    imagemBoloco8[i][j] = ImageIO.read(getClass().getResourceAsStream("Imagens\\brilhos_8.gif"));
                    imagemBoloco16[i][j] = ImageIO.read(getClass().getResourceAsStream("Imagens\\baloes_16.gif"));
                    imagemBoloco32[i][j] = ImageIO.read(getClass().getResourceAsStream("Imagens\\bobEsponja_32.gif"));
                    imagemBoloco64[i][j] = ImageIO.read(getClass().getResourceAsStream("Imagens\\abacates_64.gif"));
                    imagemBoloco128[i][j] = ImageIO.read(getClass().getResourceAsStream("Imagens\\tenor_128.gif"));
                    imagemBoloco256[i][j] = ImageIO.read(getClass().getResourceAsStream("Imagens\\giphy_256.gif"));
                    imagemBoloco512[i][j] = ImageIO.read(getClass().getResourceAsStream("Imagens\\tenor_512.gif"));
                    imagemBoloco1024[i][j] = ImageIO.read(getClass().getResourceAsStream("Imagens\\tenor_1024.gif"));
                    imagemBoloco2048[i][j] = ImageIO.read(getClass().getResourceAsStream("Imagens\\fogosDeArtificio_2048.gif"));
                }
            }
        } catch (IOException e) {
        }

        // Instanciando e definindo a imagem da opção retornar
        try {
            imagemRetornar = ImageIO.read(getClass().getResourceAsStream("Imagens\\Retornar.jpg"));
        } catch (IOException ex) {
            Logger.getLogger(TelaGraficaEDeControle.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Instanciando variáveis como -1, como utilizo as posições 0 da matriz, ocorre conflitos 
        linhaChoque[k] = -1;
        colunaChoque[k] = -1;
        linhaSoma[k] = -1;
        colunaSoma[k] = -1;
    }

    // Desenhando as telas do jogo
    public void telaDoJogo(Graphics2D g) {
        // FlagTelaInicial para apresentar a tela inicial antes de mostrar a principal
        if (flagTelaInicial == true) {
            TelasDoJogo.imprimirTelaInicial(g);
        } else {

            // Se o usuário decidir jogar, não tiver ganhado, nem perdido, o jogo é impresso e ele tem possibilidades de novas jogadas
            if (flagTelaDoJogo == true) {
                // Escolho a grossura para estar desenhando as bordas nos blocos
                g.setStroke(new BasicStroke(3));

                // Imprimindo o cabeçalho do jogo
                TelasDoJogo.imprimirCabeçalho(g);

                // Deixar visível a imagem Retornar
                g.setColor(Color.red);
                g.drawImage(imagemRetornar, 494, 79, 70, 70, this);
                //g.fillRoundRect(494, 80, 70, 70, 25, 25);
                g.setColor(Color.BLACK);
                g.drawRoundRect(494, 79, 70, 70, 1, 1);

                // Flag para se caso o usuário decidir reiniciar o jogo
                if (flagReiniciar == true) {
                    reiniciar();
                }

                // Flag para se caso o usuário decidir Retornar o jogo
                if (flagRetornar == true && flagSePodeRetornar == true) {
                    retornar();
                }

                // Converto o valor inteiro de pontuação para string
                valorPontuacao = String.valueOf(pontuacao);

                // Converto o valor inteiro de recorde para string
                valorMaiorPontuacao = String.valueOf(getRecorde());

                // Setando a font arial, negrito e tamanho 30
                g.setFont(new Font("Arial", Font.BOLD, 30)); //PONTUACAO

                // Função qu centraliza os dígitos desejados, como por exemplo da pontuação e recorde
                OrganizarDigitosParaImpressao(g, valorMaiorPontuacao, getRecorde(), 68);
                OrganizarDigitosParaImpressao(g, valorPontuacao, pontuacao, 139);

                // Desenhando o fundo da matriz
                g.setStroke(new BasicStroke(7));
                g.setColor(Color.BLACK);
                g.drawRoundRect(100, 160, 460, 475, 25, 25);
                g.setColor(corLinhas);
                g.fillRoundRect(100, 160, 460, 475, 25, 25);

                // A borda do fundo
                g.setColor(Color.BLACK);
                g.setStroke(new BasicStroke(3));

                // Verifico se o usuário selecionou a Easter Egg do jogo, se não, inicio normalmente o jogo
                if (flagInicio == true && flagEasterEgg != true) {
                    MovimentacaoDosBlocos.gerarAleatorio(2);
                    MovimentacaoDosBlocos.gerarAleatorio();

                    // Copio a matriz principal na de retornar
                    copiarMatrizESetarVariaveis();

                    flagInicio = false;
                } else { // Se tiver selecionado, adiciono 2 blocos de valor 1024 para o usuário vencer o jogo
                    if (flagEasterEgg == true && flagInicio == true) {
                        acionandoAEaster();
                    }
                }

                // Imprimo a matriz para verificações e testes
                MovimentacaoDosBlocos.imprimirMatriz();

                // Verifico quais blocos serão impressos em relação a minha matriz impressa
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        if (matrizDoJogo[i][j] == 0) {
                            g.setColor(corFundo);
                            g.fillRoundRect(115 + j * 110, 175 + i * 115, 100, 100, 25, 25);
                        } else {
                            if (matrizDoJogo[i][j] == 2) {
                                g.setColor(corBloco2);
                                g.fillRoundRect(115 + j * 110, 175 + i * 115, 100, 100, 25, 25);
                                g.setColor(Color.BLACK);
                                g.drawRoundRect(115 + j * 110, 175 + i * 115, 100, 100, 25, 25);
                                g.setFont(new Font("Arial", Font.BOLD, 40)); //2
                                g.drawString("2", 153 + j * 111, 240 + i * 116); //2
                            } else {
                                if (matrizDoJogo[i][j] == 4) {
                                    g.setColor(corBloco4);
                                    g.fillRoundRect(115 + j * 110, 175 + i * 115, 100, 100, 25, 25);
                                    g.setColor(Color.BLACK);
                                    g.drawRoundRect(115 + j * 110, 175 + i * 115, 100, 100, 25, 25);
                                    g.setFont(new Font("Arial", Font.BOLD, 40)); //4
                                    g.drawString("4", 153 + j * 111, 240 + i * 116); //4
                                } else {
                                    if (matrizDoJogo[i][j] == 8) {
                                        g.setColor(corBloco8);
                                        g.fillRoundRect(115 + j * 110, 175 + i * 115, 100, 100, 25, 25);
                                        g.setColor(Color.BLACK);
                                        g.drawRoundRect(115 + j * 110, 175 + i * 115, 100, 100, 25, 25);
                                        g.setColor(Color.WHITE);
                                        g.setFont(new Font("Arial", Font.BOLD, 40)); //8
                                        g.drawString("8", 153 + j * 111, 240 + i * 116); //8
                                    } else {
                                        if (matrizDoJogo[i][j] == 16) {
                                            g.setColor(corBloco16);
                                            g.fillRoundRect(115 + j * 110, 175 + i * 115, 100, 100, 25, 25);
                                            g.setColor(Color.BLACK);
                                            g.drawRoundRect(115 + j * 110, 175 + i * 115, 100, 100, 25, 25);
                                            g.setColor(Color.WHITE);
                                            g.setFont(new Font("Arial", Font.BOLD, 40)); //16
                                            g.drawString("16", 140 + j * 111, 240 + i * 116); //16
                                        } else {
                                            if (matrizDoJogo[i][j] == 32) {
                                                g.setColor(corBloco32);
                                                g.fillRoundRect(115 + j * 110, 175 + i * 115, 100, 100, 25, 25);
                                                g.setColor(Color.BLACK);
                                                g.drawRoundRect(115 + j * 110, 175 + i * 115, 100, 100, 25, 25);
                                                g.setColor(Color.WHITE);
                                                g.setFont(new Font("Arial", Font.BOLD, 40)); //32
                                                g.drawString("32", 140 + j * 110, 240 + i * 115); //32                                  
                                            } else {
                                                if (matrizDoJogo[i][j] == 64) {
                                                    g.setColor(corBloco64);
                                                    g.fillRoundRect(115 + j * 110, 175 + i * 115, 100, 100, 25, 25);
                                                    g.setColor(Color.BLACK);
                                                    g.drawRoundRect(115 + j * 110, 175 + i * 115, 100, 100, 25, 25);
                                                    g.setColor(Color.WHITE);
                                                    g.setFont(new Font("Arial", Font.BOLD, 40)); //64
                                                    g.drawString("64", 140 + j * 110, 240 + i * 115); //64
                                                } else {
                                                    if (matrizDoJogo[i][j] == 128) {
                                                        g.setColor(corBloco128);
                                                        g.fillRoundRect(115 + j * 110, 175 + i * 115, 100, 100, 25, 25);
                                                        g.setColor(Color.BLACK);
                                                        g.drawRoundRect(115 + j * 110, 175 + i * 115, 100, 100, 25, 25);
                                                        g.setColor(Color.WHITE);
                                                        g.setFont(new Font("Arial", Font.BOLD, 40)); //128
                                                        g.drawString("128", 130 + j * 110, 240 + i * 115); //128
                                                    } else {
                                                        if (matrizDoJogo[i][j] == 256) {
                                                            g.setColor(corBloco256);
                                                            g.fillRoundRect(115 + j * 110, 175 + i * 115, 100, 100, 25, 25);
                                                            g.setColor(Color.BLACK);
                                                            g.drawRoundRect(115 + j * 110, 175 + i * 115, 100, 100, 25, 25);
                                                            g.setColor(Color.WHITE);
                                                            g.setFont(new Font("Arial", Font.BOLD, 40)); //128
                                                            g.drawString("256", 130 + j * 110, 240 + i * 115); //128
                                                        } else {
                                                            if (matrizDoJogo[i][j] == 512) {
                                                                g.setColor(corBloco512);
                                                                g.fillRoundRect(115 + j * 110, 175 + i * 115, 100, 100, 25, 25);
                                                                g.setColor(Color.BLACK);
                                                                g.drawRoundRect(115 + j * 110, 175 + i * 115, 100, 100, 25, 25);
                                                                g.setColor(Color.WHITE);
                                                                g.setFont(new Font("Arial", Font.BOLD, 40)); //128
                                                                g.drawString("512", 130 + j * 110, 240 + i * 115); //128
                                                            } else {
                                                                if (matrizDoJogo[i][j] == 1024) {
                                                                    g.setColor(corBloco1024);
                                                                    g.fillRoundRect(115 + j * 110, 175 + i * 115, 100, 100, 25, 25);
                                                                    g.setColor(Color.BLACK);
                                                                    g.drawRoundRect(115 + j * 110, 175 + i * 115, 100, 100, 25, 25);
                                                                    g.setColor(Color.WHITE);
                                                                    g.setFont(new Font("Arial", Font.BOLD, 40)); //128
                                                                    g.drawString("1024", 120 + j * 110, 240 + i * 115); //128
                                                                } else {
                                                                    if (matrizDoJogo[i][j] == 2048) {
                                                                        g.setColor(corBloco2048);
                                                                        g.fillRoundRect(115 + j * 110, 175 + i * 115, 100, 100, 25, 25);
                                                                        g.setColor(Color.BLACK);
                                                                        g.drawRoundRect(115 + j * 110, 175 + i * 115, 100, 100, 25, 25);
                                                                        g.setColor(Color.WHITE);
                                                                        g.setFont(new Font("Arial", Font.BOLD, 40)); //128
                                                                        g.drawString("2048", 120 + j * 110, 240 + i * 115); //128
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }

                                }
                            }
                        }
                    }
                }

                // Loop para verificar possíveis animações de choques nas horizontais
                int l = 0;
                try {
                    while (linhaChoque[l] != -1 && flagCimaOuBaixo == 1) {
                        for (int i = 0; i < 4 && linhaChoque[l] >= i; i++) { //for para horizontais
                            for (int j = 0; j < 3; j++) {
                                if (linhaChoque[l] == i && colunaChoque[l] == j) {
                                    g.drawImage(imagemRaio[i][j], 176 + j * 111, 178 + i * 116, 90, 90, this);
                                }
                            }
                        }
                        l += 1;
                    }
                } catch (Exception e) {
                }

                // Loop para verificar possíveis animações de choques nas verticais
                l = 0;
                try {
                    while (linhaChoque[l] != -1 && flagCimaOuBaixo == 2) {
                        if (linhaChoque[l] < 3 && colunaChoque[l] < 3) {
                            for (int i = 0; i < 4; i++) {
                                for (int j = 0; j < 3; j++) {
                                    if (linhaChoque[l] == i && colunaChoque[l] == j) {
                                        g.drawImage(imagemRaio[i][j], 121 + i * 111, 237 + j * 116, 90, 90, this);
                                    }
                                }
                            }
                        }
                        l += 1;
                    }
                } catch (Exception e) {
                }

                // Loop para verificar quais animações surge na tela em relação a junção dos blocos
                l = 0;
                int i = 0, j = 0;
                while (linhaSoma[l] != -1) {
                    if (matrizDoJogo[(linhaSoma[l])][(colunaSoma[l])] == 4) {
                        g.drawImage(imagemBoloco4[i][j], 115 + colunaSoma[l] * 110, 175 + linhaSoma[l] * 115, 100, 100, this);
                        j += 1;
                        l += 1;
                        if (j == 4) {
                            i += 1;
                        }
                        j = 0;
                    } else {
                        if (matrizDoJogo[(linhaSoma[l])][(colunaSoma[l])] == 8) {
                            g.drawImage(imagemBoloco8[i][j], 115 + colunaSoma[l] * 110, 175 + linhaSoma[l] * 115, 100, 100, this);
                            j += 1;
                            l += 1;
                            if (j == 4) {
                                i += 1;
                            }
                            j = 0;
                        } else {
                            if (matrizDoJogo[(linhaSoma[l])][(colunaSoma[l])] == 16) {
                                g.drawImage(imagemBoloco16[i][j], 115 + colunaSoma[l] * 110, 175 + linhaSoma[l] * 115, 100, 100, this);
                                j += 1;
                                l += 1;
                                if (j == 4) {
                                    i += 1;
                                }
                                j = 0;
                            } else {
                                if (matrizDoJogo[(linhaSoma[l])][(colunaSoma[l])] == 32) {
                                    g.drawImage(imagemBoloco32[i][j], 115 + colunaSoma[l] * 110, 175 + linhaSoma[l] * 115, 100, 100, this);
                                    j += 1;
                                    l += 1;
                                    if (j == 4) {
                                        i += 1;
                                    }
                                    j = 0;
                                } else {
                                    if (matrizDoJogo[(linhaSoma[l])][(colunaSoma[l])] == 64) {
                                        g.drawImage(imagemBoloco64[i][j], 115 + colunaSoma[l] * 110, 175 + linhaSoma[l] * 115, 100, 100, this);
                                        j += 1;
                                        l += 1;
                                        if (j == 4) {
                                            i += 1;
                                        }
                                        j = 0;
                                    } else {
                                        if (matrizDoJogo[(linhaSoma[l])][(colunaSoma[l])] == 128) {
                                            g.drawImage(imagemBoloco128[i][j], 115 + colunaSoma[l] * 110, 175 + linhaSoma[l] * 115, 100, 100, this);
                                            j += 1;
                                            l += 1;
                                            if (j == 4) {
                                                i += 1;
                                            }
                                            j = 0;
                                        } else {
                                            if (matrizDoJogo[(linhaSoma[l])][(colunaSoma[l])] == 256) {
                                                g.drawImage(imagemBoloco256[i][j], 115 + colunaSoma[l] * 110, 175 + linhaSoma[l] * 115, 100, 100, this);
                                                j += 1;
                                                l += 1;
                                                if (j == 4) {
                                                    i += 1;
                                                }
                                                j = 0;
                                            } else {
                                                if (matrizDoJogo[(linhaSoma[l])][(colunaSoma[l])] == 512) {
                                                    g.drawImage(imagemBoloco512[i][j], 115 + colunaSoma[l] * 110, 175 + linhaSoma[l] * 115, 100, 100, this);
                                                    j += 1;
                                                    l += 1;
                                                    if (j == 4) {
                                                        i += 1;
                                                    }
                                                    j = 0;
                                                } else {
                                                    if (matrizDoJogo[(linhaSoma[l])][(colunaSoma[l])] == 1024) {
                                                        g.drawImage(imagemBoloco1024[i][j], 115 + colunaSoma[l] * 110, 175 + linhaSoma[l] * 115, 100, 100, this);
                                                        j += 1;
                                                        l += 1;
                                                        if (j == 4) {
                                                            i += 1;
                                                        }
                                                        j = 0;
                                                    } else {
                                                        if (matrizDoJogo[(linhaSoma[l])][(colunaSoma[l])] == 2048) {
                                                            g.drawImage(imagemBoloco2048[i][j], 115 + colunaSoma[l] * 110, 175 + linhaSoma[l] * 115, 100, 100, this);
                                                            j += 1;
                                                            l += 1;
                                                            if (j == 4) {
                                                                i += 1;
                                                            }
                                                            j = 0;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        // Verifico se o usuário venceu o jogo, se sim, imprimo a tela indicando vitória
        if (flagGanhar == true) {
            imprimirTelaGanhou(g);
            flagTelaDoJogo = false;
        } else {
            // Verifico se o usuário perdeu o jogo, se sim, imprimo a tela indicando derrota
            if (flagPerdeu == true) {
                imprimirTelaPerdeu(g);
                flagTelaDoJogo = false;
            }
        }

        //  InserirMusica.som();
    }

    //public void TelaPerdeu()
    // Verificar movimentações do teclado
    public void movimentar() {
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {

                // Guardo sempre a pontuação anterior caso o jogador deseja retornar a jogada
                pontuacaoAnterior = pontuacao;

                // Condição para verificar se selecionou a seta para esquerda ou a tecla "a" do teclado 
                if (e.getKeyCode() == 37 || e.getKeyCode() == 65) {
                    try {
                        MovimentacaoDosBlocos.esquerda(matrizDoJogo); // Movimento os blocos para a esquerda
                    } catch (IOException ex) {
                        Logger.getLogger(TelaGraficaEDeControle.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    flagCimaOuBaixo = 1; // 1 significa que moveu para esquerda ou direita
                }

                // Condição para verificar se selecionou a seta para cima ou a tecla "w" do teclado 
                if (e.getKeyCode() == 38 || e.getKeyCode() == 87) {
                    try {
                        MovimentacaoDosBlocos.cima(matrizDoJogo); // Movimento os blocos para cima
                    } catch (IOException ex) {
                        Logger.getLogger(TelaGraficaEDeControle.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    flagCimaOuBaixo = 2; // 2 significa que moveu para cima ou baixo
                }

                // Condição para verificar se selecionou a seta para direita ou a tecla "d" do teclado 
                if (e.getKeyCode() == 39 || e.getKeyCode() == 68) {
                    try {
                        MovimentacaoDosBlocos.direita(matrizDoJogo); // Movimento os blocos para a direita
                    } catch (IOException ex) {
                        Logger.getLogger(TelaGraficaEDeControle.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    flagCimaOuBaixo = 1; // 1 significa que moveu para esquerda ou direita
                }

                // Condição para verificar se selecionou a seta para baixo ou a tecla "s" do teclado 
                if (e.getKeyCode() == 40 || e.getKeyCode() == 83) {
                    try {
                        MovimentacaoDosBlocos.baixo(matrizDoJogo); // Movimento os blocos para baixo
                    } catch (IOException ex) {
                        Logger.getLogger(TelaGraficaEDeControle.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    flagCimaOuBaixo = 2; // 2 significa que moveu para cima ou baixo
                }

                // Atualizo a impressao
                repaint();

                // Imprimo pontuação para verificar se está imprimindo corretamente em relação a JFrame
                System.out.println("\nPontuacao: " + getPontuacao());
            }
        });
    }

    // Criando a parte gráfica do jogo
    @Override
    public void paintComponent(Graphics gg) {
        super.paintComponents(gg);
        Graphics2D g = (Graphics2D) gg;
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        telaDoJogo(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    // Método acionado quando o botão esquerdo do mouse é pressionado
    @Override
    public void mousePressed(MouseEvent e) {
        // Pego a posição cartesiana em relação a JFrame, x e y do mouse
        try {
            clickX = e.getX();
            clickY = e.getY();
        } catch (java.lang.NullPointerException e1) {
        }

        // Imprimir a posição para verificar se esta pegando corretamente (opcional)
        // System.out.println("click x: " + clickX + " clickY: " + clickY);
    }

    // Método acionado quando o botão esquerdo do mouse é solto
    @Override
    public void mouseReleased(MouseEvent e) {
        int deltaX = 0, deltaY = 0;
        try {
            // Pego a posição cartesiana em relação a JFrame, x e y do mouse
            soltarX = e.getX();
            soltarY = e.getY();
        } catch (java.lang.NullPointerException e1) {
        }

        // Verifico se o usuário apertou exclusivamente com o mouse na opção reiniciar
        if (clickX > 412 && clickX < 483 && clickY > 79 && clickY < 146) {
            flagReiniciar = true;
            flagTelaDoJogo = true;
            flagEasterEgg = false;
            zerarMatriz(matrizDoJogo);
            zerarMatriz(matrizRetornar);
            if (flagPerdeu == true || flagGanhar == true) {
                flagPerdeu = false;
                flagGanhar = false;
            }
        } else { // Verifico se o usuário apertou exclusivamente com o mouse na opção retornar
            if (clickX > 492 && clickX < 563 && clickY > 79 && clickY < 146 && flagSePodeRetornar == true) {
                flagRetornar = true;
            } else { // Verifico se o usuário apertou exclusivamente com o mouse no logo do Jogo (Easter Egg)
                if (clickX > 267 && clickX < 400 && clickY > 15 && clickY < 141 && flagPerdeu == false && flagEasterEgg != true) {
                    flagEasterEgg = true;
                    pontuacao = 0;
                    flagInicio = true;
                } else { // Verifico se o usuário apertou exclusivamente com o mouse na opção NOVO JOGO na Tela Inicial
                    if (clickX > 210 && clickX < 455 && clickY > 362 && clickY < 431 && flagTelaInicial == true) {
                        flagTelaInicial = false;
                        flagTelaDoJogo = true;
                        Musica mp3 = new Musica();
                        mp3.start();
                    } else { // Verifico se o usuário apertou exclusivamente com o mouse na opção GitHub na Tela Inicial
                        if (clickX > 249 && clickX < 410 && clickY > 450 && clickY < 525 && flagTelaInicial == true) {
                            try {
                                URI link = new URI("https://github.com/murilodepa/2048-Game-with-Animations");
                                Desktop.getDesktop().browse(link);
                            } catch (Exception erro) {
                            }
                        } else { // Verifico se o usuário apertou exclusivamente com o mouse na opção SAIR da Tela Inicial
                            if (clickX > 249 && clickX < 410 && clickY > 550 && clickY < 615 && flagTelaInicial == true) {
                                flagTelaInicial = false;
                                TelasDoJogo.telaDoJogo.dispose();
                                System.exit(0);
                            } else { // Verifico se o usuário apertou exclusivamente com o mouse na opção SAIR, na tela quando Perde ou Ganha
                                if (clickX > 353 && clickX < 507 && clickY > 509 && clickY < 576 && (flagGanhar == true || flagPerdeu == true)) {
                                    flagGanhar = false;
                                    flagPerdeu = false;
                                    TelasDoJogo.telaDoJogo.dispose();
                                    System.exit(0);
                                } else { // Verifico se o usuário apertou exclusivamente com o mouse na opção NOVO JOGO, na tela quando Perde ou Ganha
                                    if (clickX > 133 && clickX < 274 && clickY > 449 && clickY < 592 && (flagGanhar == true || flagPerdeu == true)) {
                                        flagReiniciar = true;
                                        flagTelaDoJogo = true;
                                        flagEasterEgg = false;
                                        zerarMatriz(matrizDoJogo);
                                        zerarMatriz(matrizRetornar);
                                        if (flagPerdeu == true || flagGanhar == true) {
                                            flagPerdeu = false;
                                            flagGanhar = false;
                                        }
                                    } else { // Verifico se o usuário clicou, arrastou e soltou o mouse, querendo realizar um movimento no jogo
                                        if (flagPerdeu != true && flagGanhar != true && flagTelaInicial != true) {
                                            try {
                                                deltaX = (soltarX - clickX); // Calculo o delta X do mouse (quando pressiono e quando solto o mouse)
                                                deltaY = (soltarY - clickY); // Calculo o delta Y do mouse (quando pressiono e quando solto o mouse)
                                                //System.out.println("click x: " + clickX + " clickY: " + clickY + "\nsoltarX: " + soltarX + " soltarY: " + soltarY); 

                                                // Guardo sempre a pontuação anterior caso o jogador deseja retornar a jogada
                                                pontuacaoAnterior = pontuacao;

                                                // Se deltaX negativo e DeltaY negativo
                                                if (deltaX < 0 && deltaY < 0) {
                                                    deltaX = (deltaX * (-1));
                                                    deltaY = (deltaY * (-1));
                                                    if (deltaX > deltaY) { // Verifico se X é maior, se for o movimento é para esquerda
                                                        try {
                                                            MovimentacaoDosBlocos.esquerda(matrizDoJogo);
                                                        } catch (IOException ex) {
                                                            Logger.getLogger(TelaGraficaEDeControle.class.getName()).log(Level.SEVERE, null, ex);
                                                        }
                                                        flagCimaOuBaixo = 1;
                                                        deltaX = (deltaX * (-1));
                                                        deltaY = (deltaY * (-1));
                                                    } else {
                                                        try {
                                                            // Senão o movimento é para cima
                                                            MovimentacaoDosBlocos.cima(matrizDoJogo);
                                                        } catch (IOException ex) {
                                                            Logger.getLogger(TelaGraficaEDeControle.class.getName()).log(Level.SEVERE, null, ex);
                                                        }
                                                        flagCimaOuBaixo = 2;
                                                        deltaX = (deltaX * (-1));
                                                        deltaY = (deltaY * (-1));
                                                    }
                                                } else { // DeltaX positivo e DeltaY negativo
                                                    if (deltaX > 0 && deltaY < 0) {
                                                        deltaY = (deltaY * (-1));

                                                        if (deltaX > deltaY) {
                                                            try {
                                                                // Se deltaX for maior que DeltaY, movimento é para direita
                                                                MovimentacaoDosBlocos.direita(matrizDoJogo);
                                                            } catch (IOException ex) {
                                                                Logger.getLogger(TelaGraficaEDeControle.class.getName()).log(Level.SEVERE, null, ex);
                                                            }
                                                            flagCimaOuBaixo = 1;
                                                            deltaY = (deltaY * (-1));
                                                        } else {
                                                            try {
                                                                // Senão o moviimento é para cima
                                                                MovimentacaoDosBlocos.cima(matrizDoJogo);
                                                            } catch (IOException ex) {
                                                                Logger.getLogger(TelaGraficaEDeControle.class.getName()).log(Level.SEVERE, null, ex);
                                                            }
                                                            flagCimaOuBaixo = 2;
                                                            deltaY = (deltaY * (-1));
                                                        }
                                                    } else { // DeltaX positivo e DeltaY positivo
                                                        if (deltaX > 0 && deltaY > 0) {

                                                            if (deltaX > deltaY) {
                                                                try {
                                                                    // Se deltaX for maior que DeltaY, movimento é para direita
                                                                    MovimentacaoDosBlocos.direita(matrizDoJogo);
                                                                } catch (IOException ex) {
                                                                    Logger.getLogger(TelaGraficaEDeControle.class.getName()).log(Level.SEVERE, null, ex);
                                                                }
                                                                flagCimaOuBaixo = 1;
                                                            } else {
                                                                try {
                                                                    // Senão o moviimento é para baixo
                                                                    MovimentacaoDosBlocos.baixo(matrizDoJogo);
                                                                } catch (IOException ex) {
                                                                    Logger.getLogger(TelaGraficaEDeControle.class.getName()).log(Level.SEVERE, null, ex);
                                                                }
                                                                flagCimaOuBaixo = 2;
                                                            }
                                                        } else { // DeltaX negativo e DeltaY positivo
                                                            if (deltaX < 0 && deltaY > 0) {
                                                                deltaX = (deltaX * (-1));

                                                                if (deltaX > deltaY) { // Se deltaX for maior que DeltaY, movimento é para esquerda
                                                                    try {
                                                                        MovimentacaoDosBlocos.esquerda(matrizDoJogo);
                                                                    } catch (IOException ex) {
                                                                        Logger.getLogger(TelaGraficaEDeControle.class.getName()).log(Level.SEVERE, null, ex);
                                                                    }
                                                                    flagCimaOuBaixo = 1;
                                                                    deltaX = (deltaX * (-1));
                                                                } else {
                                                                    try {
                                                                        // Senão o moviimento é para baixo
                                                                        MovimentacaoDosBlocos.baixo(matrizDoJogo);
                                                                    } catch (IOException ex) {
                                                                        Logger.getLogger(TelaGraficaEDeControle.class.getName()).log(Level.SEVERE, null, ex);
                                                                    }
                                                                    flagCimaOuBaixo = 2;
                                                                    deltaX = (deltaX * (-1));
                                                                }
                                                            }
                                                        }
                                                    }
                                                }
                                            } catch (java.lang.NullPointerException e1) {
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        repaint();

        System.out.println("\nPontuacao: " + getPontuacao());
    }

    @Override
    public void mouseEntered(MouseEvent arg0
    ) {
    }

    // Thread para ficar verificando se ocorre um Choque ou soma entre os blocos, e atualiza e exibe a animação por um determinado tempo na tela
    public class verificarChoqueOuSoma extends Thread {

        @Override
        public void run() {
            while (true) {
                try {
                    sleep(400);
                } catch (InterruptedException erro) {
                }
                if (linhaChoque[0] != -1 || linhaSoma[0] != -1) {
                    for (int i = 0; i < 16; i++) {
                        if (i < 12) {
                            linhaChoque[i] = -1;
                            colunaChoque[i] = -1;
                        }
                        linhaSoma[i] = -1;
                        colunaSoma[i] = -1;
                    }

                    try {
                        sleep(300);
                    } catch (InterruptedException erro) {
                    }
                    repaint();

                    // Verifico após o último movimento se o usuário perdeu
                    flagPerdeu = MovimentacaoDosBlocos.verificarSePerdeu();

                    // Verifico após o último movimento se o usuário venceu
                    flagGanhar = MovimentacaoDosBlocos.verificarSeGanhou();

                    // Se caso perdeu dou um tempo de 1 segundo , imprimo perdeu e flagCimaBaixo indica sem movimentação
                    if (flagPerdeu == true) {
                        try {
                            sleep(1000);
                        } catch (InterruptedException erro) {
                        }
                        System.out.println("\nPerdeu!");
                        flagCimaOuBaixo = 0; // 0 significa que não houve nenhuma alteração e o usuário perdeu ou ganhou
                    } else { // Se caso ganhou dou um tempo de 1 segundo , imprimo perdeu e flagCimaBaixo indica sem movimentação
                        if (flagGanhar == true) {
                            try {
                                sleep(1000);
                            } catch (InterruptedException erro) {
                            }
                            System.out.println("ganhou");
                            flagCimaOuBaixo = 0; // 0 significa que não houve nenhuma alteração e o usuário perdeu ou ganhou
                        }
                    }
                }
            }
        }
    }

    // Thread para reproduzr música durante o usuário estiver jogando
    class Musica extends Thread {

        @Override
        public void run() {
            //                       
            try {
                while (true) {
                    // Declaração de variál input do tipo InputStream, que já contém o caminho da música que esta armazenado num pacote
                    InputStream input = this.getClass().getResourceAsStream("/mp3/Beethoven_Fur_Elise.mp3");

                    // Instaciando a variável música do tipo Player
                    musica = new Player(input);

                    // ".Play para começar executar a música
                    musica.play();
                }
            } catch (JavaLayerException ex) {
                Logger.getLogger(TelaGraficaEDeControle.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    // Métodos getters e setters
    public static int getPontuacao() {
        return pontuacao;
    }

    public static void setPontuacao(int aPontuacao) {
        pontuacao = aPontuacao;
    }

    public static int[][] getMatrizDoJogo() {
        return matrizDoJogo;
    }

    public Integer getSoltarX() {
        return soltarX;
    }

    public void setSoltarX(Integer soltarX) {
        this.soltarX = soltarX;
    }

    public Integer getClickX() {
        return clickX;
    }

    public void setClickX(Integer clickX) {
        this.clickX = clickX;
    }

    public Integer getSoltarY() {
        return soltarY;
    }

    public void setSoltarY(Integer soltarY) {
        this.soltarY = soltarY;
    }

    public Integer getClickY() {
        return clickY;
    }

    public void setClickY(Integer clickY) {
        this.clickY = clickY;

    }

    public static void setMatrizDoJogo(int[][] aMatrizDoJogo) {
        matrizDoJogo = aMatrizDoJogo;
    }
}
