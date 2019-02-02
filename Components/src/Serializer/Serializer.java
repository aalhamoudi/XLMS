package Serializer;

import java.io.*;

public class Serializer {

    private Serializer() {}



    public static <T>  void Serialize(T object, String filePath) throws FileNotFoundException, IOException {
        File path = new File(filePath);

            FileOutputStream file = new FileOutputStream(path);
            ObjectOutputStream stream = new ObjectOutputStream(file);

            stream.writeObject(object);

            stream.close();
            file.close();


    }

    public static <T> T Deserialize(String filePath) throws FileNotFoundException, IOException, ClassNotFoundException {
        File path = new File(filePath);
        T object = null;

        if (path.exists()) {


            FileInputStream file = new FileInputStream(path);
            ObjectInputStream stream = new ObjectInputStream(file);


            object = (T) stream.readObject();

            file.close();
            stream.close();
        }

        return object;
    }
}
