/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.senac.tads.dsw.projetocontatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Primary
public class ContatoServiceBdImpl implements ContatoService {
    
    @Autowired
    private ContatoRepository repository;
    
    private ContatoDto convertEntityToDto(Contato entity) {
        ContatoDto dto = new ContatoDto();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setEmail(entity.getEmail());
        dto.setTelefone(entity.getTelefone());
        dto.setDataNascimento(entity.getDataNascimento());
        return dto;
    }

    @Override
    public List<ContatoDto> findAll() {
        List<Contato> contatosEntities = repository.findAll();
        List<ContatoDto> listaDtos = new ArrayList<>();
        for(Contato entity : contatosEntities) {
            listaDtos.add(convertEntityToDto(entity));
        }
        return listaDtos;
    }

    @Override
    public ContatoDto findById(int id) {
        Optional<Contato> optContato = repository.findById(id);
        if (optContato.isEmpty()) {
            return null;
        }
        Contato entity = optContato.get();
        return convertEntityToDto(entity);
    }

    @Override
    public Optional<ContatoDto> findByIdComOptional(int id) {
        return repository.findById(id).map(entity -> convertEntityToDto(entity));
    }

    @Transactional // Importado do org.springframework...
    @Override
    public void save(ContatoDto dto) {
        Contato entity = new Contato();
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setEmail(dto.getEmail());
        entity.setTelefone(dto.getTelefone());
        entity.setDataNascimento(dto.getDataNascimento());
        repository.save(entity);
        
    }

    @Transactional // Importado do org.springframework...
    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }

}
