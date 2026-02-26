import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int battleCount = 0;
        ArrayList<String> chosenEnemy = new ArrayList<>(List.of("Guerreiro", "Mago", "Arqueiro"));

        boolean isDefendig = false;

        System.out.println("Bem-vindo à Arena dos Campeôes! Digite seu nome: ");
        String name = scanner.next();
        Character character = new Character(name, 100, 15, 10, null);

        do {
            System.out.println("Escolha sua classe: 1-Guerreiro | 2-Mago | 3-Arqueiro");
            int option = scanner.nextInt();

            if (option < 1 || option > 3){
                System.out.println("Opção inválida, tente novamente!");
            }else {
                Character characterTarget = createEnemy(option);
                option -= 1;
                System.out.printf("Você escolheu: %s\n", chosenEnemy.get(option));
                System.out.printf("Inimigo encontrado: %s\n", characterTarget.getName());
                do {
                    battleCount += 1;
                    System.out.printf("Iniciando %d° turno! \n", battleCount);
                    System.out.printf("Sua vida: %d | Vida do inimigo: %d\n", character.getLife(), characterTarget.getLife());

                    System.out.println("Escolha sua ação: 1- Atacar | 2- Defender | 3- Ver inventário");
                    int actionOption = scanner.nextInt();

                    if (actionOption < 1 || actionOption > 3){
                        System.out.println("Opção inválida, tente novamente!");
                    } else {
                        if (actionOption == 1) {
                            character.strik(characterTarget, false);
                        } else if (actionOption == 2) {
                            character.defend();
                            isDefendig = true;
                        }

                        if (characterTarget.getLife() <= 0) {
                            System.out.println("O inimigo foi derrotado!");
                            break;
                        }

                        System.out.println("Turno do inimigo....");
                        characterTarget.strik(character, isDefendig);
                    }

                }while (character.getLife() > 0 && characterTarget.getLife() > 0);

            }


        }while (character.isAlive());

    }
    public static Character createEnemy(int option){
        Random random = new Random();
        int index = random.nextInt(Wizard.listAttackWizard.size());
        Character newEnemy;

        if (option == 0) {
            String nameWarrior = Warrior.listNamesWarrior.get(index);
            int lifeWarrior = Warrior.listLifeWarrior.get(index);
            int attackWarrior = Warrior.listAttackWarrior.get(index);
            int defenseWarrior = Warrior.listDefenseWarrior.get(index);
            newEnemy = new Warrior(nameWarrior, lifeWarrior, attackWarrior, defenseWarrior, null);

        } else if (option == 1) {
            String nameWizard = Wizard.namesListWizard.get(index);
            int lifeWizard = Wizard.listLifeWizard.get(index);
            int attackWizard = Wizard.listAttackWizard.get(index);
            int defenseWizard = Wizard.listDefenseWizard.get(index);
            newEnemy = new Wizard(nameWizard, lifeWizard, attackWizard, defenseWizard, null);

        } else {
            String nameArcher = Archer.nameslistArcher.get(index);
            int lifeArcher = Archer.listLifeArcher.get(index);
            int attackArcher = Archer.listAttackArcher.get(index);
            int defenseArcher = Archer.listDefenseArcher.get(index);
            newEnemy = new Archer(nameArcher, lifeArcher, attackArcher, defenseArcher, null);

        }
        return newEnemy;
    }
}
