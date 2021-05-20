import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.awt.event.ActionEvent;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Locale;

import static a.CarType.HATCHBACK;
import static java.lang.Enum.valueOf;

public class MainController{

    private String className;
    Object classInstance = null;

    private ObservableList<String> observableLeft = FXCollections.observableArrayList();
    private ObservableList<String> observableRight = FXCollections.observableArrayList();

    @FXML
    TextField ClassName;

    @FXML
    private TextField FieldName;

    @FXML
    private TextField FieldValue;

    @FXML
    private TextField MethodName;

    @FXML
    private ListView<String> listField;

    @FXML
    private ListView<String> listMethod;


    public void showAll(){
        observableLeft.clear();
        observableRight.clear();
        ListingField();
        ListingMethods();
    }

    @FXML
    public void MakeObject(){
        className = ClassName.getText();
        try {
            Class cls = Class.forName(className);

            classInstance = (Object) cls.newInstance();
            System.out.println("instance: " + classInstance.getClass());
            ListingField();
            ListingMethods();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void ListingField(){
        try {
            Field fields[] = classInstance.getClass().getDeclaredFields();

            for (Field field : fields) {
                field.setAccessible(true);
                observableLeft.add(field.getGenericType().getTypeName() + " " + field.getName() + " = " + field.get(classInstance));
                listField.setItems(observableLeft);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void ChangingValue(){
        String fieldName = FieldName.getText();
        String fieldValue = FieldValue.getText();
        String str = FieldName.getText();
        String methodName = str.substring(0,1).toUpperCase()+str.substring(1);
        String string = "java.lang.String";
        String Int = "Int";
        String Enum = "java.lang.Enum";
        String Date = "java.util.Date";
        try{
            Field field = classInstance.getClass().getDeclaredField(fieldName);
            if(field.getGenericType().getTypeName() == string){
                Method m = classInstance.getClass().getMethod("set"+methodName, String.class);
                m.invoke(classInstance, fieldValue);
            }else if(field.getGenericType().getTypeName() == Int){
                Method m = classInstance.getClass().getMethod("set"+methodName, Integer.class);
                m.invoke(classInstance, Integer.parseInt(fieldValue));
            }else if(field.getGenericType().getTypeName() == Enum){
                Method m = classInstance.getClass().getMethod("set"+methodName, Enum.class);
                m.invoke(classInstance, Enum.valueOf(fieldValue));
            }else if(field.getGenericType().getTypeName() == Date){
                Method m = classInstance.getClass().getMethod("set"+methodName, java.util.Date.class);
                m.invoke(classInstance, Date.valueOf(fieldValue));
            }
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @FXML
    void ListingMethods(){
        try {
            Method methods[] = classInstance.getClass().getDeclaredMethods();
            for (Method method : methods) {
                method.setAccessible(true);
                observableRight.add(method.getAnnotatedReturnType().toString() + " " + method.getName());
                listMethod.setItems(observableRight);
                //System.out.println(method.getAnnotatedReturnType().toString() + " " + method.getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void LaunchMethod(){
        String methodName = MethodName.getText();
        try {
            Method m = classInstance.getClass().getDeclaredMethod(methodName);
            m.setAccessible(true);
            System.out.println(m.invoke(classInstance, null));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
