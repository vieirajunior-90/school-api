package com.vieira.schoolapi.services;

import com.vieira.schoolapi.dtos.DisciplineDto;
import com.vieira.schoolapi.models.Discipline;
import com.vieira.schoolapi.repositories.DisciplineRepository;
import com.vieira.schoolapi.services.exceptions.ConstraintException;
import com.vieira.schoolapi.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DisciplineService {

    @Autowired
    private DisciplineRepository disciplineRepository;

    @Transactional
    public Discipline save(Discipline discipline) {
        if (disciplineRepository.existsByName(discipline.getName())) {
            throw new ConstraintException("Discipline already exists");
        }
        return disciplineRepository.save(discipline);
    }

    public DisciplineDto findById(Long id) {
        Optional<Discipline> discipline = disciplineRepository.findById(id);
        return DisciplineDto.convert(discipline.orElseThrow((() -> new ResourceNotFoundException(id))));
    }

    public List<DisciplineDto> findAll() {
        List<Discipline> disciplines = disciplineRepository.findAll();
        return disciplines.stream().map(DisciplineDto::convert).collect(Collectors.toList());
    }
}
