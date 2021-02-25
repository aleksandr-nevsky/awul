package cc.nevsky.java;

import com.pi4j.wiringpi.SoftPwm;
import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/awul")
public class AwulController {

    private static final Logger LOG = Logger.getLogger(AwulController.class);

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

    @Path("/pwm/{value}")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String pwm(@PathParam int value) {
        if (value >= 0 && value <= 100) {
            LOG.info("Set value = " + value);
            SoftPwm.softPwmWrite(AWUL_PIN, value);
            return "Set value = " + value;
        } else {
            LOG.info("Incorrect value = " + value);
            return "Error. Use value 0..100.";
        }
    }

}