package com.demandware.xml.impex.catalog._2006_10_31;
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
    
//    private static Map<String, String> storeEmail = new HashMap<String, String>();
    static {
////        storeEmail.put("02", "nelson@noelleeming.co.nz");
////        storeEmail.put("40", "albany@noelleeming.co.nz");
////        storeEmail.put("H4", "nlh4h4@noelleeming.co.nz");
////***        storeEmail.put("H5", "nlh5h5@noelleeming.co.nz");
////***        storeEmail.put("qs", "nlqsqs@noelleeming.co.nz");
////***        storeEmail.put("sl", "nlslsl@noelleeming.co.nz");
////        storeEmail.put("13", "nl1313@noelleeming.co.nz");
//        storeEmail.put("ka", "nlkaka@noelleeming.co.nz");
//        storeEmail.put("Wa", "nlwawa@noelleeming.co.nz");
////        storeEmail.put("31", "ashburton@noelleeming.co.nz");
//        storeEmail.put("32", "collcentre@noelleeming.co.nz");
//        storeEmail.put("33", "greymouth@noelleeming.co.nz");
//        storeEmail.put("34", "hornby@noelleeming.co.nz");
//        storeEmail.put("35", "moorhouse@noelleeming.co.nz");
//        storeEmail.put("36", "greymouth@noelleeming.co.nz");
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
    public ComplexTypeStore build(Entry<String, JsonElement> item, JsonElement jsonStateCode) {
        JsonObject jsonObject = item.getValue().getAsJsonObject();
        this.storeId = item.getKey();
        this.name = jsonObject.get("name").getAsString();

//        if (jsonObject.get("streetAddress").isJsonArray()) {
//            JsonArray jsonArray = jsonObject.get("streetAddress").getAsJsonArray();
//            
//            if (jsonArray.size() == 3) {
//                this.address1 = jsonArray.get(0).getAsString() +", " + jsonArray.get(1).getAsString().substring(0, jsonArray.get(1).getAsString().lastIndexOf("\n"));
//                this.address2 = jsonArray.get(1).getAsString().substring(jsonArray.get(1).getAsString().lastIndexOf("\n")+1);
//                this.city = jsonArray.get(2).getAsString();
//            } else if (jsonArray.size() == 2) {
//                String item0 = jsonArray.get(0).getAsString();
//                String item1 = jsonArray.get(1).getAsString();
//
//                if (item1.contains("\n")) {
//                    this.address1 = item0.replaceAll("\n", ", ");
//                    this.address2 = item1.substring(0, item1.lastIndexOf("\n"));
//                    this.city = item1.substring(item1.lastIndexOf("\n")+1);
//                } else if (item0.contains("\n")) {
//                    this.address1 = item0.substring(0, item0.lastIndexOf("\n")).replaceAll("\n", ", ");
//                    this.address2 = item0.substring(item0.lastIndexOf("\n")+1);
//                    this.city = item1;
//                }
//            }
//        } else {
            String address = jsonObject.get("streetAddress").getAsString();
            String[] split = address.split("\n");
            
            if (split.length == 2) {
                this.address1 = split[0].replaceAll("\n", ", ");
                
                if (split[1].contains(",")) {
                    String[] split2 = split[1].split(",");
                    if (split2[0].trim().equals(split2[1].trim())) {
                        this.city = split2[1].trim();
                    } else {
                        this.address2 = split2[0];
                        this.city = split2[1].trim();
                    }
                    
                } else {
                    this.city = split[1].trim();
                }
            } else if (split.length == 3) {
                
                if (split[1].contains(",")) {
                    String[] split2 = split[1].split(",");
                    this.address2 = split2[1].trim();
                    this.address1 = split[0].concat(", ").concat(split2[0]);
                    this.city = split[2].trim();
                } else {
                    this.address1 = split[0].concat(", ").concat(split[1]);
                    
                    if (split[2].contains(", ")) {
                        String[] split2 = split[2].split(",");
                        
                        if (split2[0].trim().equals(split2[1].trim())) {
                            this.city = split2[1].trim();
                        } else {
                            this.address2 = split2[0];
                            this.city = split2[1].trim();
                        }
                    } else {
                        this.city = split[2].trim();
                    }
                }
            } else if (split.length == 4) {
                this.address1 = split[0].concat(", ").concat(split[1]);
                this.address2 = split[2];
                this.city = split[3].trim();
                System.out.println("bla: " + address.replaceAll("\n", ", ") + " items: " + address.replaceAll("\n", ", ").split(",").length);
            } else { 
                this.address1 = split[0].concat(", ").concat(split[1]);
                for (int i = 0; i < split.length-2; i++) {
                    this.address1 += split[i] + ", ";
                }
                this.address1 = this.address1.substring(0, this.address1.lastIndexOf(","));
                this.address2 = split[split.length-2];
                this.city = split[split.length-1];
            }
//        }

        this.postalCode = jsonObject.get("postalCode").getAsString();
        this.stateCode =  this.getStateCodeByRegion(jsonStateCode, this.storeId);
        this.countryCode = "NZ";
        if (jsonObject.get("email") != null) {
            this.email = jsonObject.get("email").getAsString();
        }
        this.phone = jsonObject.get("phone").getAsString();
        this.latitude = jsonObject.get("latitude").getAsString();
        this.longitude = jsonObject.get("longitude").getAsString();
        this.customAttributes = new SharedTypeCustomAttributes();

        SharedTypeCustomAttribute googlePlaceId = new SharedTypeCustomAttribute();
        googlePlaceId.setAttributeId("googlePlaceId");
        googlePlaceId.getContent().add(jsonObject.get("googlePlaceID").getAsString());
        this.customAttributes.getCustomAttribute().add(googlePlaceId);

        SharedTypeCustomAttribute pageDescription = new SharedTypeCustomAttribute();
        pageDescription.setAttributeId("pageDescription");
        pageDescription.setLang("x-default");

        if (jsonObject.get("extraDescription") != null) {
            pageDescription.getContent().add(jsonObject.get("extraDescription").getAsString());
            this.customAttributes.getCustomAttribute().add(pageDescription);
        }

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

    private String getStateCodeByRegion(JsonElement jsonElement, String storeId) {
        for (Entry<String, JsonElement> item : jsonElement.getAsJsonObject().entrySet()) {
            if (item.getValue().toString().contains(storeId)) {
                return item.getKey();
            }
        }
        return null;
    }
    private void addWeekDay(JsonArray jsonArray, String weekDay, JsonElement jsonElement) {
        if (jsonElement != null) {
            jsonArray.add(this.formatHour(weekDay, jsonElement));
        }
    }

    private String formatHour(String weekDay, JsonElement jsonElement) {
        String s = jsonElement.getAsString();
        s = s.replaceAll("am", "").replaceAll("pm", "");
        s = StringUtils.replaceChars(s, '.', ':');

        String[] split = s.split("–");
        String openingHour = split[0];
        String closeHour = split[1];

        String hour = replaceHour(closeHour.substring(0, closeHour.indexOf(":")));
        closeHour = closeHour.replace(closeHour.substring(0, closeHour.indexOf(":")+1), hour + ":");

        if (openingHour.indexOf(":") == 1) {
            openingHour = "0" + openingHour;
        }
        return weekDay.concat(" ").concat(openingHour).concat("-").concat(closeHour);
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
