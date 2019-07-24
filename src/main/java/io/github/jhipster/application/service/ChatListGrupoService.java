package io.github.jhipster.application.service;

import io.github.jhipster.application.domain.ChatListGrupo;
import io.github.jhipster.application.repository.ChatListGrupoRepository;
import io.github.jhipster.application.repository.search.ChatListGrupoSearchRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * Service Implementation for managing {@link ChatListGrupo}.
 */
@Service
@Transactional
public class ChatListGrupoService {

    private final Logger log = LoggerFactory.getLogger(ChatListGrupoService.class);

    private final ChatListGrupoRepository chatListGrupoRepository;

    private final ChatListGrupoSearchRepository chatListGrupoSearchRepository;

    public ChatListGrupoService(ChatListGrupoRepository chatListGrupoRepository, ChatListGrupoSearchRepository chatListGrupoSearchRepository) {
        this.chatListGrupoRepository = chatListGrupoRepository;
        this.chatListGrupoSearchRepository = chatListGrupoSearchRepository;
    }

    /**
     * Save a chatListGrupo.
     *
     * @param chatListGrupo the entity to save.
     * @return the persisted entity.
     */
    public ChatListGrupo save(ChatListGrupo chatListGrupo) {
        log.debug("Request to save ChatListGrupo : {}", chatListGrupo);
        ChatListGrupo result = chatListGrupoRepository.save(chatListGrupo);
        chatListGrupoSearchRepository.save(result);
        return result;
    }

    /**
     * Get all the chatListGrupos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ChatListGrupo> findAll(Pageable pageable) {
        log.debug("Request to get all ChatListGrupos");
        return chatListGrupoRepository.findAll(pageable);
    }


    /**
     * Get one chatListGrupo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ChatListGrupo> findOne(Long id) {
        log.debug("Request to get ChatListGrupo : {}", id);
        return chatListGrupoRepository.findById(id);
    }

    /**
     * Delete the chatListGrupo by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ChatListGrupo : {}", id);
        chatListGrupoRepository.deleteById(id);
        chatListGrupoSearchRepository.deleteById(id);
    }

    /**
     * Search for the chatListGrupo corresponding to the query.
     *
     * @param query the query of the search.
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public Page<ChatListGrupo> search(String query, Pageable pageable) {
        log.debug("Request to search for a page of ChatListGrupos for query {}", query);
        return chatListGrupoSearchRepository.search(queryStringQuery(query), pageable);    }
}
