package kz.iitu.demo.entity;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(notes = "The database generated Book ID")
    private Long id;
    @ApiModelProperty(notes = "The Book Title")
    private String title;
    @ApiModelProperty(notes = "The Book Description")
    private String description;

    @Enumerated(EnumType.STRING)
    @ApiModelProperty(notes = "The Book Status(RETURNED, REQUESTED, ISSUED, AVAILABLE)")
    private BookStatus status;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @ApiModelProperty(notes = "The Id of User for which the book is assigned")
    private User user;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "books_authors",
            joinColumns = {@JoinColumn(name = "book_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "author_id", referencedColumnName = "id")}
    )
    @ApiModelProperty(notes = "Authors of Book")
    private List<Author> authors;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "publisher_id")
    @ApiModelProperty(notes = "Publisher of Book")
    private Publisher publisher;


}
