import java.util.ArrayList;
import java.util.List;

public class Wizard extends Character{

    public Wizard(String name, int life, int attack, int defense, List<String> inventory) {
        super(name, life, attack, defense, inventory);
    }

    public static ArrayList<String> namesListWizard = new ArrayList<>(
            List.of("Snoke", "Asajj Ventress", "Savage Opress")
    );

    public static ArrayList<Integer> listLifeWizard = new ArrayList<>(
            List.of(90, 100, 105)
    );

    public static ArrayList<Integer> listAttackWizard = new ArrayList<>(
            List.of(4,10,20)
    );

    public static ArrayList<Integer> listDefenseWizard = new ArrayList<>(
            List.of(6, 10, 12)
    );
}
