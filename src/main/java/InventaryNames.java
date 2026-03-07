public enum InventaryNames {
    POCAO_DE_CURA("Poção de cura"),
    BANDAGENS("Bandagens");

    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    private final String descricao;
    InventaryNames(String descricao){
        this.descricao = descricao;
    }

    public void accumulateItems(){
        this.quantity++;
    }

    public void decreateItems(){
        this.quantity -= 1;
    }

    public String getDescricao(){
        return this.descricao;
    }
}
