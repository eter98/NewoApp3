package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.domain.ChatGrupo;
import io.github.jhipster.application.service.ChatGrupoService;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.service.dto.ChatGrupoCriteria;
import io.github.jhipster.application.service.ChatGrupoQueryService;

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

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static org.elasticsearch.index.query.QueryBuilders.*;

/**
 * REST controller for managing {@link io.github.jhipster.application.domain.ChatGrupo}.
 */
@RestController
@RequestMapping("/api")
public class ChatGrupoResource {

    private final Logger log = LoggerFactory.getLogger(ChatGrupoResource.class);

    private static final String ENTITY_NAME = "chatGrupo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ChatGrupoService chatGrupoService;

    private final ChatGrupoQueryService chatGrupoQueryService;

    public ChatGrupoResource(ChatGrupoService chatGrupoService, ChatGrupoQueryService chatGrupoQueryService) {
        this.chatGrupoService = chatGrupoService;
        this.chatGrupoQueryService = chatGrupoQueryService;
    }

    /**
     * {@code POST  /chat-grupos} : Create a new chatGrupo.
     *
     * @param chatGrupo the chatGrupo to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new chatGrupo, or with status {@code 400 (Bad Request)} if the chatGrupo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/chat-grupos")
    public ResponseEntity<ChatGrupo> createChatGrupo(@Valid @RequestBody ChatGrupo chatGrupo) throws URISyntaxException {
        log.debug("REST request to save ChatGrupo : {}", chatGrupo);
        if (chatGrupo.getId() != null) {
            throw new BadRequestAlertException("A new chatGrupo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ChatGrupo result = chatGrupoService.save(chatGrupo);
        return ResponseEntity.created(new URI("/api/chat-grupos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /chat-grupos} : Updates an existing chatGrupo.
     *
     * @param chatGrupo the chatGrupo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated chatGrupo,
     * or with status {@code 400 (Bad Request)} if the chatGrupo is not valid,
     * or with status {@code 500 (Internal Server Error)} if the chatGrupo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/chat-grupos")
    public ResponseEntity<ChatGrupo> updateChatGrupo(@Valid @RequestBody ChatGrupo chatGrupo) throws URISyntaxException {
        log.debug("REST request to update ChatGrupo : {}", chatGrupo);
        if (chatGrupo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ChatGrupo result = chatGrupoService.save(chatGrupo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, chatGrupo.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /chat-grupos} : get all the chatGrupos.
     *
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @param criteria the criteria which the requested entities should match.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of chatGrupos in body.
     */
    @GetMapping("/chat-grupos")
    public ResponseEntity<List<ChatGrupo>> getAllChatGrupos(ChatGrupoCriteria criteria, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to get ChatGrupos by criteria: {}", criteria);
        Page<ChatGrupo> page = chatGrupoQueryService.findByCriteria(criteria, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
    * {@code GET  /chat-grupos/count} : count all the chatGrupos.
    *
    * @param criteria the criteria which the requested entities should match.
    * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the count in body.
    */
    @GetMapping("/chat-grupos/count")
    public ResponseEntity<Long> countChatGrupos(ChatGrupoCriteria criteria) {
        log.debug("REST request to count ChatGrupos by criteria: {}", criteria);
        return ResponseEntity.ok().body(chatGrupoQueryService.countByCriteria(criteria));
    }

    /**
     * {@code GET  /chat-grupos/:id} : get the "id" chatGrupo.
     *
     * @param id the id of the chatGrupo to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the chatGrupo, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/chat-grupos/{id}")
    public ResponseEntity<ChatGrupo> getChatGrupo(@PathVariable Long id) {
        log.debug("REST request to get ChatGrupo : {}", id);
        Optional<ChatGrupo> chatGrupo = chatGrupoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(chatGrupo);
    }

    /**
     * {@code DELETE  /chat-grupos/:id} : delete the "id" chatGrupo.
     *
     * @param id the id of the chatGrupo to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/chat-grupos/{id}")
    public ResponseEntity<Void> deleteChatGrupo(@PathVariable Long id) {
        log.debug("REST request to delete ChatGrupo : {}", id);
        chatGrupoService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }

    /**
     * {@code SEARCH  /_search/chat-grupos?query=:query} : search for the chatGrupo corresponding
     * to the query.
     *
     * @param query the query of the chatGrupo search.
     * @param pageable the pagination information.
     * @param queryParams a {@link MultiValueMap} query parameters.
     * @param uriBuilder a {@link UriComponentsBuilder} URI builder.
     * @return the result of the search.
     */
    @GetMapping("/_search/chat-grupos")
    public ResponseEntity<List<ChatGrupo>> searchChatGrupos(@RequestParam String query, Pageable pageable, @RequestParam MultiValueMap<String, String> queryParams, UriComponentsBuilder uriBuilder) {
        log.debug("REST request to search for a page of ChatGrupos for query {}", query);
        Page<ChatGrupo> page = chatGrupoService.search(query, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(uriBuilder.queryParams(queryParams), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

}
