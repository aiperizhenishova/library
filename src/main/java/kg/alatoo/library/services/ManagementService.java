package kg.alatoo.library.services;


import kg.alatoo.library.dto.BookCheckOut;
import kg.alatoo.library.dto.BookOverdueDto;
import kg.alatoo.library.dto.BookReturnDto;
import kg.alatoo.library.entities.BookEntity;
import kg.alatoo.library.entities.ReaderEntity;
import kg.alatoo.library.repositories.BookRepository;
import kg.alatoo.library.repositories.ReaderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class ManagementService {

    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;

    public boolean checkOutUser (BookCheckOut bookCheckOut){
        BookEntity book = bookRepository.findById(bookCheckOut.getBookId()).orElseThrow();
        ReaderEntity reader = readerRepository.findById(bookCheckOut.getReaderId()).orElseThrow();
        if(book.getReader() != null){
            throw new RuntimeException();
        }

        book.setReader(reader);
        book.setReturnDate(LocalDateTime.now().plusDays(bookCheckOut.getPeriodDays()));
        bookRepository.save(book);
        return true;
    }

    public BookOverdueDto returnBook(BookReturnDto bookReturnDto){
        BookOverdueDto bookOverdueDto = new BookOverdueDto();
        BookEntity book = bookRepository.findById(bookReturnDto.getBookId()).orElseThrow();

        Duration overdue = Duration.between(book.getReturnDate(), LocalDateTime.now());

        String overDuePeriod = "Days: " + overdue.toDays() + ", Hours " + overdue.toHoursPart() + ", Minutes: " + overdue.toMinutesPart();

        bookOverdueDto.setOverduePeriod(overDuePeriod);
        bookOverdueDto.setOverdue(LocalDateTime.now().isAfter(book.getReturnDate()));

        book.setReturnDate(null);
        book.setReader(null);

        bookRepository.save(book);
        return bookOverdueDto;
    }


}
