package produtos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import produtos.model.entity.Produtos;

import java.util.UUID;

@Repository
public interface ProdutosRepository extends JpaRepository<Produtos, UUID>, JpaSpecificationExecutor<Produtos> {

    @Modifying
    @Query("UPDATE Produtos p SET p.quantidadeEstoque = :novaQuantidade WHERE p.id = :id")
    void atualizarEstoque(@Param("id") final UUID id, @Param("novaQuantidade") final Integer novaQuantidade);
}
