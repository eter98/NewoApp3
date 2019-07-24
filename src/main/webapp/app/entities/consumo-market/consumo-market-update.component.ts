import { Component, OnInit } from '@angular/core';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import * as moment from 'moment';
import { JhiAlertService } from 'ng-jhipster';
import { IConsumoMarket, ConsumoMarket } from 'app/shared/model/consumo-market.model';
import { ConsumoMarketService } from './consumo-market.service';
import { IUser, UserService } from 'app/core';

@Component({
  selector: 'jhi-consumo-market-update',
  templateUrl: './consumo-market-update.component.html'
})
export class ConsumoMarketUpdateComponent implements OnInit {
  isSaving: boolean;

  users: IUser[];
  registroFechaInicialDp: any;
  registroFechaFinalDp: any;

  editForm = this.fb.group({
    id: [],
    totalConsumido: [],
    registroFechaInicial: [],
    registroFechaFinal: [],
    impuesto: [],
    miembro: []
  });

  constructor(
    protected jhiAlertService: JhiAlertService,
    protected consumoMarketService: ConsumoMarketService,
    protected userService: UserService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit() {
    this.isSaving = false;
    this.activatedRoute.data.subscribe(({ consumoMarket }) => {
      this.updateForm(consumoMarket);
    });
    this.userService
      .query()
      .pipe(
        filter((mayBeOk: HttpResponse<IUser[]>) => mayBeOk.ok),
        map((response: HttpResponse<IUser[]>) => response.body)
      )
      .subscribe((res: IUser[]) => (this.users = res), (res: HttpErrorResponse) => this.onError(res.message));
  }

  updateForm(consumoMarket: IConsumoMarket) {
    this.editForm.patchValue({
      id: consumoMarket.id,
      totalConsumido: consumoMarket.totalConsumido,
      registroFechaInicial: consumoMarket.registroFechaInicial,
      registroFechaFinal: consumoMarket.registroFechaFinal,
      impuesto: consumoMarket.impuesto,
      miembro: consumoMarket.miembro
    });
  }

  previousState() {
    window.history.back();
  }

  save() {
    this.isSaving = true;
    const consumoMarket = this.createFromForm();
    if (consumoMarket.id !== undefined) {
      this.subscribeToSaveResponse(this.consumoMarketService.update(consumoMarket));
    } else {
      this.subscribeToSaveResponse(this.consumoMarketService.create(consumoMarket));
    }
  }

  private createFromForm(): IConsumoMarket {
    return {
      ...new ConsumoMarket(),
      id: this.editForm.get(['id']).value,
      totalConsumido: this.editForm.get(['totalConsumido']).value,
      registroFechaInicial: this.editForm.get(['registroFechaInicial']).value,
      registroFechaFinal: this.editForm.get(['registroFechaFinal']).value,
      impuesto: this.editForm.get(['impuesto']).value,
      miembro: this.editForm.get(['miembro']).value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IConsumoMarket>>) {
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
