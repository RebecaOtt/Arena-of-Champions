import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
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
        Character character = new Character(name, 1000, 145, 12, new ArrayList<>()); //criando personagem

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
                            } else {
                                showInventary(character);
                                if (!character.getInventory().isEmpty()){
                                    System.out.println("Digite o item escolhido:");
                                    int optionItem = scanner.nextInt();
                                    character.useItem(optionItem);
                                }
                            }

                            if (characterTarget.getLife() <= 0) {
                                System.out.println("DEBUG: Itens no inimigo: " + characterTarget.getInventory().size());
                                System.out.println("O inimigo foi derrotado!");
                                characterTarget.getInventory().stream()
                                        .forEach(item -> {
                                            boolean containsItem = character.getInventory().stream()
                                                            .anyMatch(myItem -> myItem.getDescricao().equalsIgnoreCase(item.getDescricao()));
                                            if (!containsItem) {
                                                System.out.println("Você saqueou: " + item.getDescricao());
                                                character.getInventory().add(item);
                                            } else {
                                                System.out.println("Você já possui " + item.getDescricao());
                                            }
                                        });
                                characterTarget.getInventory().clear();
                                character.setQuantityUsedItem(0);
                                break;
                            }

                            System.out.println("Turno do inimigo....");
                            characterTarget.runIa(character);
                        }

                    }while (character.getLife() > 0 && characterTarget.getLife() > 0);
                }
            }

        }while (character.isAlive());

    }

    public static void startGame() {

    }

    public static void displayStatus(){

    }

    public static void playerTurn(){

    }

    public static void enemyTurn(){

    }

    public static void endOfGame(){

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
                newEnemy = new Warrior(name, life, attack, defense, new ArrayList<>());

                if (random.nextDouble() < 0.5){
                    newEnemy.generateItem();
                }
            
            }
        } else if (option == 1) {
            if (!Wizard.namesListWizard.isEmpty()) {
                int index = random.nextInt(Wizard.namesListWizard.size());

                String name = Wizard.namesListWizard.get(index);
                int life = Wizard.listLifeWizard.get(index);
                int attack = Wizard.listAttackWizard.get(index);
                int defense = Wizard.listDefenseWizard.get(index);

                Wizard.namesListWizard.remove(index);

                newEnemy = new Wizard(name, life, attack, defense, new ArrayList<>());

                if (random.nextDouble() < 0.6){
                    newEnemy.generateItem();
                }
            }
            
        } else {
            if (!Archer.nameslistArcher.isEmpty()){
                int index = random.nextInt(Archer.nameslistArcher.size());

                String name = Archer.nameslistArcher.get(index);
                int life = Archer.listLifeArcher.get(index);
                int attack = Archer.listAttackArcher.get(index);
                int defense = Archer.listDefenseArcher.get(index);

                Archer.nameslistArcher.remove(index);

                newEnemy = new Archer(name, life, attack, defense, new ArrayList<>());

                if (random.nextDouble() < 0.7){
                    newEnemy.generateItem();
                }
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

    public static void showInventary(Character character){
        if (character.getInventory().isEmpty()){
            System.out.println("Inventário vazio");
        } else {
            IntStream.range(0, character.getInventory().size())
                    .forEach(item -> System.out.println(item + "- " + character.getInventory().get(item).getDescricao()));
        }
    }
}
