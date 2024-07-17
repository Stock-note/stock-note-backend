package gunnu.stocknote.stock.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QStock is a Querydsl query type for Stock
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStock extends EntityPathBase<Stock> {

    private static final long serialVersionUID = -1755745744L;

    public static final QStock stock = new QStock("stock");

    public final gunnu.stocknote.common.entity.QBaseEntity _super = new gunnu.stocknote.common.entity.QBaseEntity(this);

    public final StringPath code = createString("code");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final EnumPath<Market> market = createEnum("market", Market.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath name = createString("name");

    public final NumberPath<Long> price = createNumber("price", Long.class);

    public final NumberPath<Long> stockId = createNumber("stockId", Long.class);

    public QStock(String variable) {
        super(Stock.class, forVariable(variable));
    }

    public QStock(Path<? extends Stock> path) {
        super(path.getType(), path.getMetadata());
    }

    public QStock(PathMetadata metadata) {
        super(Stock.class, metadata);
    }

}

