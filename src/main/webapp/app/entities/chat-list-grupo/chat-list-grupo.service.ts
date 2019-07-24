import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IChatListGrupo } from 'app/shared/model/chat-list-grupo.model';

type EntityResponseType = HttpResponse<IChatListGrupo>;
type EntityArrayResponseType = HttpResponse<IChatListGrupo[]>;

@Injectable({ providedIn: 'root' })
export class ChatListGrupoService {
  public resourceUrl = SERVER_API_URL + 'api/chat-list-grupos';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/chat-list-grupos';

  constructor(protected http: HttpClient) {}

  create(chatListGrupo: IChatListGrupo): Observable<EntityResponseType> {
    return this.http.post<IChatListGrupo>(this.resourceUrl, chatListGrupo, { observe: 'response' });
  }

  update(chatListGrupo: IChatListGrupo): Observable<EntityResponseType> {
    return this.http.put<IChatListGrupo>(this.resourceUrl, chatListGrupo, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IChatListGrupo>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IChatListGrupo[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IChatListGrupo[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
