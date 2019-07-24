import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IChatsListado, ChatsListado } from 'app/shared/model/chats-listado.model';
import { ChatsListadoService } from './chats-listado.service';
import { IUser, UserService } from 'app/core';

@Component({
  selector: 'jhi-chats-listado-update',
  templateUrl: './chats-listado-update.component.html'
})
export class ChatsListadoUpdateComponent implements OnInit {
  isSaving: boolean;

  users: IUser[];

  editForm = this.fb.group({
    id: [],
    propietario: [],
    destinatario: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected chatsListadoService: ChatsListadoService,
    protected userService: UserService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ chatsListado }) => {
      this.updateForm(chatsListado);
    });
    this.userService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IUser[]>) => mayBeOk.ok),
        map((response: HttpResponse<IUser[]>) => response.body)
      )
      .subscribe((res: IUser[]) => (this.users = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(chatsListado: IChatsListado) {
    this.editForm.patchValue({
      id: chatsListado.id,
      propietario: chatsListado.propietario,
      destinatario: chatsListado.destinatario
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const chatsListado = this.createFromForm();
    if (chatsListado.id !== undefined) {
      this.subscribeToSaveResponse(this.chatsListadoService.update(chatsListado));
    } else {
      this.subscribeToSaveResponse(this.chatsListadoService.create(chatsListado));
    }
  }

  private createFromForm(): IChatsListado {
    return {
      ...new ChatsListado(),
      id: this.editForm.get(['id']).value,
      propietario: this.editForm.get(['propietario']).value,
      destinatario: this.editForm.get(['destinatario']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IChatsListado>>) {
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
