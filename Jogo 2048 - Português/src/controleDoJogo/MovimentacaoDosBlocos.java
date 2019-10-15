package controleDoJogo;

import static controleDoJogo.EscreverArquivo.getRecorde;
import static controleDoJogo.EscreverArquivo.setRecorde;
import java.io.IOException;
import java.util.Random;

/**
 *
 * @author Murilo Araujo
 */
public class MovimentacaoDosBlocos extends TelaGraficaEDeControle {

    /**
     * @param args the command line arguments
     */
    // Declaração de todas as variáveis utilizada na classe MovimentacaoDosBlocos
    private static final Random aleatorio = new Random();
    public static boolean flagMover = false;
    public static int[][] matrizRetornar = new int[4][4];
    public static int[] linhaChoque = new int[12];
    static int[] colunaChoque = new int[12];
    static int[] linhaSoma = new int[16];
    static int[] colunaSoma = new int[16];
    int cont1 = 0, cont2 = 0, linha0a3 = 0, coluna0a3 = 0;

    // Gera números aleatórios, com probabilidade de 20% de gerar o número 4
    public static void gerarAleatorio() {
        int BlocoVisivel = 0;
        int linha0a3 = 0, coluna0a3 = 0, valor0a4 = 0;

        do {
            BlocoVisivel = 0;
            linha0a3 = aleatorio.nextInt(4);
            coluna0a3 = aleatorio.nextInt(4);
            valor0a4 = aleatorio.nextInt(5);

            if (getMatrizDoJogo()[linha0a3][coluna0a3] == 0 && valor0a4 == 2 && flagInicio != true) {
                getMatrizDoJogo()[linha0a3][coluna0a3] = 4;
            } else {
                if (getMatrizDoJogo()[linha0a3][coluna0a3] == 0 && valor0a4 != 2) {
                    getMatrizDoJogo()[linha0a3][coluna0a3] = 2;
                } else {
                    BlocoVisivel = 1;
                }
            }
        } while (BlocoVisivel == 1);
    }

    // Gera num bloco aleatório o valor passado como parâmetro 
    public static void gerarAleatorio(int n) {
        int linha0a3 = 0, coluna0a3 = 0;
        linha0a3 = aleatorio.nextInt(4);
        coluna0a3 = aleatorio.nextInt(4);
        matrizDoJogo[linha0a3][coluna0a3] = n;
    }

    // Gera na posição 0x0 e 0x1 da matriz os dois valores passados como parâmetros 
    public static void gerarAleatorio(int n, int m) {
        int linha0a3 = 0, coluna0a3 = 0;
        matrizDoJogo[linha0a3][coluna0a3] = n;
        matrizDoJogo[linha0a3][coluna0a3 + 1] = m;
    }

