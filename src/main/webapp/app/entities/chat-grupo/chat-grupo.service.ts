import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IChatGrupo } from 'app/shared/model/chat-grupo.model';

type EntityResponseType = HttpResponse<IChatGrupo>;
type EntityArrayResponseType = HttpResponse<IChatGrupo[]>;

@Injectable({ providedIn: 'root' })
export class ChatGrupoService {
  public resourceUrl = SERVER_API_URL + 'api/chat-grupos';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/chat-grupos';

  constructor(protected http: HttpClient) {}

  create(chatGrupo: IChatGrupo): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(chatGrupo);
    return this.http
      .post<IChatGrupo>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(chatGrupo: IChatGrupo): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(chatGrupo);
    return this.http
      .put<IChatGrupo>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IChatGrupo>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IChatGrupo[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<any>> {
    return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IChatGrupo[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  protected convertDateFromClient(chatGrupo: IChatGrupo): IChatGrupo {
    const copy: IChatGrupo = Object.assign({}, chatGrupo, {
      fecha: chatGrupo.fecha != null && chatGrupo.fecha.isValid() ? chatGrupo.fecha.toJSON() : null
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.fecha = res.body.fecha != null ? moment(res.body.fecha) : null;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((chatGrupo: IChatGrupo) => {
        chatGrupo.fecha = chatGrupo.fecha != null ? moment(chatGrupo.fecha) : null;
      });
    }
    return res;
  }
}
