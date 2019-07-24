import { IUser } from 'app/core/user/user.model';
import { IChat } from 'app/shared/model/chat.model';

export interface IChatsListado {
  id?: number;
  propietario?: IUser;
  destinatario?: IUser;
  chats?: IChat[];
}

export class ChatsListado implements IChatsListado {
  constructor(public id?: number, public propietario?: IUser, public destinatario?: IUser, public chats?: IChat[]) {}
}
