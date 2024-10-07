public class SpeedCyborg extends Cyborg {

    private String favoriteShoes;
    
    public SpeedCyborg(
        String model,
        String manufacturer,
        String favoriteShoes
    ) {
        super(model, manufacturer);
        this.favoriteShoes = favoriteShoes;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.printf("Favorite Speed Shoes: %s\n", favoriteShoes);
    }

    @Override
    public void performTask() {
        System.out.printf("%s is using their favorite pair of %s to perform a task extremely quickly.\n", model, favoriteShoes);
    }
}
