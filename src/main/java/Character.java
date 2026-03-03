import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Character {
    protected String name;
    protected int life;
    protected int attack;
    protected int defense;
    private List<InventaryNames> inventory;
    protected boolean isDefending = false;
    private int quantityUsedItem = 0;

    public Character(String name, int life, int attack, int defense, List<InventaryNames> inventory) {
        this.name = name;
        this.life = life;
        this.attack = attack;
        this.defense = defense;
        this.inventory = inventory;
    }

//--------------------- METODOS
    public void strik(Character characterTarget){
        System.out.printf("%s atacou %s!\n", this.name, characterTarget.getName());
        characterTarget.takeDamage(this.attack); //chama o metodo de tomarDano, manda o valor do ataque do personagem para o metodo, onde faz os calculos lá
    }

    public void defend(){
        this.isDefending = true; //avisa que ativou a defesa para no metodo de tomar dano levar isso em conta na subtracao da vida
        System.out.println("Defesa ativa, aguardando golpe...");
    }

    public void takeDamage(int damage){
        int damageFinal;
        if (this.isDefending){ //se estiver defendendo ele subtrai o dano levando em conta a defesa
            damageFinal = damage - this.defense;
            this.isDefending = false;
        } else { //senão o dano é completamente aplicado
            damageFinal = damage;
        }
        if (damageFinal < 0) damageFinal = 0; //dano não fica negativo, dando mais vida
        if ((this.life -= damageFinal) < 0) this.life = 0; //atuliza a vida, não deixa aparecer negativo

        System.out.printf("%s recebeu %d de dano! Vida atual: %d \n", this.name, damageFinal, this.life);
    }

    public boolean isAlive(){
        return getLife() > 0;
    }

    public void generateItem() {
        InventaryNames[] everbodyItem = InventaryNames.values(); //pega todos os valores do Enum
        InventaryNames itemRandom = everbodyItem[new Random().nextInt(everbodyItem.length)]; //escolhe de forma aletória
        this.inventory.add(itemRandom); //adiciona no inventário
    }

    public void useItem(int index){
        if (quantityUsedItem >= 3){
            System.out.println("Só é possivel usar 3 vezes por jogada");
        }

        if (index >=0 && index< inventory.size()){
            InventaryNames usedItem = inventory.get(index);

            if (usedItem == InventaryNames.POCAO_DE_CURA){
                this.life += 20;
                System.out.println("Poção de cura usada e recuperou 20 de vida");
                inventory.remove(index);
                this.quantityUsedItem++;
            } else if (usedItem == InventaryNames.BANDAGENS) {
                this.life +=10;
                System.out.println("Bandagem usada e recuperou 10 de vida");
                inventory.remove(index);
                this.quantityUsedItem++;
            } else {
                System.out.println("Item inválido");
            }
        }

    }

    public void runIa(Character character) {
        this.strik(character);
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

    public List<InventaryNames> getInventory() {
        if (this.inventory == null) {
            this.inventory = new ArrayList<>();
        }
        return inventory;
    }

    public void setInventory(List<InventaryNames> inventory) {
        this.inventory = inventory;
    }

    public int getQuantityUsedItem() {
        return quantityUsedItem;
    }

    public void setQuantityUsedItem(int quantityUsedItem) {
        this.quantityUsedItem = quantityUsedItem;
    }
}
