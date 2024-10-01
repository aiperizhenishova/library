package kg.alatoo.library.repositories;

import kg.alatoo.library.entities.BookEntity;
import kg.alatoo.library.entities.ReaderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReaderRepository extends JpaRepository<ReaderEntity, Long> {

    List<ReaderEntity> findAllByFullNameContainingIgnoreCase(String name);

}