package cc.nevsky.java;

import org.jboss.logging.Logger;

/**
 * Отдельный поток для плавного увеличения яркости.
 *
 * @author Aleksandr Nevsky
 */
public class AwulLogicThead extends Thread {
    private static final Logger LOGGER = Logger.getLogger(AwulLogicThead.class);

    @Override
    public void run() {
        LOGGER.info("AwulLogicThead run.");
        try {
            int counter = 0;

            while (AwulValues.isIsNowPwmEnable()
                    && AwulValues.getPwmValue() < 100
                    && counter < 1200) {

                counter++;
                AwulValues.setPwmValue(counter / 12);

                //noinspection BusyWait
                Thread.sleep(500);
            }
        } catch (InterruptedException e) {
            LOGGER.error(e);
        } finally {
            AwulValues.setIsNowPwmEnable(false);
            LOGGER.info("AwulLogicThead finally.");
        }
    }
}
