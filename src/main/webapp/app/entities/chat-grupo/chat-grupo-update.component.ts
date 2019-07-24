import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { JhiAlertService } from 'ng-jhipster';
import { IChatGrupo, ChatGrupo } from 'app/shared/model/chat-grupo.model';
import { ChatGrupoService } from './chat-grupo.service';
import { IUser, UserService } from 'app/core';
import { IChatListGrupo } from 'app/shared/model/chat-list-grupo.model';
import { ChatListGrupoService } from 'app/entities/chat-list-grupo';
import { IGrupos } from 'app/shared/model/grupos.model';
import { GruposService } from 'app/entities/grupos';

@Component({
  selector: 'jhi-chat-grupo-update',
  templateUrl: './chat-grupo-update.component.html'
})
export class ChatGrupoUpdateComponent implements OnInit {
  isSaving: boolean;

  users: IUser[];

  chatlistgrupos: IChatListGrupo[];

  grupos: IGrupos[];

  editForm = this.fb.group({
    id: [],
    mensaje: [null, [Validators.required]],
    fecha: [],
    leido: [],
    de: [],
    para: [],
    chatListGrupo: [],
    grupo: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected chatGrupoService: ChatGrupoService,
    protected userService: UserService,
    protected chatListGrupoService: ChatListGrupoService,
    protected gruposService: GruposService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ chatGrupo }) => {
      this.updateForm(chatGrupo);
    });
    this.userService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IUser[]>) => mayBeOk.ok),
        map((response: HttpResponse<IUser[]>) => response.body)
      )
      .subscribe((res: IUser[]) => (this.users = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.chatListGrupoService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IChatListGrupo[]>) => mayBeOk.ok),
        map((response: HttpResponse<IChatListGrupo[]>) => response.body)
      )
      .subscribe((res: IChatListGrupo[]) => (this.chatlistgrupos = res), (res: HttpErrorResponse) => this.onError(res.message));
    this.gruposService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IGrupos[]>) => mayBeOk.ok),
        map((response: HttpResponse<IGrupos[]>) => response.body)
      )
      .subscribe((res: IGrupos[]) => (this.grupos = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(chatGrupo: IChatGrupo) {
    this.editForm.patchValue({
      id: chatGrupo.id,
      mensaje: chatGrupo.mensaje,
      fecha: chatGrupo.fecha != null ? chatGrupo.fecha.format(DATE_TIME_FORMAT) : null,
      leido: chatGrupo.leido,
      de: chatGrupo.de,
      para: chatGrupo.para,
      chatListGrupo: chatGrupo.chatListGrupo,
      grupo: chatGrupo.grupo
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const chatGrupo = this.createFromForm();
    if (chatGrupo.id !== undefined) {
      this.subscribeToSaveResponse(this.chatGrupoService.update(chatGrupo));
    } else {
      this.subscribeToSaveResponse(this.chatGrupoService.create(chatGrupo));
    }
  }

  private createFromForm(): IChatGrupo {
    return {
      ...new ChatGrupo(),
      id: this.editForm.get(['id']).value,
      mensaje: this.editForm.get(['mensaje']).value,
      fecha: this.editForm.get(['fecha']).value != null ? moment(this.editForm.get(['fecha']).value, DATE_TIME_FORMAT) : undefined,
      leido: this.editForm.get(['leido']).value,
      de: this.editForm.get(['de']).value,
      para: this.editForm.get(['para']).value,
      chatListGrupo: this.editForm.get(['chatListGrupo']).value,
      grupo: this.editForm.get(['grupo']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IChatGrupo>>) {
    result.subscribe(() => this.onSaveSuccess(), () => this.onSaveError());
  }

  protected onSaveSuccess() {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError() {
    this.isSaving = false;
  }
  protected onError(errorMessage: string) {
    this.jhiAlertService.error(errorMessage, null, null);
  }

  trackUserById(index: number, item: IUser) {
    return item.id;
  }

  trackChatListGrupoById(index: number, item: IChatListGrupo) {
    return item.id;
  }

  trackGruposById(index: number, item: IGrupos) {
    return item.id;
  }
}
