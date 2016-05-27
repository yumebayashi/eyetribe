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

        boolean bool = gm.activate();
        final GazeListener gazeListener = new GazeListener();
        gm.addGazeListener(gazeListener);

//        gm.addScreenStateListener(new ScreenListener());

        //TODO: Do awesome gaze control wizardry


        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.out.println("finished");
                gm.removeGazeListener(gazeListener);
                gm.deactivate();
            }
        });

    }

    private class ScreenListener implements IScreenStateListener {

        @Override
        public void onScreenStatesChanged(int i, int i1, int i2, float v, float v1) {

        }
    }

    private class GazeListener implements IGazeListener {
        private float[] xs = new float[30];
        private float[] ys = new float[30];
        private int counter = 0;

        @Override
        public void onGazeUpdate(GazeData gazeData) {
//            System.out.println(gazeData.toString());
//            System.out.println(gazeData.smoothedCoordinates);
            if (counter > 29) counter = 0;
            xs[counter] = gazeData.smoothedCoordinates.x;
            ys[counter] = gazeData.smoothedCoordinates.y;
            counter++;

//            if (counter % 10 == 0) {
//                Point2D pt = getAverage(counter);
//                String ret = pt.x + ":" + pt.y;
//                System.out.println(ret);
//                Chat.broadcastMessage("move", ret);
//            }

            if (counter % 30 == 0) {
//                System.out.println(getAverage());
//                x 0-1400
//                y 0-900
                Point2D pt = getMedian();
                String ret = "";

                if (pt.x < 450 && pt.y < 300) {
                    ret = "leftup";
                } else if (pt.x > 450 && pt.x < 900 && pt.y < 300) {
                    ret = "centerup";
                } else if (pt.x > 900 && pt.y < 300) {
                    ret = "rightup";
                } else if (pt.x < 450 && pt.y > 300 && pt.y < 600) {
                    ret = "leftmiddle";
                } else if (pt.x > 450 && pt.x < 900 && pt.y > 300 && pt.y < 600) {
                    ret = "centermiddle";
                } else if (pt.x > 900 && pt.y > 300 && pt.y < 600) {
                    ret = "rightmiddle";
                } else if (pt.x < 450 && pt.y > 600) {
                    ret = "leftdown";
                } else if (pt.x > 450 && pt.x < 900 && pt.y > 600) {
                    ret = "centerdown";
                } else if (pt.x > 900 && pt.y > 600) {
                    ret = "rightdown";
                }
                Chat.broadcastMessage("stop", ret);
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
