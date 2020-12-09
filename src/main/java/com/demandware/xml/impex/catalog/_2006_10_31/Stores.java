package com.demandware.xml.impex.catalog._2006_10_31;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "stores")
public class Stores {
    @XmlAttribute(name = "xmlns", required = true)
    private String namespace = "http://www.demandware.com/xml/impex/store/2007-04-30";
    
    
    private List<ComplexTypeStore> store = new ArrayList<ComplexTypeStore>();

    public List<ComplexTypeStore> getStore() {
        return store;
    }

    public void add(ComplexTypeStore store) {
        this.store.add(store);
    }
}
