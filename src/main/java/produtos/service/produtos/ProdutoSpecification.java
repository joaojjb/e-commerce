package produtos.service.produtos;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.Builder;
import lombok.NonNull;
import org.springframework.data.jpa.domain.Specification;
import produtos.model.entity.Produtos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.util.Optional.ofNullable;

@Builder
public class ProdutoSpecification implements Specification<Produtos> {

    private static final String NOME = "nome";
    private static final String CATEGORIA = "categoria";
    private static final String PRECO = "preco";
    private static final String QUANTIDADE_ESTOQUE = "quantidadeEstoque";

    @Builder.Default
    private final transient String nome = null;

    @Builder.Default
    private final transient String categoria = null;

    @Builder.Default
    private final transient BigDecimal precoMinimo = null;

    @Builder.Default
    private final transient BigDecimal precoMaximo = null;

    @Builder.Default
    private final transient Integer quantidadeEstoqueMinima = null;

    @Override
    public Predicate toPredicate(@NonNull final Root<Produtos> root, final CriteriaQuery<?> query,
                                 @NonNull final CriteriaBuilder builder) {
        final List<Predicate> predicates = new ArrayList<>();

        ofNullable(nome).ifPresent(value -> predicates.add(
                builder.like(builder.lower(root.get(NOME)), "%" + value.toLowerCase() + "%")
        ));

        ofNullable(categoria).ifPresent(value -> predicates.add(
                builder.equal(root.get(CATEGORIA), value)
        ));

        ofNullable(precoMinimo).ifPresent(value -> predicates.add(
                builder.greaterThanOrEqualTo(root.get(PRECO), value)
        ));

        ofNullable(precoMaximo).ifPresent(value -> predicates.add(
                builder.lessThanOrEqualTo(root.get(PRECO), value)
        ));

        ofNullable(quantidadeEstoqueMinima).ifPresent(value -> predicates.add(
                builder.greaterThanOrEqualTo(root.get(QUANTIDADE_ESTOQUE), value)
        ));

        return builder.and(predicates.toArray(new Predicate[0]));
    }
}