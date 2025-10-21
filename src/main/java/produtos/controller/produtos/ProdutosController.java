package produtos.controller.produtos;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import produtos.model.dto.produtos.ProdutosRequest;
import produtos.model.dto.produtos.ProdutosResponse;
import produtos.service.produtos.ProdutosFiltroRequest;
import produtos.service.produtos.ProdutosService;

import java.util.UUID;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutosController {

    private final ProdutosService produtosService;

    @GetMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Page<ProdutosResponse> listar(
            @ModelAttribute final ProdutosFiltroRequest filtros,
            @PageableDefault(size = 20, sort = "nome") final Pageable pageable) {
        return produtosService.buscar(filtros, pageable);
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProdutosResponse> criar(@Valid @RequestBody final ProdutosRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(produtosService.criar(request));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProdutosResponse> atualizar(
            @PathVariable final UUID id,
            @Valid @RequestBody final ProdutosRequest request) {
        return ResponseEntity.ok(produtosService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletar(@PathVariable final UUID id) {
        produtosService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
