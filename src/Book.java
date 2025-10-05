import java.util.Objects;

public class Book
    {
        private String name;
        private String author;
        private int year;
        private int amountOfPages;

        public Book(String name, String author, int year, int amountOfPages){
            this.name = name;
            this.author = author;
            this.year = year;
            this.amountOfPages = amountOfPages;
        }

        public Integer getAmountOfPages(){
            return amountOfPages;
        }

        public Integer getYear(){
            return year;
        }

        public String getName(){
            return name;
        }

        public String getAuthor(){
            return author;
        }


        //Переопределяю equals и hashCode для корректной работы distinct
        @Override
        public boolean equals(Object o){
            if(this == o)
                return true;
            if(!(o instanceof Book))
                return false;
            Book bookObject = (Book)o;
            return name == bookObject.name &&
                    author == bookObject.author &&
                    year == bookObject.year &&
                    amountOfPages == bookObject.amountOfPages;
        }

        @Override
        public int hashCode(){
            return Objects.hash(name, author, year, amountOfPages);
        }

        @Override
        public String toString(){
            return "Name: " + name + " Author: " + author + " Year: " + year + " Amount of pages: " + amountOfPages;
        }
    }
