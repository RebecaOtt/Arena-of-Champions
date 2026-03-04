public enum InventaryNames {
    POCAO_DE_CURA("Poção de cura"),
    BANDAGENS("Bandagens");


    private final String descricao;
    InventaryNames(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao(){
        return this.descricao;
    }
}
