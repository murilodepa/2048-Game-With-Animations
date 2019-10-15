# Jogo 2048 com Animações

* <a> [Read this page in English](https://github.com/murilodepa/2048-Game-with-Animations/edit/master/README.md)

## Jogo 2048 com diferencial de animações para cada junção de blocos e se caso colidirem!

### Descrição do Jogo
* O jogo 2048, sendo o meu primeiro projeto realizado na linguagem Java, consiste num jogo de uma matriz 4×4 quadrados e começa com dois dos espaços completos com o número 2, no decorrer do jogo, continua aparecendo quadrados aleatórios na matriz sendo de 2 ou 4, tendo 20% apenas de chance de aparecer o 4. Aperte as setas ou araste o mouse, e os números se moverão até o fim da linha onde estão. Se o bloco encostar em outro com o mesmo número eles se somam, transformar apenas em um bloco de número maior e aparece uma animação indicando qual é o resultado dessa soma, sendo sempre potência de 2, e automaticamente já é comutado o seu ponto no quadro de pontuação. Se caso não somarem, indicaram que os blocos possuem valores diferentes, então aparece uma animação de um raio, indicando que um "choque" entre os blocos. O objetivo do jogo é alcançar um bloco com a soma de 2048, não importando tanto a sua pontuação.
* **Neste repositório, contém a versão do jogo em português e em inglês, com nomes de pacotes, classes, métodos, funções, variáveis e comentários na sua respectiva linguagem, apresentando também,  uma interface gráfica apropriada a linguagem escolhida.**
* A interface gráfica do jogo foi toda desenhada através de códigos de programação, utilizando a classe Graphics2D da linguagem Java, que fornece funcionalidades básicas para o desenho de objetos e funções para a renderização de formas geométricas básicas, como por exemplo, linhas, arcos, retângulos, e entre outras, e também, imagens, que no caso do projeto foi utilizado imagens apenas para as animações. O código do jogo está praticamente todo comentado e as funções e variáveis, foram criadas com nomes bastantes intuitivos, permitindo um melhor entendimento para quem desejar estudar, aprimorar, ou desenvolver um projeto similar. Abaixo podemos observar imagens das telas do jogo em tempo de execução:

* **Tela inicial do jogo**, disponibilizando ao usuário, a opção de *NOVO JOGO*, acessar o repositório do jogo no meu *GitHub* e a opção de *SAIR DO JOGO*:
![JOGO2048_INICIAL](https://user-images.githubusercontent.com/56207941/66797671-f101f380-eee1-11e9-8ea9-9c17a64d9fbb.PNG)

* **Tela principal do jogo**, tela no qual estava sendo jogada e o jogo foi continuado, após a retirada da captura de tela:
![Jogo2048](https://user-images.githubusercontent.com/56207941/66712410-95652800-ed72-11e9-8470-1314ee0179a5.PNG)


* **Tela de vitória**, indicando ao usuário que ele(a), conseguiu juntar um bloco com 2048. Oferecendo as opções de *JOGAR NOVAMENTE* e a de *SAIR DO JOGO*.
![JOGO2048_GANHOU](https://user-images.githubusercontent.com/56207941/66797674-f5c6a780-eee1-11e9-8724-a5852f2d5a97.PNG)


* **Tela de derrota**, indicando ao usuário que ele(a), infelismente perdeu o jogo, não tendo mais nenhuma movimentação dos blocos. Disponibilizando as opções de "JOGAR NOVAMENTE* e a de *SAIR DO JOGO*.
![JOGO2048_PERDEU](https://user-images.githubusercontent.com/56207941/66797679-fbbc8880-eee1-11e9-8749-6f7f244a5000.PNG)

### Pré-requisitos

#### Sistema Operacional
* Foi utilizado o Windows 10, mas pode ser realizado em outro sistema operacional, necessita instalar o compilador compatível com o sistema operacional.

 #### Ambiente de Desenvolvimento Integrado (IDE) Java
* Foi utilizado o NetBeans, que é um ambiente de desenvolvimento integrado (IDE) Java desenvolvido pela empresa Sun Microsystems.
* <a> [Obter informações sobre o NetBeans](https://www.oficinadanet.com.br/artigo/1061/o_que_e_o_netbeans)

* **Observação:** Para desenvolver este projeto, precisa apenas de conhecimentos básicos sobre lógica de programação e de linguagem Java.

### Guia de Instalação
* Para executar o programa na linguagem Java, no qual o algoritmo foi realizado, necessita apenas de um ambiente de desenvolvimento integrado (IDE) Java, no caso do projeto, foi utilizado o NetBeans, mas pode ser usado outra plataforma, não precisando de nenhuma outra ferramenta adicional ou especial, essa plataforma de desenvolvimento pode ser obtido no link acima, para instalar o NetBeans, é necessário instalar o JDK, abaixo pode ser observado um tutorial, de como instalar o JDK e depois o NetBeans em relação a compatibilidade do seu sistema operacional:

* <a> [Tutorial de como instalar o JDK e o NetBeans: Necessário para o desenvolvimento do projeto](https://www.oficinadanet.com.br/post/16771-netbeans-requisitos-e-como-instalar)

### Desenvolvimento
* Git clone https://github.com/murilodepa/Jogo-2048-com-Animacao
* Se realizar "Download ZIP", necessita de um descompactador de arquivos.
* Após ter instalado o NetBeans ou outro programa similar, execute-o e selecione "arquivo" e depois "abrir projeto".
* Selecione o diretório que deseja executar onde está salvo o arquivo e depois selecione a opção abrir.
* Logo em seguida, selecione a opção executar e o jogo começará a ser executado e o usuário poderá estar jogando.

### Controles
* **W / A / S / D** - Controlam as direções Cima, Esquerda, Direita e Baixo, respectivamente; 
* **Gestos do mouse (clicar, arrastar e soltar)** - Controlam as direções Cima, Esquerda, Direita e Baixo, respectivamente, em relação ao gesto realizado;
* **↑, ←, →, ↓** - Controlam as direções Cima, Esquerda, Direita e Baixo, respectivamente.

### Interface Do Usuário
* Contém um Score que mostra a pontuação em tempo de execução;
* Contém um Recorde, onde fica salvo a pontuação de maior valor, foi utilizado *Arquivo* (escrita e leitura de dados em arquivos *.txt*) para realizar esse método. Esse arquivo foi salvo no diretório, "SalvarArquivo", que se encontra nesse repositório, podendo estar alterando o recorde do jogo através do valor na sua primeira linha, inicia com 0 como padrão.
* Atualizações de gifs para cada bloco juntado "somado" e também gifs animado para caso não ocorra a soma, ocorrendo um "choque" entre eles; 
* Opção de começar um novo jogo, clicando no quadrado *NOVO JOGO*;
* Opção para retornar a última jogada., clicando no quadrado que tem uma seta indicando retornar, ao lado de *NOVO JOGO*.
* Se o usuário conseguir juntar um bloco com 2048, indica que venceu o jogo, aparecendo uma nova tela que mostra que o usuário venceu, e permite que selecione a opção *JOGAR NOVAMENTE* ou a *SAIR*.
* Se o usuário não tiver mais movimentações, ou seja, todos os blocos ocupados, e sem nenhum bloco vizinho semelhante, indica que perdeu o jogo, aparecendo uma nova tela que mostra que o usuário perdeu, e permite que selecione a opção *JOGAR NOVAMENTE* ou a *SAIR*.

### Bibliotecas Utilizadas
* Foi utilizada a biblioteca *jl1.0.1.jar*, para estar adicionando música em Java.
* <a> [Tutorial de como instalar, importar e utilizar a biblioteca *jl1.0.1.jar*](https://www.youtube.com/watch?v=kC9_dK5hQPo)

### Música Utilizada no Jogo
* Foi utilizada apenas uma música, sendo ela uma música erudita, considerada uma das mais conhecidas mundialmente a *"Für Elise"*, conhecida também *"Para Elisa"*, de Ludwig van Beethoven. A música, somente começa a ser reproduzida, assim que o usuário pressiona o botão *NOVO JOGO*, e se caso a música acabar, começa novamente.
* O download dessa música foi realizado no site *Portal domínio público biblioteca digital desenvolvida em software livre*, como o nome do site já diz essa música contem domínio público, não precisando me preocupar com direitos autorais.
* <a> [Realizar o download da música em *domínio público*](http://www.dominiopublico.gov.br/pesquisa/DetalheObraForm.do?select_action=&co_obra=3982)
* Entretanto, a música só tinha no formato *.mid*, e a biblioteca utilizada para reproduzir a música só permite músicas com formato *.mp3*, então foi preciso utilizar um site para realizar essa conversão de formato.
* <a> [Site utilizado para converter a música de *.mid*, para *.mp3*](https://www.onlineconverter.com/convert/10358f965f5c5df82609d249e98e2fd143)

### *Easter Egg* do Jogo
* Se clicar no logo do jogo, que é um quadrado escrito *2048*, aparece uma possibilidade direta de ganhar, aparecendo 2 blocos de 1024, quando somados você ganha!

### Dicas Para Vencer O *JOGO 2048*
* <a> [Dicas do site *RachaCuca* para vencer o jogo](https://rachacuca.com.br/raciocinio/2048/)
  
### Contribuições
- Contribuições e possíveis melhorias, no meu ponto de vista são sempre bem-vindas. Bom jogo e divirta-se!
