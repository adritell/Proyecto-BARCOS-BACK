package com.api.sociosBarcos.serviceImpl;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.sociosBarcos.entity.Socio;
import com.api.sociosBarcos.repository.SocioRepository;
import com.api.sociosBarcos.service.SocioService;





@Service
public class SocioServiceImpl implements SocioService {

    @Autowired
    private SocioRepository socioRepository;

    

    @Override
    public Socio agregarSocio(Socio socio) {
        return socioRepository.save(socio);
    }

    

    @Override
    public Socio obtenerSocioPorId(Long id) {
        if (id == null) {
            return null; // O puedes lanzar una excepción aquí si prefieres
        }
        return socioRepository.findById(id).orElse(null);
    }

    

    @Override
    public Socio actualizarSocio(Long id, Socio socio) {
        Socio nuevoSocio = obtenerSocioPorId(id);

        nuevoSocio.setNombre(socio.getNombre());
        nuevoSocio.setApellidos(socio.getApellidos());
        nuevoSocio.setDni(socio.getDni());

        return socioRepository.save(nuevoSocio);
    }

    

    @Override
    public void eliminarSocio(Long id) {
        socioRepository.deleteById(id);
    }

    

    @Override
    public List<Socio> listarTodasLasSocio() {
        return socioRepository.findAll();
    }

    

    /**
     * Busca el socio con la mayor cantidad de barcos asociados.
     * 
     * @return El socio con la mayor cantidad de barcos asociados.
     */
    @Override
    public Socio buscarSocioMaxBarcos() {
        return socioRepository.findSocioWithMostBarcos();
    }

	@Override
	public List<Socio> findAllWithoutBarco() {
		return socioRepository.findAllWithoutBarco();
	}
}