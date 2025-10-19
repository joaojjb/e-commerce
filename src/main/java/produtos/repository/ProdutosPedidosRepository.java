package produtos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import produtos.entity.ProdutosPedidos;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProdutosPedidosRepository extends JpaRepository<ProdutosPedidos, UUID> {
    List<ProdutosPedidos> findByPedidoId(UUID pedidoId);
    List<ProdutosPedidos> findByProdutoId(UUID produtoId);
}

