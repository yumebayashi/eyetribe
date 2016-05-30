import com.theeyetribe.clientsdk.GazeManager;
import com.theeyetribe.clientsdk.IGazeListener;
import com.theeyetribe.clientsdk.IScreenStateListener;
import com.theeyetribe.clientsdk.data.GazeData;
import com.theeyetribe.clientsdk.data.Point2D;

import java.util.Arrays;

/**
 * Created by a13423 on 5/25/16.
 */
public class EyeTrack {
    public void execute() {
        final GazeManager gm = GazeManager.getInstance();

        final GazeListener gazeListener = new GazeListener();
        gm.addGazeListener(gazeListener);

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.out.println("finished");
                gm.removeGazeListener(gazeListener);
                gm.deactivate();
            }
        });

    }


    private class GazeListener implements IGazeListener {
        private float[] xs = new float[30];
        private float[] ys = new float[30];
        private int counter = 0;

        @Override
        public void onGazeUpdate(GazeData gazeData) {
            if (counter > 29) counter = 0;
            xs[counter] = gazeData.smoothedCoordinates.x;
            ys[counter] = gazeData.smoothedCoordinates.y;
            counter++;

            if (counter % 30 == 0) {
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
                Chat.broadcastMessage("stop", posType.getPosition());
            }

        }

        private Point2D getMedian() {
            Arrays.sort(xs);
            Arrays.sort(ys);

            float x = (xs[14] + xs[15]) / 2;
            float y = (ys[14] + ys[15]) / 2;

            return new Point2D(x, y);
        }

        private Point2D getMedian(int index) {
            float[] xs = new float[10];
            float[] ys = new float[10];

            for (int i = 0; i < 10; i++) {
                index--;
                xs[i] = this.xs[index];
                ys[i] = this.ys[index];
            }

            Arrays.sort(xs);
            Arrays.sort(ys);

            float x = (xs[4] + xs[5]) / 2;
            float y = (ys[4] + ys[5]) / 2;

            return new Point2D(x, y);
        }
    }

}
