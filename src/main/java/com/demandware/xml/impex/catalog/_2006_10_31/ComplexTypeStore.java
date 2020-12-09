package com.demandware.xml.impex.catalog._2006_10_31;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

@XmlAccessorType(XmlAccessType.FIELD)
public class ComplexTypeStore {
    
    private static Map<String, String> storeInfo = new HashMap<String, String>();
    static {
        storeInfo.put("02", "NZ-NSN|nelson@noelleeming.co.nz");
        storeInfo.put("40", "NZ-AUK|albany@noelleeming.co.nz");
        storeInfo.put("H4", "NZ-AUK|nlh4h4@noelleeming.co.nz");
        storeInfo.put("H5", "NZ-AUK|nlh5h5@noelleeming.co.nz");
        storeInfo.put("qs", "NZ-AUK|nlqsqs@noelleeming.co.nz");
        storeInfo.put("sl", "NZ-AUK|nlslsl@noelleeming.co.nz");
        storeInfo.put("13", "NZ-CAN|nl1313@noelleeming.co.nz");
        storeInfo.put("ka", "NZ-NTL|nlkaka@noelleeming.co.nz");
        storeInfo.put("Wa", "NZ-WKO|nlwawa@noelleeming.co.nz");
        storeInfo.put("31", "NZ-CAN|ashburton@noelleeming.co.nz");
        storeInfo.put("32", "NZ-CAN|collcentre@noelleeming.co.nz");
        storeInfo.put("33", "NZ-CAN|greymouth@noelleeming.co.nz");
        storeInfo.put("34", "NZ-CAN|hornby@noelleeming.co.nz");
        storeInfo.put("35", "NZ-CAN|moorhouse@noelleeming.co.nz");
        storeInfo.put("36", "NZ-CAN|greymouth@noelleeming.co.nz");
    }

    @XmlAttribute(name = "store-id", required = true)
    private String storeId;
    private String name;
    private String address1;
    private String address2;
    private String city;
    @XmlElement(name = "postal-code")
    private String postalCode;
    @XmlElement(name = "state-code")
    private String stateCode;
    @XmlElement(name = "country-code")
    private String countryCode;
    private String email;
    private String phone;
    private String latitude;
    private String longitude;
    @XmlElement(name = "custom-attributes")
    private SharedTypeCustomAttributes customAttributes;

