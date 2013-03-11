package com.bingoflights.model;

import org.eclipse.persistence.oxm.annotations.XmlPath;

import javax.xml.bind.annotation.*;

//@XmlAccessorType(XmlAccessType.FIELD)
public class Airport {

    //@XmlPath("text()")
    private String name;

    //@XmlAttribute(name = "code")
    private String code;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
