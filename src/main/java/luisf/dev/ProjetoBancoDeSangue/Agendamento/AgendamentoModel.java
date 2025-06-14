package luisf.dev.ProjetoBancoDeSangue.Agendamento;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import luisf.dev.ProjetoBancoDeSangue.Doador.DoadorModel;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_agendamentos")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AgendamentoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "data_hora_agendamento")
    private LocalDateTime dataHoraAgendamento;

    @Column(name = "status_agendamento", nullable = false)
    private StatusAgendamento status;

    @JsonBackReference("doador-agendamentos")
    @ManyToOne
    @JoinColumn(name = "doador_id")
    private DoadorModel doador;
}
