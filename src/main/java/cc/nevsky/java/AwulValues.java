package cc.nevsky.java;

import com.pi4j.wiringpi.SoftPwm;
import org.jboss.logging.Logger;

/**
 * Переключатели для awul.
 *
 * @author Aleksandr Nevsky
 */
public class AwulValues {
    private static final Logger LOGGER = Logger.getLogger(AwulValues.class);

    /**
     * Пин для ШИМ.
     * Шестой сверху. № 12.
     */
    private static final int AWUL_PIN = 1;

    static {
        // initialize wiringPi library
        com.pi4j.wiringpi.Gpio.wiringPiSetup();

        // create soft-pwm pins (min=0 ; max=100)
        SoftPwm.softPwmCreate(AWUL_PIN, 0, 100);
    }

    /**
     * Включать ли срабатывание ШИМ awul.
     */
    private static volatile boolean isPwmEventEnable = true;

    /**
     * Включен ли сейчас ШИМ awul.
     */
    private static volatile boolean isNowPwmEnable = false;

    private static volatile int pwmValue = 0;

    public static int getPwmValue() {
        return pwmValue;
    }

    /**
     * Прописываем значение в переменную класса и отправляем значение на контроллер.
     *
     * @param pwmValue значение ШИМ.
     */
    public static void setPwmValue(int pwmValue) {
        if(pwmValue != AwulValues.pwmValue) {
            AwulValues.pwmValue = pwmValue;
            SoftPwm.softPwmWrite(AwulValues.getAwulPin(), pwmValue);
        }
    }

    public static boolean isIsNowPwmEnable() {
        return isNowPwmEnable;
    }

    public static void setIsNowPwmEnable(boolean isNowPwmEnable) {
        AwulValues.isNowPwmEnable = isNowPwmEnable;
    }

    public static boolean isIsPwmEventEnable() {
        return isPwmEventEnable;
    }

    public static void setIsPwmEventEnable(boolean isPwmEventEnable) {
        AwulValues.isPwmEventEnable = isPwmEventEnable;
    }

    public static int getAwulPin() {
        return AWUL_PIN;
    }
}
