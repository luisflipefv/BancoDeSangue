package luisf.dev.ProjetoBancoDeSangue.EstoqueDeSangue;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import luisf.dev.ProjetoBancoDeSangue.Doador.TipoSanguineo;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_estoque")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EstoqueModel {

    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_sanguineo")
    private TipoSanguineo tipoSanguineo;

    @Column(name = "quantidade_bolsas")
    private int quantidadeBolsas;

    @Column(name = "data_ultima_atualizacao")
    private LocalDateTime ultimaAtualizacao;
}
