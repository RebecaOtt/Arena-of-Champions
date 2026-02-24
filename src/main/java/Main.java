import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bem-vindo à Arena dos Campeôes! Digite seu nome: ");
        String name = scanner.next();
        Character character = new Character(name, 100, 15, 10, null);

        System.out.println("Seu personagem:");
        System.out.printf("Nome: %s\n", character.getName());
        System.out.printf("Vida: %d\n", character.getLife());
        System.out.printf("Ataque: %d\n", character.getAttack());
        System.out.printf("Defesa: %d\n", character.getDefense());
        System.out.printf("Inverntario: %s\n",character.getInventory() );

        Random random = new Random();
        int index = random.nextInt(Wizard.listAttackWizard.size());

        String nameWarrior = Warrior.listNamesWarrior.get(index);
        String nameWizard = Wizard.namesListWizard.get(index);
        String nameArcher = Archer.nameslistArcher.get(index);

        int lifeWarrior = Warrior.listLifeWarrior.get(index);
        int lifeWizard = Wizard.listLifeWizard.get(index);
        int lifeArcher = Archer.listLifeArcher.get(index);

        int attackWarrior = Warrior.listAttackWarrior.get(index);
        int attackWizard = Wizard.listAttackWizard.get(index);
        int attackArcher = Archer.listAttackArcher.get(index);

        int defenseWarrior = Warrior.listDefenseWarrior.get(index);
        int defenseWizard = Wizard.listDefenseWizard.get(index);
        int defenseArcher = Archer.listDefenseArcher.get(index);

        Warrior warrior = new Warrior(nameWarrior, lifeWarrior, attackWarrior, defenseWarrior, null);
        Wizard wizard = new Wizard(nameWizard, lifeWizard, attackWizard, defenseWizard, null);
        Archer archer = new Archer(nameArcher, lifeArcher, attackArcher, defenseArcher, null);


        System.out.println("Testando arqueiro:");
        System.out.printf("Nome: %s\n", archer.getName());
        System.out.printf("Vida: %d\n", archer.getLife());
        System.out.printf("Ataque: %d\n", archer.getAttack());
        System.out.printf("Defesa: %d\n", archer.getDefense());
        System.out.printf("Inverntario: %s\n", archer.getInventory() );
    }
}
