package com.cooksys.ftd.assignments.socket.model;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Config {
    @XmlElement
    private LocalConfig local;

    @XmlElement
    private RemoteConfig remote;

    @XmlElement(name = "student-file-path")
    private String studentFilePath;

    public LocalConfig getLocal() {
        return local;
    }

    public void setLocal(LocalConfig local) {
        this.local = local;
    }

    public RemoteConfig getRemote() {
        return remote;
    }

    public void setRemote(RemoteConfig remote) {
        this.remote = remote;
    }

    public String getStudentFilePath() {
        return studentFilePath;
    }

    public void setStudentFilePath(String studentFilePath) {
        this.studentFilePath = studentFilePath;
    }
    
    public static void main(String[] args) {
        try {  
        	   
            File file = new File("C:/Users/ftd-7/code/combined-assignments/5-socket-io-serialization/config/config.xml");  
            JAXBContext jaxbContext = JAXBContext.newInstance(Config.class);  
       
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();  
            Config config= (Config) jaxbUnmarshaller.unmarshal(file);  
              
            System.out.println("Local- port: " + config.getLocal().getPort() + "\nRemote- host: " + config.getRemote().getHost() + " port: " + config.getRemote().getPort() + "\nStudent File path: " + config.getStudentFilePath());  
       
          } catch (JAXBException e) {  
            e.printStackTrace();  
          } 
    }
}
