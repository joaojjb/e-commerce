package produtos.dto.relatorios;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RelatorioResponse {
    private String titulo;
    private Object dados;
    private LocalDateTime geradoEm;
}
