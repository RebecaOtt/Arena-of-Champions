import java.util.ArrayList;
import java.util.List;

public class Archer extends Character{

    public Archer(String name, int life, int attack, int defense, List<String> inventory) {
        super(name, life, attack, defense, inventory);
    }

    public static ArrayList<String> nameslistArcher = new ArrayList<>(
            List.of("Fennec Shand", "Cad Bane", "IG-88")
    );

    public static ArrayList<Integer> listLifeArcher = new ArrayList<>(
            List.of(100, 110, 120)
    );

    public static ArrayList<Integer> listAttackArcher = new ArrayList<>(
            List.of(20, 30, 40)
    );

    public static ArrayList<Integer> listDefenseArcher = new ArrayList<>(
            List.of(14, 24, 38)
    );
}
