/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab.dialogues.entities;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 */
@XmlRootElement(name = "DIALOGUES")
@XmlAccessorType(XmlAccessType.FIELD)
public class Dialogues implements Serializable {

    private static final long serialVersionUID = 1L;

    
    @XmlElement(name = "dial")
    private List<Dial>dials;

    public List<Dial> getDials() {
        return dials;
    }

    public void setDials(List<Dial> dials) {
        this.dials = dials;
    }

    
    
    
    
}
