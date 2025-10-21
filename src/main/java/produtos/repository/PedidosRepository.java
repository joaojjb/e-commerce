package produtos.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import produtos.model.entity.Pedidos;
import produtos.model.entity.User;

import java.util.List;
import java.util.UUID;

@Repository
public interface PedidosRepository extends JpaRepository<Pedidos, UUID>, JpaSpecificationExecutor<Pedidos> {

    @EntityGraph(attributePaths = {"produtosPedidos", "produtosPedidos.criadoPor",
            "produtosPedidos.atualizadoPor", "produtosPedidos.produto", "criadoPor", "atualizadoPor"})
    List<Pedidos> findByCriadoPor(final User usuario);

    boolean existsByProdutosPedidos_Produto_Id(final UUID id);
}

