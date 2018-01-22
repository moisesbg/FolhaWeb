package com.moisesbg.dao;

import com.moisesbg.model.Entidade;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.EntityPath;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.path.PathBuilderFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class GenericDao<T extends Entidade> implements Serializable {

    private final Class<T> classe;

    private EntityManager em;

    private PathBuilderFactory factory;

    public GenericDao(Class<T> classe, EntityManager em) {
        this.classe = classe;
        this.em = em;
        this.factory = new PathBuilderFactory();
    }

    public void inserir(@Valid T entidade) {
        em.persist(entidade);
    }

    public T atualizar(@Valid T entidade) {
        return em.merge(entidade);
    }

    public void remover(Integer id) {
        T entidade = em.getReference(classe, id);
        if(Objects.isNull(entidade)) {
            throw new EntityNotFoundException("Não foi encontrado o registro de "+classe.getSimpleName()
                    +" contendo o ID: "+id);
        }
        em.remove(entidade);
    }

    public List<T> listar() {
        return listar(null);
    }

    public List<T> listar(Predicate where){
        EntityPath<T> classQ = factory.create(classe);
        JPAQuery query = new JPAQuery(em).from(classQ);
        if(Objects.nonNull(where)){
            return query.where(where).list(classQ);
        }
        return query.list(classQ);
    }

    public T listarPorId(Integer id) {
        T entidade = em.find(classe, id);
        if (Objects.isNull(entidade)) {
            throw new EntityNotFoundException("Não foi encontrado o registro de "+classe.getSimpleName()
                    +" contendo o ID: "+id);
        }
        return entidade;
    }
}