package com.liujun.itself.xml;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.liujun.itself.xml.Server.User;

public class ParseMsg {

    /**
     * 转换对象
    * @字段说明 jaxContext
    */
    private final JAXBContext jaxContext;

    public ParseMsg() throws JAXBException {
        jaxContext = JAXBContext.newInstance(Userinfo.class);
    }

    public static void main(String[] args) throws JAXBException {
        ParseMsg parse = new ParseMsg();

        Userinfo user = new Userinfo();

        user.setName("name");
        user.setPasswd("passwd");

        String path = ParseMsg.class.getClassLoader().getResource("com/liujun/itself/xml/").getPath();
        path = path.substring(1) + "user.xml";
        // parse.parse(user, path, "userinfo");
        String serverPath = ParseMsg.class.getClassLoader().getResource("com/liujun/itself/xml/").getPath();
        serverPath = serverPath + "server.xml";

        Server server = new Server();

        Server.System systems = new Server.System();

        Property pars = new Property();

        pars.setName("name");
        pars.setValue("小恩泽");

        List<Property> property = new ArrayList<>();
        property.add(pars);
        systems.setProperty(property);
        server.setSystem(systems);

        List<User> userList = new ArrayList<>();
        Server.User serUser = new Server.User();
        serUser.setName("userName");

        Property pars2 = new Property();

        pars2.setName("name2");
        pars2.setValue("小恩泽2");

        List<Property> propertys = new ArrayList<>();
        propertys.add(pars2);

        serUser.setProperty(propertys);

        server.setUser(userList);

        // 进行转换
        parse.parse(server, serverPath, "server");

    }

    public void parse(Userinfo user, String inputPath, String name) {
        try {
            Marshaller marshaller = this.jaxContext.createMarshaller();
            // 设置格式化输出
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            //
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            // 指定dtd文件
            marshaller.setProperty("com.sun.xml.internal.bind.xmlHeaders",
                    String.format("<!DOCTYPE mycat:%1$s SYSTEM \"%1$s.dtd\">", name));

            Path path = Paths.get(inputPath);

            OutputStream out = Files.newOutputStream(path, StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);

            marshaller.marshal(user, out);

        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void parse(Server user, String inputPath, String name) {
        try {
            Marshaller marshaller = this.jaxContext.createMarshaller();
            // 设置格式化输出
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            //
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            
            
            //此处文件冲突
            marshaller.setProperty("com.sun.xml.internal.bind.xmlHeaders",
                    "<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
            
            // 指定dtd文件
            marshaller.setProperty("com.sun.xml.internal.bind.xmlHeaders",
                    "<?xml version=\"1.0\" encoding=\"UTF-8\"?>");

           
            // marshaller.setProperty("com.sun.xml.internal.bind.xmlHeaders",
            // String.format("<!DOCTYPE mycat:%1$s SYSTEM \"%1$s.dtd\">",
            // name));
            // marshaller.setProperty("com.sun.xml.internal.bind.xmlHeaders",
            // String.format("<!DOCTYPE mycat:%1$s SYSTEM \"%1$s.dtd\">",
            // name));

            Path path = Paths.get(inputPath);

            OutputStream out = Files.newOutputStream(path, StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);

            marshaller.marshal(user, out);

        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
