===================================================================
                    ARENA DE BATALHA - JOGO DE TERMINAL
===================================================================

1. INTRODUÇÃO
--------------
Este é um jogo de RPG por turnos processados inteiramente via terminal.
O objetivo é sobreviver e derrotar todos os adversários antes que seus
pontos de vidas cheguem a zero.

2. FUNCIONALIDADES
------------------
- Escolha de nome e classe de personagens
- Sistema de combate por turno (Atacar, Defender, Usar item).
- Inimigos são gerados aleatóriamente.
- Modularização total seguindo Orientação a Objetos.

3. REQUISITOS
-------------
- Java JDK 17 ou superior.
- Um terminal ou IDE

4. COMO EXECUTAR
----------------
1. Compile os arquivos .java:
  javac *.java
2. Execute o jogo:
  java Main

5. MECÂNICAS DO JOGO
--------------------
- Atacar: Causa dano direto ao inimigos baseado nos seus atributos.
- Defender: Reduz o dano recebido no turno do inimigo.
- Itens: Recupera a vida, podendo ser usado apenas 3 vezes por turno.

6. CONDIÇÃO DE VITÓRIA
----------------------
- Vitória: Derrotar todos os inimigos da sequência.
- Derrota: Quando os pontos de vida do herói chegam a zero.
