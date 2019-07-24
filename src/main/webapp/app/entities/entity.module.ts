import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'miembros',
        loadChildren: './miembros/miembros.module#NewoApp3MiembrosModule'
      },
      {
        path: 'entrada-miembros',
        loadChildren: './entrada-miembros/entrada-miembros.module#NewoApp3EntradaMiembrosModule'
      },
      {
        path: 'invitados',
        loadChildren: './invitados/invitados.module#NewoApp3InvitadosModule'
      },
      {
        path: 'entrada-invitados',
        loadChildren: './entrada-invitados/entrada-invitados.module#NewoApp3EntradaInvitadosModule'
      },
      {
        path: 'sedes',
        loadChildren: './sedes/sedes.module#NewoApp3SedesModule'
      },
      {
        path: 'espacio-libre',
        loadChildren: './espacio-libre/espacio-libre.module#NewoApp3EspacioLibreModule'
      },
      {
        path: 'tipo-espacio',
        loadChildren: './tipo-espacio/tipo-espacio.module#NewoApp3TipoEspacioModule'
      },
      {
        path: 'host-sede',
        loadChildren: './host-sede/host-sede.module#NewoApp3HostSedeModule'
      },
      {
        path: 'reservas',
        loadChildren: './reservas/reservas.module#NewoApp3ReservasModule'
      },
      {
        path: 'espacios-reserva',
        loadChildren: './espacios-reserva/espacios-reserva.module#NewoApp3EspaciosReservaModule'
      },
      {
        path: 'registro-compra',
        loadChildren: './registro-compra/registro-compra.module#NewoApp3RegistroCompraModule'
      },
      {
        path: 'facturacion',
        loadChildren: './facturacion/facturacion.module#NewoApp3FacturacionModule'
      },
      {
        path: 'equipo-empresas',
        loadChildren: './equipo-empresas/equipo-empresas.module#NewoApp3EquipoEmpresasModule'
      },
      {
        path: 'miembros-equipo-empresas',
        loadChildren: './miembros-equipo-empresas/miembros-equipo-empresas.module#NewoApp3MiembrosEquipoEmpresasModule'
      },
      {
        path: 'cuenta-asociada',
        loadChildren: './cuenta-asociada/cuenta-asociada.module#NewoApp3CuentaAsociadaModule'
      },
      {
        path: 'beneficio',
        loadChildren: './beneficio/beneficio.module#NewoApp3BeneficioModule'
      },
      {
        path: 'perfil-equipo-empresa',
        loadChildren: './perfil-equipo-empresa/perfil-equipo-empresa.module#NewoApp3PerfilEquipoEmpresaModule'
      },
      {
        path: 'empresa',
        loadChildren: './empresa/empresa.module#NewoApp3EmpresaModule'
      },
      {
        path: 'landing',
        loadChildren: './landing/landing.module#NewoApp3LandingModule'
      },
      {
        path: 'productos-servicios',
        loadChildren: './productos-servicios/productos-servicios.module#NewoApp3ProductosServiciosModule'
      },
      {
        path: 'pais',
        loadChildren: './pais/pais.module#NewoApp3PaisModule'
      },
      {
        path: 'ciudad',
        loadChildren: './ciudad/ciudad.module#NewoApp3CiudadModule'
      },
      {
        path: 'blog',
        loadChildren: './blog/blog.module#NewoApp3BlogModule'
      },
      {
        path: 'comentario-blog',
        loadChildren: './comentario-blog/comentario-blog.module#NewoApp3ComentarioBlogModule'
      },
      {
        path: 'feed',
        loadChildren: './feed/feed.module#NewoApp3FeedModule'
      },
      {
        path: 'comentario-feed',
        loadChildren: './comentario-feed/comentario-feed.module#NewoApp3ComentarioFeedModule'
      },
      {
        path: 'chat',
        loadChildren: './chat/chat.module#NewoApp3ChatModule'
      },
      {
        path: 'chat-grupo',
        loadChildren: './chat-grupo/chat-grupo.module#NewoApp3ChatGrupoModule'
      },
      {
        path: 'chats-listado',
        loadChildren: './chats-listado/chats-listado.module#NewoApp3ChatsListadoModule'
      },
      {
        path: 'chat-list-grupo',
        loadChildren: './chat-list-grupo/chat-list-grupo.module#NewoApp3ChatListGrupoModule'
      },
      {
        path: 'evento',
        loadChildren: './evento/evento.module#NewoApp3EventoModule'
      },
      {
        path: 'categoria-contenidos',
        loadChildren: './categoria-contenidos/categoria-contenidos.module#NewoApp3CategoriaContenidosModule'
      },
      {
        path: 'grupos',
        loadChildren: './grupos/grupos.module#NewoApp3GruposModule'
      },
      {
        path: 'miembros-grupo',
        loadChildren: './miembros-grupo/miembros-grupo.module#NewoApp3MiembrosGrupoModule'
      },
      {
        path: 'recursos-fisicos',
        loadChildren: './recursos-fisicos/recursos-fisicos.module#NewoApp3RecursosFisicosModule'
      },
      {
        path: 'uso-recurso-fisico',
        loadChildren: './uso-recurso-fisico/uso-recurso-fisico.module#NewoApp3UsoRecursoFisicoModule'
      },
      {
        path: 'tipo-recurso',
        loadChildren: './tipo-recurso/tipo-recurso.module#NewoApp3TipoRecursoModule'
      },
      {
        path: 'consumo-market',
        loadChildren: './consumo-market/consumo-market.module#NewoApp3ConsumoMarketModule'
      },
      {
        path: 'prepago-consumo',
        loadChildren: './prepago-consumo/prepago-consumo.module#NewoApp3PrepagoConsumoModule'
      },
      {
        path: 'margen-newo-eventos',
        loadChildren: './margen-newo-eventos/margen-newo-eventos.module#NewoApp3MargenNewoEventosModule'
      },
      {
        path: 'margen-newo-grupos',
        loadChildren: './margen-newo-grupos/margen-newo-grupos.module#NewoApp3MargenNewoGruposModule'
      },
      {
        path: 'margen-newo-blog',
        loadChildren: './margen-newo-blog/margen-newo-blog.module#NewoApp3MargenNewoBlogModule'
      },
      {
        path: 'margen-newo-productos',
        loadChildren: './margen-newo-productos/margen-newo-productos.module#NewoApp3MargenNewoProductosModule'
      },
      {
        path: 'tipo-prepago-consumo',
        loadChildren: './tipo-prepago-consumo/tipo-prepago-consumo.module#NewoApp3TipoPrepagoConsumoModule'
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ],
  declarations: [],
  entryComponents: [],
  providers: [],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NewoApp3EntityModule {}
