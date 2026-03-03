import java.util.ArrayList;
import java.util.List;

public class Wizard extends Character{

    public Wizard(String name, int life, int attack, int defense, List<InventaryNames> inventory) {
        super(name, life, attack, defense, inventory);
    }

    public static ArrayList<String> namesListWizard = new ArrayList<>(
            List.of("Snoke", "Asajj Ventress", "Savage Opress")
    );

    public static ArrayList<Integer> listLifeWizard = new ArrayList<>(
            List.of(85, 95, 105));

    public static ArrayList<Integer> listAttackWizard = new ArrayList<>(
            List.of(4,10,20)
    );

    public static ArrayList<Integer> listDefenseWizard = new ArrayList<>(
            List.of(6, 10, 12)
    );

    @Override
    public void runIa(Character character) {
        double chance = Math.random(); //random para sortear o que o inimigo vai fazer

        if (chance < 0.8) { //isso faz ele atacar 80% que defender, por ser mago vai ser menor o ataque do que o guerreiro
            System.out.println("Atacando!");
            this.strik(character);
        } else {
            System.out.println("Defendendo!");
            this.defend();
        }
    }
}
