package ny.rina.gestioncommune.population.personne.service;

import org.springframework.data.jpa.repository.JpaRepository;

import ny.rina.gestioncommune.core.service.ServiceImpl;

public abstract class PersonneServiceImpl<
        E, // Enity
        R, // Response DTO
        Q // Request DTO
> extends ServiceImpl<E,R,Q>{

    protected PersonneServiceImpl(JpaRepository<E, Long> repository) {
        super(repository);
    }}