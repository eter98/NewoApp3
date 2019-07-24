package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ChatGrupo;
import io.github.jhipster.application.repository.ChatGrupoRepository;
import io.github.jhipster.application.repository.search.ChatGrupoSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link ChatGrupo}.
 */
@Service
@Transactional
public class ChatGrupoService {

    private final Logger log = LoggerFactory.getLogger(ChatGrupoService.class);

    private final ChatGrupoRepository chatGrupoRepository;

    private final ChatGrupoSearchRepository chatGrupoSearchRepository;

    public ChatGrupoService(ChatGrupoRepository chatGrupoRepository, ChatGrupoSearchRepository chatGrupoSearchRepository) {
        this.chatGrupoRepository = chatGrupoRepository;
        this.chatGrupoSearchRepository = chatGrupoSearchRepository;
    }

    /**
     * Save a chatGrupo.
     *
     * @param chatGrupo the entity to save.
     * @return the persisted entity.
     */
    public ChatGrupo save(ChatGrupo chatGrupo) {
        log.debug("Request to save ChatGrupo : {}", chatGrupo);
        ChatGrupo result = chatGrupoRepository.save(chatGrupo);
        chatGrupoSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the chatGrupos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ChatGrupo> findAll(Pageable pageable) {
        log.debug("Request to get all ChatGrupos");
        return chatGrupoRepository.findAll(pageable);
    }


    /**
     * Get one chatGrupo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ChatGrupo> findOne(Long id) {
        log.debug("Request to get ChatGrupo : {}", id);
        return chatGrupoRepository.findById(id);
    }

    /**
     * Delete the chatGrupo by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ChatGrupo : {}", id);
        chatGrupoRepository.deleteById(id);
        chatGrupoSearchRepository.deleteById(id);
    }

    /**
     * Search for the chatGrupo corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ChatGrupo> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of ChatGrupos for query {}", query);
        return chatGrupoSearchRepository.search(queryStringQuery(query), pageable);    }
}
