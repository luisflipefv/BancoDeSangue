package luisf.dev.ProjetoBancoDeSangue.Doacao;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import luisf.dev.ProjetoBancoDeSangue.Doador.DoadorModel;

import java.time.LocalDateTime;


@Entity
@Table(name = "tb_doacao")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class DoacaoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(optional = false)
    @JsonBackReference("doador-doacoes")
    @JoinColumn(name = "doador_id")
    private DoadorModel doador;

    @Column(name = "data_doacao", nullable = false)
    private LocalDateTime dataDoacao;

    @Column(name = "quantidade_ml", nullable = false)
    private  int quantidadeMl;
}
