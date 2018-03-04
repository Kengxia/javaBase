package my.externalizable_t;

import java.io.*;

/**
 * Externalizable 自定义对那些属性序列化
 * 使用Externalizable接口进行序列化，必须要重写writeExternal（ObjectOutput output）和readExternal（ObjectInput input）方法
 * 使用该接口的序列化方法需要我们来实现，因此可以对static和transient数据进行序列化。
 */
public class externalizableT {

    public static void main(String[] args) {
        Person person = new Person(26, "Juventus");
        person.setWorkDay(7);
        try {
            FileOutputStream fs = new FileOutputStream("foo.ser");
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(person);
            os.close();
            Person.name = "Alex";
            FileInputStream in = new FileInputStream("foo.ser");
            ObjectInputStream s = new ObjectInputStream(in);
            Person p = (Person) s.readObject();
            System.out.println("name==" + Person.name + " age==" + p.getAge()
                    + " workDay==" + p.getWorkDay() + " fClub==" + p.getfClub());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}


class Person implements  Externalizable{

    private static final long serialVersionUID = -842029427676826563L;
    public static String name;
    private int age;
    private transient int workDay = 5;
    private String fClub;
    public Person() {
        System.out.println("none-arg constructor");
    }
    public Person(int age, String fClub) {
        this.age = age;
        this.fClub = fClub;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Person.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWorkDay() {
        return workDay;
    }

    public void setWorkDay(int workDay) {
        this.workDay = workDay;
    }

    public String getfClub() {
        return fClub;
    }

    public void setfClub(String fClub) {
        this.fClub = fClub;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();//执行默认的序列化机制
        out.writeInt(workDay);
        System.out.println("正在进行序列持久化");
    }
    private void readObject(ObjectInputStream in) throws IOException,
            ClassNotFoundException {
        in.defaultReadObject();
        workDay = in.readInt();
        System.out.println("读取持久化对象");
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {


    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {

    }
}
