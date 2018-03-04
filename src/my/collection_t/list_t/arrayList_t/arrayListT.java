package my.collection_t.list_t.arrayList_t;

import java.util.ArrayList;

/**
 *
 * java Collection 综述
 *  1、Collection是一个接口，主要有两个分支 list和set
 *  list 有序且允许重复元素 set 不允许重复
 *
 *
 * ArrayList 不是线程安全的，底层是基于数组实现的
 *
 * ArrayList 是一个数组队列，相当于 动态数组。与Java中的数组相比，它的容量能动态增长。它继承于AbstractList，实现了List, RandomAccess, Cloneable, java.io.Serializable这些接口。
 * ArrayList 继承了AbstractList，实现了List。它是一个数组队列，提供了相关的添加、删除、修改、遍历等功能。
 * ArrayList 实现了RandmoAccess接口，即提供了随机访问功能。RandmoAccess是java中用来被List实现，
 *      为List提供快速访问功能的。在ArrayList中，我们即可以通过元素的序号快速获取元素对象；
 *      这就是快速随机访问。稍后，我们会比较List的“快速随机访问”和“通过Iterator迭代器访问”的效率。
 * ArrayList 实现了Cloneable接口，即覆盖了函数clone()，能被克隆。
 * ArrayList 实现java.io.Serializable接口，这意味着ArrayList支持序列化，能通过序列化去传输。
 *
 * ArrayList 实际上是通过一个数组去保存数据的。
 * 当我们构造ArrayList时；若使用默认构造函数，
 * 则ArrayList的默认容量大小是10。
 *
 *当ArrayList容量不足以容纳全部元素时，
 *  ArrayList会重新设置容量：新的容量=“(原始容量x3)/2 + 1”。
 *
 * ArrayList 中主要的api
 *
 *1、indexOf
 *2、lastIndexOf
 *3、public <T> T[] toArray(T[] a)
 *
 *
 */
public class arrayListT {

    public static void main(String[] args) {
        ArrayList <String> list  = new ArrayList<String>(14);
        for(int i=0;i<10;i++){
            list.add("sss");
        }
        /**
         * ArrayList所说没有用的值并不是null，而是ArrayList每次增长会预申请多一点空间，1.5倍+1，而不是两倍
         *
         * 这样就会出现当size() = 1000的时候，ArrayList已经申请了1200空间的情况
         * trimToSize 的作用只是去掉预留元素位置，就是删除多余的200，改为只申请1000,内存紧张的时候会用到.
         */
        list.trimToSize();
        /**
         * list转数组
         *
         * 1、如果传入的数组长度大于集合的长度(size)，将集合里的元素直接进行拷贝，多余的元素赋值为null。
         * 2、如果传入的数组长度不大于集合的长度(size)，则集合里的数据(elementData)，
         * 集合的实际长度(size)，及集合的泛型(a.getClass())传入到Arrays的copyOf方法，
         * 通过方法创建一个长度与集合长度一样的新数组，并将集合里的数据拷贝到新数组中，
         * 并转型成集合的泛型的类型，将数组返回
         *
         */
        int size = list.size();
        String[] arr = (String[])list.toArray(new String[size]);
        for(String str:arr){
            System.out.println(str);
        }
        System.out.println("......................");
        String[] arr1 = (String[])list.toArray(new String[4]);
        for(String str:arr1){
            System.out.println(str);
        }
    }


}
