package nz.co.twg.converter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.commons.lang3.RandomUtils;

import com.demandware.xml.impex.catalog._2006_10_31.ComplexTypeStore;
import com.demandware.xml.impex.catalog._2006_10_31.Stores;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import pl.jalokim.propertiestojson.util.PropertiesToJsonConverter;

public class Converter {
    
    private static final String STORE_PROPERTIES_FILE = "C:\\Users\\309072\\Downloads\\stores.properties";
    private static final String STORE_EXT_PROPERTIES_FILE = "C:\\Users\\309072\\Downloads\\stores-ext.properties";
    private static final String STORE_INFO_PROPERTIES_FILE = "C:\\Users\\309072\\Downloads\\stores-info.properties";
    private static final String STORE_JSON_FILE = "C:\\Users\\309072\\Downloads\\stores.properties2.json";
    private static final String STORE_XML_FILE = "C:\\Users\\309072\\Downloads\\stores-";

    public static void main(String[] args) {
        Converter converter = new Converter();
        converter.exec();
    }

    private void exec() {
        Map<String, Object> map = new HashMap<String, Object>();

        try {
            FileReader reader = new FileReader(STORE_PROPERTIES_FILE);  
            Properties p=new Properties();
            p.load(reader);
            
            p.forEach((key, value) -> {
                map.put(String.valueOf(key), value);
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FileReader reader = new FileReader(STORE_EXT_PROPERTIES_FILE);  
            Properties p=new Properties();
            p.load(reader);

            p.forEach((key, value) -> {
                String keyAux = key.toString().substring(0, key.toString().lastIndexOf('.'));

                if (map.keySet().contains(keyAux.concat(".name")) 
                        &&  (String.valueOf(key).contains(".name") || String.valueOf(key).contains(".extraDescription") )) {
                    map.put(String.valueOf(key), value);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            FileReader reader = new FileReader(STORE_INFO_PROPERTIES_FILE);  
            Properties p=new Properties();
            p.load(reader);

            p.forEach((key, value) -> {
                map.put(String.valueOf(key), value);
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

      // convert from file
        String jsonFromProperties = new PropertiesToJsonConverter().convertFromValuesAsObjectMap(map);
      try (FileWriter fw = new FileWriter(STORE_JSON_FILE)) {
          fw.write(jsonFromProperties);
      } catch (IOException e) {
          e.printStackTrace();
      }

      // pretty print 
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      Stores stores = new Stores();

      try (Reader reader = new FileReader(STORE_JSON_FILE)) {
          JsonElement json = gson.fromJson(reader, JsonElement.class);
          JsonElement jsonStateCode = json.getAsJsonObject().get("statecode");

          json.getAsJsonObject().get("store").getAsJsonObject().entrySet().iterator().forEachRemaining(item -> {
              ComplexTypeStore store = new ComplexTypeStore();
              stores.add(store.build(item, jsonStateCode));
          });

      } catch (IOException e) {
          e.printStackTrace();
      }

      try {
          JAXBContext jaxbContext = JAXBContext.newInstance(Stores.class);
          Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
          jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
          File file = new File(STORE_XML_FILE + RandomUtils.nextLong() +".xml");

          //Writes XML file to file-system
          jaxbMarshaller.marshal(stores, file); 
      } catch (JAXBException e) {
          e.printStackTrace();
      }
    }
}
