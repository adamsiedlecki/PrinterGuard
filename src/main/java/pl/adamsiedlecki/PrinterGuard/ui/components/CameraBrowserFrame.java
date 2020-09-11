package pl.adamsiedlecki.PrinterGuard.ui.components;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.BrowserFrame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import pl.adamsiedlecki.PrinterGuard.http.InfoGetter;

@Component
@Scope("prototype")
public class CameraBrowserFrame extends BrowserFrame {

    @Autowired
    public CameraBrowserFrame(Environment env) {
        InfoGetter infoGetter = new InfoGetter();
        String externalAddress = infoGetter.getExternalIpAddress();

        String address = "http://" + externalAddress + ":" + env.getProperty("camera.port");

        setSizeUndefined();
        setAlternateText(env.getProperty("camera.alternative.text"));
        setWidth(Float.parseFloat(env.getProperty("camera.width", "480")), Unit.PIXELS);
        setHeight(Float.parseFloat(env.getProperty("camera.height", "400")), Unit.PIXELS);
        setSource(new ExternalResource(address));

    }


}
