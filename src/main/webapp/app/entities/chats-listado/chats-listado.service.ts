import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IChatsListado } from 'app/shared/model/chats-listado.model';

type EntityResponseType = HttpResponse<IChatsListado>;
type EntityArrayResponseType = HttpResponse<IChatsListado[]>;

@Injectable({ providedIn: 'root' })
export class ChatsListadoService {
  public resourceUrl = SERVER_API_URL + 'api/chats-listados';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/chats-listados';

  constructor(protected http: HttpClient) {}

  create(chatsListado: IChatsListado): Observable<EntityResponseType> {
    return this.http.post<IChatsListado>(this.resourceUrl, chatsListado, { observe: 'response' });
  }

  update(chatsListado: IChatsListado): Observable<EntityResponseType> {
    return this.http.put<IChatsListado>(this.resourceUrl, chatsListado, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IChatsListado>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IChatsListado[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IChatsListado[]>(this.resourceSearchUrl, { params: options, observe: 'response' });
  }
}
