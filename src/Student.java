import java.util.Comparator;
import java.util.List;

public class Student {
    private String name;
    private List<Book> books;
    
    public Student(String name, List<Book> books){
        this.name = name;
        this.books = books;
    }

    public List<Book> getBooks(){
        return books;
    }

    @Override
    public String toString(){
        return name;
    }

    public static void main(String[] args){

        List<Student> students = List.of(
                new Student("Student_1",List.of(
                    new Book("Book_1", "Author_1", 2000, 342),
                    new Book("Book_2", "Author_2", 2020, 73),
                    new Book("Book_3", "Author_3", 1982, 1000),
                    new Book("Book_4", "Author_4", 1703, 394),
                    new Book("Book_5", "Author_5", 1999, 894))),

                new Student("Student_2",List.of(
                    new Book("Book_6", "Author_6", 2000, 120),
                    new Book("Book_2", "Author_2", 2020, 73),
                    new Book("Book_7", "Author_7", 2001, 1500),
                    new Book("Book_9", "Author_9", 1895, 793),
                    new Book("Book_5", "Author_5", 1999, 894))),

                new Student("Student_3",List.of(
                    new Book("Book_10", "Author_10", 1922, 1900),
                    new Book("Book_5", "Author_5", 2003, 894),
                    new Book("Book_2", "Author_2", 2020, 73),
                    new Book("Book_9", "Author_9", 1895, 793),
                    new Book("Book_6", "Author_6", 2000, 120))),

                new Student("Student_4",List.of(
                    new Book("Book_4", "Author_4", 1703, 394),
                    new Book("Book_2", "Author_2", 2020, 73),
                    new Book("Book_10", "Author_10", 1922, 1900),
                    new Book("Book_6", "Author_6", 2000, 120),
                    new Book("Book_5", "Author_5", 2003, 894))),

                new Student("Student_5",List.of(
                    new Book("Book_10", "Author_10", 1922, 1900),
                    new Book("Book_1", "Author_1", 2000, 342),
                    new Book("Book_7", "Author_7", 2001, 1500),
                    new Book("Book_3", "Author_3", 1982, 1000),
                    new Book("Book_5", "Author_5", 2003, 894))),

                new Student("Student_6",List.of(
                    new Book("Book_6", "Author_6", 2000, 120),
                    new Book("Book_2", "Author_2", 2020, 73),
                    new Book("Book_3", "Author_3", 1982, 1000),
                    new Book("Book_4", "Author_4", 1703, 394),
                    new Book("Book_5", "Author_5", 2003, 894)))
        );

        students.stream()
            .peek(System.out::println) //Выбираем всех студентов
            .flatMap(student -> student.getBooks().stream()) // Раскрываем коллекцию книг для каждого студента
            .sorted(Comparator.comparingInt(Book::getAmountOfPages)) // Сортируем книги по количеству страниц
            .distinct() //Оставляем только уникальные книги
            .filter(book -> book.getYear() > 2000) //Фильтруем книги и оставляем только те которые были выпущены после 2000 года
            .limit(3)// Оставляем 3 результата
            .map(Book::getYear) //Отображаем каждый элемент в виде года выпуска
            .findFirst()// Получаем Optional книги
            .ifPresentOrElse(year  -> System.out.println(year),//Если книга найдена выводим  год
                            () -> System.out.println("Book is not found") //Если книга не найдена то выводится Book is not found
            );



    }
}
