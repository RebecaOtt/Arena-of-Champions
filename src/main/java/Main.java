import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //lista onde cotem o nome d todos os inimigos, para usar no verificarInimigosExistentes
        List<String> enemysExistence = new ArrayList<>();

        int battleCount = 0; //usado para contar os turnos
        ArrayList<String> chosenEnemy = new ArrayList<>(List.of("Guerreiro", "Mago", "Arqueiro")); //usado para mostrar a opção que a pessoa escolheu
        Character character = startGame(enemysExistence, scanner); //criando personagem com o return do startGame


//-------------ARENA
        while (character.isAlive()) {
            if (enemysExistence.isEmpty()) { //verifica se existem inimigos ainda, se tiver vazio = derrotou todos
                System.out.println("Parabens!! você derrotou todos os inimigos");
                break; //acaba com o looping na hora
            }

            System.out.println("Escolha sua classe: 1-Guerreiro | 2-Mago | 3-Arqueiro");
            int option = scanner.nextInt();

            if (option < 1 || option > 3) { //validando opção
                System.out.println("Opção inválida!");
                continue; //não deixa crashar
            }

            Character characterTarget = createEnemy(option); //criando inimigo de forma aleatoria

            if (characterTarget == null) { //se o characterTarget retornar null -> não tem como criar mais inimigos daquela opção
                System.out.printf("Você não encontra nenhum %s\n", chosenEnemy.get(option - 1));
                System.out.println("Explore as outras arenas, ainda existe inimigos a sua epera!");
            } else {
                verifyEnemyExistence(characterTarget, enemysExistence); //chamo ele para ter certeza que removo o nome da lista

                option -= 1; //menos 1 para usar nos index
                System.out.printf("Você escolheu: %s\n", chosenEnemy.get(option));
                System.out.printf("Inimigo encontrado: %s\n", characterTarget.getName());

//----------------------JOGO
                while (character.isAlive() && characterTarget.isAlive()) { //lopping que vai funcionar enquanto os dois estiverem vivos
                    battleCount += 1; //contador do turno
                    displayStatus(battleCount, character, characterTarget); //mostra informações do turno, atualizando valores
                    playerTurn(scanner, character, characterTarget); //chama metodo para o jogador ter sua ação
                    if (characterTarget.isAlive()) {
                        enemyTurn(character, characterTarget); //se inimigo estiver vivo, continua com o turno dele
                    }
                }

                if (!characterTarget.isAlive()) {
                    character.setQuantityUsedItem(0); //quando acaba a batalha com um inimigo, vai zerar para fazer as contagens de form correta
                    battleCount = 0;
                }
            }
        }

        endOfGame(character, enemysExistence);

    }

    public static Character startGame(List<String> enemysExistence, Scanner scanner) {
        enemysExistence.addAll( //adiciona todos os nomes
                Stream.of(Archer.nameslistArcher, Wizard.namesListWizard, Warrior.listNamesWarrior)
                        .flatMap(Collection::stream) //vai juntar todos os nomes das listas de inimigos
                        .collect(Collectors.toCollection(ArrayList::new))); //coleta em uma nova lista

        System.out.println("Bem-vindo à Arena dos Campeôes! Digite seu nome: ");
        String name = scanner.next();
        return new Character(name, 100, 15, 12, new ArrayList<>()); //criando personagem
    }

    public static void displayStatus(int battleCount, Character character, Character characterTarget){
        System.out.printf("Iniciando %d° turno! \n", battleCount);
        System.out.printf("Sua vida: %d | Vida do inimigo: %d |  Ataque inimigo: %d \n", character.getLife(), characterTarget.getLife(), characterTarget.attack);

    }

    public static void playerTurn(Scanner scanner, Character character, Character characterTarget){
        System.out.println("Escolha sua ação: 1- Atacar | 2- Defender | 3- Usar item do inventário");
        int actionOption = scanner.nextInt();

        if (actionOption < 1 || actionOption > 3){ //valida opção de ação
            System.out.println("Opção inválida, tente novamente!");
        } else {
            if (actionOption == 1) {
                character.strik(characterTarget); //chama metodo de atacar do Charachter
            } else if (actionOption == 2) {
                character.defend(); //chama metodo de defender do Character
            } else {
                showInventary(character); //chama metodo de mostrar inventário
                if (!character.getInventory().isEmpty()) { //se não estiver vazia a lista, permite escolher um dos itens
                    System.out.println("Digite o item escolhido:");
                    int optionItem = scanner.nextInt();
                    if (optionItem >= 0 && optionItem < character.getInventory().size()){ //valida a opção
                        character.useItem(optionItem); //usa o item, chamando o metodo do Character
                    } else {
                        System.out.println("Opção inválida, turno perdido...");
                    }
                }
            }

            if (characterTarget.getLife() <= 0) {
                System.out.println("O inimigo foi derrotado!");
                characterTarget.getInventory()
                        .forEach(item -> { //ve todos os itens do inventario do inimigo
                            Optional<InventaryNames> existingItem = character.getInventory().stream()
                                    .filter(myItem -> myItem.getDescricao().equalsIgnoreCase(item.getDescricao())) //garante se tem no inventario
                                    .findFirst();
                            if (existingItem.isPresent()) {
                                existingItem.get().accumulateItems(); //se tivr ele vai acumular
                                System.out.printf("Saqueado, tendo %dx %s\n", existingItem.get().getQuantity(), item.getDescricao());
                            } else {
                                character.getInventory().add(item); //senão ele adiciona no inventário e mostra 1x na quantidade
                                item.setQuantity(1);
                                System.out.printf("Você saqueou %s\n", item.getDescricao());
                            }
                        });
                characterTarget.getInventory().clear(); //limpa inventário do inimigo
                character.setQuantityUsedItem(0); //zera a quatidade de vezes usadas o item
            }
        }
    }

    public static void enemyTurn(Character character, Character characterTarget){
            System.out.println("Turno do inimigo....");
            characterTarget.runIa(character); //uso a ia do inimigo que decide o que ele faz
        }


    public static void endOfGame(Character character, List<String> enemysExistence){
        if (character.getLife() <= 0){
            System.out.println("Você foi derrotado.... Fim de jogo");
        } else if (enemysExistence.isEmpty()) {
            System.out.println("Fim de jogo!");
        }

    }

    public static Character createEnemy(int option){
        option -=1; //para usar nas opções
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
                    newEnemy.generateItem(); //faço uma pequena ia para de forma aleatoria surgir inimigos com item ou não
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

    public static void verifyEnemyExistence(Character characterTarget, List<String> enemysExistence){
        enemysExistence.remove(characterTarget.getName()); //remove o nome do inimigo da lista, para fazer a contagem correta
    }

    public static void showInventary(Character character){
        if (character.getInventory().isEmpty()){
            System.out.println("Inventário vazio");
        } else {
            IntStream.range(0, character.getInventory().size()) //para mostrar o numero do lado do item no inventario, começa de 0 e até o tamanho da lista
                    .forEach(item -> System.out.println(item + "- "+ character.getInventory().get(item).getQuantity() + "x" + character.getInventory().get(item).getDescricao())); //mensagem de cada item
        }
    }
}
