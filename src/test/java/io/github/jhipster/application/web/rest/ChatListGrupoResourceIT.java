package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.NewoApp3App;
import io.github.jhipster.application.domain.ChatListGrupo;
import io.github.jhipster.application.domain.User;
import io.github.jhipster.application.domain.ChatGrupo;
import io.github.jhipster.application.repository.ChatListGrupoRepository;
import io.github.jhipster.application.repository.search.ChatListGrupoSearchRepository;
import io.github.jhipster.application.service.ChatListGrupoService;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ChatListGrupoCriteria;
import io.github.jhipster.application.service.ChatListGrupoQueryService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;
import static org.hamcrest.Matchers.hasItem;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@Link ChatListGrupoResource} REST controller.
 */
@SpringBootTest(classes = NewoApp3App.class)
public class ChatListGrupoResourceIT {

    @Autowired
    private ChatListGrupoRepository chatListGrupoRepository;

    @Autowired
    private ChatListGrupoService chatListGrupoService;

    /**
     * This repository is mocked in the io.github.jhipster.application.repository.search test package.
     *
     * @see io.github.jhipster.application.repository.search.ChatListGrupoSearchRepositoryMockConfiguration
     */
    @Autowired
    private ChatListGrupoSearchRepository mockChatListGrupoSearchRepository;

    @Autowired
    private ChatListGrupoQueryService chatListGrupoQueryService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restChatListGrupoMockMvc;

