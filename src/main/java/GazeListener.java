import com.theeyetribe.clientsdk.IGazeListener;
import com.theeyetribe.clientsdk.data.GazeData;
import com.theeyetribe.clientsdk.data.Point2D;

import java.util.Arrays;

/**
 * Created by yumebayashi on 5/14/17.
 */
public class GazeListener implements IGazeListener {
    private float[] xs = new float[10];
    private float[] ys = new float[10];
    private int counter = 0;

    @Override
    public void onGazeUpdate(GazeData gazeData) {
        if (counter++ >= 10) counter = 0;
        xs[counter] = gazeData.smoothedCoordinates.x;
        ys[counter] = gazeData.smoothedCoordinates.y;

        if (counter >= 9) {
            Point2D pt = getMedian();
            PosType posType = null;

            if (pt.x < 450 && pt.y < 300) {
                posType = PosType.LU;
            } else if (pt.x > 450 && pt.x < 900 && pt.y < 300) {
                posType = PosType.CU;
            } else if (pt.x > 900 && pt.y < 300) {
                posType = PosType.RU;
            } else if (pt.x < 450 && pt.y > 300 && pt.y < 600) {
                posType = PosType.LM;
            } else if (pt.x > 450 && pt.x < 900 && pt.y > 300 && pt.y < 600) {
                posType = PosType.CM;
            } else if (pt.x > 900 && pt.y > 300 && pt.y < 600) {
                posType = PosType.RM;
            } else if (pt.x < 450 && pt.y > 600) {
                posType = PosType.LD;
            } else if (pt.x > 450 && pt.x < 900 && pt.y > 600) {
                posType = PosType.CD;
            } else if (pt.x > 900 && pt.y > 600) {
                posType = PosType.RD;
            }
            WebSocketBroadcaster.getInstance().sendToAll(posType.getPosition());
        }

    }

    private Point2D getMedian() {
        Arrays.sort(xs);
        Arrays.sort(ys);

        float x = (xs[4] + xs[5]) / 2;
        float y = (ys[4] + ys[5]) / 2;

        return new Point2D(x, y);
    }
}