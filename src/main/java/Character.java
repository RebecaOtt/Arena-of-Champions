import java.util.ArrayList;
import java.util.List;

public class Character {
    protected String name;
    protected int life;
    protected int attack;
    protected int defense;
    private List<String> inventory = new ArrayList<>();

    public Character(String name, int life, int attack, int defense, List<String> inventory) {
        this.name = name;
        this.life = life;
        this.attack = attack;
        this.defense = defense;
        this.inventory = inventory;
    }

    public void strik(String characterTarget){

    }

    public void defend(){

    }

    public int takeDamage(int damage){
        return getLife() - damage;
    }

    public boolean isAlive(){
        return getLife() > 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public List<String> getInventory() {
        return inventory;
    }

    public void setInventory(List<String> inventory) {
        this.inventory = inventory;
    }
}
