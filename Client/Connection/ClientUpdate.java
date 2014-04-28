/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import Message.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocket;

/**
 *
 * @author Marc
 */
public class ClientUpdate {

    NodeList n = new NodeList();

    ClientUpdate() {
        ClientSSL clissl = new ClientSSL();
        try (SSLSocket s = clissl.main(12347); OutputStream os = s.getOutputStream(); ObjectOutputStream oos = new ObjectOutputStream(os)) {
            InputStream is = s.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            Stack st = (Stack) ois.readObject();
            n.updateStack(st);
            oos.close();
            os.close();
            is.close();
            ois.close();
            s.close();
        }
        catch (IOException e) {
            System.out.println(e);
        }
        catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

}
