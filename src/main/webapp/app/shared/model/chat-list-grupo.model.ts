import { IUser } from 'app/core/user/user.model';
import { IChatGrupo } from 'app/shared/model/chat-grupo.model';

export interface IChatListGrupo {
  id?: number;
  propietario?: IUser;
  destinatario?: IUser;
  chatGrupos?: IChatGrupo[];
}

export class ChatListGrupo implements IChatListGrupo {
  constructor(public id?: number, public propietario?: IUser, public destinatario?: IUser, public chatGrupos?: IChatGrupo[]) {}
}
