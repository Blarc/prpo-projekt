package beans;

import javax.enterprise.context.ApplicationScoped;
import java.util.logging.Logger;

@ApplicationScoped
public class CallCounterBean {

    private final Logger log = Logger.getLogger(CallCounterBean.class.getName());
    private int counter;

    public void increaseCounter() {
        this.counter += 1;
        log.info("Število klicev: " + counter);
    }
}
