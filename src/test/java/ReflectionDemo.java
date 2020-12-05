import com.hpf.study.mybatisdemo2.model.User;
import org.apache.ibatis.reflection.TypeParameterResolver;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.invoker.GetFieldInvoker;
import org.apache.ibatis.reflection.property.PropertyNamer;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Arrays;

public class ReflectionDemo {


    @Test
    public void testReflectionFactory(){

        DefaultObjectFactory defaultObjectFactory = new DefaultObjectFactory();
        final User user = defaultObjectFactory.create(User.class);
            user.setName("li");

        System.out.println(user.getName());

    }


    @Test
    public void testReflectionInvoker() throws Exception{

        final DefaultObjectFactory defaultObjectFactory = new DefaultObjectFactory();
        final User user = defaultObjectFactory.create(User.class);
        user.setName("han");
        user.setAge(10);

        final Class<? extends User> userClass = user.getClass();
        final Field[] fields = userClass.getDeclaredFields();

        for(Field field:fields){
            final GetFieldInvoker getFieldInvoker = new GetFieldInvoker(field);
            System.out.println(field.getName());
            System.out.println(getFieldInvoker.getType());
            System.out.println(getFieldInvoker.invoke(user,null));
        }


    }

    @Test
    public void testProperty(){
        final DefaultObjectFactory defaultObjectFactory = new DefaultObjectFactory();
        final User user = defaultObjectFactory.create(User.class);
        final Class<? extends User> userClass = user.getClass();
        final Method[] methods = userClass.getMethods();
        Arrays.stream(methods)
                .filter(method->(method.getName().startsWith("get")||method.getName().startsWith("set")||method.getName().startsWith("is")))
                .map(method -> PropertyNamer.methodToProperty(method.getName()))
                .distinct()
                .forEach(System.out::println);

    }


    @Test
    public void testResolveParame() throws Exception{

       Type type = TypeParameterResolver.resolveReturnType(People.class.getMethod("getInfo"), People.class);
        System.out.println(type.getTypeName());

        final Type type1 = TypeParameterResolver.resolveReturnType(People.class.getMethod("getInfo"), Student.class);
        System.out.println(type1.getTypeName());



        People p = new People();
        p.testArr(1,1);

    }


}
