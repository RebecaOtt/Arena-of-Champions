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

//    @Override
//    public void strik(Character character){
//        int damage = this.attack - character.getDefense();
//        if (damage < 0) damage = 0;
//
//        int lifeNew = character.getLife() - damage;
//        character.setLife(lifeNew);
//        System.out.printf("Inimigo atacou e causou %d de dano!\n", damage);
//        System.out.printf("%s, sua vida restante: %d", character.getName(), character.getLife());
//    }
//
//    @Override
//    public void defend(int attackEnemy){
//        int damage = attackEnemy - this.defense;
//        setLife(this.life-damage);
//        System.out.printf("Você defendeu! recebeu %d de dano, sua vida atual: %d\n", damage, getLife());
//    }
//
////    @Override
////    public void runIa(Character character) {
////        double chance = Math.random();
////        if (chance < 0.9) {
////            this.strik(character);
////        } else {
////
////        }
////    }

}
