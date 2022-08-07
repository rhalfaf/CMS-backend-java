package pl.softr.cms.models.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import pl.softr.cms.models.category.Category;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Post {
    @Id
    @GeneratedValue
    private Long id;
    private Instant createDate;
    private Instant publishingDate;
    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @Column(length = 512)
    private String title;
    @Lob
    private String content;
}
