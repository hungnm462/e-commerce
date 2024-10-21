package vn.gs.order.repository.config;

import lombok.NonNull;
import org.springframework.data.jpa.provider.QueryExtractor;
import org.springframework.data.jpa.repository.query.JpaQueryMethod;
import org.springframework.data.jpa.repository.query.JpaQueryMethodFactory;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.util.Assert;

import java.lang.reflect.Method;

public class JpaQueryResourceMethodFactory implements JpaQueryMethodFactory {
    private final QueryExtractor extractor;

    public JpaQueryResourceMethodFactory(QueryExtractor extractor) {
        Assert.notNull(extractor, "QueryExtractor must not be null");
        this.extractor = extractor;
    }

    @Override
    public @NonNull JpaQueryMethod build(@NonNull Method method, @NonNull RepositoryMetadata metadata, @NonNull ProjectionFactory factory) {
        return new JpaQueryResourceMethod(method, metadata, factory, this.extractor);
    }
}