import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IChatListGrupo } from 'app/shared/model/chat-list-grupo.model';

@Component({
  selector: 'jhi-chat-list-grupo-detail',
  templateUrl: './chat-list-grupo-detail.component.html'
})
export class ChatListGrupoDetailComponent implements OnInit {
  chatListGrupo: IChatListGrupo;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ chatListGrupo }) => {
      this.chatListGrupo = chatListGrupo;
    });
  }

  previousState() {
    window.history.back();
  }
}
