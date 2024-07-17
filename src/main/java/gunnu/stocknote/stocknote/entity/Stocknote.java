package gunnu.stocknote.stocknote.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "stocknotes")
@SQLRestriction("deleted_at is NULL")
@SQLDelete(sql = "UPDATE stocknotes SET deleted_at = NOW() WHERE stocknote_id = ?")
public class Stocknote {

    @Id
    @Column(name = "stocknote_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stocknoteId;

    @Column(name = "title")
    private String title;

    @Column(name = "contents")
    private String contents;

    @Column(name = "image")
    private String image;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "stock_id")
    private Long stockId;
}
