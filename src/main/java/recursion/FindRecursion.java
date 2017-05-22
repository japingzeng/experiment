package recursion;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Created by Administrator on 2017/5/22.
 */
public class FindRecursion {

    private  static int MAX_INDEX = 10;
    public static void main(String[] args) throws Exception{
        ObjectA objectA = new ObjectA();
        ObjectB objectB = new ObjectB();
        List<ObjectB> list = new ArrayList<>();
        objectB.setObjectA(objectA);
        list.add(objectB);
        objectA.setList(list);
        ObjectC objectC = new ObjectC();
        objectC.setList(list);
        System.out.print(findObject(objectC));

    }

    public static String findObject(Object targetObject) throws Exception{
        LinkNode linkNode = new FindRecursion.LinkNode(null, targetObject);
        List<Object> list = new ArrayList<>();
        return findObject(linkNode, list, 0);
    }

    public static String findObject(LinkNode linkNode, List<Object> list, int index) throws Exception{
        List<String> retList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        Object targetObject = null;
        if (null != linkNode) {
            targetObject = linkNode.getCurrentNode();
        }
        if (null != targetObject) {
            if (list.contains(targetObject)) {
                retList.add(getMsg(linkNode, null));
            } else {
                if (index < MAX_INDEX) {
                    Class<?> cls = targetObject.getClass();
                    while (!(Object.class.equals(cls))) {
                        Field[] fields = getFileds(targetObject);
                        for (Field field : fields) {
                            field.setAccessible(true);
                            Object val = field.get(targetObject);
                            if (list.contains(val)) {
                                retList.add(getMsg(linkNode, field));
                            } else {
                                list.add(targetObject);
                                if (isMap(field.getType())) {
                                    for (Object temp : ((Map)val).values()) {
                                        if (isJavaObject(temp)) {
                                            retList.add(findObject(new LinkNode(linkNode, temp), list, index+1));
                                        }
                                    }
                                } else if (isCollection(field.getType())) {
                                    for (Object temp : (List)val) {
                                        if (isJavaObject(temp)) {
                                            retList.add(findObject(new LinkNode(linkNode, temp), list, index+1));
                                        }
                                    }
                                } else if (isJavaType(field.getType())) {
                                    retList.add(findObject(new LinkNode(linkNode, val), list, index+1));
                                }
                                list.remove(targetObject);
                            }

                        }
                        cls = cls.getSuperclass();
                    }

                }
            }
        }
        for (String item : retList) {
            if (null != item && ! "".equals(item.trim())) {
                sb.append(item.trim()).append("\n");
            }
        }

        return sb.toString();
    }

    private static boolean isMap(Class<?> cls) {
        if (Map.class.isAssignableFrom(cls)) {
            return  true;
        }
        return false;
    }

    private static boolean isCollection(Class<?> cls) {
        if (Collection.class.isAssignableFrom(cls)) {
            return true;
        }
        return false;
    }

    private static boolean isJavaObject(Object object) {
        boolean ret = true;
        if (null != object) {
            ret = isJavaType(object.getClass());
        }
        return ret;
    }

    private static boolean isJavaType(Class<?> cls) {
        boolean ret = true;
        if(cls != null){
            if("int".equals(cls.getName())){
                ret = false;
            }else if("long".equals(cls.getName())){
                ret = false;
            }else if("double".equals(cls.getName())){
                ret = false;
            }else if("boolean".equals(cls.getName())){
                ret = false;
            }else if("float".equals(cls.getName())){
                ret = false;
            }else if("java.lang.Long".equals(cls.getName())){
                ret = false;
            }else if("java.lang.Integer".equals(cls.getName())){
                ret = false;
            }else if("java.lang.Double".equals(cls.getName())){
                ret = false;
            }else if("java.lang.Float".equals(cls.getName())){
                ret = false;
            }else if("java.lang.String".equals(cls.getName())){
                ret = false;
            }else if("java.lang.Boolean".equals(cls.getName())){
                ret = false;
            }else if(Number.class.getName().equals(cls.getName())){
                ret = false;
            }else if(Date.class.getName().equals(cls.getName())){
                ret = false;
            }else if(Enum.class.isAssignableFrom(cls)){
                ret = false;
            }
        }
        return ret;
    }




    private static Field[] getFileds(Object object) {
        Field[] fields = null;
        if (null != object) {
            fields = object.getClass().getDeclaredFields();
        }
        return fields;
    }



    private static String getMsg(LinkNode linkNode, Field field) {
        if (null != field) {
            return getMsg(linkNode) + field.getName();
        }
        return  getMsg(linkNode);
    }

    private static String getMsg(LinkNode linkNode) {
        StringBuffer sb = new StringBuffer();
        if (null != linkNode && null != linkNode.getPreviousNode()) {
            sb.append(getMsg(linkNode.getPreviousNode()));
        }
        if (null != linkNode && null != linkNode.getCurrentNode()) {
            sb.append(linkNode.getCurrentNode().getClass().getName()).append("-->");
        }
        return sb.toString();
    }

    private static String getClassName(Class<?> cls) {
        String className = null;
        if (null != cls) {
            className = cls.getName();
        }
        return className;
    }

    private static class LinkNode {

        private LinkNode previousNode;
        private Object currentNode;

        public LinkNode(LinkNode previousNode, Object currentNode) {
            this.previousNode = previousNode;
            this.currentNode = currentNode;
        }

        public LinkNode getPreviousNode() {
            return previousNode;
        }

        public void setPreviousNode(LinkNode previousNode) {
            this.previousNode = previousNode;
        }

        public Object getCurrentNode() {
            return currentNode;
        }

        public void setCurrentNode(Object currentNode) {
            this.currentNode = currentNode;
        }
    }
}
