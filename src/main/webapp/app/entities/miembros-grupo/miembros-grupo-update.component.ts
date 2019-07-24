import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { JhiAlertService } from 'ng-jhipster';
import { IMiembrosGrupo, MiembrosGrupo } from 'app/shared/model/miembros-grupo.model';
import { MiembrosGrupoService } from './miembros-grupo.service';
import { IUser, UserService } from 'app/core';

@Component({
  selector: 'jhi-miembros-grupo-update',
  templateUrl: './miembros-grupo-update.component.html'
})
export class MiembrosGrupoUpdateComponent implements OnInit {
  isSaving: boolean;

  users: IUser[];

  editForm = this.fb.group({
    id: [],
    miembro: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected miembrosGrupoService: MiembrosGrupoService,
    protected userService: UserService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ miembrosGrupo }) => {
      this.updateForm(miembrosGrupo);
    });
    this.userService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IUser[]>) => mayBeOk.ok),
        map((response: HttpResponse<IUser[]>) => response.body)
      )
      .subscribe((res: IUser[]) => (this.users = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(miembrosGrupo: IMiembrosGrupo) {
    this.editForm.patchValue({
      id: miembrosGrupo.id,
      miembro: miembrosGrupo.miembro
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const miembrosGrupo = this.createFromForm();
    if (miembrosGrupo.id !== undefined) {
      this.subscribeToSaveResponse(this.miembrosGrupoService.update(miembrosGrupo));
    } else {
      this.subscribeToSaveResponse(this.miembrosGrupoService.create(miembrosGrupo));
    }
  }

  private createFromForm(): IMiembrosGrupo {
    return {
      ...new MiembrosGrupo(),
      id: this.editForm.get(['id']).value,
      miembro: this.editForm.get(['miembro']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMiembrosGrupo>>) {
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
