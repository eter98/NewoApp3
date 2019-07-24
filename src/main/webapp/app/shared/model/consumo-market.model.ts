import { Moment } from 'moment';
import { IUser } from 'app/core/user/user.model';

export const enum Impuestod {
  IVA19 = 'IVA19',
  IVA6 = 'IVA6',
  IVA0 = 'IVA0',
  ICO = 'ICO',
  IPOCONSUMO8 = 'IPOCONSUMO8'
}

export interface IConsumoMarket {
  id?: number;
  totalConsumido?: number;
  registroFechaInicial?: Moment;
  registroFechaFinal?: Moment;
  impuesto?: Impuestod;
  miembro?: IUser;
}

export class ConsumoMarket implements IConsumoMarket {
  constructor(
    public id?: number,
    public totalConsumido?: number,
    public registroFechaInicial?: Moment,
    public registroFechaFinal?: Moment,
    public impuesto?: Impuestod,
    public miembro?: IUser
  ) {}
}
