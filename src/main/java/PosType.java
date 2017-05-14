/**
 * Created by a13423 on 6/28/16.
 */
public enum PosType {
    LT("lefttop", 1, 1),
    CT("centertop", 2, 1),
    RT("righttop", 3, 1),
    LM("leftmiddle", 1, 2),
    CM("centermiddle", 2, 2),
    RM("rightmiddle", 3, 2),
    LB("leftbottom", 1, 3),
    CB("centerbottom", 2, 3),
    RB("rightbottom", 3, 3);

    private String position;
    private int x;
    private int y;

    PosType(String position, int x, int y) {
        this.position = position;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getPosition() {
        return position;
    }
}
