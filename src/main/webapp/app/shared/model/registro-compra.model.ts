import { IUser } from 'app/core/user/user.model';
import { IFacturacion } from 'app/shared/model/facturacion.model';
import { IReservas } from 'app/shared/model/reservas.model';
import { IEntradaInvitados } from 'app/shared/model/entrada-invitados.model';
import { IEntradaMiembros } from 'app/shared/model/entrada-miembros.model';

export interface IRegistroCompra {
  id?: number;
  consumoMarket?: boolean;
  miembro?: IUser;
  facturacion?: IFacturacion;
  reservas?: IReservas;
  entradaInvitados?: IEntradaInvitados;
  entradaMiembros?: IEntradaMiembros;
}

export class RegistroCompra implements IRegistroCompra {
  constructor(
    public id?: number,
    public consumoMarket?: boolean,
    public miembro?: IUser,
    public facturacion?: IFacturacion,
    public reservas?: IReservas,
    public entradaInvitados?: IEntradaInvitados,
    public entradaMiembros?: IEntradaMiembros
  ) {
    this.consumoMarket = this.consumoMarket || false;
  }
}