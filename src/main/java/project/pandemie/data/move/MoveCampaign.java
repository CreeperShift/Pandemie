package project.pandemie.data.move;

public class MoveCampaign extends Move {

    private String type = "launchCampaign";
    private String city;

    public MoveCampaign(String city) {
        super(0);
        this.city = city;
    }

    @Override
    public int getCost() {
        return 3;
    }


    public String getType() {
        return type;
    }

}
