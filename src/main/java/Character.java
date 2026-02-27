import java.util.List;

public class Character {
    protected String name;
    protected int life;
    protected int attack;
    protected int defense;
    private List<String> inventory;
    protected boolean isDefending = false;

    public Character(String name, int life, int attack, int defense, List<String> inventory) {
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

    public List<String> getInventory() {
        return inventory;
    }

    public void setInventory(List<String> inventory) {
        this.inventory = inventory;
    }
}
