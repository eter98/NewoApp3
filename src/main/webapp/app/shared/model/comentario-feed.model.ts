import { Moment } from 'moment';
import { IFeed } from 'app/shared/model/feed.model';
import { IUser } from 'app/core/user/user.model';

export interface IComentarioFeed {
  id?: number;
  comentario?: any;
  fecha?: Moment;
  meGusta?: boolean;
  seguir?: boolean;
  feed?: IFeed;
  miembro?: IUser;
}

export class ComentarioFeed implements IComentarioFeed {
  constructor(
    public id?: number,
    public comentario?: any,
    public fecha?: Moment,
    public meGusta?: boolean,
    public seguir?: boolean,
    public feed?: IFeed,
    public miembro?: IUser
  ) {
    this.meGusta = this.meGusta || false;
    this.seguir = this.seguir || false;
  }
}
