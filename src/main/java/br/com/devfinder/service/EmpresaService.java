package br.com.devfinder.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.devfinder.model.Desafio;
import br.com.devfinder.model.Empresa;
import br.com.devfinder.model.ids.DesafioId;
import br.com.devfinder.repository.EmpresaRepository;

/**
 * @author Ronaldo Costa
 *
 */
@Service
public class EmpresaService {

	@Autowired
	private EmpresaRepository repository;
	
	@Autowired
	private DesafioService service;

	/**
	 * Métodos POST
	 */
	public Empresa saveEmpresa(Empresa empresa) {
		return repository.save(empresa);
	}

	public List<Empresa> saveEmpresas(List<Empresa> empresas) {
		return repository.saveAll(empresas);
	}

	/**
	 * Métodos GET
	 */
	public List<Empresa> getEmpresas() {
		return repository.findAll();
	}
	public List<Empresa> getEmpresas(String[] texto) {
		return repository.findEmpresa(texto);
	}
	public Empresa getEmpresaById(String email) {
		return repository.findById(email).orElse(null);
	}

	public Empresa getEmpresaByNomeFantasia(String nomeFantasia) {
		return repository.findByNomeFantasia(nomeFantasia);
	}

	/**
	 * Método DELETE
	 */
	public String deleteEmpresa(String email) {
		for(Desafio d: service.getDesafios(email)) {
			service.deleteDesafio(new DesafioId(d.getEmailEmpresa(), d.getId()));
		}
		repository.deleteById(email);
		return "empresa deletada: " + email;
	}

	/**
	 * Métodos PUT
	 */
	public Empresa updateEmpresa(Empresa empresa) {
		Empresa existingEmpresa = repository.findById(empresa.getEmail()).orElse(null);
		existingEmpresa.setFoto(empresa.getFoto());
		existingEmpresa.setSenha(empresa.getSenha());
		existingEmpresa.setEndereco(empresa.getEndereco());
		existingEmpresa.setSite(empresa.getSite());
		existingEmpresa.setTelefone(empresa.getTelefone());
		existingEmpresa.setApresentacao(empresa.getApresentacao());
		existingEmpresa.setTotalDesenvolvedores(empresa.getTotalDesenvolvedores());
		existingEmpresa.setNomeFantasia(empresa.getNomeFantasia());
		existingEmpresa.setRazaoSocial(empresa.getRazaoSocial());
		existingEmpresa.setRamoMercado(empresa.getRamoMercado());
		existingEmpresa.setTipoVaga(empresa.getTipoVaga());
		existingEmpresa.setTotalDesenvolvedores(empresa.getTotalDesenvolvedores());
		//existingEmpresa.setVagasRemotas(empresa.isVagasRemotas());
		//existingEmpresa.setVagasFisicas(empresa.isVagasFisicas());
		return repository.save(existingEmpresa);
	}
}
