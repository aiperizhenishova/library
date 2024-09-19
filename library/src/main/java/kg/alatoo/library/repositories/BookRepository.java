package kg.alatoo.library.repositories;

import kg.alatoo.library.entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
}
