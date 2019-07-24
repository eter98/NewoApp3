import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { ChatGrupo } from 'app/shared/model/chat-grupo.model';
import { ChatGrupoService } from './chat-grupo.service';
import { ChatGrupoComponent } from './chat-grupo.component';
import { ChatGrupoDetailComponent } from './chat-grupo-detail.component';
import { ChatGrupoUpdateComponent } from './chat-grupo-update.component';
import { ChatGrupoDeletePopupComponent } from './chat-grupo-delete-dialog.component';
import { IChatGrupo } from 'app/shared/model/chat-grupo.model';

@Injectable({ providedIn: 'root' })
export class ChatGrupoResolve implements Resolve<IChatGrupo> {
  constructor(private service: ChatGrupoService) {}

  resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<IChatGrupo> {
    const id = route.params['id'] ? route.params['id'] : null;
    if (id) {
      return this.service.find(id).pipe(
        filter((response: HttpResponse<ChatGrupo>) => response.ok),
        map((chatGrupo: HttpResponse<ChatGrupo>) => chatGrupo.body)
      );
    }
    return of(new ChatGrupo());
  }
}

export const chatGrupoRoute: Routes = [
  {
    path: '',
    component: ChatGrupoComponent,
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp3App.chatGrupo.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ChatGrupoDetailComponent,
    resolve: {
      chatGrupo: ChatGrupoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp3App.chatGrupo.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ChatGrupoUpdateComponent,
    resolve: {
      chatGrupo: ChatGrupoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp3App.chatGrupo.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ChatGrupoUpdateComponent,
    resolve: {
      chatGrupo: ChatGrupoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp3App.chatGrupo.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];

export const chatGrupoPopupRoute: Routes = [
  {
    path: ':id/delete',
    component: ChatGrupoDeletePopupComponent,
    resolve: {
      chatGrupo: ChatGrupoResolve
    },
    data: {
      authorities: ['ROLE_USER'],
      pageTitle: 'newoApp3App.chatGrupo.home.title'
    },
    canActivate: [UserRouteAccessService],
    outlet: 'popup'
  }
];
