package a;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static a.CarType.*;

public class App {

    public static void main(String[] args) {
        String className = "Car";
        Object classInstance = null;
        Object classInstance2 = null;

        //czytanie className
        className = App.class.getPackage().getName() + "." + className;

        //tworzenie instancji 'className'
        //1
        try {
            Class cls = Class.forName(className);
            Class partypes[] = new Class[5];
            partypes[0] = String.class;
            partypes[1] = String.class;
            partypes[2] = Enum.class;
            partypes[3] = Integer.TYPE;
            partypes[4] = Integer.TYPE;

            Constructor classConstructor = cls.getConstructor(partypes);
            classConstructor.setAccessible(true);

            Object arglist[] = new Object[5];
            arglist[0] = "Honda";
            arglist[1] = "Civic";
            arglist[2] = HATCHBACK;
            arglist[3] = 988;
            arglist[4] = 129;
            classInstance = classConstructor.newInstance(arglist);
            System.out.println("instance1: " + classInstance.getClass());

            //2
            //classInstance2 = (Object) cls.newInstance();
            //System.out.println("instance2: " + classInstance2.getClass());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //listowanie pól
        try {
            Field fields[] = classInstance.getClass().getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);
                System.out.println(field.getGenericType().getTypeName() + " " + field.getName() + " = " + field.get(classInstance) + "\n");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //listowanie metod
        try {
            Method methods[] = classInstance.getClass().getDeclaredMethods();
            for (Method method : methods) {
                method.setAccessible(true);
                System.out.println(method.getAnnotatedReturnType().toString() + " " + method.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //wywołanie metody po nazwie
        try {
            Method m = classInstance.getClass().getMethod("drive");
            System.out.println(m.invoke(classInstance, null));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
