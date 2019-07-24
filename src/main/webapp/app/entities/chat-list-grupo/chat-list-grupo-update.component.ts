import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IChatListGrupo, ChatListGrupo } from 'app/shared/model/chat-list-grupo.model';
import { ChatListGrupoService } from './chat-list-grupo.service';
import { IUser, UserService } from 'app/core';

@Component({
  selector: 'jhi-chat-list-grupo-update',
  templateUrl: './chat-list-grupo-update.component.html'
})
export class ChatListGrupoUpdateComponent implements OnInit {
  isSaving: boolean;

  users: IUser[];

  editForm = this.fb.group({
    id: [],
    propietario: [],
    destinatario: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected chatListGrupoService: ChatListGrupoService,
    protected userService: UserService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ chatListGrupo }) => {
      this.updateForm(chatListGrupo);
    });
    this.userService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IUser[]>) => mayBeOk.ok),
        map((response: HttpResponse<IUser[]>) => response.body)
      )
      .subscribe((res: IUser[]) => (this.users = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(chatListGrupo: IChatListGrupo) {
    this.editForm.patchValue({
      id: chatListGrupo.id,
      propietario: chatListGrupo.propietario,
      destinatario: chatListGrupo.destinatario
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const chatListGrupo = this.createFromForm();
    if (chatListGrupo.id !== undefined) {
      this.subscribeToSaveResponse(this.chatListGrupoService.update(chatListGrupo));
    } else {
      this.subscribeToSaveResponse(this.chatListGrupoService.create(chatListGrupo));
    }
  }

  private createFromForm(): IChatListGrupo {
    return {
      ...new ChatListGrupo(),
      id: this.editForm.get(['id']).value,
      propietario: this.editForm.get(['propietario']).value,
      destinatario: this.editForm.get(['destinatario']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IChatListGrupo>>) {
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
}