    public String getStoreId() {
        return storeId;
    }
    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress1() {
        return address1;
    }
    public void setAddress1(String address1) {
        this.address1 = address1;
    }
    public String getAddress2() {
        return address2;
    }
    public void setAddress2(String address2) {
        this.address2 = address2;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    public String getStateCode() {
        return stateCode;
    }
    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }
    public String getCountryCode() {
        return countryCode;
    }
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getLatitude() {
        return latitude;
    }
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }
    public String getLongitude() {
        return longitude;
    }
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
    public SharedTypeCustomAttributes getCustomAttributes() {
        return customAttributes;
    }
    public void setCustomAttributes(SharedTypeCustomAttributes customAttributes) {
        this.customAttributes = customAttributes;
    }
    public ComplexTypeStore build(Entry<String, JsonElement> item) {
        JsonObject jsonObject = item.getValue().getAsJsonObject();
        this.storeId = item.getKey();
        this.name = jsonObject.get("name").getAsString();

        if (jsonObject.get("streetAddress").isJsonArray()) {
            JsonArray jsonArray = jsonObject.get("streetAddress").getAsJsonArray();
            
            if (jsonArray.size() == 3) {
                this.address1 = jsonArray.get(0).getAsString() +", " + jsonArray.get(1).getAsString().substring(0, jsonArray.get(1).getAsString().lastIndexOf("\n"));
                this.address2 = jsonArray.get(1).getAsString().substring(jsonArray.get(1).getAsString().lastIndexOf("\n")+1);
                this.city = jsonArray.get(2).getAsString();
            } else if (jsonArray.size() == 2) {
                String item0 = jsonArray.get(0).getAsString();
                String item1 = jsonArray.get(1).getAsString();

                if (item1.contains("\n")) {
                    this.address1 = item0.replaceAll("\n", ", ");
                    this.address2 = item1.substring(0, item1.lastIndexOf("\n"));
                    this.city = item1.substring(item1.lastIndexOf("\n")+1);
                } else if (item0.contains("\n")) {
                    this.address1 = item0.substring(0, item0.lastIndexOf("\n")).replaceAll("\n", ", ");
                    this.address2 = item0.substring(item0.lastIndexOf("\n")+1);
                    this.city = item1;
                }
            }
        } else {
            String address = jsonObject.get("streetAddress").getAsString();
            this.city = address.substring(address.lastIndexOf("\n")+1);
            String s = address.substring(0, address.lastIndexOf("\n"));

            if (s.contains("\n")) {
                String[] split = s.split("\n");
                this.address1 = split[0];
                this.address2 = split[1];
            } else {
                this.address1 = s;
                this.address2 = this.city;
            }
        }

        String storeInfo = ComplexTypeStore.storeInfo.get(this.storeId);
        
        this.postalCode = "0612";
        this.stateCode =  storeInfo != null ? storeInfo.substring(0, storeInfo.indexOf("|")) :  "";
        this.countryCode = "NZ";
        this.email = storeInfo != null ? storeInfo.substring(storeInfo.indexOf("|")+1) :  "";
        this.phone = jsonObject.get("phone").getAsString();
        this.latitude = jsonObject.get("latitude").getAsString();
        this.longitude = jsonObject.get("longitude").getAsString();
        this.customAttributes = new SharedTypeCustomAttributes();

        SharedTypeCustomAttribute googlePlaceId = new SharedTypeCustomAttribute();
        googlePlaceId.setAttributeId("googlePlaceId");
//        googlePlaceId.getContent().add("PLACEHOLDER-ChIJs0q6R7dBDW0RNL7tW2W3VX8");
        googlePlaceId.getContent().add("");
        this.customAttributes.getCustomAttribute().add(googlePlaceId);

        SharedTypeCustomAttribute pageDescription = new SharedTypeCustomAttribute();
        pageDescription.setAttributeId("pageDescription");
        pageDescription.setLang("x-default");
        pageDescription.getContent().add(String.format("Check out the opening hours, closing hours and store location for Noel Leeming in %s. Get more store information at noelleeming.co.nz/stores. Visit us today!", this.name));
        this.customAttributes.getCustomAttribute().add(pageDescription);

        SharedTypeCustomAttribute pageTitle = new SharedTypeCustomAttribute();
        pageTitle.setAttributeId("pageTitle");
        pageTitle.setLang("x-default");
        pageTitle.getContent().add(String.format("Noel Leeming %s", this.name));
        this.customAttributes.getCustomAttribute().add(pageTitle);

        SharedTypeCustomAttribute storeHoursJson = new SharedTypeCustomAttribute();
        storeHoursJson.setAttributeId("storeHoursJson");

        JsonArray jsonArray = new JsonArray();
        this.addWeekDay(jsonArray, "Mo", jsonObject.get("MONDAY"));
        this.addWeekDay(jsonArray, "Tu", jsonObject.get("TUESDAY"));
        this.addWeekDay(jsonArray, "We", jsonObject.get("WEDNESDAY"));
        this.addWeekDay(jsonArray, "Th", jsonObject.get("THURSDAY"));
        this.addWeekDay(jsonArray, "Fr", jsonObject.get("FRIDAY"));
        this.addWeekDay(jsonArray, "Sa", jsonObject.get("SATURDAY"));
        this.addWeekDay(jsonArray, "Su", jsonObject.get("SUNDAY"));

        storeHoursJson.getContent().add("{\"openingHours\": "+jsonArray.toString()+"}");
        this.customAttributes.getCustomAttribute().add(storeHoursJson);

        return this;
    }

    private void addWeekDay(JsonArray jsonArray, String weekDay, JsonElement jsonElement) {
        if (jsonElement != null) {
            jsonArray.add(this.formatHour(weekDay, jsonElement));
        }
    }

    private String formatHour(String weekDay, JsonElement jsonElement) {
        String s = jsonElement.getAsString();
//        s = s.replaceAll("am", "").replaceAll("pm", "");
//        if (s.indexOf(".") == 1) {
//            s = "0" + s;
//        }

//TODO verificar este trecho        
        
        String hour = replaceHour(s.substring(s.indexOf("–")+1, s.indexOf("–")+2));
        s = s.replace(s.substring(s.indexOf("–")+1, s.indexOf("–")+2), hour);
        s = s.replaceAll("am", "").replaceAll("pm", "");
        if (s.indexOf(".") == 1) {
            s = "0" + s;
        }
        s = StringUtils.replaceChars(s, '.', ':');
        return weekDay.concat(" ").concat(s);
    }
    
    private String replaceHour(String hour) {
        switch (hour) {
        case "1":
            return "13";
        case "2":
            return "14";
        case "3":
            return "15";
        case "4":
            return "16";
        case "5":
            return "17";
        case "6":
            return "18";
        case "7":
            return "19";
        case "8":
            return "20";
        case "9":
            return "21";
        case "10":
            return "22";
        case "11":
            return "23";
        default:
            return hour;
        }
    }
}
