package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.domain.ChatListGrupo;
import io.github.jhipster.application.service.ChatListGrupoService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ChatListGrupoCriteria;
import io.github.jhipster.application.service.ChatListGrupoQueryService;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing {@link io.github.jhipster.application.domain.ChatListGrupo}.
 */
@RestController
@RequestMapping("/api")
public class ChatListGrupoResource {

    private final Logger log = LoggerFactory.getLogger(ChatListGrupoResource.class);

    private static final String ENTITY_NAME = "chatListGrupo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ChatListGrupoService chatListGrupoService;

    private final ChatListGrupoQueryService chatListGrupoQueryService;

    public ChatListGrupoResource(ChatListGrupoService chatListGrupoService, ChatListGrupoQueryService chatListGrupoQueryService) {
        this.chatListGrupoService = chatListGrupoService;
        this.chatListGrupoQueryService = chatListGrupoQueryService;
    }

    /**
     * {@code POST  /chat-list-grupos} : Create a new chatListGrupo.
     *
     * @param chatListGrupo the chatListGrupo to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new chatListGrupo, or with status {@code 400 (Bad Request)} if the chatListGrupo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/chat-list-grupos")
    public ResponseEntity<ChatListGrupo> createChatListGrupo(@RequestBody ChatListGrupo chatListGrupo) throws URISyntaxException {
        log.debug("REST request to save ChatListGrupo : {}", chatListGrupo);
        if (chatListGrupo.getId() != null) {
            throw new BadRequestAlertException("A new chatListGrupo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ChatListGrupo result = chatListGrupoService.save(chatListGrupo);
        return ResponseEntity.created(new URI("/api/chat-list-grupos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /chat-list-grupos} : Updates an existing chatListGrupo.
     *
     * @param chatListGrupo the chatListGrupo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated chatListGrupo,
     * or with status {@code 400 (Bad Request)} if the chatListGrupo is not valid,
     * or with status {@code 500 (Internal Server Error)} if the chatListGrupo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/chat-list-grupos")
    public ResponseEntity<ChatListGrupo> updateChatListGrupo(@RequestBody ChatListGrupo chatListGrupo) throws URISyntaxException {
        log.debug("REST request to update ChatListGrupo : {}", chatListGrupo);
        if (chatListGrupo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ChatListGrupo result = chatListGrupoService.save(chatListGrupo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, chatListGrupo.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /chat-list-grupos} : get all the chatListGrupos.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of chatListGrupos in body.
     */
    @GetMapping("/chat-list-grupos")
    public ResponseEntity<List<ChatListGrupo>> getAllChatListGrupos(ChatListGrupoCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ChatListGrupos by criteria: {}", criteria);
        Page<ChatListGrupo> page = chatListGrupoQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /chat-list-grupos/count} : count all the chatListGrupos.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/chat-list-grupos/count")
    public ResponseEntity<Long> countChatListGrupos(ChatListGrupoCriteria criteria) {
        log.debug("REST request to count ChatListGrupos by criteria: {}", criteria);
        return ResponseEntity.ok().body(chatListGrupoQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /chat-list-grupos/:id} : get the "id" chatListGrupo.
     *
     * @param id the id of the chatListGrupo to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the chatListGrupo, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/chat-list-grupos/{id}")
    public ResponseEntity<ChatListGrupo> getChatListGrupo(@PathVariable Long id) {
        log.debug("REST request to get ChatListGrupo : {}", id);
        Optional<ChatListGrupo> chatListGrupo = chatListGrupoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(chatListGrupo);
    }

    /**
     * {@code DELETE  /chat-list-grupos/:id} : delete the "id" chatListGrupo.
     *
     * @param id the id of the chatListGrupo to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/chat-list-grupos/{id}")
    public ResponseEntity<Void> deleteChatListGrupo(@PathVariable Long id) {
        log.debug("REST request to delete ChatListGrupo : {}", id);
        chatListGrupoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/chat-list-grupos?query=:query} : search for the chatListGrupo corresponding
     * to the query.
     *
     * @param query the query of the chatListGrupo search.
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the result of the search.
     */
    @GetMapping("/_search/chat-list-grupos")
    public ResponseEntity<List<ChatListGrupo>> searchChatListGrupos(@RequestParam String query, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to search for a page of ChatListGrupos for query {}", query);
        Page<ChatListGrupo> page = chatListGrupoService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
