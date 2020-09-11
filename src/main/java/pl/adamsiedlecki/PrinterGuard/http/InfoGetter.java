package pl.adamsiedlecki.PrinterGuard.http;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;
import pl.adamsiedlecki.PrinterGuard.pojo.IpAddress;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InfoGetter {

    /*
        Returns external router address or localhost address, if ping to API is not successful
    */
    public String getExternalIpAddress() {

        String apiAddress = "https://api.myip.com";

        if (!Ping.isActive(apiAddress.split("//")[1])) {
            return "127.0.0.1";
        }

        RestTemplate restTemplate = new RestTemplate();
        addMessageConverter(restTemplate);

        ResponseEntity<IpAddress> response = restTemplate.getForEntity(
                apiAddress,
                IpAddress.class);
        IpAddress ipAddress = response.getBody();

        System.out.println("Router IP: " + ipAddress.getIp());
        return "" + ipAddress.getIp();
    }

    private void addMessageConverter(RestTemplate restTemplate) {
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        //Add the Jackson Message converter
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

        // Note: here we are making this converter to process any kind of response,
        // not only application/*json, which is the default behaviour
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);
    }
}
