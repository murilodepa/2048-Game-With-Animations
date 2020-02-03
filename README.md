# 2048 Game with Animations

 * <a> [Leia esta página em português](https://github.com/murilodepa/2048-Game-with-Animations/blob/master/README%20PORTUGU%C3%8AS.md)

## 2048 Game with animations for each block grouping and if they collide!

### Game Description
* The 2048 game, being my first Java language project, consists of a 4 × 4 square matrix game and starts with two full spaces, with number 2, throughout the game, still randomly appearing in matrix 2 or 4 , with a 20% chance of appearing 4. The keyboard arrows or the mouse, movement the numbers will move to the end of the line. If a block touches in another with the same number, they add up, turning them into a block and an animation appears that is the result of this sum, always being a power of 2, and automatically changes on the *scoreboard*. otherwise, it indicates that the blocks have different values, then a *lightning* animation appears, indicating that a "shock" between the blocks. The objective of the game is to reach a block with the value of 2048, no matter how much your score.
* **This repository contains the Portuguese and English version of the game, with package names, classes, methods, functions, variables and comments in their respective language, also featuring, a graphical interface appropriate to the chosen language.**
* A graphical interface of the game, was drawn using the Java language Graphics2D class, which provides basic object design features and functions for rendering basic geometric shapes, such as, lines, arcs, rectangles, and others. As well as images, which in the case of the project were only used for animation. The game code was commented and the functions and variables, were used with very intuitive names, allowing a better understanding for those who want to study, improve or develop a similar project. Below, we can see screenshots of the running game:

* **Game Home Screen**, making available to the user, the option of *NEW GAME*, access the repository of this game on my *GitHub* and *LEAVE THE GAME*:
![telaInicialIngles](https://user-images.githubusercontent.com/56207941/66858499-ca829d80-ef5f-11e9-8e9a-ef46b5716536.PNG)

* **Game Main Screen**, Runtime game::
![JogandoIngles](https://user-images.githubusercontent.com/56207941/66858715-3bc25080-ef60-11e9-8f18-e0acc1826cd9.PNG)

* **Victory Screen**, indicating to the user that he / she, grouped a block with 2048. Making available to the user, the option *PLAY AGAIN*, and *LEAVE THE GAME*.
![VoceVenceuIngles](https://user-images.githubusercontent.com/56207941/66859317-6bbe2380-ef61-11e9-8c86-cf8dac25bbe6.PNG)

* **Defeat Screen**, indicating to the user that he / she, unfortunately lost the game. Making available to the user, the option *PLAY AGAIN*, and *LEAVE THE GAME*.
![voceperdeuIngles](https://user-images.githubusercontent.com/56207941/66859568-f0a93d00-ef61-11e9-8a85-89cae6455e2d.PNG)

### Prerequisite

#### Operational System
* Windows 10 was used but can be done on another operating system; You need to install the operating system compatible compiler.

 #### Java Integrated Development Environment (IDE)
* NetBeans was used, which is a Java integrated development environment (IDE) developed by Sun Microsystems.
* <a> [Get information about NetBeans](https://netbeans.org/about/)

* **Observation:** To develop this project, you only need basic knowledge of programming logic and the Java language.

### Installation Guide
* To run the program in the Java language,  only need a Java integrated development environment (IDE), this project, NetBeans was used, but another platform can be used, whitout needing any Another additional tool. This development platform can be obtained from the link above, however, to install NetBeans, you need to install JDK, below have a tutorial on how to install JDK and then NetBeans regarding your operating system compatibility:

* <a> [How To Install and Get Started with Java Programming 
(on Windows, Mac OS and Ubuntu)](https://www3.ntu.edu.sg/home/ehchua/programming/howto/netbeans_howto.html)

### Development
* Git clone https://github.com/murilodepa/2048-Game-with-Animations.git
* if accomplish "Download ZIP", need a file decompressor.
* After you have installed NetBeans or another similar program, run it and select "file" and then "open project".
* Select the directory you want to execute where the file is saved and then select the open option.
* Then select the run option and the game will start playing.

### Controls
* **W / A / S / D** - Control Up, Left, Right and Down directions, respectively;
* **Mouse Gestures (click, drag and drop)** - Control the Up, Left, Right and Down directions, respectively, in relation to the performed gesture;
* **↑, ←, →, ↓** - Control Up, Left, Right and Down directions, respectively.

### User Interface
* Contains a block that shows the score at runtime;
* Contains a Record, where the highest score is saved, was used *File* (writing and reading data in *.txt* files) to perform this method. This file has been saved to the "SaveFile" directory in this repository and may be changing the game record by the value in its first line, starting with 0 as default.
* Gif updates for each block added "summed" and also animated gifs in case the sum does not occur, there is a "shock" between them;
* Option to start a new game by clicking on the square *NEW GAME*;
* Option to return the last move by clicking on the square that has an arrow indicating return next to *NEW GAME*.
If the user group a block with 2048, this indicates that he has won the game, a new screen will be displayed that the user has won and allowing them to select *PLAY AGAIN* or *LEAVE*.
* If the user no longer has moves, ie all blocks occupied, and no similar neighboring blocks, indicates that he has lost the game, a new screen will appear showing the user has lost, and allows to select the option *PLAY AGAIN* or *LEAVE*.

### Libraries Used
* The library was used *jl1.0.1.jar*, to insert music in Java.
* <a> [how to install, import and use library *jl1.0.1.jar*](https://www.youtube.com/watch?v=kC9_dK5hQPo)

### Music played in the game
* Only one song was used, being a classical song, considered one of the best known worldwide *"Für Elise"*, also known *"For Elisa"*, by Ludwig Van Beethoven. The song only starts playing as soon as the user presses the *NEW GAME* button, and if the song ends, it starts again.
* This song was downloaded from the site *"Portal domínio público biblioteca digital desenvolvida em software livre"*, as the site name already says, this song contains public domain, whitout to worry about copyright.
* <a> [Download music in *public domain*] (http://www.dominiopublico.gov.br/pesquisa/DetalheObraForm.do?select_action=&co_obra=3982)
* However, the song was only in *.mid* format, and the library used to play the song only allows *.mp3* format music, so it was necessary to use a website to perform this format conversion.
* <a> [Site used to convert music from *.mid* to *.mp3*](https://www.onlineconverter.com/convert/10358f965f5c5df82609d249e98e2fd143)

### *Easter Egg* of Game
* If you click on the game logo, which is a square written *2048*, there is a direct chance of winning, appearing 2 blocks of 1024, when added together you win!

### Dicas Para Vencer O *JOGO 2048*
* <a> [2048game: Tips to win the game](https://2048game.com/tips-and-tricks/)
* <a> [The definitive guide to winning 2048](https://www.dailydot.com/debug/how-to-win-2048/)
  
### Developer
* Murilo de Paula Araujo.
  
### Contributions
- Contributions and possible improvements, in my view, are always welcome. **Good game and have fun!**
