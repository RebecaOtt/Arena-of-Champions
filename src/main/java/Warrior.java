import java.util.ArrayList;
import java.util.List;

public class Warrior extends Character{

    public Warrior(String name, int life, int attack, int defense, List<String> inventory) {
        super(name, life, attack, defense, inventory);
    }

    public static ArrayList<String> listNamesWarrior = new ArrayList<>(
            List.of("Darth Vader", "Darth Maul", "Kylo Ren")
    );

    public static ArrayList<Integer> listLifeWarrior = new ArrayList<>(
            List.of(80, 90, 100)
    );

    public static ArrayList<Integer> listAttackWarrior = new ArrayList<>(
            List.of(2,5,10)
    );

    public static ArrayList<Integer> listDefenseWarrior = new ArrayList<>(
            List.of(3, 5, 6)
    );

}
