package luisf.dev.ProjetoBancoDeSangue.Doacao;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/doacoes")
public class DoacaoController {

    private DoacaoService doacaoService;

    public DoacaoController(DoacaoService doacaoService) {
        this.doacaoService = doacaoService;
    }

    //post
    @PostMapping
    public ResponseEntity<DoacaoModel> criarDoacao(@RequestBody DoacaoModel doacao){
        DoacaoModel doacaoCriada = doacaoService.criaDoacao(doacao);
        URI location = URI.create("/doacoes/" + doacaoCriada.getId());
        return ResponseEntity.created(location).body(doacaoCriada);
    }

    //get - listar todas as doacoes
    @GetMapping
    public ResponseEntity<List<DoacaoModel>> listarTodasDoacoes(){
        List<DoacaoModel> lista = doacaoService.consultaTodasDoacoes();
        return ResponseEntity.ok().body(lista);
    }

    //get - listar por id
    @GetMapping("/{id}")
    public ResponseEntity<DoacaoModel> listarDoacaoPorId(@PathVariable Long id){
        DoacaoModel doacaoEncontrada = doacaoService.consultaDoacaoPorId(id);
        return ResponseEntity.ok().body(doacaoEncontrada);
    }

    //get - listar por doador
    @GetMapping("/doador/{id}")
    public ResponseEntity<List <DoacaoModel>> listarDoacoesPorDoador(@PathVariable Long id){
        List<DoacaoModel> doacaoEncontrada = doacaoService.consultaDoacaoPorDoador(id);
        return ResponseEntity.ok().body(doacaoEncontrada);
    }


}
