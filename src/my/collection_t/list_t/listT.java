package my.collection_t.list_t;

/**
 *
 *
 * 1、ArrayList
 * 非线程安全
 * 基于对象数组
 * get(int index)不需要遍历数组，速度快；
 * iterator()方法中调用了get(int index)，所以速度也快
 * set(int index, E e)不需要遍历数组，速度快
 * add方法需要考虑扩容与数组复制问题，速度慢
 * remove(Object o)需要遍历数组，并复制数组元素，速度慢
 * remove(int index)不需要遍历数组，需要复制数组元素，但不常用
 * contain(E)需要遍历数组
 *
 * 2、LinkedList
 * 非线程安全
 * 基于环形双向链表
 * get(int index)需要遍历链表，速度慢；
 * iterator()方法中调用了get(int index)，所以速度也慢
 * set(int index, E e)方法中调用了get(int index)，所以速度也慢
 * add方法不需要考虑扩容与数组复制问题，只需创建新对象，再将新对象的前后节点的指针指向重新分配一下就好，速度快
 * remove(Object o)需要遍历链表，但不需要复制元素，只需将所要删除的对象的前后节点的指针指向重新分配一下以及将所要删除的对象的三个属性置空即可，速度快
 * remove(int index)需要遍历链表，但不需要复制元素，只需将所要删除的对象的前后节点的指针指向重新分配一下以及将所要删除的对象的三个属性置空即可，但不常用
 * contain(E)需要遍历链表
 *
 */
public class listT {
}