    private ChatListGrupo chatListGrupo;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ChatListGrupoResource chatListGrupoResource = new ChatListGrupoResource(chatListGrupoService, chatListGrupoQueryService);
        this.restChatListGrupoMockMvc = MockMvcBuilders.standaloneSetup(chatListGrupoResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ChatListGrupo createEntity(EntityManager em) {
        ChatListGrupo chatListGrupo = new ChatListGrupo();
        return chatListGrupo;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ChatListGrupo createUpdatedEntity(EntityManager em) {
        ChatListGrupo chatListGrupo = new ChatListGrupo();
        return chatListGrupo;
    }

    @BeforeEach
    public void initTest() {
        chatListGrupo = createEntity(em);
    }

    @Test
    @Transactional
    public void createChatListGrupo() throws Exception {
        int databaseSizeBeforeCreate = chatListGrupoRepository.findAll().size();

        // Create the ChatListGrupo
        restChatListGrupoMockMvc.perform(post("/api/chat-list-grupos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chatListGrupo)))
            .andExpect(status().isCreated());

        // Validate the ChatListGrupo in the database
        List<ChatListGrupo> chatListGrupoList = chatListGrupoRepository.findAll();
        assertThat(chatListGrupoList).hasSize(databaseSizeBeforeCreate + 1);
        ChatListGrupo testChatListGrupo = chatListGrupoList.get(chatListGrupoList.size() - 1);

        // Validate the ChatListGrupo in Elasticsearch
        verify(mockChatListGrupoSearchRepository, times(1)).save(testChatListGrupo);
    }

    @Test
    @Transactional
    public void createChatListGrupoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = chatListGrupoRepository.findAll().size();

        // Create the ChatListGrupo with an existing ID
        chatListGrupo.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restChatListGrupoMockMvc.perform(post("/api/chat-list-grupos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chatListGrupo)))
            .andExpect(status().isBadRequest());

        // Validate the ChatListGrupo in the database
        List<ChatListGrupo> chatListGrupoList = chatListGrupoRepository.findAll();
        assertThat(chatListGrupoList).hasSize(databaseSizeBeforeCreate);

        // Validate the ChatListGrupo in Elasticsearch
        verify(mockChatListGrupoSearchRepository, times(0)).save(chatListGrupo);
    }


    @Test
    @Transactional
    public void getAllChatListGrupos() throws Exception {
        // Initialize the database
        chatListGrupoRepository.saveAndFlush(chatListGrupo);

        // Get all the chatListGrupoList
        restChatListGrupoMockMvc.perform(get("/api/chat-list-grupos?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(chatListGrupo.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getChatListGrupo() throws Exception {
        // Initialize the database
        chatListGrupoRepository.saveAndFlush(chatListGrupo);

        // Get the chatListGrupo
        restChatListGrupoMockMvc.perform(get("/api/chat-list-grupos/{id}", chatListGrupo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(chatListGrupo.getId().intValue()));
    }

    @Test
    @Transactional
    public void getAllChatListGruposByPropietarioIsEqualToSomething() throws Exception {
        // Initialize the database
        User propietario = UserResourceIT.createEntity(em);
        em.persist(propietario);
        em.flush();
        chatListGrupo.setPropietario(propietario);
        chatListGrupoRepository.saveAndFlush(chatListGrupo);
        Long propietarioId = propietario.getId();

        // Get all the chatListGrupoList where propietario equals to propietarioId
        defaultChatListGrupoShouldBeFound("propietarioId.equals=" + propietarioId);

        // Get all the chatListGrupoList where propietario equals to propietarioId + 1
        defaultChatListGrupoShouldNotBeFound("propietarioId.equals=" + (propietarioId + 1));
    }


    @Test
    @Transactional
    public void getAllChatListGruposByDestinatarioIsEqualToSomething() throws Exception {
        // Initialize the database
        User destinatario = UserResourceIT.createEntity(em);
        em.persist(destinatario);
        em.flush();
        chatListGrupo.setDestinatario(destinatario);
        chatListGrupoRepository.saveAndFlush(chatListGrupo);
        Long destinatarioId = destinatario.getId();

        // Get all the chatListGrupoList where destinatario equals to destinatarioId
        defaultChatListGrupoShouldBeFound("destinatarioId.equals=" + destinatarioId);

        // Get all the chatListGrupoList where destinatario equals to destinatarioId + 1
        defaultChatListGrupoShouldNotBeFound("destinatarioId.equals=" + (destinatarioId + 1));
    }


    @Test
    @Transactional
    public void getAllChatListGruposByChatGrupoIsEqualToSomething() throws Exception {
        // Initialize the database
        ChatGrupo chatGrupo = ChatGrupoResourceIT.createEntity(em);
        em.persist(chatGrupo);
        em.flush();
        chatListGrupo.addChatGrupo(chatGrupo);
        chatListGrupoRepository.saveAndFlush(chatListGrupo);
        Long chatGrupoId = chatGrupo.getId();

        // Get all the chatListGrupoList where chatGrupo equals to chatGrupoId
        defaultChatListGrupoShouldBeFound("chatGrupoId.equals=" + chatGrupoId);

        // Get all the chatListGrupoList where chatGrupo equals to chatGrupoId + 1
        defaultChatListGrupoShouldNotBeFound("chatGrupoId.equals=" + (chatGrupoId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultChatListGrupoShouldBeFound(String filter) throws Exception {
        restChatListGrupoMockMvc.perform(get("/api/chat-list-grupos?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(chatListGrupo.getId().intValue())));

        // Check, that the count call also returns 1
        restChatListGrupoMockMvc.perform(get("/api/chat-list-grupos/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultChatListGrupoShouldNotBeFound(String filter) throws Exception {
        restChatListGrupoMockMvc.perform(get("/api/chat-list-grupos?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restChatListGrupoMockMvc.perform(get("/api/chat-list-grupos/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingChatListGrupo() throws Exception {
        // Get the chatListGrupo
        restChatListGrupoMockMvc.perform(get("/api/chat-list-grupos/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateChatListGrupo() throws Exception {
        // Initialize the database
        chatListGrupoService.save(chatListGrupo);
        // As the test used the service layer, reset the Elasticsearch mock repository
        reset(mockChatListGrupoSearchRepository);

        int databaseSizeBeforeUpdate = chatListGrupoRepository.findAll().size();

        // Update the chatListGrupo
        ChatListGrupo updatedChatListGrupo = chatListGrupoRepository.findById(chatListGrupo.getId()).get();
        // Disconnect from session so that the updates on updatedChatListGrupo are not directly saved in db
        em.detach(updatedChatListGrupo);

        restChatListGrupoMockMvc.perform(put("/api/chat-list-grupos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedChatListGrupo)))
            .andExpect(status().isOk());

        // Validate the ChatListGrupo in the database
        List<ChatListGrupo> chatListGrupoList = chatListGrupoRepository.findAll();
        assertThat(chatListGrupoList).hasSize(databaseSizeBeforeUpdate);
        ChatListGrupo testChatListGrupo = chatListGrupoList.get(chatListGrupoList.size() - 1);

        // Validate the ChatListGrupo in Elasticsearch
        verify(mockChatListGrupoSearchRepository, times(1)).save(testChatListGrupo);
    }

    @Test
    @Transactional
    public void updateNonExistingChatListGrupo() throws Exception {
        int databaseSizeBeforeUpdate = chatListGrupoRepository.findAll().size();

        // Create the ChatListGrupo

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restChatListGrupoMockMvc.perform(put("/api/chat-list-grupos")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chatListGrupo)))
            .andExpect(status().isBadRequest());

        // Validate the ChatListGrupo in the database
        List<ChatListGrupo> chatListGrupoList = chatListGrupoRepository.findAll();
        assertThat(chatListGrupoList).hasSize(databaseSizeBeforeUpdate);

        // Validate the ChatListGrupo in Elasticsearch
        verify(mockChatListGrupoSearchRepository, times(0)).save(chatListGrupo);
    }

    @Test
    @Transactional
    public void deleteChatListGrupo() throws Exception {
        // Initialize the database
        chatListGrupoService.save(chatListGrupo);

        int databaseSizeBeforeDelete = chatListGrupoRepository.findAll().size();

        // Delete the chatListGrupo
        restChatListGrupoMockMvc.perform(delete("/api/chat-list-grupos/{id}", chatListGrupo.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ChatListGrupo> chatListGrupoList = chatListGrupoRepository.findAll();
        assertThat(chatListGrupoList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the ChatListGrupo in Elasticsearch
        verify(mockChatListGrupoSearchRepository, times(1)).deleteById(chatListGrupo.getId());
    }

    @Test
    @Transactional
    public void searchChatListGrupo() throws Exception {
        // Initialize the database
        chatListGrupoService.save(chatListGrupo);
        when(mockChatListGrupoSearchRepository.search(queryStringQuery("id:" + chatListGrupo.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(chatListGrupo), PageRequest.of(0, 1), 1));
        // Search the chatListGrupo
        restChatListGrupoMockMvc.perform(get("/api/_search/chat-list-grupos?query=id:" + chatListGrupo.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(chatListGrupo.getId().intValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ChatListGrupo.class);
        ChatListGrupo chatListGrupo1 = new ChatListGrupo();
        chatListGrupo1.setId(1L);
        ChatListGrupo chatListGrupo2 = new ChatListGrupo();
        chatListGrupo2.setId(chatListGrupo1.getId());
        assertThat(chatListGrupo1).isEqualTo(chatListGrupo2);
        chatListGrupo2.setId(2L);
        assertThat(chatListGrupo1).isNotEqualTo(chatListGrupo2);
        chatListGrupo1.setId(null);
        assertThat(chatListGrupo1).isNotEqualTo(chatListGrupo2);
    }
}
