import { Moment } from 'moment';
import { IRegistroCompra } from 'app/shared/model/registro-compra.model';
import { IUser } from 'app/core/user/user.model';
import { IEspaciosReserva } from 'app/shared/model/espacios-reserva.model';

export const enum EstadoReservad {
  Disponible = 'Disponible',
  Reservado = 'Reservado',
  Ocupado = 'Ocupado',
  Extendido = 'Extendido',
  Cerrado = 'Cerrado'
}

export interface IReservas {
  id?: number;
  registroFechaEntrada?: Moment;
  registroFechaSalida?: Moment;
  estadoReserva?: EstadoReservad;
  registroCompras?: IRegistroCompra[];
  miembro?: IUser;
  espacio?: IEspaciosReserva;
}

export class Reservas implements IReservas {
  constructor(
    public id?: number,
    public registroFechaEntrada?: Moment,
    public registroFechaSalida?: Moment,
    public estadoReserva?: EstadoReservad,
    public registroCompras?: IRegistroCompra[],
    public miembro?: IUser,
    public espacio?: IEspaciosReserva
  ) {}
}
