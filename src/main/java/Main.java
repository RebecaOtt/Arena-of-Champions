import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //lista onde cotem o nome d todos os inimigos, para usar no verificarInimigosExistentes
        List<String> enemysExistence = Stream.of(
                        Archer.nameslistArcher, Wizard.namesListWizard, Warrior.listNamesWarrior)
                .flatMap(list -> list.stream())
                .collect(Collectors.toCollection(ArrayList::new));

        int battleCount = 0; //usado para contar os turnos
        ArrayList<String> chosenEnemy = new ArrayList<>(List.of("Guerreiro", "Mago", "Arqueiro")); //usado para mostrar a opção que a pessoa escolheu

        System.out.println("Bem-vindo à Arena dos Campeôes! Digite seu nome: ");
        String name = scanner.next();
        Character character = new Character(name, 100, 15, 12, null); //criando personagem

//        Looping da arena
        do {
            //verifica se a lista dos nomes está vazia
            if (enemysExistence.isEmpty()){
                System.out.println("Parabens!! você derrotou todos os inimigos");
                break; //acaba com o looping na hora
            }

            System.out.println("Escolha sua classe: 1-Guerreiro | 2-Mago | 3-Arqueiro");
            int option = scanner.nextInt();

            if (option < 1 || option > 3){
                System.out.println("Opção inválida, tente novamente!");
            } else {
                //criando o inimigo
                Character characterTarget = createEnemy(option);

                //se retornar null é pq não tem nomes na determidada opção escolhida
                if (characterTarget == null) {
                    System.out.printf("Você não encontra nenhum %s\n", chosenEnemy.get(option-1));
                    System.out.println("Explore as outras arenas, ainda existe inimigos a sua epera!");
                }
                else {
                    verifyEnemyExistence(characterTarget, enemysExistence); //chamo ele para ter certeza que removo o nome da lista

                    option -= 1; //menos 1 para usar nos index
                    System.out.printf("Você escolheu: %s\n", chosenEnemy.get(option));
                    System.out.printf("Inimigo encontrado: %s\n", characterTarget.getName());

                    //                Inicio do turno
                    do {
                        battleCount += 1;
                        System.out.printf("Iniciando %d° turno! \n", battleCount);
                        System.out.printf("Sua vida: %d | Vida do inimigo: %d |  Ataque inimigo: %d \n", character.getLife(), characterTarget.getLife(), characterTarget.attack);

                        System.out.println("Escolha sua ação: 1- Atacar | 2- Defender | 3- Ver inventário");
                        int actionOption = scanner.nextInt();

                        if (actionOption < 1 || actionOption > 3){
                            System.out.println("Opção inválida, tente novamente!");
                        } else {
                            if (actionOption == 1) {
                                character.strik(characterTarget);
                            } else if (actionOption == 2) {
                                character.defend();
                            }

                            if (characterTarget.getLife() <= 0) {
                                System.out.println("O inimigo foi derrotado!");
                                break;
                            }

                            System.out.println("Turno do inimigo....");
                            characterTarget.runIa(character);
                        }

                    }while (character.getLife() > 0 && characterTarget.getLife() > 0);
                }
            }

        }while (character.isAlive());
        System.out.printf("%s, você morreu... Jogo encerrado", name);

    }

    public static void startGame() {

    }

    public static Character createEnemy(int option){
        option -=1;
        Random random = new Random();
        Character newEnemy = null;
        //eu uso um index com tamanho dos nomes de cada inimigo para conseguir organizar corretamente
        //se ficar o mesmo index, ele iria diminuir e não conseguir ser usado nas outras classes
        if (option == 0) {
            if (!Warrior.listNamesWarrior.isEmpty()){
                int index = random.nextInt(Warrior.listNamesWarrior.size());

                String name = Warrior.listNamesWarrior.get(index);
                int life = Warrior.listLifeWarrior.get(index);
                int attack = Warrior.listAttackWarrior.get(index);
                int defense = Warrior.listDefenseWarrior.get(index);

                // Removo depois de salvar nas variáveis
                Warrior.listNamesWarrior.remove(index);
                newEnemy = new Warrior(name, life, attack, defense, null);
            }
        } else if (option == 1) {
            if (!Wizard.namesListWizard.isEmpty()) {
                int index = random.nextInt(Wizard.namesListWizard.size());

                String name = Wizard.namesListWizard.get(index);
                int life = Wizard.listLifeWizard.get(index);
                int attack = Wizard.listAttackWizard.get(index);
                int defense = Wizard.listDefenseWizard.get(index);

                Wizard.namesListWizard.remove(index);

                newEnemy = new Wizard(name, life, attack, defense, null);
            }
            
        } else {
            if (!Archer.nameslistArcher.isEmpty()){
                int index = random.nextInt(Archer.nameslistArcher.size());

                String name = Archer.nameslistArcher.get(index);
                int life = Archer.listLifeArcher.get(index);
                int attack = Archer.listAttackArcher.get(index);
                int defense = Archer.listDefenseArcher.get(index);

                Archer.nameslistArcher.remove(index);

                newEnemy = new Archer(name, life, attack, defense, null);
            }
        }
        return newEnemy;
    }

    public static boolean verifyEnemyExistence(Character characterTarget, List<String> enemysExistence){
        if (enemysExistence.contains(characterTarget.getName())) {
            enemysExistence.remove(characterTarget.getName());
            return true;
        }
        else {
            return false;
        }
    }
}
