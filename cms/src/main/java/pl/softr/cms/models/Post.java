package pl.softr.cms.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
    @Column(length = 512)
    private String title;
    @Lob
    private String content;
}
