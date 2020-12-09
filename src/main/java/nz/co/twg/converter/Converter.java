package nz.co.twg.converter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

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
    
    private final String STORE_PROPERTIES_FILE = "C:\\Users\\309072\\Downloads\\stores.properties";
    private final String STORE_JSON_FILE = "C:\\Users\\309072\\Downloads\\stores.properties2.json";
    private final String STORE_XML_FILE = "C:\\Users\\309072\\Downloads\\stores-";

    public static void main(String[] args) {
        Converter converter = new Converter();
        converter.exec();
    }
    
    private void exec() {
      // convert from file
      String jsonFromProperties = new PropertiesToJsonConverter().convertPropertiesFromFileToJson(STORE_PROPERTIES_FILE);
      try (FileWriter fw = new FileWriter(STORE_JSON_FILE)) {
          fw.write(jsonFromProperties);
      } catch (IOException e) {
          e.printStackTrace();
      }

      // pretty print 
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      Map<String, JsonElement> map = new HashMap<String, JsonElement>();
      Stores stores = new Stores();

      try (Reader reader = new FileReader(STORE_JSON_FILE)) {
          JsonElement json = gson.fromJson(reader, JsonElement.class);

          json.getAsJsonObject().get("store").getAsJsonObject().entrySet().iterator().forEachRemaining(item -> {
              map.put(item.getKey(), item.getValue());
//              System.out.println(item);
              ComplexTypeStore store = new ComplexTypeStore();
              stores.add(store.build(item));
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
      } 
      catch (JAXBException e) {
          e.printStackTrace();
      }
    }
}
