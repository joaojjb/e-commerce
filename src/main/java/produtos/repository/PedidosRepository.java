package produtos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import produtos.entity.Pedidos;
import produtos.entity.User;

import java.util.List;
import java.util.UUID;

@Repository
public interface PedidosRepository extends JpaRepository<Pedidos, UUID>, JpaSpecificationExecutor<Pedidos> {
    List<Pedidos> findByCriadoPor(final User usuario);
}

