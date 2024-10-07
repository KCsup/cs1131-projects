public class EnhancedVisionCyborg extends Cyborg {
    private String visionType;

    public EnhancedVisionCyborg(
        String model,
        String manufacturer,
        String visionType
    ) {
        super( model, manufacturer );
        this.visionType = visionType;
    }

    @Override
    public void displayInfo( ) {
        super.displayInfo( );
        System.out.println( "Vision Type: " + visionType );
    }

    @Override
    public void performTask( ) {
        System.out.printf(
            "%s is using %s to perform a task.\n",
            model,
            visionType
        );
    }
}
