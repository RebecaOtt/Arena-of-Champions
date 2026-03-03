import java.util.ArrayList;
import java.util.List;

public class Archer extends Character{

    public Archer(String name, int life, int attack, int defense, List<InventaryNames> inventory) {
        super(name, life, attack, defense, inventory);
    }

    public static ArrayList<String> nameslistArcher = new ArrayList<>(
            List.of("Fennec Shand", "Cad Bane", "IG-88")
    );

    public static ArrayList<Integer> listLifeArcher = new ArrayList<>(
            List.of(88, 98, 108)
    );

    public static ArrayList<Integer> listAttackArcher = new ArrayList<>(
            List.of(20, 30, 40)
    );

    public static ArrayList<Integer> listDefenseArcher = new ArrayList<>(
            List.of(14, 24, 38)
    );

    @Override
    public void runIa(Character character) {
        double chance = Math.random(); //random para sortear o que o inimigo vai fazer

        if (chance < 0.7) { //isso faz ele atacar 70% que defender, por ser arqueiro
            System.out.println("Atacando!");
            this.strik(character);
        } else {
            System.out.println("Defendendo!");
            this.defend();
        }
    }
}
