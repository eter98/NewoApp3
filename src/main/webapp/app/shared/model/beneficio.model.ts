import { IUser } from 'app/core/user/user.model';

export const enum Beneficiosd {
  Market = 'Market',
  Entrada_Miembro = 'Entrada_Miembro',
  Espacios_Reserva = 'Espacios_Reserva',
  Invitados = 'Invitados',
  Landings = 'Landings'
}

export interface IBeneficio {
  id?: number;
  tipoBeneficio?: Beneficiosd;
  descuento?: number;
  miembro?: IUser;
}

export class Beneficio implements IBeneficio {
  constructor(public id?: number, public tipoBeneficio?: Beneficiosd, public descuento?: number, public miembro?: IUser) {}
}
