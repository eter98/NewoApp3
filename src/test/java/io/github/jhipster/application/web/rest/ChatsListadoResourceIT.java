package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.NewoApp3App;
import io.github.jhipster.application.domain.ChatsListado;
import io.github.jhipster.application.domain.User;
import io.github.jhipster.application.domain.Chat;
import io.github.jhipster.application.repository.ChatsListadoRepository;
import io.github.jhipster.application.repository.search.ChatsListadoSearchRepository;
import io.github.jhipster.application.service.ChatsListadoService;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;
import io.github.jhipster.application.service.dto.ChatsListadoCriteria;
import io.github.jhipster.application.service.ChatsListadoQueryService;

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
 * Integration tests for the {@Link ChatsListadoResource} REST controller.
 */
@SpringBootTest(classes = NewoApp3App.class)
public class ChatsListadoResourceIT {

    @Autowired
    private ChatsListadoRepository chatsListadoRepository;

    @Autowired
    private ChatsListadoService chatsListadoService;

    /**
     * This repository is mocked in the io.github.jhipster.application.repository.search test package.
     *
     * @see io.github.jhipster.application.repository.search.ChatsListadoSearchRepositoryMockConfiguration
     */
    @Autowired
    private ChatsListadoSearchRepository mockChatsListadoSearchRepository;

    @Autowired
    private ChatsListadoQueryService chatsListadoQueryService;

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

    private MockMvc restChatsListadoMockMvc;

