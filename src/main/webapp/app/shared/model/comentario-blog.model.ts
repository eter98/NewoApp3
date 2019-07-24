import { Moment } from 'moment';
import { IBlog } from 'app/shared/model/blog.model';
import { IUser } from 'app/core/user/user.model';

export interface IComentarioBlog {
  id?: number;
  comentario?: any;
  fecha?: Moment;
  meGusta?: boolean;
  seguir?: boolean;
  blog?: IBlog;
  miembro?: IUser;
}

export class ComentarioBlog implements IComentarioBlog {
  constructor(
    public id?: number,
    public comentario?: any,
    public fecha?: Moment,
    public meGusta?: boolean,
    public seguir?: boolean,
    public blog?: IBlog,
    public miembro?: IUser
  ) {
    this.meGusta = this.meGusta || false;
    this.seguir = this.seguir || false;
  }
}
