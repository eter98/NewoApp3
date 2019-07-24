import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ChatListGrupo } from 'app/shared/model/chat-list-grupo.model';
import { ChatListGrupoService } from './chat-list-grupo.service';
import { ChatListGrupoComponent } from './chat-list-grupo.component';
import { ChatListGrupoDetailComponent } from './chat-list-grupo-detail.component';
import { ChatListGrupoUpdateComponent } from './chat-list-grupo-update.component';
import { ChatListGrupoDeletePopupComponent } from './chat-list-grupo-delete-dialog.component';
import { IChatListGrupo } from 'app/shared/model/chat-list-grupo.model';

@Injectable({ providedIn: 'root' })
export class ChatListGrupoResolve implements Resolve<IChatListGrupo> {
  constructor(private service: ChatListGrupoService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IChatListGrupo> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<ChatListGrupo>) => response.ok),
        map((chatListGrupo: HttpResponse<ChatListGrupo>) => chatListGrupo.body)
      );
    }
    return of(new ChatListGrupo());
  }
}

export const chatListGrupoRoute: Routes = [
  {
    path: '',
    component: ChatListGrupoComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp3App.chatListGrupo.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ChatListGrupoDetailComponent,
    resolve: {
      chatListGrupo: ChatListGrupoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp3App.chatListGrupo.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ChatListGrupoUpdateComponent,
    resolve: {
      chatListGrupo: ChatListGrupoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp3App.chatListGrupo.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ChatListGrupoUpdateComponent,
    resolve: {
      chatListGrupo: ChatListGrupoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp3App.chatListGrupo.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const chatListGrupoPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ChatListGrupoDeletePopupComponent,
    resolve: {
      chatListGrupo: ChatListGrupoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp3App.chatListGrupo.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
