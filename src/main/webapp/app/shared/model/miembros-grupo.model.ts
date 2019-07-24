import { IUser } from 'app/core/user/user.model';

export interface IMiembrosGrupo {
  id?: number;
  miembro?: IUser;
}

export class MiembrosGrupo implements IMiembrosGrupo {
  constructor(public id?: number, public miembro?: IUser) {}
}
