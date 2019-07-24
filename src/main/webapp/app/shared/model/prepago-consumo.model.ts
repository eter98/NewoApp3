import { Moment } from 'moment';
import { IUser } from 'app/core/user/user.model';
import { ITipoPrepagoConsumo } from 'app/shared/model/tipo-prepago-consumo.model';

export interface IPrepagoConsumo {
  id?: number;
  aporte?: number;
  saldoActual?: number;
  fechaRegistro?: Moment;
  fechaSaldoActual?: Moment;
  miembro?: IUser;
  tipoPrepago?: ITipoPrepagoConsumo;
}

export class PrepagoConsumo implements IPrepagoConsumo {
  constructor(
    public id?: number,
    public aporte?: number,
    public saldoActual?: number,
    public fechaRegistro?: Moment,
    public fechaSaldoActual?: Moment,
    public miembro?: IUser,
    public tipoPrepago?: ITipoPrepagoConsumo
  ) {}
}
