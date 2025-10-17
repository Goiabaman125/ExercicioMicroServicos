package com.aula.pos.msproduto.service;

import com.aula.pos.msproduto.dto.PessoaDto;
import com.aula.pos.msproduto.model.Produto;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.aula.pos.msproduto.repository.PessoaRepository;

@RequiredArgsConstructor
@Service
public class PessoaServiceImpl implements PessoaService {

    private final PessoaRepository pessoaRepository;

    public Page<PessoaDto> findAll(Pageable pagination){
        return pessoaRepository.findAll(pagination).map(PessoaDto::new);
    }

    public PessoaDto findById(Long id){
        return pessoaRepository.findById(id).map(PessoaDto::new).orElseThrow(EntityNotFoundException::new);
    }

    @Transactional
    public PessoaDto save(PessoaDto pessoaDto){
        var pessoa = Produto.fromDto(pessoaDto);
        return new PessoaDto(pessoaRepository.save(pessoa));
    }

    @Transactional
    public PessoaDto update(Long id, PessoaDto pessoaDto){
        var pessoa = pessoaRepository
                .findById(id).orElseThrow(()->new EntityNotFoundException("Pessoa não Encontrada"));
        pessoa.setNome(pessoaDto.nome());
        pessoa.setCpf(pessoaDto.cpf());
        pessoa.setEmail(pessoaDto.email());
        pessoa.setTelefone(pessoaDto.telefone());
        return new PessoaDto(pessoaRepository.save(pessoa));
    }

    @Transactional
    public void delete(Long id){
        pessoaRepository
                .findById(id).orElseThrow(()->new EntityNotFoundException("Pessoa não Encontrada"));
        pessoaRepository.deleteById(id);
    }
}