    // Zera uma matriz passada por parâmetro
    public static void zerarMatriz(int[][] matriz) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                matriz[i][j] = 0;
            }
        }
    }

    // imprimir a Matriz do jogo (Principal)
    public static void imprimirMatriz() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.printf("%6d", getMatrizDoJogo()[i][j]);
            }
            System.out.println("");
        }
    }

    public static void acionandoAEaster() {
        zerarMatriz(matrizDoJogo);
        gerarAleatorio(1024, 1024);
        for (int k = 0; k < 16; k++) {
            linhaSoma[k] = -1;
            colunaSoma[k] = -1;
            if (k < 12) {
                linhaChoque[k] = -1;
                linhaChoque[k] = -1;
            }
        }

        // Copio a matriz principal na de retornar
        copiarMatrizESetarVariaveis();

        // Zero a pontuação e flagInicio recebe falso
        pontuacao = 0;
        flagInicio = false;
    }

    // Copio a matriz principal do jogo numa auxiliar se caso o usuário decidir retornar a jogada
    // Inicio as variáveis como -1, para nao ter conflito com posição 0
    public static void copiarMatrizESetarVariaveis() {
        int i = 0, j = 0, k = 0;
        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                matrizRetornar[i][j] = matrizDoJogo[i][j];
                if (k < 12) {
                    linhaChoque[k] = -1;
                    colunaChoque[k] = -1;
                }
                if (k < 16) {
                    linhaSoma[k] = -1;
                    colunaSoma[k] = -1;
                }
                k += 1;
            }
        }
    }

    // Realizo a movimentação para a esquerda dos blocos, verificando junções, casas vazias e choques entre blocos
    public static void esquerda(int[][] matriz) throws IOException {
        int flagSoma = 0, aux = 0, cont = 1, cont1 = 0, cont2 = 0, i = 0, j = 0, k = 0, l = 0;
        flagMover = false;

        copiarMatrizESetarVariaveis();

        for (i = 0; i < 4; i++) {
            for (j = 0; j < 3; j++) {
                aux = cont;

                while (aux > flagSoma) {
                    // 
                    if (matriz[i][aux - 1] == 0 && matriz[i][aux] != 0) {
                        matriz[i][aux - 1] = matriz[i][aux];
                        matriz[i][aux] = 0;
                        flagMover = true;
                    }
                    if (matriz[i][aux - 1] == matriz[i][aux] && matriz[i][aux] != 0) {
                        matriz[i][aux - 1] += matriz[i][aux];
                        linhaSoma[l] = i;
                        colunaSoma[l] = (aux - 1);
                        l += 1;
                        setPontuacao(getPontuacao() + matriz[i][aux - 1]);

                        if (getPontuacao() > getRecorde()) {
                            setRecorde(getPontuacao());
                            new EscreverArquivo();
                        }

                        matriz[i][aux] = 0;
                        flagSoma += 1;
                        flagMover = true;
                    } else {
                        if (matriz[i][aux - 1] != 0 && matriz[i][aux] != 0) {
                            linhaChoque[cont1] = i;
                            colunaChoque[cont2] = (aux - 1);
                            cont1 += 1;
                            cont2 += 1;
                            flagSoma += 1;
                        }
                    }
                    aux -= 1;
                }
                cont += 1;
            }
            cont = 1;
            flagSoma = 0;
        }

        // Se tiver ocorido movimento e não tiver acionado a Easter Egg, o programa gera outro número aleatório após a movimentação
        if (flagMover == true && flagEasterEgg != true) {
            gerarAleatorio();
        }
        flagSePodeRetornar = true;
    }

    // Realizo a movimentação para a direita dos blocos, verificando junções, casas vazias e choques entre blocos
    public static void direita(int[][] matriz) throws IOException {
        int flagSoma = 3, aux = 0, cont = 2, cont1 = 0, cont2 = 0, i = 0, j = 0, k = 0, l = 0;
        flagMover = false;

        copiarMatrizESetarVariaveis();

        for (i = 0; i < 4; i++) {
            for (j = 0; j < 3; j++) {
                aux = cont;

                while (aux < flagSoma) {

                    if (matriz[i][aux + 1] == 0 && matriz[i][aux] != 0) {
                        matriz[i][aux + 1] = matriz[i][aux];
                        matriz[i][aux] = 0;
                        flagMover = true;
                    }
                    if (matriz[i][aux + 1] == matriz[i][aux] && matriz[i][aux] != 0) {
                        matriz[i][aux + 1] += matriz[i][aux];
                        linhaSoma[l] = i;
                        colunaSoma[l] = (aux + 1);
                        l += 1;
                        pontuacao += matriz[i][aux + 1];

                        if (getPontuacao() > getRecorde()) {
                            setRecorde(getPontuacao());
                            new EscreverArquivo();
                        }

                        matriz[i][aux] = 0;
                        flagSoma -= 1;
                        flagMover = true;
                    } else {
                        if (matriz[i][aux + 1] != 0 && matriz[i][aux] != 0) {
                            linhaChoque[cont1] = i;
                            colunaChoque[cont2] = (aux);
                            cont1 += 1;
                            cont2 += 1;
                            flagSoma -= 1;
                        }
                    }
                    aux += 1;
                }
                cont -= 1;
            }
            cont = 2;
            flagSoma = 3;
        }

        // Se tiver ocorido movimento e não tiver acionado a Easter Egg, o programa gera outro número aleatório após a movimentação
        if (flagMover == true && flagEasterEgg != true) {
            gerarAleatorio();
        }
        flagSePodeRetornar = true;
    }

    // Realizo a movimentação para cima dos blocos, verificando junções, casas vazias e choques entre blocos
    public static void cima(int[][] matriz) throws IOException {
        int flagSoma = 0, aux = 0, cont = 1, cont1 = 0, cont2 = 0, i = 0, j = 0, k = 0, l = 0;
        flagMover = false;

        copiarMatrizESetarVariaveis();

        for (i = 0; i < 4; i++) {
            for (j = 0; j < 3; j++) {
                aux = cont;

                while (aux > flagSoma) {
                    if (matriz[aux - 1][i] == 0 && matriz[aux][i] != 0) {
                        matriz[aux - 1][i] = matriz[aux][i];
                        matriz[aux][i] = 0;
                        flagMover = true;
                    }
                    if (matriz[aux - 1][i] == matriz[aux][i] && matriz[aux][i] != 0) {
                        matriz[aux - 1][i] += matriz[aux][i];
                        linhaSoma[l] = (aux - 1);
                        colunaSoma[l] = i;
                        l += 1;
                        pontuacao += matriz[aux - 1][i];

                        if (getPontuacao() > getRecorde()) {
                            setRecorde(getPontuacao());
                            new EscreverArquivo();
                        }

                        matriz[aux][i] = 0;
                        flagSoma += 1;
                        flagMover = true;
                    } else {
                        if (matriz[aux - 1][i] != 0 && matriz[aux][i] != 0) {
                            linhaChoque[cont1] = i;
                            colunaChoque[cont2] = (aux - 1);
                            cont1 += 1;
                            cont2 += 1;
                            flagSoma += 1;
                        }
                    }
                    aux -= 1;
                }
                cont += 1;
            }
            cont = 1;
            flagSoma = 0;
        }

        // Se tiver ocorido movimento e não tiver acionado a Easter Egg, o programa gera outro número aleatório após a movimentação
        if (flagMover == true && flagEasterEgg != true) {
            gerarAleatorio();
        }
        flagSePodeRetornar = true;
    }

    // Realizo a movimentação para baixo dos blocos, verificando junções, casas vazias e choques entre blocos
    public static void baixo(int[][] matriz) throws IOException {
        int flagSoma = 3, aux = 0, cont = 2, cont1 = 0, cont2 = 0, i = 0, j = 0, k = 0, l = 0;
        flagMover = false;

        copiarMatrizESetarVariaveis();

        for (i = 0; i < 4; i++) {
            for (j = 0; j < 3; j++) {
                aux = cont;

                while (aux < flagSoma) {

                    if (matriz[aux + 1][i] == 0 && matriz[aux][i] != 0) {
                        matriz[aux + 1][i] = matriz[aux][i];
                        matriz[aux][i] = 0;
                        flagMover = true;
                    }
                    if (matriz[aux + 1][i] == matriz[aux][i] && matriz[aux][i] != 0) {
                        matriz[aux + 1][i] += matriz[aux][i];
                        linhaSoma[l] = (aux + 1);
                        colunaSoma[l] = i;
                        l += 1;
                        pontuacao += matriz[aux + 1][i];

                        if (getPontuacao() > getRecorde()) {
                            setRecorde(getPontuacao());
                            new EscreverArquivo();
                        }

                        matriz[aux][i] = 0;
                        flagSoma -= 1;
                        flagMover = true;
                    } else {
                        if (matriz[aux + 1][i] != 0 && matriz[aux][i] != 0) {
                            linhaChoque[cont1] = i;
                            colunaChoque[cont2] = aux;
                            cont1 += 1;
                            cont2 += 1;
                            flagSoma -= 1;
                        }
                    }
                    aux += 1;
                }
                cont -= 1;
            }
            cont = 2;
            flagSoma = 3;
        }

        // Se tiver ocorido movimento e não tiver acionado a Easter Egg, o programa gera outro número aleatório após a movimentação
        if (flagMover == true && flagEasterEgg != true) {
            gerarAleatorio();
        }
        flagSePodeRetornar = true;
    }

    // Função que verifica se o usuário perdeu, percorrendo a matriz e verificando se tem blocos iguais em posições vizinhas
    public static boolean verificarSePerdeu() {
        boolean flagPerder = false;
        int aux = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                if (matrizDoJogo[i][j] == matrizDoJogo[i][j + 1] || matrizDoJogo[i][j] == 0 || matrizDoJogo[i][j + 1] == 0) {
                    return flagPerder;
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                if (matrizDoJogo[j][i] == matrizDoJogo[j + 1][i]) {
                    return flagPerder;
                }
            }
        }
        flagPerder = true;
        return flagPerder;
    }

    // Verifico se o usuário venceu, ou seja, se tem um bloco com o valor 2048
    public static boolean verificarSeGanhou() {
        boolean flagGanhou = false;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (matrizDoJogo[i][j] == 2048) {
                    flagGanhou = true;
                    return flagGanhou;
                }
            }
        }
        return flagGanhou;
    }
}
