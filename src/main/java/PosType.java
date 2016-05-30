/**
 * Created by a13423 on 6/28/16.
 */
public enum PosType {
    LU("leftup"),
    CU("centerup"),
    RU("rightup"),
    LM("leftmiddle"),
    CM("centermiddle"),
    RM("rightmiddle"),
    LD("leftdown"),
    CD("centerdown"),
    RD("rightdown");

    private String position;

    PosType(String position) {
        this.position = position;
    }

    public String getPosition() {
        return position;
    }
}
