import java.util.HashMap;

public class MyHashSet<E> {

    private static final Object VALUE_OBJECT = new Object(); //Затычка для значения в HashMap map
    
    protected HashMap<E,Object> map; //Тело коллекции. Запись данных будет происходить в ключ что обеспечивает уникальность данных.

    public MyHashSet()
    {
        this.map = new HashMap<>();
    }

    //Метод для добавления элемента в коллекцию.
    public boolean add(E input){
        return map.put(input, VALUE_OBJECT) == null; //Добавляет элемент в коллекцию. Возвращает true если такого элемента еще не было, false, когда элемент уже есть в коллекции.
    }

    //Метод для удаления элемента из коллекции.
    public boolean remove (E input){
        return map.remove(input) == VALUE_OBJECT;//Удаляет элемент из коллекции. Возвращает true если элемент был найден и удален.
    }

    @Override
    public String toString(){
        return map.keySet().toString();
    }

    public static void main(String[] args) throws Exception //Main для проверки вывода
    {
        MyHashSet<Integer> testMyHashSet = new MyHashSet<Integer>();

        for(int i = 0; i < 10; i++)
        {
            testMyHashSet.add(i);
        }
        //Пишу вывод на английском потому что не выводит русские символы
        System.out.println("Initial hashset "+testMyHashSet);
        testMyHashSet.add(9);
        System.out.println("Try to add 9 "+testMyHashSet);
        testMyHashSet.add(10);
        System.out.println("Add 10 "+testMyHashSet);
        System.out.println("Try to add 10 boolean "+testMyHashSet.add(10));

        testMyHashSet.remove(9);
        System.out.println("Try to remove 9 "+testMyHashSet);
        System.out.println("Try to remove 9 boolean "+testMyHashSet.remove(9));

    
    }


}