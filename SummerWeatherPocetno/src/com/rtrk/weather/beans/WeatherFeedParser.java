package com.rtrk.weather.beans;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class WeatherFeedParser {
    /** Final URL feedUrl. */
    private static final String FORCAST = "forcast";
    private static final String SIMPLE_FORCAST = "simpleforecast";
    private static final String FORCAST_DAY = "forecastday";
    private static final String FORCAST_DAY_PERIOD = "period";
    private static final String HIGH_TEMPERATURE = "high";
    private static final String LOW_TEMPERATURE = "low";
    private static final String CELSIUS = "celsius";
    private static final String CONDITIONS = "conditions";
    private static final String ICONS = "icons";
    private static final String ICON_SET = "iconset";
    private URL feedUrl = null;

    public WeatherFeedParser(String url) {
        try {
            this.feedUrl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public List<Weather> parse() {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        List<Weather> weatherList = new ArrayList<Weather>();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            URLConnection connection = feedUrl.openConnection();
            connection.setReadTimeout(20000);
            connection.setDoInput(true);
            Document dom = builder.parse(connection.getInputStream());
            Element root = dom.getDocumentElement();
            Node simpleforcast = root.getElementsByTagName(SIMPLE_FORCAST)
                    .item(0);
            NodeList forcastdays = simpleforcast.getChildNodes();
            for (int i = 0; i < forcastdays.getLength(); i++) {
                Node forcastday = forcastdays.item(i);
                if (forcastday.getNodeName().equalsIgnoreCase(FORCAST_DAY)) {
                    Weather weather = new Weather();
                    NodeList properties = forcastday.getChildNodes();
                    for (int j = 0; j < properties.getLength(); j++) {
                        Node property = properties.item(j);
                        String name = property.getNodeName();
                        if (name.equalsIgnoreCase(FORCAST_DAY_PERIOD)) {
                            weather.setPeriod(Integer.parseInt(property
                                    .getFirstChild().getNodeValue()));
                        }
                        if (name.equalsIgnoreCase(HIGH_TEMPERATURE)) {
                            Node temperature_property = property
                                    .getFirstChild();
                            while (temperature_property != null) {
                                String propery_name = temperature_property
                                        .getNodeName();
                                if (propery_name.equalsIgnoreCase(CELSIUS)) {
                                    weather.setHiTemperatureC(Integer
                                            .parseInt(temperature_property
                                                    .getFirstChild()
                                                    .getNodeValue()));
                                    break;
                                }
                                temperature_property = temperature_property
                                        .getNextSibling();
                            }
                        }
                        if (name.equalsIgnoreCase(LOW_TEMPERATURE)) {
                            Node temperature_property = property
                                    .getFirstChild();
                            while (temperature_property != null) {
                                String propery_name = temperature_property
                                        .getNodeName();
                                if (propery_name.equalsIgnoreCase(CELSIUS)) {
                                    weather.setLowTemeratureC(Integer
                                            .parseInt(temperature_property
                                                    .getFirstChild()
                                                    .getNodeValue()));
                                    break;
                                }
                                temperature_property = temperature_property
                                        .getNextSibling();
                            }
                        }
                        if (name.equalsIgnoreCase(CONDITIONS)) {
                            weather.setConditions(property.getFirstChild()
                                    .getNodeValue());
                        }
                        if (name.equalsIgnoreCase(ICONS)) {
                            Node icon_set = property.getFirstChild()
                                    .getNextSibling();
                            Node icon_url = icon_set.getFirstChild()
                                    .getNextSibling();
                            weather.setIconUrl(icon_url.getFirstChild()
                                    .getNodeValue());
                        }
                    }
                    weatherList.add(weather);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return weatherList;
    }
}
