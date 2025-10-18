package com.universidade.service;

import com.universidade.entity.Funcionario;
import com.universidade.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {
    
    @Autowired
    private FuncionarioRepository funcionarioRepository;
    
    // Criar novo funcionário
    public Funcionario criarFuncionario(String nome, String email, String telefone) {
        Funcionario funcionario = new Funcionario(nome, email, telefone);
        return funcionarioRepository.save(funcionario);
    }
    
    // Buscar funcionário por ID
    public Funcionario buscarPorId(Long id) {
        Optional<Funcionario> funcionario = funcionarioRepository.findById(id);
        return funcionario.orElse(null);
    }
    
    // Buscar todos os funcionários
    public List<Funcionario> buscarTodos() {
        return funcionarioRepository.findAll();
    }
    
    // Atualizar funcionário
    public Funcionario atualizarFuncionario(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }
    
    // Deletar funcionário
    public void deletarFuncionario(Long id) {
        funcionarioRepository.deleteById(id);
    }
    
    // Verificar se funcionário existe
    public boolean funcionarioExiste(Long id) {
        return funcionarioRepository.existsById(id);
    }
    
    // Contar total de funcionários
    public long contarFuncionarios() {
        return funcionarioRepository.count();
    }
}