package io.github.jhipster.application.service;

import java.util.List;

import javax.persistence.criteria.JoinType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.jhipster.service.QueryService;

import io.github.jhipster.application.domain.ChatListGrupo;
import io.github.jhipster.application.domain.*; // for static metamodels
import io.github.jhipster.application.repository.ChatListGrupoRepository;
import io.github.jhipster.application.repository.search.ChatListGrupoSearchRepository;
import io.github.jhipster.application.service.dto.ChatListGrupoCriteria;

/**
 * Service for executing complex queries for {@link ChatListGrupo} entities in the database.
 * The main input is a {@link ChatListGrupoCriteria} which gets converted to {@link Specification},
 * in a way that all the filters must apply.
 * It returns a {@link List} of {@link ChatListGrupo} or a {@link Page} of {@link ChatListGrupo} which fulfills the criteria.
 */
@Service
@Transactional(readOnly = true)
public class ChatListGrupoQueryService extends QueryService<ChatListGrupo> {

    private final Logger log = LoggerFactory.getLogger(ChatListGrupoQueryService.class);

    private final ChatListGrupoRepository chatListGrupoRepository;

    private final ChatListGrupoSearchRepository chatListGrupoSearchRepository;

    public ChatListGrupoQueryService(ChatListGrupoRepository chatListGrupoRepository, ChatListGrupoSearchRepository chatListGrupoSearchRepository) {
        this.chatListGrupoRepository = chatListGrupoRepository;
        this.chatListGrupoSearchRepository = chatListGrupoSearchRepository;
    }

    /**
     * Return a {@link List} of {@link ChatListGrupo} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public List<ChatListGrupo> findByCriteria(ChatListGrupoCriteria criteria) {
        log.debug("find by criteria : {}", criteria);
        final Specification<ChatListGrupo> specification = createSpecification(criteria);
        return chatListGrupoRepository.findAll(specification);
    }

    /**
     * Return a {@link Page} of {@link ChatListGrupo} which matches the criteria from the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param page The page, which should be returned.
     * @return the matching entities.
     */
    @Transactional(readOnly = true)
    public Page<ChatListGrupo> findByCriteria(ChatListGrupoCriteria criteria, Pageable page) {
        log.debug("find by criteria : {}, page: {}", criteria, page);
        final Specification<ChatListGrupo> specification = createSpecification(criteria);
        return chatListGrupoRepository.findAll(specification, page);
    }

    /**
     * Return the number of matching entities in the database.
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the number of matching entities.
     */
    @Transactional(readOnly = true)
    public long countByCriteria(ChatListGrupoCriteria criteria) {
        log.debug("count by criteria : {}", criteria);
        final Specification<ChatListGrupo> specification = createSpecification(criteria);
        return chatListGrupoRepository.count(specification);
    }

    /**
     * Function to convert ConsumerCriteria to a {@link Specification}
     * @param criteria The object which holds all the filters, which the entities should match.
     * @return the matching {@link Specification} of the entity.
     */    
    private Specification<ChatListGrupo> createSpecification(ChatListGrupoCriteria criteria) {
        Specification<ChatListGrupo> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getId() != null) {
                specification = specification.and(buildSpecification(criteria.getId(), ChatListGrupo_.id));
            }
            if (criteria.getPropietarioId() != null) {
                specification = specification.and(buildSpecification(criteria.getPropietarioId(),
                    root -> root.join(ChatListGrupo_.propietario, JoinType.LEFT).get(User_.id)));
            }
            if (criteria.getDestinatarioId() != null) {
                specification = specification.and(buildSpecification(criteria.getDestinatarioId(),
                    root -> root.join(ChatListGrupo_.destinatario, JoinType.LEFT).get(User_.id)));
            }
            if (criteria.getChatGrupoId() != null) {
                specification = specification.and(buildSpecification(criteria.getChatGrupoId(),
                    root -> root.join(ChatListGrupo_.chatGrupos, JoinType.LEFT).get(ChatGrupo_.id)));
            }
        }
        return specification;
    }
}
