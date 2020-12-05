import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ComparaUtils {



    public static Map<String,String> compareTwoObject(Object object1,Object object2) throws Exception{

        Map<String,String> result = new HashMap<>();

        final Class<?> object1Class = object1.getClass();
        final Class<?> object2Class = object2.getClass();


          if(object1Class.equals(object2Class)){

              Field[] fields = object1Class.getDeclaredFields();
              for(Field field:fields){
                  field.setAccessible(true);
                  final Object o1Value = field.get(object1);
                  final Object o2Value = field.get(object2);
                  if((o1Value ==null && o2Value!=null)||(o1Value!=null&& !o1Value.equals(o2Value))){
                      result.put(field.getName(),"from "+o1Value +"to "+o2Value);
                  }
              } 
          }

          return result;

    }


}
