import com.theeyetribe.clientsdk.IGazeListener;
import com.theeyetribe.clientsdk.data.GazeData;
import com.theeyetribe.clientsdk.data.Point2D;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

/**
 * Created by yumebayashi on 5/14/17.
 */
public class GazeListener implements IGazeListener {
    private float[] xs = new float[10];
    private float[] ys = new float[10];
    private int counter = 0;
    private int widthOffset;
    private int heihtOffset;
    private int devidedWidth;
    private int devidedHeight;

    public GazeListener(int width, int height) {
        this.widthOffset = (width / 3) / 2;
        this.heihtOffset = (height / 3) / 2;
        this.devidedWidth = width / 3;
        this.devidedHeight = height / 3;
    }

    @Override
    public void onGazeUpdate(GazeData gazeData) {
        if (counter++ >= 10) counter = 0;
        xs[counter] = gazeData.smoothedCoordinates.x;
        ys[counter] = gazeData.smoothedCoordinates.y;

        if (counter >= 9) {
            Point2D pt = getMedian();
            PosType posType = chooseNearest(pt);
            WebSocketBroadcaster.getInstance().sendToAll(posType.getPosition());
        }

    }

    private PosType chooseNearest(Point2D point) {
        Optional<PosType> posType = Arrays.stream(PosType.values()).min(Comparator.comparing(p -> {
            int x = p.getX() * devidedWidth - widthOffset;
            int y = p.getY() * devidedHeight - heihtOffset;
            return Math.pow((x - point.x), 2) + Math.pow((y - point.y), 2);
        }));
        return posType.orElse(PosType.CM);
    }

    private Point2D getMedian() {
        Arrays.sort(xs);
        Arrays.sort(ys);

        float x = (xs[4] + xs[5]) / 2;
        float y = (ys[4] + ys[5]) / 2;

        return new Point2D(x, y);
    }
}