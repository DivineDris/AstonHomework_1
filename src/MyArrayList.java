import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class MyArrayList<E> {

    private static final int DEFAULT_CAPACITY = 10; //Емкость по умолчанию
    private int size; //количество элементов записанных в коллекцию
    private Object[] elementData;// Массив куда записываются элементы. По сути само тело коллекции.

    //Конструктор без параметров. Создает коллекцию с размером по умолчанию
    public MyArrayList(){
        this.elementData = (E[])new Object[DEFAULT_CAPACITY];
    }

    //Конструктор с параметром. Создает коллекцию с заданным размером.
    public MyArrayList(int capacity){
        if(capacity > 0)
            this.elementData = (E[])new Object[capacity];
        else if(capacity == 0)
            this.elementData = (E[])new Object[DEFAULT_CAPACITY];
        else 
            throw new IllegalArgumentException("Invalid capacity: " + capacity);
    }

    //Метод для проверки вместительности коллекции. Если в коллекции хватает места для того чтобы добавить новый элемент то возвращает true. 
    //Если места не достаточно то расширяет коллекцию и возвращает false и расширяет коллекцию.
    private boolean ensureCapacity(int minCapacity){
        if(minCapacity >= elementData.length){
            grow();
            return false;
        }
        return true;

    }
    //Метод для динамического расширения коллекции. Старые данные записываются в локальный массив oldElementData. 
    //В elementData создается новый массив с емкостью которая расчитывается по формуле oldCapacity * 3)/2+1.
    //Данные из oldElementData копируются в новый массив elementData.
    private void grow(){
        int oldCapacity = elementData.length;
        if(oldCapacity > 0){
            Object[] oldElementData = Arrays.copyOf(elementData, oldCapacity);
            elementData = (E[])new Object[(oldCapacity * 3)/2+1];
            System.arraycopy(oldElementData, 0, elementData, 0, size);
        }
    }

    //Метод для добавления нового элемента в коллекцию. Проверяется емкость массива elementData через ensureCapacity
    //Затем добавляется новый элемент и увеличивается размер коллекции.
    public boolean add(E element){
        ensureCapacity(size);
        elementData[size] = element;
        size++;
        return true;
    }
    
    //Добавление элемента по индексу. Данные из массива копируются после заданного индекса и переносятся на 1 индекс дальше. 
    //Элемент добавляктся в ячейку по заданному индексу
    public boolean add(int index, E element){
        ensureCapacity(size);
        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = element;
        size++;
        return true;
    }
    //Добавление коллекции в коллекцию. 
    public boolean addAll(Collection<? extends E> collection){
        while (!ensureCapacity(size + collection.size())) {} //Цикл выполняется пока в массиве elementData не достаточно места для вставки коллекции. 
        Object[] collectionArray = collection.toArray();
        System.arraycopy(collectionArray, 0, elementData, size, collectionArray.length); // Коллекция копируется и вставляется в конец массива elementData
        size += collection.size(); // размер увеличивается на количество вставленных элементов.
        return true;
    }
    //Добавление коллекции в коллекцию на заданный индекс
    public boolean addAll(int index, Collection<? extends E> collection){
        while (!ensureCapacity(size + collection.size())) {} //Цикл выполняется пока в массиве elementData не достаточно места для вставки коллекции. 
        Object[] collectionArray = collection.toArray();
        System.arraycopy(elementData, index, elementData, index + collectionArray.length, size - index); //Смещение элементов массива elementData на количество элементов в вставляемое коллекции после заданного индекса.
        System.arraycopy(collectionArray, 0, elementData, index, collectionArray.length); //Коллекция копируется и вставляется в массив elementData начиная с заданного индекса
        size += collection.size();
        return true;
    }


    //Метод для получения элемента по индексу
    public E get(int index){
        return (E)elementData[index];
    }

    //Удаление элемента из коллекции. 
    public boolean remove(int index){
        int movedElements = size - index - 1; //Определяется какое количество элементов надо скопировать
        System.arraycopy(elementData, index + 1, elementData, index, movedElements); //Элементы копируются
        elementData[--size] = null; //Размер массива уменьшается, последний элемент удаляется
        return true;
    }

    public int getSize(){
        return size;
    }

    @Override
    public String toString(){
        return Arrays.toString(elementData);
    }

    public static void main(String[] args) throws Exception 
    {
        MyArrayList<Integer> testList = new MyArrayList<>();
        
        ArrayList<Integer> testList2 = new ArrayList<>();
        for(int i = 0; i < 15; i++){

            //Заполнение коллекций
            testList.add(i);
            testList2.add(2*i);
        }
        System.out.println(testList + " size: " + testList.getSize());
        testList.add(8, 100);
        System.out.println("Try to add 100 at index 8 " + testList + " size: " + testList.getSize());
        testList.addAll(testList2);
        System.out.println("Try to add testList2 to collection " + testList + " size: " + testList.getSize());
        testList.addAll(5,testList2);
        System.out.println("Try to add testList2 to collection at index 5" + testList + " size: " + testList.getSize());
        testList.remove(8);
        System.out.println("Try to remove item  at index 8" + testList + " size: " + testList.getSize());
        testList.remove(0);
        System.out.println("Try to remove item  at index 0" + testList + " size: " + testList.getSize());
    
    }

}