    private ChatsListado chatsListado;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final ChatsListadoResource chatsListadoResource = new ChatsListadoResource(chatsListadoService, chatsListadoQueryService);
        this.restChatsListadoMockMvc = MockMvcBuilders.standaloneSetup(chatsListadoResource)
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
    public static ChatsListado createEntity(EntityManager em) {
        ChatsListado chatsListado = new ChatsListado();
        return chatsListado;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ChatsListado createUpdatedEntity(EntityManager em) {
        ChatsListado chatsListado = new ChatsListado();
        return chatsListado;
    }

    @BeforeEach
    public void initTest() {
        chatsListado = createEntity(em);
    }

    @Test
    @Transactional
    public void createChatsListado() throws Exception {
        int databaseSizeBeforeCreate = chatsListadoRepository.findAll().size();

        // Create the ChatsListado
        restChatsListadoMockMvc.perform(post("/api/chats-listados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chatsListado)))
            .andExpect(status().isCreated());

        // Validate the ChatsListado in the database
        List<ChatsListado> chatsListadoList = chatsListadoRepository.findAll();
        assertThat(chatsListadoList).hasSize(databaseSizeBeforeCreate + 1);
        ChatsListado testChatsListado = chatsListadoList.get(chatsListadoList.size() - 1);

        // Validate the ChatsListado in Elasticsearch
        verify(mockChatsListadoSearchRepository, times(1)).save(testChatsListado);
    }

    @Test
    @Transactional
    public void createChatsListadoWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = chatsListadoRepository.findAll().size();

        // Create the ChatsListado with an existing ID
        chatsListado.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restChatsListadoMockMvc.perform(post("/api/chats-listados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chatsListado)))
            .andExpect(status().isBadRequest());

        // Validate the ChatsListado in the database
        List<ChatsListado> chatsListadoList = chatsListadoRepository.findAll();
        assertThat(chatsListadoList).hasSize(databaseSizeBeforeCreate);

        // Validate the ChatsListado in Elasticsearch
        verify(mockChatsListadoSearchRepository, times(0)).save(chatsListado);
    }


    @Test
    @Transactional
    public void getAllChatsListados() throws Exception {
        // Initialize the database
        chatsListadoRepository.saveAndFlush(chatsListado);

        // Get all the chatsListadoList
        restChatsListadoMockMvc.perform(get("/api/chats-listados?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(chatsListado.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getChatsListado() throws Exception {
        // Initialize the database
        chatsListadoRepository.saveAndFlush(chatsListado);

        // Get the chatsListado
        restChatsListadoMockMvc.perform(get("/api/chats-listados/{id}", chatsListado.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(chatsListado.getId().intValue()));
    }

    @Test
    @Transactional
    public void getAllChatsListadosByPropietarioIsEqualToSomething() throws Exception {
        // Initialize the database
        User propietario = UserResourceIT.createEntity(em);
        em.persist(propietario);
        em.flush();
        chatsListado.setPropietario(propietario);
        chatsListadoRepository.saveAndFlush(chatsListado);
        Long propietarioId = propietario.getId();

        // Get all the chatsListadoList where propietario equals to propietarioId
        defaultChatsListadoShouldBeFound("propietarioId.equals=" + propietarioId);

        // Get all the chatsListadoList where propietario equals to propietarioId + 1
        defaultChatsListadoShouldNotBeFound("propietarioId.equals=" + (propietarioId + 1));
    }


    @Test
    @Transactional
    public void getAllChatsListadosByDestinatarioIsEqualToSomething() throws Exception {
        // Initialize the database
        User destinatario = UserResourceIT.createEntity(em);
        em.persist(destinatario);
        em.flush();
        chatsListado.setDestinatario(destinatario);
        chatsListadoRepository.saveAndFlush(chatsListado);
        Long destinatarioId = destinatario.getId();

        // Get all the chatsListadoList where destinatario equals to destinatarioId
        defaultChatsListadoShouldBeFound("destinatarioId.equals=" + destinatarioId);

        // Get all the chatsListadoList where destinatario equals to destinatarioId + 1
        defaultChatsListadoShouldNotBeFound("destinatarioId.equals=" + (destinatarioId + 1));
    }


    @Test
    @Transactional
    public void getAllChatsListadosByChatIsEqualToSomething() throws Exception {
        // Initialize the database
        Chat chat = ChatResourceIT.createEntity(em);
        em.persist(chat);
        em.flush();
        chatsListado.addChat(chat);
        chatsListadoRepository.saveAndFlush(chatsListado);
        Long chatId = chat.getId();

        // Get all the chatsListadoList where chat equals to chatId
        defaultChatsListadoShouldBeFound("chatId.equals=" + chatId);

        // Get all the chatsListadoList where chat equals to chatId + 1
        defaultChatsListadoShouldNotBeFound("chatId.equals=" + (chatId + 1));
    }

    /**
     * Executes the search, and checks that the default entity is returned.
     */
    private void defaultChatsListadoShouldBeFound(String filter) throws Exception {
        restChatsListadoMockMvc.perform(get("/api/chats-listados?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(chatsListado.getId().intValue())));

        // Check, that the count call also returns 1
        restChatsListadoMockMvc.perform(get("/api/chats-listados/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("1"));
    }

    /**
     * Executes the search, and checks that the default entity is not returned.
     */
    private void defaultChatsListadoShouldNotBeFound(String filter) throws Exception {
        restChatsListadoMockMvc.perform(get("/api/chats-listados?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$").isArray())
            .andExpect(jsonPath("$").isEmpty());

        // Check, that the count call also returns 0
        restChatsListadoMockMvc.perform(get("/api/chats-listados/count?sort=id,desc&" + filter))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(content().string("0"));
    }


    @Test
    @Transactional
    public void getNonExistingChatsListado() throws Exception {
        // Get the chatsListado
        restChatsListadoMockMvc.perform(get("/api/chats-listados/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateChatsListado() throws Exception {
        // Initialize the database
        chatsListadoService.save(chatsListado);
        // As the test used the service layer, reset the Elasticsearch mock repository
        reset(mockChatsListadoSearchRepository);

        int databaseSizeBeforeUpdate = chatsListadoRepository.findAll().size();

        // Update the chatsListado
        ChatsListado updatedChatsListado = chatsListadoRepository.findById(chatsListado.getId()).get();
        // Disconnect from session so that the updates on updatedChatsListado are not directly saved in db
        em.detach(updatedChatsListado);

        restChatsListadoMockMvc.perform(put("/api/chats-listados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedChatsListado)))
            .andExpect(status().isOk());

        // Validate the ChatsListado in the database
        List<ChatsListado> chatsListadoList = chatsListadoRepository.findAll();
        assertThat(chatsListadoList).hasSize(databaseSizeBeforeUpdate);
        ChatsListado testChatsListado = chatsListadoList.get(chatsListadoList.size() - 1);

        // Validate the ChatsListado in Elasticsearch
        verify(mockChatsListadoSearchRepository, times(1)).save(testChatsListado);
    }

    @Test
    @Transactional
    public void updateNonExistingChatsListado() throws Exception {
        int databaseSizeBeforeUpdate = chatsListadoRepository.findAll().size();

        // Create the ChatsListado

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restChatsListadoMockMvc.perform(put("/api/chats-listados")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(chatsListado)))
            .andExpect(status().isBadRequest());

        // Validate the ChatsListado in the database
        List<ChatsListado> chatsListadoList = chatsListadoRepository.findAll();
        assertThat(chatsListadoList).hasSize(databaseSizeBeforeUpdate);

        // Validate the ChatsListado in Elasticsearch
        verify(mockChatsListadoSearchRepository, times(0)).save(chatsListado);
    }

    @Test
    @Transactional
    public void deleteChatsListado() throws Exception {
        // Initialize the database
        chatsListadoService.save(chatsListado);

        int databaseSizeBeforeDelete = chatsListadoRepository.findAll().size();

        // Delete the chatsListado
        restChatsListadoMockMvc.perform(delete("/api/chats-listados/{id}", chatsListado.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ChatsListado> chatsListadoList = chatsListadoRepository.findAll();
        assertThat(chatsListadoList).hasSize(databaseSizeBeforeDelete - 1);

        // Validate the ChatsListado in Elasticsearch
        verify(mockChatsListadoSearchRepository, times(1)).deleteById(chatsListado.getId());
    }

    @Test
    @Transactional
    public void searchChatsListado() throws Exception {
        // Initialize the database
        chatsListadoService.save(chatsListado);
        when(mockChatsListadoSearchRepository.search(queryStringQuery("id:" + chatsListado.getId()), PageRequest.of(0, 20)))
            .thenReturn(new PageImpl<>(Collections.singletonList(chatsListado), PageRequest.of(0, 1), 1));
        // Search the chatsListado
        restChatsListadoMockMvc.perform(get("/api/_search/chats-listados?query=id:" + chatsListado.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(chatsListado.getId().intValue())));
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ChatsListado.class);
        ChatsListado chatsListado1 = new ChatsListado();
        chatsListado1.setId(1L);
        ChatsListado chatsListado2 = new ChatsListado();
        chatsListado2.setId(chatsListado1.getId());
        assertThat(chatsListado1).isEqualTo(chatsListado2);
        chatsListado2.setId(2L);
        assertThat(chatsListado1).isNotEqualTo(chatsListado2);
        chatsListado1.setId(null);
        assertThat(chatsListado1).isNotEqualTo(chatsListado2);
    }
}
