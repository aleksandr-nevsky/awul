package cc.nevsky.java;

import com.pi4j.wiringpi.SoftPwm;
import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Контроллер awul.
 *
 * @author Aleksandr Nevsky
 */
@Path("/awul")
public class AwulController {

    private static final Logger LOG = Logger.getLogger(AwulController.class);



    /**
     * Регулировка яркости. При value = 0 выключается.
     *
     * @param value значение ШИМ.
     * @return значение ШИМ.
     */
    @Path("/pwm/{value}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String pwm(@PathParam int value) {
        if (value >= 0 && value <= 100) {
            LOG.info("Set value = " + value);
            AwulValues.setPwmValue(value);
            return "Set value = " + value;
        } else {
            LOG.info("Incorrect value = " + value);
            return "Error. Use value 0..100.";
        }
    }

    /**
     * Получаем текущее значение ШИМ.
     *
     * @return текущее значение ШИМ.
     */
    @Path("/pwm/getValue")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public int getValue() {
        LOG.info("getValue");
        return AwulValues.getPwmValue();
    }

    /**
     * Включаем/выключаем световой будильник.
     *
     * @param isOn включить или выключить.
     */
    @Path("/wakeupToggle/{isOn}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String wakeupToggleSet(@PathParam boolean isOn) {
        LOG.info("wakeupToggleSet " + isOn);
        AwulValues.setIsPwmEventEnable(isOn);

        return "Set " + isOn;
    }

    /**
     * Включаем/выключаем световой будильник.
     *
     * @param isOn включить или выключить.
     */
    @Path("/wakeupToggle")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public boolean wakeupToggle(@PathParam boolean isOn) {
        LOG.info("wakeupToggle");
        return AwulValues.isIsPwmEventEnable();
    }

    /**
     * Выключаем свет и будильник.
     */
    @Path("/lightOff")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String lightOff() {
        LOG.info("lightOff");
        AwulValues.setPwmValue(0);
        SoftPwm.softPwmWrite(AwulValues.getAwulPin(), 0);
        AwulValues.setIsNowPwmEnable(false);

        return "lightOff";
    }
}