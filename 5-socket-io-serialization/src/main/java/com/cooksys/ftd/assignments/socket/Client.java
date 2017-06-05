package com.cooksys.ftd.assignments.socket;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.cooksys.ftd.assignments.socket.model.Student;

public class Client {

    /**
     * The client should load a {@link com.cooksys.ftd.assignments.socket.model.Config} object from the
     * <project-root>/config/config.xml path, using the "port" and "host" properties of the embedded
     * {@link com.cooksys.ftd.assignments.socket.model.RemoteConfig} object to create a socket that connects to
     * a {@link Server} listening on the given host and port.
     *
     * The client should expect the server to send a {@link com.cooksys.ftd.assignments.socket.model.Student} object
     * over the socket as xml, and should unmarshal that object before printing its details to the console.
     * @throws JAXBException 
     */
    public static void main(String[] args) throws JAXBException {
//    	Socket s;
		try {
//			s = new Socket("10.1.1.225", 1234);
//		OutputStream os = s.getOutputStream();
//		os.write(9);
//		int value = s.getInputStream().read();
		JAXBContext context = JAXBContext.newInstance(Student.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		Student unmarshalledStudent = (Student) unmarshaller.unmarshal(new FileInputStream("/config/config.xml"));
		System.out.println(unmarshalledStudent);
		
//		System.out.println(value);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
