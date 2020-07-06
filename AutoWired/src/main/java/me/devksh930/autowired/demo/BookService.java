package me.devksh930.autowired.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

//    @Autowired(required = false) //필드를 통한주입
//    @Autowired @Qualifier("myBookRepository") //qualifier를 통해 원하는 bean을 주입한다 .
//    BookRepository bookRepository;

//    여러개의 bean 받기
//    @Autowired
//    List<BookRepository> bookRepositories;


    @Autowired
    BookRepository myBookRepository;

    public void printRepository(){
//        this.bookRepositories.forEach(System.out::println);
        System.out.println(myBookRepository.getClass());
    }

//    @Autowired //생성자 주입방식
//    public BookService(BookRepository bookRepository) {
//        this.bookRepository = bookRepository;
//    }

//    @Autowired//setter를 통한 주입
//    public void setBookRepository(BookRepository bookRepository) {
//        this.bookRepository = bookRepository;
//    }
}
