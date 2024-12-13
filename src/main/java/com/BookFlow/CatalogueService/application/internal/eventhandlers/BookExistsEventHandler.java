package com.BookFlow.CatalogueService.application.internal.eventhandlers;

import com.BookFlow.CatalogueService.domain.model.aggregates.Book;
import com.BookFlow.CatalogueService.domain.model.commands.PopulateBookCommand;
import com.BookFlow.CatalogueService.infrastructure.persistence.jpa.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookExistsEventHandler {
    private static final List<PopulateBookCommand> INITIAL_BOOKS = List.of(
            new PopulateBookCommand(1L, "A CALAMITY OF SOULS",1L,
                    "https://storage.googleapis.com/du-prd/books/images/9781538765029.jpg",
                    "Lawyers from different backgrounds represent a Black man charged with killing a wealthy white couple in Virginia in 1968.",
                    "David Baldacci",
                    "https://m.media-amazon.com/images/S/amzn-author-media-prod/b9rqnc2ga7f3t3md67ju67gkh6._SX300_CR0%2C0%2C300%2C300_.jpg",
                    "Grand Central",
                    "1"),
            new PopulateBookCommand(2L, "THE WOME",2L,
                    "https://storage.googleapis.com/du-prd/books/images/9781250178633.jpg",
                    "In 1965, a nursing student follows her brother to serve during the Vietnam War and returns to a divided America.",
                    "Kristin Hannah",
                    "https://m.media-amazon.com/images/S/amzn-author-media-prod/3dkvmtgigvmp2cpkfovivq25f1._SX300_CR0%2C0%2C300%2C300_.jpg",
                    "St. Martin's",
                    "2"),
            new PopulateBookCommand(3L, "A COURT OF THORNS AND ROSES",3L,
                    "https://storage.googleapis.com/du-prd/books/images/9781619634459.jpg",
                    "After killing a wolf in the woods, Feyre is taken from her home and placed inside the world of the Fae.",
                    "Sarah J. Maas",
                    "https://m.media-amazon.com/images/S/amzn-author-media-prod/lrl2v1leaksru6628gnernrop0._SX300_CR0%2C0%2C300%2C300_.jpg",
                    "Bloomsbury",
                    "3"),
            new PopulateBookCommand(4L, "THE MURDER INN",4L,
                    "https://storage.googleapis.com/du-prd/books/images/9781538710944.jpg",
                    "A crime boss moves into the town where a former cop runs an inn.",
                    "James Patterson and Candice Fox",
                    "https://m.media-amazon.com/images/S/amzn-author-media-prod/f83dinp2g2c6q52rf44bs32ich._SX300_CR0%2C0%2C300%2C300_.jpg",
                    "Grand Central",
                    "4"),
            new PopulateBookCommand(5L,"A COURT OF MIST AND FURY",3L,
                    "https://storage.googleapis.com/du-prd/books/images/9781635575583.jpg",
                    "The second book in the Court of Thorns and Roses series. Feyre gains the powers of the High Fae and a greater evil emerges.",
                    "Sarah J. Maas", "https://m.media-amazon.com/images/S/amzn-author-media-prod/lrl2v1leaksru6628gnernrop0._SX300_CR0%2C0%2C300%2C300_.jpg",
                    "Bloomsbury","5")
    );

    @Autowired
    private BookRepository bookRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void populateGenreTable(){
        for (PopulateBookCommand command : INITIAL_BOOKS){
            if (!bookRepository.existsByBookTitle(command.getBookTitle())) {
                Book book = new Book(command.getBookId(),command.getBookTitle(),command.getBookGenreId(),command.getBookImage(),command.getBookDescription(),command.getBookAuthor(),command.getBookAuthorImage(),command.getBookPublisher(),command.getBookRank());
                bookRepository.save(book);
            }
        }
    }
}
