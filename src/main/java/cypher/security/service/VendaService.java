package cypher.security.service;

import cypher.security.dto.VendaDTO;
import cypher.security.fraud.MotorFraude;
import cypher.security.fraud.ResultadoAnalise;
import cypher.security.model.Produto;
import cypher.security.repository.VendaRepository;
import cypher.security.repository.VendedorRepository;

import java.util.List;

public class VendaService {

    private final VendaRepository vendaRepository;
    private final VendedorRepository vendedorRepository;
    private final MotorFraude motorFraude;

    public VendaService(VendaRepository vendaRepository, VendedorRepository vendedorRepository, MotorFraude motorFraude) {
        this.vendaRepository = vendaRepository;
        this.vendedorRepository = vendedorRepository;
        this.motorFraude = motorFraude;
    }

    public ResultadoAnalise salvarEAnalisar(VendaDTO dto) {}
}
