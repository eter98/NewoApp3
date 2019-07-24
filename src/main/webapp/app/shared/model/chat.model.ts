import { Moment } from 'moment';
import { IChatsListado } from 'app/shared/model/chats-listado.model';
import { IUser } from 'app/core/user/user.model';

export interface IChat {
  id?: number;
  mensaje?: string;
  fecha?: Moment;
  leido?: boolean;
  chatsListado?: IChatsListado;
  de?: IUser;
  para?: IUser;
}

export class Chat implements IChat {
  constructor(
    public id?: number,
    public mensaje?: string,
    public fecha?: Moment,
    public leido?: boolean,
    public chatsListado?: IChatsListado,
    public de?: IUser,
    public para?: IUser
  ) {
    this.leido = this.leido || false;
  }
}
