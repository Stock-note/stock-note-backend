package gunnu.stocknote.stocknote.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QStocknote is a Querydsl query type for Stocknote
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStocknote extends EntityPathBase<Stocknote> {

    private static final long serialVersionUID = -1818658576L;

    public static final QStocknote stocknote = new QStocknote("stocknote");

    public final StringPath contents = createString("contents");

    public final StringPath image = createString("image");

    public final NumberPath<Long> stockId = createNumber("stockId", Long.class);

    public final NumberPath<Long> stocknoteId = createNumber("stocknoteId", Long.class);

    public final StringPath title = createString("title");

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QStocknote(String variable) {
        super(Stocknote.class, forVariable(variable));
    }

    public QStocknote(Path<? extends Stocknote> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStocknote(PathMetadata metadata) {
        super(Stocknote.class, metadata);
    }

}

