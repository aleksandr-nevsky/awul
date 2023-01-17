package cc.nevsky.java;

import io.quarkus.scheduler.Scheduled;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;

/**
 * Бин для управления ШИМ.
 *
 * @author Aleksandr Nevsky
 */
@ApplicationScoped
public class AwulBean {
    private static final Logger LOGGER = Logger.getLogger(AwulBean.class);

    @Scheduled(cron="{cron.pwm.expr}")
    @Scheduled(cron="{cron.pwm.expr2}")
    void cronAwul() {
        if(AwulValues.isIsPwmEventEnable()) {
            LOGGER.info("cronAwul PwmEvent enable");
            AwulValues.setIsNowPwmEnable(true);
            AwulLogicThead awulLogicThead = new AwulLogicThead();
            awulLogicThead.start();
        } else {
            LOGGER.info("cronAwul PwmEvent enable");
        }
    }

}
