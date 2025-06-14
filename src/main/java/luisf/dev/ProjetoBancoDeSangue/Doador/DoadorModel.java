package luisf.dev.ProjetoBancoDeSangue.Doador;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import luisf.dev.ProjetoBancoDeSangue.Agendamento.AgendamentoModel;
import luisf.dev.ProjetoBancoDeSangue.Doacao.DoacaoModel;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tb_doador")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DoadorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @JsonManagedReference("doador-agendamentos")
    @OneToMany(mappedBy = "doador")
    private List<AgendamentoModel> agendamentos;

    @JsonManagedReference("doador-doacoes")
    @OneToMany(mappedBy = "doador")
    private List<DoacaoModel> doacoes;
}
