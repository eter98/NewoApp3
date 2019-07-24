package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.ChatsListado;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the ChatsListado entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ChatsListadoRepository extends JpaRepository<ChatsListado, Long>, JpaSpecificationExecutor<ChatsListado> {

    @Query("select chatsListado from ChatsListado chatsListado where chatsListado.propietario.login = ?#{principal.username}")
    List<ChatsListado> findByPropietarioIsCurrentUser();

    @Query("select chatsListado from ChatsListado chatsListado where chatsListado.destinatario.login = ?#{principal.username}")
    List<ChatsListado> findByDestinatarioIsCurrentUser();

}
