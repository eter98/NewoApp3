import { ISedes } from 'app/shared/model/sedes.model';
import { IUser } from 'app/core/user/user.model';

export interface IHostSede {
  id?: number;
  sede?: ISedes;
  miembro?: IUser;
}

export class HostSede implements IHostSede {
  constructor(public id?: number, public sede?: ISedes, public miembro?: IUser) {}
}
