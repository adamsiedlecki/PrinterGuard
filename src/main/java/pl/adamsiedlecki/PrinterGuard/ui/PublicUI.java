package pl.adamsiedlecki.PrinterGuard.ui;


import com.github.sarxos.webcam.Webcam;
import com.vaadin.annotations.StyleSheet;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.*;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import pl.adamsiedlecki.PrinterGuard.hardware.RaspberryGPIO;
import pl.adamsiedlecki.PrinterGuard.tests.MakePhoto;
import pl.adamsiedlecki.PrinterGuard.tests.MakePhotos;
import pl.adamsiedlecki.PrinterGuard.tests.SavePicture;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.concurrent.atomic.AtomicReference;

@SpringUI(path="/")
@StyleSheet({"https://fonts.googleapis.com/css?family=Ubuntu&display=swap"})
@Scope("prototype")
public class PublicUI extends UI {

    private VerticalLayout root;
    private volatile int num = 0;
    private Environment env;
    private final RaspberryGPIO raspberryGPIO = new RaspberryGPIO();

    @Autowired
    public PublicUI(Environment env) {

        this.env = env;
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        root = new VerticalLayout();
        setHeader();
        initButtons();
        setFooter();
        setContent(root);
        root.setMargin(false);
        root.setSpacing(false);
        root.forEach(component -> root.setComponentAlignment(component, Alignment.MIDDLE_CENTER));
    }

    private void setHeader() {
        Label label = new Label(env.getProperty("app.name")+" v"+env.getProperty("app.version"));
        label.setStyleName("h1");
        root.addComponent(label);
        root.setComponentAlignment(label, Alignment.MIDDLE_CENTER);
    }

    private void initButtons() {
        File lastFile = new File("frames/img0.jpg");


        String address = "http://"+env.getProperty("camera.address")+":"+env.getProperty("camera.port");
        BrowserFrame browserFrame = new BrowserFrame();
        browserFrame.setSizeUndefined();
        browserFrame.setAlternateText(env.getProperty("camera.alternative.text"));
        browserFrame.setWidth(Float.parseFloat(env.getProperty("camera.width","480")), Unit.PIXELS);
        browserFrame.setHeight(Float.parseFloat(env.getProperty("camera.height", "400")), Unit.PIXELS);
        browserFrame.setSource(new ExternalResource(address));

        Panel videoPanel = new Panel();
        videoPanel.setSizeUndefined();
        videoPanel.setWidth(1000, Unit.PIXELS);
        videoPanel.setHeight(1000, Unit.PERCENTAGE);


        Panel printerOnOffPanel = getOnOffPanel("Printer ON/OFF (GPIO 14)", 14);
        Panel lightOnOffPanel = getOnOffPanel("Light ON/OFF (GPIO 15)", 15);

        HorizontalLayout panelsLayout = new HorizontalLayout(browserFrame, printerOnOffPanel, lightOnOffPanel);
        panelsLayout.setSpacing(true);
        panelsLayout.setSizeUndefined();
        //panelsLayout.setWidth(100, Unit.PERCENTAGE);
        //VerticalLayout v = new VerticalLayout(browserFrame, panelsLayout);

        videoPanel.setContent(panelsLayout);


        root.addComponentsAndExpand(videoPanel);

    }

    private void setFooter() {

        Label art = new Label(textArt, ContentMode.PREFORMATTED);
        root.addComponent(art);
        root.setComponentAlignment(art, Alignment.MIDDLE_CENTER);

        Label sign = new Label(signArt, ContentMode.PREFORMATTED);
        sign.setStyleName("h2");
        root.addComponent(sign);
        root.setComponentAlignment(sign, Alignment.MIDDLE_CENTER);
    }

    private Panel getOnOffPanel(String deviceName, int gpio){
        Button buttonOn = new Button("ON");   //probably may by replace by prettier method :)
        if(raspberryGPIO.getStatusByPin(gpio)){
            buttonOn.setIcon(VaadinIcons.CHECK_CIRCLE);
        }
        Button buttonOff = new Button("OFF"); // same
        if(!raspberryGPIO.getStatusByPin(gpio)){
            buttonOff.setIcon(VaadinIcons.CLOSE_CIRCLE);
        }
        buttonOn.addClickListener(e->{

            raspberryGPIO.setHigh(gpio);
            buttonOn.setIcon(VaadinIcons.CHECK_CIRCLE);
            buttonOff.setIcon(null);


        });
        buttonOff.addClickListener(e->{

            raspberryGPIO.setLow(gpio);
            buttonOff.setIcon(VaadinIcons.CLOSE_CIRCLE);
            buttonOn.setIcon(null);

        });

        buttonOff.setStyleName(ValoTheme.BUTTON_LARGE);
        buttonOn.setStyleName(ValoTheme.BUTTON_LARGE);
        buttonOn.setWidth(160, Unit.PIXELS);
        buttonOff.setWidth(160, Unit.PIXELS);

        Panel onOffPanel = new Panel(deviceName);
        onOffPanel.setWidth(200, Unit.PIXELS);
        onOffPanel.setContent(new VerticalLayout(buttonOn, buttonOff));
        return onOffPanel;
    }

    private final String textArt = "°º¤ø,¸¸,ø¤º°`°º¤ø,¸,ø¤°º¤ø,¸¸,ø¤º°`°º¤ø,¸";

    private final String signArt = "BY ADAM SIEDLECKI";

}
