package luisf.dev.ProjetoBancoDeSangue.Doador;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "tb_doador")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DoadorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name="nome_completo", nullable = false)
    private String nomeCompleto;

    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_sanguineo", nullable = false)
    private TipoSanguineo tipoSanguineo;

    @Column(name = "ultima_doacao")
    private LocalDate ultimaDoacao;
}
