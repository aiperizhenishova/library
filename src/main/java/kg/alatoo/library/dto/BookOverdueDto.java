package kg.alatoo.library.dto;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors( chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookOverdueDto {

    boolean isOverdue;

    String overduePeriod;
}
